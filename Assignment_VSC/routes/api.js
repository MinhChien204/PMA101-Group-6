var express = require('express');
var routes = express.Router();
const multer = require('multer');
const path = require('path');
const fs = require('fs');

// Thiết lập multer để lưu trữ tệp vào thư mục uploads
const storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, './uploads');
  },
  filename: function (req, file, cb) {
    cb(null, file.fieldname + '-' + Date.now() + path.extname(file.originalname));
  }
});
const upload = multer({ storage: storage });

// Thêm model
const Distributors = require('../models/distributors');
const Fruits = require('../models/fruit');

// API thêm distributor
routes.post('/add-distributor', async (req, res) => {
  try {
    const data = req.body; // Lấy dữ liệu từ body
    const newDistributor = new Distributors({
      name: data.name
    });
    const result = await newDistributor.save();
    if (result) {
      // Nếu thêm thành công result != null trả về dữ liệu
      res.json({
        "status": 200,
        "message": "Thêm thành công",
        "data": result
      });
    } else {
      // Nếu thêm không thành công result == null, thông báo không thành công
      res.json({
        "status": 400,
        "message": "Thêm không thành công",
        "data": []
      });
    }
  } catch (error) {
    console.log(error);
    res.status(500).json({
      "status": 500,
      "message": "Lỗi server",
      "data": []
    });
  }
});

// API thêm fruit
routes.post('/add-fruit', upload.single('image'), async (req, res) => {
  try {
    const data = req.body;
    const newFruit = new Fruits({
      name: data.name,
      quantity: data.quantity,
      price: data.price,
      status: data.status,
      image: req.file ? req.file.filename : null,
      description: data.description,
      id_distributor: data.id_distributor,
    });
    const result = await newFruit.save();
    if (result) {
      // Nếu thêm thành công result != null trả về dữ liệu
      res.json({
        "status": 200,
        "message": "Thêm thành công",
        "data": result
      });
    } else {
      // Nếu thêm không thành công result == null, thông báo không thành công
      res.json({
        "status": 400,
        "message": "Lỗi, Thêm không thành công",
        "data": []
      });
    }
  } catch (error) {
    console.log(error);
    res.status(500).json({
      "status": 500,
      "message": "Lỗi server",
      "data": []
    });
  }
});

// Truy vấn danh sách
routes.get('/get-list-fruit', async (req, res) => {
  try {
    const data = await Fruits.find().populate('id_distributor');
    res.json({
      "status": 200,
      "message": "Danh sách fruit",
      "data": data
    });
  } catch (error) {
    console.log(error);
    res.status(500).json({
      "status": 500,
      "message": "Lỗi server",
      "data": []
    });
  }
});

routes.get('/get-fruit-by-id/:id', async (req, res) => {
  try {
    const { id } = req.params;
    const data = await Fruits.findById(id).populate('id_distributor');
    res.json({
      "status": 200,
      "message": "Thông tin chi tiết fruit",
      "data": data
    });
  } catch (error) {
    console.log(error);
    res.status(500).json({
      "status": 500,
      "message": "Lỗi server",
      "data": []
    });
  }
});

// API tải lên tệp
routes.post('/upload', upload.single('image_cloth'), (req, res) => {
  try {
    res.json({
      "status": 200,
      "message": "Tải lên thành công",
      "data": req.file
    });
  } catch (error) {
    console.log(error);
    res.status(500).json({
      "status": 500,
      "message": "Lỗi server",
      "data": []
    });
  }
});

module.exports = routes;
