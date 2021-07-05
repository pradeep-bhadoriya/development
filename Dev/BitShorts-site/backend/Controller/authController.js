const userModel=require("../Model/usersModel")

async function signup(req , res){
    try {
        let user=req.body;
        let newUser=await userModel.create(user);
        res.status(200).json({
            message:"successfully signedup!",
            data:newUser
        })

    } catch (error) {
        res.status(501).json({
            message:"signedup failed!",
            error
        })
    }
}

async function login(req , res){
    try {
        let {email , password}=req.body;
        let loggedinUser=await userModel.find({email:email , password:password});
        if(loggedinUser.length){
            res.status(200).json({
                message:"user found and logged in",
                data:loggedinUser
            })
        }
        else{
            res.status(200).json({
                message:"Wrong emailID or password",
            })
        }
    } catch (error) {
        res.status(200).json({
            message:"Something went wrong !!!",
            error
        })
    }
}

module.exports.signup=signup;
module.exports.login=login;