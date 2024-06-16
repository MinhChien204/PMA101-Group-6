let products = [];

// Fetch products from the server
async function fetchProducts() {
    try {
        const response = await fetch('/api/product');
        products = await response.json();
        renderProductTable(products);
    } catch (error) {
        console.error('Error fetching products:', error);
    }
}
 
// Render products in the table
function renderProductTable(products) {
    const productTableBody = document.getElementById('productTableBody');
    productTableBody.innerHTML = '';
    products.forEach(product => {
        const productRow = document.createElement('tr');
        productRow.innerHTML = `
            <td>${product.name_cloth}</td>
            <td>${product.price_cloth}</td>
            <td>${product.mota}</td>
            <td>${product.brand}</td>
            <td><img src="${product.image_cloth}" alt="Product Image" class="product-image" width="50"></td>
            <td>
                <button class="btn btn-warning btn-sm" onclick="editProduct('${product._id}')">Edit</button>
                <button class="btn btn-danger btn-sm" onclick="deleteProduct('${product._id}')">Delete</button>
            </td>
        `;
        productTableBody.appendChild(productRow);
    });
}

// Add or update a product
document.getElementById('productForm').addEventListener('submit', async function (event) {
    event.preventDefault();

    const productId = document.getElementById('productId').value;
    const product = {
        name_cloth: document.getElementById('productName').value,
        price_cloth: document.getElementById('productPrice').value,
        mota: document.getElementById('productDescription').value,
        brand: document.getElementById('productBrand').value,
        image_cloth: document.getElementById('productImage').value
    };

    try {
        const response = productId ? await fetch(`/api/update/${productId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(product)
        }) : await fetch('/api/add_cloth', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(product)
        });

        const result = await response.json();
        $('#productModal').modal('hide');
        fetchProducts();
    } catch (error) {
        console.error('Error adding/updating product:', error);
    }
});

// Edit product
function editProduct(productId) {
    const product = products.find(product => product._id === productId);
    document.getElementById('productId').value = product._id;
    document.getElementById('productName').value = product.name_cloth;
    document.getElementById('productPrice').value = product.price_cloth;
    document.getElementById('productDescription').value = product.mota;
    document.getElementById('productBrand').value = product.brand;
    document.getElementById('productImage').value = product.image_cloth;
    $('#productModal').modal('show');
}

// Delete product
async function deleteProduct(productId) {
    if (confirm('Are you sure you want to delete this product?')) {
        try {
            await fetch(`/api/delete/${productId}`, {
                method: 'DELETE'
            });
            fetchProducts();
        } catch (error) {
            console.error('Error deleting product:', error);
        }
    }
}

// Search products
function searchProducts() {
    const searchInput = document.getElementById('searchInput').value.toLowerCase();
    const filteredProducts = products.filter(product => 
        product.name_cloth.toLowerCase().includes(searchInput) || 
        product.brand.toLowerCase().includes(searchInput)
    );
    renderProductTable(filteredProducts);
}

// Initialize
fetchProducts();
