const userModel=require("../Model/usersModel");
// const fs=require("fs");
// const { uuid }=require("uuidv4");
// const path=require("path");
// let usersPath=path.join(__dirname,"..","Model","usersModel.json");

function usingSystemAsDbAndFsModule(){
    // function getAllUsers(req, res){
    //     res.status(200).json({
    //         message:"got all users",
    //         data:users
    //     })
    // }
    // function createUser(req,res){
    //     let user=req.body;
    //     user.id=uuid();
    //     users.push(user);
    //     fs.writeFileSync(usersPath, JSON.stringify(users));
    //     res.status(201).json({
    //         message: "successfully created a user",
    //         data: users
    //     })
    // }
    // function userByID(req,res){
    //     let {id}=req.params;
    
    //     let filteredusers=users.filter(function(user){
    //         return user.id==id
    //     })
    
    //     if(filteredusers.length){
    //         res.status(200).json({
    //             message:"Success got",
    //             data:filteredusers
    //         })
    //     }
    //     else{
    //         res.status(404).json({
    //             message:"plan not found with this id ",
    //         })
    //     }
    
    // }
    // function updateUser(req,res){
    //     let {id}=req.params;
    //     let updateObj=req.body;
    
    //     let filteredusers=users.filter(function(user){
    //         return user.id==id
    //     })
    
    //     if(filteredusers.length){
    //         let user=filteredusers[0];
    //         for(key in updateObj){
    //             user[key]=updateObj[key];
    //         }
    //         fs.writeFileSync(usersPath, JSON.stringify(users));
    //     }
    //     else{
    //         res.status(404).json({
    //             message:"user not found with this id ",
    //         })
    //     }
    
    // }
    // function deleteUser(req,res){
    //     let {id}=req.params;
    
    //     let filteredusers=users.filter(function(user){
    //         return user.id!=id
    //     })
    
    //     if(filteredusers.length==users.length){
    //         res.status(404).json({
    //             message:"plan not found with this id ",
    //         })
    //     }
    //     else{
            
    //         fs.writeFileSync(usersPath,JSON.stringify(filteredusers))
    //         res.status(200).json({
    //             message:"Success got",
    //             data:filteredusers
    //         })
    //     }
    
    // }
}

async function getAllUsers(req, res) {
    try {
        let allUsers = await userModel.find();
        res.status(200).json({
            message: "got all users",
            data: allUsers
        })
    }
    catch (error) {
        res.status(501).json({
            message: "No users found",
            error
        })
    }

    // console.log(__dirname);

}
async function createUser(req, res) {
    try {
        let user = req.body
        let newUser = await userModel.create(user);
        res.status(201).json({
            message: "successfully created a user",
            data: newUser
        })
    }
    catch (error) {
        res.status(201).json({
            message: "user creation failed",
            error: error
        })
    }
}
async function userByID(req, res) {
    try {
        let { id } = req.params;
        let user = await userModel.findById(id);
        res.status(200).json({
            message: "got user by id",
            data: user
        })
    } catch (error) {
        res.status(404).json({
            message: "user not found with this id ",
            error: error
        })
    }
}
async function updateUser(req, res) {
    try {
        let { id } = req.params;
        let updateObj = req.body;

        let updatedUser = await userModel.findByIdAndUpdate(id, updateObj, { new: true })
        res.status(200).json({
            message: "user  with this id updated",
            data: updatedUser
        })
    } catch (error) {
        res.status(200).json({
            message: "user  with this id not updated",
            error
        })
    }
}
async function deleteUser(req, res) {
    try {
        let { id } = req.params;

        let deletedUser=await userModel.findByIdAndDelete(id);
        res.status(200).json({
            message: "User with this id deleted",
            data:deletedUser
        })
    } catch (error) {
        res.status(200).json({
            message: "User with this id not deleted",
            error
        })
    }
}


module.exports.getAllUsers=getAllUsers
module.exports.createUser=createUser;
module.exports.userByID=userByID;
module.exports.updateUser=updateUser;
module.exports.deleteUser=deleteUser;