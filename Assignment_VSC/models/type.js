const mongoose = require( 'mongoose' );
const Scheme = mongoose.Schema;

const Types = new Scheme( {
   imagetype: {
      type: String
   }
}, {
   timestamps: true
} )
const typeModel = mongoose.model( 'types', Types );

module.exports = typeModel;