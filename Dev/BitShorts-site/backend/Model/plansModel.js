const mongoose=require("mongoose");
const { DB_LINK } = require("../config/secret");

mongoose.connect(DB_LINK,{ useNewUrlParser: true , useUnifiedTopology: true  } ).then((db)=>{

})
.then((db)=>{
console.log("db connected");
})

let planSchema=new mongoose.Schema({
    plan:{
        type:String,
        required:true,
        maxlength:[40,"plan name is more than 40 charcaters"]
    },
    duration:{
        type:Number,
        required:true,
    },
    price:{
        type:Number,
        required:true
    },
    ratings:Number,
    discount:{
        type:Number,
        validate:{
            validator:function(){
                return this.discount<this.price;
            },
            message:"discount is more than actual price"
        }
    }
})

const planModel=mongoose.model("planCollection",planSchema);

module.exports=planModel;