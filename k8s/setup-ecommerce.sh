#!/bin/sh
set -e

CLUSTER_NAME="ecommerce-cluster"
NAMESPACE="ecommerce"

echo "Criando namespace..."
kubectl create namespace $NAMESPACE || echo "Namespace already exists"

echo "Deploying MySQL..."
kubectl apply -f /k8s/secret.yaml
kubectl apply -f /k8s/mysql-pvc.yaml
kubectl apply -f /k8s/mysql-deployment.yaml
kubectl apply -f /k8s/mysql-service.yaml

echo "Waiting for MySQL pod to be ready..."
while true; do
    MYSQL_POD=$(kubectl get pod -l app=mysql -n $NAMESPACE -o jsonpath='{.items[0].metadata.name}')
    READY=$(kubectl get pod $MYSQL_POD -n $NAMESPACE -o jsonpath='{.status.containerStatuses[0].ready}')
    [ "$READY" = "true" ] && break
    echo "Waiting for MySQL pod..."
    sleep 5
done
echo "MySQL pod is ready: $MYSQL_POD"

echo "Waiting for database initialization..."
while ! kubectl exec -n $NAMESPACE $MYSQL_POD -- mysql -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" -e "USE $DB_NAME;" 2>/dev/null; do
    echo "Database not ready yet..."
    sleep 5
done
echo "Database $DB_NAME is ready!"

echo "Deploying Backend..."
kubectl apply -f /k8s/configmap.yaml
kubectl apply -f /k8s/backend-deployment.yaml
kubectl apply -f /k8s/backend-service.yaml

echo "Waiting for Backend pod to be ready..."
while true; do
    BACKEND_POD=$(kubectl get pod -l app=ecommerce-backend -n $NAMESPACE -o jsonpath='{.items[0].metadata.name}')
    READY=$(kubectl get pod $BACKEND_POD -n $NAMESPACE -o jsonpath='{.status.containerStatuses[0].ready}')
    [ "$READY" = "true" ] && break
    echo "Waiting for Backend pod..."
    sleep 5
done
echo "Backend pod is ready: $BACKEND_POD"

echo "Testing /products endpoint..."
RESPONSE=$(kubectl exec -n $NAMESPACE $BACKEND_POD -- sh -c "wget -qO- http://localhost:8080/ECommerce/products")
echo "Response: $RESPONSE"

[ "$RESPONSE" = "[]" ] && { echo "Error: /products returned empty list"; exit 1; }

echo "E-commerce setup completed successfully!"
