let users = [];

// Fetch users from the server
async function fetchUsers() {
    try {
        const response = await fetch('/api/users');
        users = await response.json();
        renderUserTable(users);
    } catch (error) {
        console.error('Error fetching users:', error);
    }
}

// Render users in the table
function renderUserTable(users) {
    const userTableBody = document.getElementById('userTableBody');
    userTableBody.innerHTML = '';
    users.forEach(user => {
        const userRow = document.createElement('tr');
        userRow.innerHTML = `
            <td>${user.email}</td>
            <td>${user.name}</td>
            <td>${user.address}</td>
            <td>${user.phonenumber}</td>
            <td>${user.role || 'User'}</td>
            <td><img src="${user.avartar}" alt="Avatar" class="avatar" width="50"></td>
            <td>
                <button class="btn btn-warning btn-sm" onclick="editUser('${user._id}')">Edit</button>
                <button class="btn btn-danger btn-sm" onclick="deleteUser('${user._id}')">Delete</button>
            </td>
        `;
        userTableBody.appendChild(userRow);
    });
}

// Add or update a user
document.getElementById('userForm').addEventListener('submit', async function (event) {
    event.preventDefault();

    const userId = document.getElementById('userId').value;
    const user = {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
        email: document.getElementById('email').value,
        name: document.getElementById('name').value,
        phonenumber: document.getElementById('phonenumber').value,
        address: document.getElementById('address').value,
        avartar: document.getElementById('avartar').value
    };

    try {
        const response = userId ? await fetch(`/api/users/${userId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        }) : await fetch('/api/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });

        const result = await response.json();
        $('#userModal').modal('hide');
        fetchUsers();
    } catch (error) {
        console.error('Error adding/updating user:', error);
    }
});

// Edit user
function editUser(userId) {
    const user = users.find(user => user._id === userId);
    document.getElementById('userId').value = user._id;
    document.getElementById('username').value = user.username;
    document.getElementById('password').value = user.password;
    document.getElementById('email').value = user.email;
    document.getElementById('name').value = user.name;
    document.getElementById('phonenumber').value = user.phonenumber;
    document.getElementById('address').value = user.address;
    document.getElementById('avartar').value = user.avartar;
    $('#userModal').modal('show');
}

// Delete user
async function deleteUser(userId) {
    if (confirm('Are you sure you want to delete this user?')) {
        try {
            await fetch(`/api/users/${userId}`, {
                method: 'DELETE'
            });
            fetchUsers();
        } catch (error) {
            console.error('Error deleting user:', error);
        }
    }
}

// Search users
function searchUsers() {
    const searchInput = document.getElementById('searchInput').value.toLowerCase();
    const filteredUsers = users.filter(user => 
        user.name.toLowerCase().includes(searchInput) || 
        user.email.toLowerCase().includes(searchInput)
    );
    renderUserTable(filteredUsers);
}

// Initialize
fetchUsers();
