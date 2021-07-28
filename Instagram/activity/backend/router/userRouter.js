const express=require("express");
const { getAllUser, createUser, getUserById, updateUserById, deleteUserById } = require("../controller/userController");
const userRouter=express.Router();

userRouter.route("/").get(getAllUser).post(createUser);
userRouter.route("/:uid").get(getUserById).patch(updateUserById).delete(deleteUserById)

module.exports=userRouter;