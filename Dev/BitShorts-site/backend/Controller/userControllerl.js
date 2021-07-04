const users=require("../Model/usersModel.json");
const fs=require("fs");
const { uuid }=require("uuidv4");
const path=require("path");
let usersPath=path.join(__dirname,"..","Model","usersModel.json");

function getAllUsers(req, res){
    res.status(200).json({
        message:"got all users",
        data:users
    })
}
function createUser(req,res){
    let user=req.body;
    user.id=uuid();
    users.push(user);
    fs.writeFileSync(usersPath, JSON.stringify(users));
    res.status(201).json({
        message: "successfully created a user",
        data: users
    })
}
function userByID(req,res){
    let {id}=req.params;

    let filteredusers=users.filter(function(user){
        return user.id==id
    })

    if(filteredusers.length){
        res.status(200).json({
            message:"Success got",
            data:filteredusers
        })
    }
    else{
        res.status(404).json({
            message:"plan not found with this id ",
        })
    }

}
function updateUser(req,res){
    let {id}=req.params;
    let updateObj=req.body;

    let filteredusers=users.filter(function(user){
        return user.id==id
    })

    if(filteredusers.length){
        let user=filteredusers[0];
        for(key in updateObj){
            user[key]=updateObj[key];
        }
        fs.writeFileSync(usersPath, JSON.stringify(users));
    }
    else{
        res.status(404).json({
            message:"user not found with this id ",
        })
    }

}
function deleteUser(req,res){
    let {id}=req.params;

    let filteredusers=users.filter(function(user){
        return user.id!=id
    })

    if(filteredusers.length==users.length){
        res.status(404).json({
            message:"plan not found with this id ",
        })
    }
    else{
        
        fs.writeFileSync(usersPath,JSON.stringify(filteredusers))
        res.status(200).json({
            message:"Success got",
            data:filteredusers
        })
    }

}

module.exports.getAllUsers=getAllUsers
module.exports.createUser=createUser;
module.exports.userByID=userByID;
module.exports.updateUser=updateUser;
module.exports.deleteUser=deleteUser;