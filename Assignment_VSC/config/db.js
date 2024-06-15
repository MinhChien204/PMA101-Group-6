const mongoose = require('mongoose');

const local = "mongodb+srv://nmchien2204:chien1234@cluster0.4b0kubq.mongodb.net/pma101";

const connect = async () => {
    try {
        await mongoose.connect(local);
        console.log('Connect success');
    } catch (error) {
        console.error('Connection to MongoDB failed:', error);
    }
}  

module.exports = { connect };
