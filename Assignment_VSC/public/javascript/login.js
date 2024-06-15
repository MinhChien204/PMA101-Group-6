const loginForm = document.getElementById('loginForm');
const userten = document.getElementById('username');
const pw = document.getElementById('password');

const loginUser = async () => {
    try {
        var formData = new FormData();
        formData.append("username", userten.value);
        formData.append("password", pw.value);

        const response = await fetch('http://localhost:3000/api/login', {
            method: 'POST',
            body: formData
        });

        const result = await response.json();

        if (response.status === 200) {
            alert('Login thành công');
            window.location.href = '/home';
        } else {
            alert('Không thành công: ' + result.message);
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Đã xảy ra lỗi');
    }
};

loginForm.addEventListener('submit', event => {
    event.preventDefault();
    loginUser();
});
