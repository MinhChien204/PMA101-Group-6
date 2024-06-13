const registerForm = document.getElementById('registerForm');
const username = document.getElementById('username');
const password = document.getElementById('password');
const nameuser = document.getElementById('name');
const email = document.getElementById('email');
const avartar = document.getElementById('image');
const phonenumber = document.getElementById('phonenumber');
const address = document.getElementById('address');

const addAccount = async () => {
    try {
        const formData = new FormData();
        formData.append('username', username.value);
        formData.append('password', password.value);
        formData.append('name', nameuser.value);
        formData.append('email', email.value);
        formData.append('phonenumber', phonenumber.value);
        formData.append('address', address.value);
        formData.append('avartar', avartar.files[0]);

        const response = await fetch('http://localhost:3000/register-send-email', {
            method: 'POST',
            body: formData
        });

        const result = await response.json();

        if (result.status === 200) {
            alert('Tạo tài khoản thành công');
            window.location.href = '/login';
        } else {
            alert('Tạo tài khoản không thành công');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Đã xảy ra lỗi');
    }
};

registerForm.addEventListener('submit', event => {
    event.preventDefault();
    addAccount();
});
