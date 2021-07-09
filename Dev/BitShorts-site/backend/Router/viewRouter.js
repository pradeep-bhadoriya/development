const express = require("express");
const { getDemoPage, homePage, loginPage } = require("../Controller/viewController");

const viewRouter=express.Router();

viewRouter.route("").get(getDemoPage);
viewRouter.route("/home").get(homePage)
viewRouter.route("/loginpage").get(loginPage);



module.exports = viewRouter;