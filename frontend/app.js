let cart = [];

function renderProducts(products) {
  const productList = document.getElementById("product-list");
  productList.innerHTML = "";

  products.forEach(product => {
    const card = document.createElement("div");
    card.className = "card";

    card.innerHTML = `
      <h3>${product.name}</h3>
      <p>Price: $${product.price}</p>
      <button onclick="addToCart(${product.id}, '${product.name}', ${product.price})">Add to Cart</button>
    `;

    productList.appendChild(card);
  });
}

function addToCart(id, name, price) {
  const existing = cart.find(item => item.id === id);
  if (existing) {
    existing.quantity = (existing.quantity || 1) + 1;
  } else {
    cart.push({ id, name, price, quantity: 1 });
  }
  updateCart();
}

function updateCart() {
  const cartItems = document.getElementById("cart-items");
  const cartTotal = document.getElementById("cart-total");

  cartItems.innerHTML = "";
  let total = 0;

  cart.forEach((item, index) => {
    const li = document.createElement("li");

    
    const nameSpan = document.createElement("span");
    nameSpan.className = "item-name";
    nameSpan.textContent = `${item.name} - $${item.price}`;


    const qtyInput = document.createElement("input");
    qtyInput.type = "number";
    qtyInput.min = 1;
    qtyInput.value = item.quantity || 1;
    qtyInput.addEventListener("change", (e) => {
      item.quantity = parseInt(e.target.value);
      updateCart();
    });


    const removeBtn = document.createElement("button");
    removeBtn.textContent = "Remove";
    removeBtn.addEventListener("click", () => {
      cart.splice(index, 1);
      updateCart();
    });

    li.appendChild(nameSpan);
    li.appendChild(qtyInput);
    li.appendChild(removeBtn);

    cartItems.appendChild(li);

    const qty = item.quantity || 1;
    total += item.price * qty;
  });

  cartTotal.textContent = total.toFixed(2);
}


document.addEventListener("DOMContentLoaded", () => {
  fetch("/ECommerce/products")
    .then(response => response.json())
    .then(data => {
      console.log("Products received from backend:", data);
      renderProducts(data);
    })
    .catch(error => console.error("Error fetching products:", error));
});

