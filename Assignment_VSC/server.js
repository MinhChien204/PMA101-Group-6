const express = require("express");
const path = require("path");
const mongoose = require("mongoose");
const app = express();
const upload = require("./config/Upload");  
const uri =
  "mongodb+srv://nmchien2204:chien1234@cluster0.4b0kubq.mongodb.net/pma101";
const users = require("./models/user");

const cloModel = require("./clothesModel");
const apiRouter = require("./api");
const { type } = require("os");
const typeModel = require("./models/type");
const addressModel = require("./models/address");

app.use(express.json());
app.use("/uploads", express.static(path.join(__dirname, "public/uploads"))); // Serve uploaded files
app.use("/api", apiRouter);

const port = 3000;
app.listen(port, () => {
  console.log(`Example app listening on port ${port}`);
});

// Connect to MongoDB
mongoose
  .connect(uri, { useNewUrlParser: true, useUnifiedTopology: true })
  .then(() => console.log("Connect success"))
  .catch((err) => console.log("Connect failed:", err));


  //Thể Loại
  app.get("/type", async (req, res) => {
	try {
	  let type = await typeModel.find();
	  res.send(type);
	} catch (error) {
	  console.log("lỗi ");
	}
	});

  //Địa Chỉ
  app.get("/address", async (req, res) => {
	try {
	  let address = await addressModel.find();
	  res.send(address);
	} catch (error) {
	  console.log("lỗi ");
	}
	});

    app.post("/add_address", async (req, res) => {
        try {
            const { nameAddress, phoneAddress, locationAddress } = req.body;
     
            // Validate the request body
            if (!nameAddress || !phoneAddress || !locationAddress) {
                return res.status(400).send({ error: "All fields are required" });
            }
    
            // Create a new address document
            let newAddress = new addressModel({
                nameAddress,
                phoneAddress,
                locationAddress
            });
    
            // Save the address to the database
            await newAddress.save();
    
            // Send a success response
            res.status(201).send(newAddress);
        } catch (error) {
            console.log("Error: ", error);
            res.status(500).send({ error: "An error occurred while adding the address" });
        }
    });    

    app.delete("/del_address/:id", async (req, res) => {
        try {
            const { id } = req.params;
    
            // Find the address by ID and delete it
            let deletedAddress = await addressModel.findByIdAndDelete(id);
    
            if (!deletedAddress) {
                return res.status(404).send({ error: "Address not found" });
            }
    
            // Send a success response
            res.status(200).send({ message: "Address deleted successfully" });
        } catch (error) {
            console.log("Error: ", error);
            res.status(500).send({ error: "An error occurred while deleting the address" });
        }
    });
//Sản Phẩm
app.get("/product", async (req, res) => {
try {
  let cloth = await cloModel.find();
  res.send(cloth); 
} catch (error) {
  console.log("lỗi ");
}
});
app.get("/product/:id", async (req, res) => {
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

app.post("/add_cloth", upload.single("image_cloth"), async (req, res) => {
  try { 
    const data = req.body;
    const { file } = req;
    const urlsImage =  `${req.protocol}://${req.get("host")}/uploads/${file.filename}`
  
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

app.put("/update/:id", upload.single("image_cloth"), async (req, res) => {
  try {
    const data = req.body;
    const studentId = req.params.id;
    const { file } = req;

    const imageUrl = `${req.protocol}://${req.get("host")}/uploads/${
      file.filename
    }`;

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

app.delete("/delete/:id", async (req, res) => {
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

app.post("/register-send-email", upload.single("avartar"), async (req, res) => {
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

app.post("/login", async (req, res) => {
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
app.put("/update-no-image/:id", upload.array("image_cloth", 5), async (req, res) => {
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

app.get("/search", async (req, res) => {
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

// const sapxepgiamdan = (products) => {
//   return products.sort((a, b) => b.tuoi - a.tuoi);
// };

// app.get("/giam-dan", async (req, res) => {
//   try {
//     let svien = await cloModel.find();
//     let sortedProducts = sapxepgiamdan(svien);
//     res.json(sortedProducts);
//   } catch (error) {
//     console.error(
//       "Lỗi khi sắp xếp danh sách sản phẩm theo giá tăng dần:",
//       error
//     );
//     res.status(500).send("Đã xảy ra lỗi khi sắp xếp danh sách sản phẩm");
//   }
// });

// const sapxeptangdan = (products) => {
//   return products.sort((a, b) => a.tuoi - b.tuoi);
// };

// app.get("/tang-dan", async (req, res) => {
//   try {
//     let svien = await cloModel.find();
//     let sortedProducts = sapxeptangdan(svien);
//     res.json(sortedProducts);
//   } catch (error) {
//     console.error(
//       "Lỗi khi sắp xếp danh sách sản phẩm theo giá giảm dần:",
//       error
//     );
//     res.status(500).send("Đã xảy ra lỗi khi sắp xếp danh sách sản phẩm");
//   }
// });

module.exports = app;
