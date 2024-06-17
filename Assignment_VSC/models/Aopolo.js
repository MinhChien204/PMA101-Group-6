var fs = require( 'fs' );
const mongoose = require( 'mongoose' );
const { type } = require( 'os' );


const AopoloSchema = mongoose.Schema( {
    image_polo: {
        type: String,
    },
    brand_polo: {
        type: String,
    },
    name_polo: {
        type: String,
    },
    price_polo: {
        type: Number,
    },
    mota_polo: {
        type: String,
    }

} )


const AopolocModel = mongoose.model( 'aopolos', AopoloSchema );

module.exports = AopolocModel;