const express=require("express");
const { signup, login, authenticate, authorize, forgotPassword, resetPassword } = require("../Controller/authController");
const { getAllUsers, createUser, userByID, updateUser, deleteUser } = require("../Controller/userControllerl");


const userRouter=express.Router();

userRouter.post("/login" , login)
userRouter.post("/signup" , signup)

userRouter.post("/forget" , forgotPassword); 
userRouter.patch("/resetpassword/:resetToken" , resetPassword);   
// userRouter.route("").get(authenticate , getAllUsers).post(createUser);
userRouter.use(authenticate);
userRouter.use(authorize);
userRouter.route("").get(userByID).patch(updateUser).delete(deleteUser);



module.exports=userRouter;