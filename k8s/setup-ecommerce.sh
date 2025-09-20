#!/bin/bash
set -e

CLUSTER_NAME="ecommerce-cluster"
NAMESPACE="ecommerce"
KUBECONFIG_PATH="$PWD/kubeconfig.yaml"

echo "Installing K3d..."
curl -s https://raw.githubusercontent.com/k3d-io/k3d/main/install.sh | bash
k3d --version

echo "Creating K3d cluster..."
k3d cluster create $CLUSTER_NAME --wait

echo "Exporting kubeconfig..."
k3d kubeconfig get $CLUSTER_NAME > $KUBECONFIG_PATH
export KUBECONFIG=$KUBECONFIG_PATH

echo "Cluster info:"
kubectl cluster-info

echo "Creating namespace..."
kubectl create namespace $NAMESPACE || echo "Namespace already exists"

echo "Deploying MySQL..."
kubectl apply -f k8s/secret.yaml
kubectl apply -f k8s/mysql-pvc.yaml
kubectl apply -f k8s/mysql-deployment.yaml
kubectl apply -f k8s/mysql-service.yaml

echo "Waiting for MySQL pod to be ready..."
kubectl wait --for=condition=ready pod -l app=mysql -n $NAMESPACE --timeout=180s

echo "Deploying Backend..."
kubectl apply -f k8s/configmap.yaml
kubectl apply -f k8s/backend-deployment.yaml
kubectl apply -f k8s/backend-service.yaml

echo "Waiting for Backend pod to be ready..."
kubectl wait --for=condition=ready pod -l app=ecommerce-backend -n $NAMESPACE --timeout=180s

echo "Testing /products endpoint..."
BACKEND_POD=$(kubectl get pod -l app=ecommerce-backend -n $NAMESPACE -o jsonpath='{.items[0].metadata.name}')
RESPONSE=$(kubectl exec -n $NAMESPACE $BACKEND_POD -- curl -s http://localhost:8080/ECommerce/products)
echo "Response: $RESPONSE"

if [[ $RESPONSE == "[]" ]]; then
  echo "Error: /products returned empty list"
  exit 1
fi

echo "E-commerce setup completed successfully!"
