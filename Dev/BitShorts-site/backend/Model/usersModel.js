const mongoose=require("mongoose");
const secret = require("../config/secret");

mongoose.connect(secret.DB_LINK,{ useNewUrlParser: true , useUnifiedTopology: true  } ).then((db)=>{

})
.then((db)=>{
console.log("db connected");
})

let userSchema=new mongoose.Schema({
    name:{
        type:String,
        required:true,
    },
    email:{
        type:String,
        required:true,
        unique:true
    },
    password:{
        type:String,
        required:true,
        minlength:[8,"password must be greater than 8 charachters"]
    },
    confirmPassword:{
        type:String,
        required:true,
        validate:{
            validator:function(){
                return this.password==this.confirmPassword;
            },
            message:"password do not matched"
        }
    },
    role:{
        type:String,
        enum:["admin" , "user" , "owner" , "delivery boy"],
        default:"user"
    }
})

userSchema.pre("save" , function(){
    this.confirmPassword=undefined;
})
// compiling schema into collection
const userModel=mongoose.model("usercollection" , userSchema);

module.exports=userModel;