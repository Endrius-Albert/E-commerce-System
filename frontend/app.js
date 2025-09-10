
let cart = [];


window.addToCart = function(id, name, price) {
  const existing = cart.find(item => item.id === id);
  if (existing) {
    existing.quantity = (existing.quantity || 1) + 1;
  } else {
    cart.push({ id, name, price, quantity: 1 });
  }
  updateCart();
};

function updateCart() {
  const cartItems = document.getElementById("cart-items");
  const cartTotal = document.getElementById("cart-total");

  cartItems.innerHTML = "";
  let total = 0;

  cart.forEach((item, index) => {
    const li = document.createElement("li");

   
    const span = document.createElement("span");
    span.textContent = `${item.name} - $${item.price}`;
    li.appendChild(span);

    const qtyInput = document.createElement("input");
    qtyInput.type = "number";
    qtyInput.min = 1;
    qtyInput.value = item.quantity || 1;
    qtyInput.addEventListener("change", (e) => {
      const value = parseInt(e.target.value);
      if (value > 0) {
        item.quantity = value;
        updateCart();
      }
    });
    li.appendChild(qtyInput);

    const removeBtn = document.createElement("button");
    removeBtn.textContent = "Remove";
    removeBtn.addEventListener("click", () => {
      cart.splice(index, 1);
      updateCart();
    });
    li.appendChild(removeBtn);

    cartItems.appendChild(li);

    total += item.price * (item.quantity || 1);
  });

  cartTotal.textContent = total.toFixed(2);
}

function renderProducts(products) {
  const productList = document.getElementById("product-list");
  productList.innerHTML = "";

  products.forEach(product => {
    const card = document.createElement("div");
    card.className = "card";

    
    const nameEl = document.createElement("h3");
    nameEl.textContent = product.name;
    const priceEl = document.createElement("p");
    priceEl.textContent = `Price: $${product.price}`;


    const btn = document.createElement("button");
    btn.textContent = "Add to Cart";
    btn.addEventListener("click", () => addToCart(product.id, product.name, product.price));

 
    card.appendChild(nameEl);
    card.appendChild(priceEl);
    card.appendChild(btn);

    productList.appendChild(card);
  });
}

document.addEventListener("DOMContentLoaded", () => {
  
  const url = `${window.location.origin}/ECommerce/products`;

  fetch(url)
    .then(response => response.json())
    .then(data => {
      console.log("Products received from backend:", data);
      renderProducts(data);
    })
    .catch(error => console.error("Error fetching products:", error));
});
