const planModel = require("../Model/plansModel");


// function getDemoPage(req , res){
//     res.render("base.pug")
// }

function homePage(req , res){
    res.render("homepage.pug" ,{name:req.name})
}

function loginPage(req , res){
    res.render("login.pug");
}

function signupPage(req , res){
    res.render("signup.pug" , {name:req.name});
}

async function plansPage(req , res){
    let plans=await planModel.find();
    console.log(plans);
    res.render("allPlans.pug" , {plans:plans , name:req.name});
}

// module.exports.getDemoPage=getDemoPage;
module.exports.homePage=homePage;
module.exports.loginPage=loginPage;
module.exports.signupPage=signupPage;
module.exports.plansPage=plansPage;