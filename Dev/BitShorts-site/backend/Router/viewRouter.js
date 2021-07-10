const express = require("express");
const { getDemoPage, homePage, loginPage, signupPage } = require("../Controller/viewController");

const viewRouter=express.Router();

viewRouter.route("").get(getDemoPage);
viewRouter.route("/home").get(homePage)
viewRouter.route("/loginpage").get(loginPage);
viewRouter.route("/signup").get(signupPage);


module.exports = viewRouter;