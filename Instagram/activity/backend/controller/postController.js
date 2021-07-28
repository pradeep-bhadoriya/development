const connection=require("../model/db");
const {v4:uuidv4}=require("uuid")
async function getAllPost(req , res){
    try {
        const sql=`select * from post_table`;
        connection.query(sql , function(error , data){
            if(error){
                res.status(501).json({
                    message:"failed to get all posts",
                    error
                })
            }
            else{
                res.status(200).json({
                    message:"got all post successfully",
                    data
                })
            }
        })
    } catch (error) {
        res.status(404).json({
            message:"failed to get all posts - Inside catch",
            error
        })
    }
}

function createPostPromisified(postObj){
    return new Promise(function(resolve , reject){
        const uid=uuidv4();
        const {name ,email,pw,postname,bio,isPublic}=postObj;
        let sql;
        if(isPublic!=undefined){
            sql=`insert into post_table(uid, name, email, pw, postname, bio, isPublic) values('${uid}', '${name}', '${email}', '${pw}', '${postname}', '${bio}', ${isPublic})`;
        }
        else{
            sql=`insert into post_table(uid, name, email, pw, postname, bio) values('${uid}', '${name}', '${email}', '${pw}', '${postname}', '${bio}')`;            
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
async function createPost(req , res){
    try {
        const postObj=req.body;
        let data = await createPostPromisified(postObj);
        res.status(220).json({
            data:data,
            messgae:"post added successfully"
        })
    } catch (error) {
        res.status(404).json({
            message:"failed to create a post",
            error
        })
    }
}

async function getPostById(req , res){
    try {
        var uid=req.params.uid
        console.log(uid);
        const sql=`select * from post_table where uid='${uid}'`;
        connection.query(sql , function(error , data){
            if(error){
                res.json({
                    message:"failed to get post by id",
                    error
                })
            }
            else{
                res.json({
                    message:"successfully got post by id",
                    data
                })
            }
        })
    } catch (error) {
        res.json({
            message:"failed to get post by id - Inside catch",
            error
        })
    }
}

async function updatePostById(req , res){
    try {
        const uid=req.params.uid;
        const newPostObj=req.body;
        let fetchSql=`select * from post_table where uid='${uid}'`;
        connection.query(fetchSql , function(error , data){
            if(error){
                res.json({
                    message:"failed to update post by id",
                    error
                })
            }
            else{
                const {name, email, pw, pimage, postname, bio, isPublic}=data[0];
                const nname=newPostObj.name==undefined?name:newPostObj.name;
                const nemail=newPostObj.email==undefined?email:newPostObj.email;
                const npw=newPostObj.pw==undefined?pw:newPostObj.pw;
                const npimage=newPostObj.pimage==undefined?pimage:newPostObj.pimage;
                const npostname=newPostObj.postname==undefined?postname:newPostObj.postname;
                const nbio=newPostObj.bio==undefined?bio:newPostObj.bio;
                const nisPublic=newPostObj.isPublic==undefined?isPublic:newPostObj.isPublic;

                let updateSql=`update post_table set name='${nname}', email='${nemail}', pw='${npw}', pimage='${npimage}', postname='${npostname}', bio='${nbio}', isPublic=${nisPublic} where uid='${uid}'`;
                connection.query(updateSql , function(error , data){
                    if(error){
                        res.json({
                            message:"failed to update post by ID",
                            error
                        })
                    }
                    else{
                        res.json({
                            message:"successfully updated post by ID"
                        })
                    }
                })
                // res.json({
                //     message:"successfully updated post by id",
                //     data
                // })
            }
        })
    } catch (error) {
        res.json({
            message:"failed to update post by id - Inside catch",
            error
        })
    }
}

async function deletePostById(req , res){
    try {
        var uid=req.params.uid
        console.log(uid);
        const sql=`delete from post_table where uid='${uid}'`;
        connection.query(sql , function(error , data){
            if(error){
                res.json({
                    message:"failed to delete post by id",
                    error
                })
            }
            else{
                res.json({
                    message:"successfully delete post by id",
                    data
                })
            }
        })
    } catch (error) {
        res.json({
            message:"failed to delete post by id - Inside catch",
            error
        })
    }
}

module.exports.getAllPost=getAllPost
module.exports.createPost=createPost
module.exports.getPostById=getPostById
module.exports.updatePostById=updatePostById
module.exports.deletePostById=deletePostById