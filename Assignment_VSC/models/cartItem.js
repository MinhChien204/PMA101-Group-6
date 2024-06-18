var fs = require('fs');
const mongoose = require('mongoose');
const { type } = require('os');

const CartItemSchema = mongoose.Schema({
    productid_item:{
        type:String
    },
    productsize_item:{
        type:String
    },
    productquantity_item:{
        type:Number
    },
    productImage_item:{
        type:String
    },
    productName_item:{
        type:String
    },
    productPrice_item:{
        type:Number
    }
})

const cartItemModel = mongoose.model('cartitems',CartItemSchema)

module.exports = cartItemModel