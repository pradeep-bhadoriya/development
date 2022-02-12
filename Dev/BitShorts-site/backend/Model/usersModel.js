const mongoose=require("mongoose");
const secret = require("../config/secret");
const crypto = require("crypto");

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
    },
    pwToken:String,
    tokenTime:String
})

userSchema.pre("save" , function(){
    this.confirmPassword=undefined;
})

userSchema.methods.createPwToken=function(){
    console.log("inside createPwToken");

    let token=crypto.randomBytes(32).toString("hex");
    let time=Date.now()*6010*1000;

    this.pwToken=token;
    this.tokenTime=time;

    // console.log(this);
    return(token);
    // iske bad save krna hai

}

userSchema.methods.resetPasswordHandler = function(password , confirmPassword){
    console.log("Inside resetPasswordHandler");
    this.password=password;
    this.confirmPassword=confirmPassword;
    this.pwToken=undefined;
    this.tokenTime=undefined;
}

// compiling schema into collection
const userModel=mongoose.model("usercollection" , userSchema);

module.exports=userModel;