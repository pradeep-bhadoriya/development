const express=require("express");
const { signup, login } = require("../Controller/authController");
const { getAllUsers, createUser, userByID, updateUser, deleteUser } = require("../Controller/userControllerl");


const userRouter=express.Router();

userRouter.route("").get(getAllUsers).post(createUser);
userRouter.route("/:id").get(userByID).patch(updateUser).delete(deleteUser);

userRouter.post("/signup" , signup)
userRouter.post("/login" , login)


module.exports=userRouter;