const express = require('express');
const router = express.Router();
const multer = require('multer');
const path = require('path');
const mongoose = require('mongoose');
const upload = require("../config/Upload");

const users = require('../models/user');
const cloModel = require('../models/clothesModel');
const typeModel = require('../models/type');
const addressModel = require('../models/address');
// Cấu hình multer để tải lên tệp


// Các tuyến API

// Lấy danh sách thể loại
router.get('/type', async (req, res) => {
  try {
    let type = await typeModel.find();
    res.send(type);
  } catch (error) {
    console.log("lỗi");
  }
});

// Lấy danh sách địa chỉ
router.get('/address', async (req, res) => {
  try {
    let address = await addressModel.find();
    res.send(address);
  } catch (error) {
    console.log("lỗi");
  }
});

// Thêm địa chỉ
router.post('/add_address', async (req, res) => {
  try {
    const { nameAddress, phoneAddress, locationAddress } = req.body;

    if (!nameAddress || !phoneAddress || !locationAddress) {
      return res.status(400).send({ error: "All fields are required" });
    }

    let newAddress = new addressModel({
      nameAddress,
      phoneAddress,
      locationAddress
    });

    await newAddress.save();
    res.status(201).send(newAddress);
  } catch (error) {
    console.log("Error: ", error);
    res.status(500).send({ error: "An error occurred while adding the address" });
  }
});

// Xóa địa chỉ
router.delete('/del_address/:id', async (req, res) => {
  try {
    const { id } = req.params;
    let deletedAddress = await addressModel.findByIdAndDelete(id);

    if (!deletedAddress) {
      return res.status(404).send({ error: "Address not found" });
    }

    res.status(200).send({ message: "Address deleted successfully" });
  } catch (error) {
    console.log("Error: ", error);
    res.status(500).send({ error: "An error occurred while deleting the address" });
  }
});

// Lấy danh sách sản phẩm
router.get('/product', async (req, res) => {
  try {
    let cloth = await cloModel.find();
    res.send(cloth);
  } catch (error) {
    console.log("lỗi");
  }
});

// Lấy thông tin chi tiết của sản phẩm
router.get('/product/:id', async (req, res) => {
  try {
    const id = req.params.id;
    if (!mongoose.Types.ObjectId.isValid(id)) {
      return res.status(400).json({ message: 'Invalid ObjectId' });
    }
    const cloth = await cloModel.findById(id);
    if (!cloth) {
      return res.status(404).json({ message: 'Product not found' });
    }
    res.send(cloth);
  } catch (error) {
    console.error('Error:', error);
    res.status(500).json({ message: 'Internal server error' });
  }
});

// Thêm sản phẩm
router.post('/add_cloth', upload.single('image_cloth'), async (req, res) => {
  try {
    const data = req.body;
    const { file } = req;
    const urlsImage = `${req.protocol}://${req.get("host")}/uploads/${file.filename}`;

    const newClothes = new cloModel({
      image_cloth: urlsImage,
      brand: data.brand,
      name_cloth: data.name_cloth,
      price_cloth: data.price_cloth,
      mota: data.mota,
      tinhtrang: data.tinhtrang,
    });

    await newClothes.save();
    res.send(newClothes);
  } catch (error) {
    console.error(error);
    res.status(500).send("Internal Server Error");
  }
});

// Cập nhật sản phẩm
router.put('/update/:id', upload.single('image_cloth'), async (req, res) => {
  try {
    const data = req.body;
    const studentId = req.params.id;
    const { file } = req;

    const imageUrl = `${req.protocol}://${req.get("host")}/uploads/${file.filename}`;

    const updatedCloth = await cloModel.findByIdAndUpdate(studentId, {
      image_cloth: imageUrl,
      name_cloth: data.name_cloth,
      price_cloth: data.price_cloth,
      brand: data.brand,
      mota: data.mota,
      tinhtrang: data.tinhtrang,
    });

    if (updatedCloth) {
      res.json({
        status: 200,
        message: "Cập nhật thành công",
        data: updatedCloth,
      });
    } else {
      res.json({
        status: 400,
        message: "Không tìm thấy quần áo",
        data: [],
      });
    }
  } catch (error) {
    console.error(error);
    res.status(500).send("Internal Server Error");
  }
});

// Xóa sản phẩm
router.delete('/delete/:id', async (req, res) => {
  try {
    const id = req.params.id;
    await cloModel.deleteOne({ _id: id });
    const cloth = await cloModel.find();
    console.log(cloth);
    res.send(cloth);
  } catch (error) {
    console.error(error);
    res.status(500).send("Internal Server Error");
  }
});

// Đăng ký tài khoản
router.post("/register-send-email", upload.single("avartar"), async (req, res) => {
  try {
    const data = req.body;
    const { file } = req;
    const avatar = `${req.protocol}://${req.get("host")}/uploads/${
      file.filename
    }`; 
    const newUser = users({
      username: data.username,
      password: data.password,
      email: data.email,
      name: data.name,
      phonenumber:data.phonenumber,
      address:data.address,
      avartar: avatar,
    });
    const result = await newUser.save();
    if (result) {
      res.json({
        status: 200,
        messenger: "Thêm thành công",
        data: result,
      });
    } else {
      res.json({
        status: 400,
        messenger: "Lỗi, thêm không thành công",
        data: [],
      });
    }
  } catch (error) {
    console.log(error);
  }
});

// Đăng nhập
router.post("/login", async (req, res) => {
  try {
    const { username, password } = req.body;
    const user = await users.findOne({ username, password });
    if (user) {
      res.json({
        status: 200,
        messenger: "Đăng nhâp thành công",
        data: user,
      });
    } else {
      res.json({
        status: 400,
        messenger: "Lỗi, đăng nhập không thành công",
        data: [],
      });
    }
  } catch (error) {
    console.log(error);
  }
});

// Cập nhật sản phẩm không có ảnh
router.put('/update-no-image/:id', async (req, res) => {
  try {
    const svId = req.params.id;
    const data = req.body;
    const result = await cloModel.findByIdAndUpdate(svId, {
      name_cloth: data.name_cloth,
      price_cloth: data.price_cloth,
      brand: data.brand,
      quantity_cloth: data.quantity_cloth,
      mota: data.mota,
      tinhtrang: data.tinhtrang,
    });

    if (result) {
      res.json({
        status: 200,
        messenger: "Cập nhật thành công",
        data: result,
      });
    } else {
      res.status(404).json({
        status: 404,
        messenger: "Không tìm thấy quần áo",
        data: [],
      });
    }
  } catch (error) {
    console.log(error);
    res.status(500).json({
      status: 500,
      messenger: "Lỗi, không thể cập nhật quần áo",
      data: [],
    });
  }
});

// Tìm kiếm sản phẩm theo tên
router.get('/search', async (req, res) => {
  try {
    const tuKhoa = req.query.key;
    const ketQuaTimKiem = await cloModel.find({
      name_cloth: { $regex: new RegExp(tuKhoa, "i") },
    });

    if (ketQuaTimKiem.length > 0) {
      res.json(ketQuaTimKiem);
    } else {
      res.json([]);
    }
  } catch (error) {
    res.status(500).send("Lỗi máy chủ nội bộ");
  }
});

module.exports = router;
