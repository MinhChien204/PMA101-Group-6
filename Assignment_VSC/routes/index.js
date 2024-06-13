var express = require( 'express' );
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('login', {
    title: 'Login'
  });
});

router.get('/register', function (req, res, next) {
  res.render('register', { title: 'Register' });
});

router.get('/login', function (req, res, next) {
  res.render('login', { title: 'Login' });
});
router.get('/home', function (req, res, next) {
  res.render('home', { title: 'home' });
});
router.get('/user-management', function (req, res, next) {
  res.render('user-management', { title: 'Quản lí khách hàng' });
});
router.get('/product-management', (req, res) => {
  res.render('product-management',{title: 'Quản lí Sản Phẩm'}); // Ensure 'product-management' matches your view file name
});

router.get('/invoice-management', function (req, res, next) {
  res.render('invoice-management', { title: 'Quản lí Hóa Đơn' });
});
router.get('/thongke-management', function (req, res, next) {
  res.render('thongke-management', { title: 'Thống Kê' });
});
module.exports = router;
