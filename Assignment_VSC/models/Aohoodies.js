var fs = require( 'fs' );
const mongoose = require( 'mongoose' );
const { type } = require( 'os' );

const AohoodieSchema = mongoose.Schema( {
    image_hoodie: {
        type: String,
    },
    brand_hoodie: {
        type: String,
    },
    name_hoodie: {
        type: String,
    },
    price_hoodie: {
        type: Number,
    },
    mota_hoodie: {
        type: String,
    }

} )


const AohoodiesModel = mongoose.model( 'aohoodies', AohoodieSchema );

module.exports = AohoodiesModel;