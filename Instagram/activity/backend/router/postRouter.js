const express=require("express");
const { getAllPost, createPost, getPostById, updatePostById, deletePostById } = require("../controller/postController");
const postRouter=express.Router();
const path = require("path");
const multer = require("multer");

const storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, 'public/images/post')
    },

    filename: function (req, file, cb) {
        cb(null, Date.now() + file.originalname);
    }
});
//only accept mimetype == image
const fileFilter = (req, file, cb) => {
    if (file.mimetype === "image/jpg" ||
        file.mimetype === "image/jpeg" ||
        file.mimetype === "image/png") {

        cb(null, true);
    } else {
        cb(null, false);
    }
}
const upload = multer({ storage: storage, fileFilter: fileFilter });

postRouter.route("/").get(getAllPost).post(upload.single("post") , createPost);
postRouter.route("/:pid").get(getPostById).patch(upload.single("post") , updatePostById).delete(deletePostById)

module.exports=postRouter;