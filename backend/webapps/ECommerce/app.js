let cart = [];

// --- Cart functions ---
window.addToCart = function(id, name, price) {
  const existing = cart.find(item => item.id === id);
  if (existing) existing.quantity = (existing.quantity || 1) + 1;
  else cart.push({ id, name, price, quantity: 1 });
  updateCart();
};

function updateCart() {
  const cartItems = document.getElementById("cart-items");
  const cartTotal = document.getElementById("cart-total");
  cartItems.innerHTML = "";
  let total = 0;
  cart.forEach((item, index) => {
    const li = document.createElement("li");
    li.className = "cart-item";

    li.innerHTML = `
      <span class="item-info">${item.name} - $${item.price.toFixed(2)}</span>
      <span class="item-controls">
        <input type="number" min="1" value="${item.quantity}" onchange="updateQuantity(${index}, this.value)">
        <button onclick="removeItem(${index})">Remove</button>
      </span>
    `;
    cartItems.appendChild(li);
    total += item.price * item.quantity;
  });
  cartTotal.textContent = total.toFixed(2);
}

function updateQuantity(index, value) {
  value = parseInt(value);
  if (value > 0) cart[index].quantity = value;
  updateCart();
}

function removeItem(index) {
  cart.splice(index, 1);
  updateCart();
}

// --- Render functions ---
function renderProducts(products) {
  const productList = document.getElementById("product-list");
  productList.innerHTML = "";
  products.forEach(product => {
    const card = document.createElement("div");
    card.className = "card";
    const safeName = product.name.replace(/'/g, "\\'");
    card.innerHTML = `
      <h3>${safeName}</h3>
      <p>Price: $${product.price.toFixed(2)}</p>
      <button onclick="addToCart(${product.id}, '${safeName}', ${product.price})">Add to Cart</button>
    `;
    productList.appendChild(card);
  });
}

function renderUsers(users) {
  const usersSection = document.getElementById("users-section");
  usersSection.innerHTML = `
    <h2>Users</h2>
    <table class="table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Email</th>
        </tr>
      </thead>
      <tbody>
        ${users.map(user => `
          <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
          </tr>
        `).join('')}
      </tbody>
    </table>
  `;
}

function renderOrders(orders) {
  const ordersSection = document.getElementById("orders-section");
  ordersSection.innerHTML = `
    <h2>Orders</h2>
    <table class="table">
      <thead>
        <tr>
          <th>Order ID</th>
          <th>User</th>
          <th>Items</th>
          <th>Total</th>
        </tr>
      </thead>
      <tbody>
        ${orders.map(order => `
          <tr>
            <td>${order.id}</td>
            <td>${order.user.name}</td>
            <td>${order.items.map(i => i.product.name + ' x' + i.quantity).join(', ')}</td>
            <td>$${order.total.toFixed(2)}</td>
          </tr>
        `).join('')}
      </tbody>
    </table>
  `;
}

// --- Users CRUD ---
function loadUsers() {
  fetch("/ECommerce/users")
    .then(res => res.json())
    .then(data => renderUsers(data));
}

// --- Orders ---
function loadOrders() {
  fetch("/ECommerce/orders")
    .then(res => res.json())
    .then(data => renderOrders(data));
}

// --- Products ---
function loadProducts() {
  fetch("/ECommerce/products")
    .then(res => res.json())
    .then(data => renderProducts(data));
}

// --- Navigation / Sections ---
function showSection(sectionId) {
  document.querySelectorAll('.section').forEach(s => s.style.display = 'none');
  document.getElementById(sectionId).style.display = 'block';
}

// --- Menu buttons ---
document.getElementById('menu-products').addEventListener('click', () => {
  loadProducts();
  showSection('products-section');
});

document.getElementById('menu-users').addEventListener('click', () => {
  loadUsers();
  showSection('users-section');
});

document.getElementById('menu-orders').addEventListener('click', () => {
  loadOrders();
  showSection('orders-section');
});

// --- Initial load ---
document.addEventListener("DOMContentLoaded", () => {
  loadProducts();
  showSection('products-section');
});
