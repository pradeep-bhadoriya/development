const connection=require("../model/db");
const {v4:uuidv4}=require("uuid")
async function getAllUser(req , res){
    try {
        const sql=`select * from user_table`;
        connection.query(sql , function(error , data){
            if(error){
                res.status(501).json({
                    message:"failed to get all users",
                    error
                })
            }
            else{
                res.status(200).json({
                    message:"got all user successfully",
                    data
                })
            }
        })
    } catch (error) {
        res.status(404).json({
            message:"failed to get all users - Inside catch",
            error
        })
    }
}

function createUserPromisified(userObj){
    return new Promise(function(resolve , reject){
        const uid=uuidv4();
        const {name ,email,pw,username,bio,isPublic,pimage}=userObj;
        let sql;
        if(isPublic!=undefined){
            sql=`insert into user_table(uid, name, email, pw, pimage, username, bio, isPublic) values('${uid}', '${name}', '${email}', '${pw}', '${pimage?pimage:"default.png"}', '${username}', '${bio}', ${isPublic})`;
        }
        else{
            sql=`insert into user_table(uid, name, email, pw, pimage, username, bio) values('${uid}', '${name}', '${email}', '${pw}', '${pimage?pimage:"default.png"}', '${username}', '${bio}')`;            
        }
        
        connection.query(sql , function(error , data){
            if(error){
                reject(error);
            }
            else{
                resolve(data);
            }
        })
    })
}
async function createUser(req , res){
    try {
        const userObj=req.body;
        console.log(userObj);
        console.log(req.file)
        let pimage=req.file.destination+"/"+req.file.filename;
        pimage=pimage.substring(7);
        userObj.pimage=pimage;
        console.log(userObj);
        let data = await createUserPromisified(userObj);
        res.status(220).json({
            data:data,
            messgae:"user added successfully"
        })
    } catch (error) {
        res.status(404).json({
            message:"failed to create a user",
            error
        })
    }
}

function getUserByIdPromisified(uid){
    return new Promise(function(resolve , reject){
        const sql=`select * from user_table where uid='${uid}'`;
        connection.query(sql , function(error , data){
            if(error){
                reject(error);
            }
            else{
                resolve(data);
            }
        })
    })
}
async function getUserById(req , res){
    try {
        var uid=req.params.uid;
        // console.log(uid);
        const data=await getUserByIdPromisified(uid);
        if(data){
            res.status(200).json({
                message:"successfull to get user by id",
                data
            })
        }
    } catch (error) {
        res.json({
            message:"failed to get user by id - Inside catch",
            error
        })
    }
}

async function updateUserById(req , res){
    try {

        const uid=req.params.uid;
        const newUserObj=req.body;
        if(req.file!=undefined){
            let pimage=req.file.destination+"/"+req.file.filename;
            pimage=pimage.substring(7);
            newUserObj.pimage=pimage;
        }
        let fetchSql=`select * from user_table where uid='${uid}'`;
        

        connection.query(fetchSql , function(error , data){
            if(error){
                res.json({
                    message:"failed to update user by id",
                    error
                })
            }
            else{
                const {name, email, pw, pimage, username, bio, isPublic}=data[0];
                const nname=newUserObj.name==undefined?name:newUserObj.name;
                const nemail=newUserObj.email==undefined?email:newUserObj.email;
                const npw=newUserObj.pw==undefined?pw:newUserObj.pw;
                const npimage=newUserObj.pimage==undefined?pimage:newUserObj.pimage;
                const nusername=newUserObj.username==undefined?username:newUserObj.username;
                const nbio=newUserObj.bio==undefined?bio:newUserObj.bio;
                const nisPublic=newUserObj.isPublic==undefined?isPublic:newUserObj.isPublic;

                let updateSql=`update user_table set name='${nname}', email='${nemail}', pw='${npw}', pimage='${npimage}', username='${nusername}', bio='${nbio}', isPublic=${nisPublic} where uid='${uid}'`;
                connection.query(updateSql , function(error , data){
                    if(error){
                        res.json({
                            message:"failed to update user by ID",
                            error
                        })
                    }
                    else{
                        res.json({
                            message:"successfully updated user by ID"
                        })
                    }
                })
                // res.json({
                //     message:"successfully updated user by id",
                //     data
                // })
            }
        })
    } catch (error) {
        res.json({
            message:"failed to update user by id - Inside catch",
            error:error
        })
    }
}

async function deleteUserById(req , res){
    try {
        var uid=req.params.uid
        console.log(uid);
        const sql=`delete from user_table where uid='${uid}'`;
        connection.query(sql , function(error , data){
            if(error){
                res.json({
                    message:"failed to delete user by id",
                    error
                })
            }
            else{
                res.json({
                    message:"successfully delete user by id",
                    data
                })
            }
        })
    } catch (error) {
        res.json({
            message:"failed to delete user by id - Inside catch",
            error
        })
    }
}

module.exports.getAllUser=getAllUser
module.exports.createUser=createUser
module.exports.getUserById=getUserById
module.exports.updateUserById=updateUserById
module.exports.deleteUserById=deleteUserById
module.exports.getUserByIdPromisified=getUserByIdPromisified