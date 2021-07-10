

function getDemoPage(req , res){
    res.render("base.pug")
}

function homePage(req , res){
    res.render("homepage.pug")
}

function loginPage(req , res){
    res.render("login.pug");
}

function signupPage(req , res){
    res.render("signup.pug")
}

module.exports.getDemoPage=getDemoPage;
module.exports.homePage=homePage;
module.exports.loginPage=loginPage;
module.exports.signupPage=signupPage;