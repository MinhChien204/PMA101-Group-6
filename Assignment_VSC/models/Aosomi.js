var fs = require( 'fs' );
const mongoose = require( 'mongoose' );
const { type } = require( 'os' );


const AosomiSchema = mongoose.Schema( {
    image_somi: {
        type: String,
    },
    brand_somi: {
        type: String,
    },
    name_somi: {
        type: String,
    },
    price_somi: {
        type: Number,
    },
    mota_somi: {
        type: String,
    }

} )


const AosomiModel = mongoose.model( 'aosomis', AosomiSchema );

module.exports = AosomiModel;