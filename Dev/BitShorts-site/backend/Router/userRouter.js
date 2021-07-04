const express=require("express");
const { getAllUsers, createUser, userByID, updateUser, deleteUser } = require("../Controller/userControllerl");


const userRouter=express.Router();

userRouter.route("").get(getAllUsers).post(createUser);
userRouter.route("/:id").get(userByID).patch(updateUser).delete(deleteUser);

module.exports=userRouter;