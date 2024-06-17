var fs = require( 'fs' );
const mongoose = require( 'mongoose' );
const { type } = require( 'os' );

const AokhoacSchema = mongoose.Schema( {
    image_khoac: {
        type: String,
    },
    brand_khoac: {
        type: String,
    },
    name_khoac: {
        type: String,
    },
    price_khoac: {
        type: Number,
    },
    image_khoac: {
        type: String,
    }

} )


const AokhoacModel = mongoose.model( 'aokhoac', AokhoacSchema );

module.exports = AokhoacModel;