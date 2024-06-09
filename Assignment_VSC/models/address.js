const mongoose = require('mongoose');
const Scheme = mongoose.Schema;

const Address = new Scheme({
    nameAddress:{
        type: String,
    },
    phoneAddress:{
        type:String,
    },
    locationAddress:{
        type: String,
    }
},{
    timestamps: true
})

const addressModel = mongoose.model('diachis',Address)

module.exports = addressModel