// simulating backend for now
const products = [
    { id: 1, name: "Laptop", price: 1200.0 },
    { id: 2, name: "Mouse", price: 25.0 },
    { id: 3, name: "Keyboard", price: 45.0 },
    { id: 4, name: "Monitor", price: 250.0 }
];

const users = [
    { id: 1, name: "Alice", email: "alice@email.com" },
    { id: 2, name: "Bob", email: "bob@email.com" },
    { id: 3, name: "Charlie", email: "charlie@email.com" }
];

// Render Products
const productList = document.getElementById("product-list");
products.forEach(p => {
    const card = document.createElement("div");
    card.className = "card";
    card.innerHTML = `<h3>${p.name}</h3><p>Price: $${p.price}</p>`;
    productList.appendChild(card);
});

// Render Users
const userList = document.getElementById("user-list");
users.forEach(u => {
    const card = document.createElement("div");
    card.className = "card";
    card.innerHTML = `<h3>${u.name}</h3><p>Email: ${u.email}</p>`;
    userList.appendChild(card);
});
