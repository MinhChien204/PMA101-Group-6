
document.addEventListener('DOMContentLoaded', function () {
    fetchProducts();

    document.getElementById('productForm').addEventListener('submit', function (e) {
        e.preventDefault();
        saveProduct();
    });
});

function fetchProducts() {
    fetch('/products')
        .then(response => response.json())
        .then(products => {
            const productTableBody = document.getElementById('productTableBody');
            productTableBody.innerHTML = '';

            products.forEach(product => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.description}</td>
                    <td><img src="${product.imageUrl}" alt="Product Image" width="50"></td>
                    <td>${product.category}</td>
                    <td>${product.stock}</td>
                    <td>
                        <button class="btn btn-warning btn-sm" onclick="editProduct('${product._id}')">Edit</button>
                        <button class="btn btn-danger btn-sm" onclick="deleteProduct('${product._id}')">Delete</button>
                    </td>
                `;
                productTableBody.appendChild(row);
            });
        });
}

function saveProduct() {
    const productId = document.getElementById('productId').value;
    const method = productId ? 'PUT' : 'POST';
    const url = productId ? `/products/${productId}` : '/products';
    const formData = new FormData(document.getElementById('productForm'));

    fetch(url, {
        method: method,
        body: formData
    })
        .then(response => response.json())
        .then(product => {
            $('#productModal').modal('hide');
            fetchProducts();
            document.getElementById('productForm').reset();
        });
}

function editProduct(id) {
    fetch(`/products/${id}`)
        .then(response => response.json())
        .then(product => {
            document.getElementById('productId').value = product._id;
            document.getElementById('name').value = product.name;
            document.getElementById('price').value = product.price;
            document.getElementById('description').value = product.description;
            document.getElementById('image').value = product.imageUrl;
            document.getElementById('category').value = product.category;
            document.getElementById('stock').value = product.stock;

            $('#productModal').modal('show');
        });
}

function deleteProduct(id) {
    fetch(`/products/${id}`, {
        method: 'DELETE'
    })
        .then(() => {
            fetchProducts();
        });
}

function searchProducts() {
    const searchInput = document.getElementById('searchInput').value.toLowerCase();
    const productTableBody = document.getElementById('productTableBody');
    const rows = productTableBody.getElementsByTagName('tr');

    Array.from(rows).forEach(row => {
        const name = row.cells[0].textContent.toLowerCase();
        if (name.includes(searchInput)) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
}
