const express = require("express");
const { getDemoPage, homePage, loginPage, signupPage, plansPage } = require("../Controller/viewController");

const viewRouter=express.Router();

viewRouter.route("").get(getDemoPage);
viewRouter.route("/home").get(homePage)
viewRouter.route("/loginpage").get(loginPage);
viewRouter.route("/signup").get(signupPage);
viewRouter.route("/plans").get(plansPage);


module.exports = viewRouter;