
var fs = require('fs');
const mongoose = require('mongoose');
const { type } = require('os');

const ClothSchema = mongoose.Schema({
  image_cloth:{
    type:String,
  },
  name_cloth: {
    type: String,
  },
  price_cloth: {
    type: Number,
  },
  brand: {
    type: String,
  },
  chatlieu:{
    type: String,
  },
  mota:{
    type: String,
  },
  tinhtrang: {
    type: Boolean,
  }
});

const cloModel = mongoose.model('clothes', ClothSchema);

module.exports = cloModel;