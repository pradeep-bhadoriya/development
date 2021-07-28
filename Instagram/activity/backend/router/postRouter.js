const express=require("express");
const { getAllpost, createpost, getpostById, updatepostById, deletepostById } = require("../controller/postController");
const postRouter=express.Router();

// postRouter.route("/").get(getAllpost).post(createpost);
// postRouter.route("/:uid").get(getpostById).patch(updatepostById).delete(deletepostById)

module.exports=postRouter;