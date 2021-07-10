const express = require("express");
const { isLoggedIn, logout } = require("../Controller/authController");
const { getDemoPage, homePage, loginPage, signupPage, plansPage } = require("../Controller/viewController");

const viewRouter=express.Router();

viewRouter.use(isLoggedIn);
viewRouter.route("/").get(homePage)
viewRouter.route("/signup").get(signupPage);
viewRouter.route("/login").get(loginPage);
viewRouter.route("/logout").get(logout);
viewRouter.route("/plans").get(plansPage);


module.exports = viewRouter;