const userModel = require("../Model/usersModel")
const jwt = require("jsonwebtoken");
const { SECRET_KEY } = require("../config/secret");

async function signup(req, res) {
    try {
        let user = req.body;
        console.log(user);
        let newUser = await userModel.create(user);
        console.log(newUser._id);
        res.status(200).json({
            message: "successfully signedup!",
            data: newUser,
            // token
        })

    } catch (error) {
        res.status(501).json({
            message: "signedup failed!",
            error
        })
    }
}

async function login(req, res) {
    try {
        let { email, password } = req.body;
        console.log(email,password);
        let loggedinUser = await userModel.find({ email: email });
        if (loggedinUser.length) {
            let user = loggedinUser[0];
            if (user.password == password) {
                const token = jwt.sign({ id: user["_id"] }, SECRET_KEY)
                res.status(200).json({
                    message: "user found and logged in",
                    data: loggedinUser[0],
                    token
                })
            }
            else {
                res.status(501).json({
                    message: "Wrong emailID or password",
                })
            }
        }
    } catch (error) {
        res.status(200).json({
            message: "Something went wrong !!!",
            error
        })
    }
}

async function authenticate(req, res, next) {
    try {
        const token = req.headers.authorization.split(" ").pop();
        console.log(token);
        const payload = jwt.verify(token, SECRET_KEY);
        console.log(payload);
        if (payload) {
            console.log("going to call next");
            req.id = payload.id;
            next();
        }
        else {
            res.status(501).json({
                message: "log in please "
                // data:receivedToken
            })
        }
    } catch (error) {
        res.status(501).json({
            message: "log in please ",
            error
            // data:receivedToken
        })
    }
}

async function authorize(req, res, next) {
    try {
        // const {token:receivedToken}=req.body
        let userid = req.id;
        let user = await userModel.findById(userid);
        // console.log(receivedToken);
        console.log(user);
        if (user.role == "admin") {
            console.log("going to call next");
            // req.id=payload.id;
            next();
        }
        else {
            res.status(501).json({
                message: "you are not authorized ",
                // data:receivedToken
            })
        }
    } catch (error) {
        res.status(501).json({
            message: "log in please ",
            error
            // data:receivedToken
        })
    }
}

async function forgotPassword(req, res) {
    try {
        let { email } = req.body
        console.log(email);
        let user = await userModel.findOne({ email: email });
        console.log(user);
        if (user) {
            console.log("user found")
            let pwToken = user.createPwToken();
            console.log(pwToken);
            await user.save({ validateBeforeSave: false });
            let resetLink = `http://localhost:3000/app/user/resetpassword/${pwToken}`;
            res.status(200).json({
                message: "link sent to yoour email id",
                email: resetLink
            })
        }
        else {
            res.status(404).json({
                message: "user not found"
            })
        }
    } catch (error) {
        res.status(501).json({
            message: "request failed",
            error
        })
    }
}

async function resetPassword(req, res) {
    try {
        console.log("Inside resetPassword");
        const resetToken = req.params.resetToken;
        console.log(resetToken);
        const { password, confirmPassword } = req.body;
        const user = await userModel.findOne({
            pwToken: resetToken,
            tokenTime: { $gt: Date.now() }
        })
        if (user) {
            user.resetPasswordHandler(password, confirmPassword);
            await user.save();
            res.status(200).json({
                message: "password reset successfully",
                data: user
            })
        }
        else{
            res.status(501).json({
                message: "failed inside else",
                data: user
            })
        }
    } catch (error) {
        res.status(501).json({
            message: "failed to update password",
            error
        })
    }
}

module.exports.signup = signup;
module.exports.login = login;
module.exports.authenticate = authenticate;
module.exports.authorize = authorize;
module.exports.forgotPassword = forgotPassword;
module.exports.resetPassword = resetPassword;