const mongoose = require('mongoose');

const connect = () => {
  mongoose.connect('mongodb+srv://nmchien2204:chien1234@cluster0.4b0kubq.mongodb.net/pma101', {
    useNewUrlParser: true,
    useUnifiedTopology: true
  })
  .then(() => console.log('MongoDB connected'))
  .catch(err => console.log('MongoDB connection error:', err));
};

module.exports = { connect };
