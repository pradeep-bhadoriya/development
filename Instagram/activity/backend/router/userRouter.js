const express=require("express");
const { getAllUser, createUser, getUserById, updateUserById, deleteUserById } = require("../controller/userController");
const userRouter=express.Router();
const path=require("path");
const multer=require("multer");

const storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, 'public/images/user')
    },
    
    filename: function (req, file, cb) {
        cb(null, Date.now()+file.originalname);
    }
});
//only accept mimetype == image
const fileFilter = (req ,file ,cb ) => {
    if(file.mimetype === "image/jpg"  || 
       file.mimetype ==="image/jpeg"  || 
       file.mimetype ===  "image/png"){
        
    cb(null, true);
   }else{
    cb(null, false);
}
}
const upload = multer({storage: storage, fileFilter : fileFilter});

userRouter.route("/").get(getAllUser).post(upload.single("user") , createUser);
userRouter.route("/:uid").get(getUserById).patch(upload.single("user") , updateUserById).delete(deleteUserById)

module.exports=userRouter;