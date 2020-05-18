// để sử dụng module cần thực hiện lệnh [npm install mongoose]
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

// schema - Lượt đồ tương ứng các trường trong database
var userSchema = new Schema({
    username: { type: String, required: true, unique: true },
    password: { type: String, required: true },
    fullname: String
});

// the schema is useless so far
// we need to create a model using it
var User = mongoose.model('Users', userSchema);

// make this available to our users in our Node applications
module.exports = User;

