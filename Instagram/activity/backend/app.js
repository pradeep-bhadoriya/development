const express=require("express");
const { getUserByIdPromisified } = require("./controller/userController");
const connection = require("./model/db");
const postRouter = require("./router/postRouter");
const userRouter = require("./router/userRouter");

const app=express();
app.use(express.json());

// user router
app.use("/user" , userRouter)
// post router
app.use("/post" , postRouter)

//on basis of id

//send request
app.post("/request", async function(req , res){
    let {uid , follow_id}=req.body;
    console.log(uid);
    let user=await getUserByIdPromisified(follow_id);
    console.log(user[0])
    let isPublic=user[0].isPublic;
    if(isPublic){
        //add in follower table and follwing table

        //Inserting in follwing table
        const followingSql=`insert into following_table values('${uid}', '${follow_id}', ${isPublic})`;
        connection.query(followingSql , function(error , data){
            if(error){
                res.json({
                    message:"failed to send friend reques",
                    error
                })
            }
            else{
                res.json({
                    message:"friend request send successfully",
                    data
                })
            }
        })

        //Inserting in follower table
        const followerSql=`insert into follower_table values('${follow_id}', '${uid}')`;
        connection.query(followerSql , function(error , data){
            if(error){
                res.json({
                    message:"follower added successfully",
                    error
                })
            }
            else{
                res.json({
                    message:"failed to add follower",
                    data
                })
            }
        })
    }
    else{
        //add in following table
        const followingSql=`insert into following_table values('${uid}', '${follow_id}', ${isPublic})`;
        connection.query(followingSql , function(error , data){
            if(error){
                res.json({
                    message:"failed to send friend reques",
                    error
                })
            }
            else{
                res.json({
                    message:"friend request send successfully",
                    data
                })
            }
        })
    }
})
//accepting a pending request
app.post("/accept", async function(req , res){
    // we have to update follwing table and follower table
    let accepterId=req.body.follow_id;
    let requesterId=req.body.uid;
    // updating following table
    const followingSql=`update following_table set isAccepted=true where uid='${requesterId}' and followId='${accepterId}'`;
    connection.query(followingSql , function(error, data){
        if(error){
            res.json({
                message:"failed to accept friend request",
                error
            })
        }
        else{
            // updating follower table
            const followerSql=`insert into follower_table values('${accepterId}', '${requesterId}')`;
            connection.query(followerSql , function(error, data){
                if(error){
                    res.json({
                        message:"failed to accept friend request",
                        error
                    })
                }
                else{
                    res.json({
                        message:"friend request accpeted successfully- - Inside follwer",
                        data
                    })
                }
            })
        }
    })
})

//view all pending requests
app.get("/allrequest/:uid" , async function(req , res){
    let uid=req.params.uid;
    let sql=`select * from following_table where followId='${uid}'`;
    connection.query(sql , async function(error, data){
        try {
            if(error){
                res.json({
                    message:"failed to get pending requests",
                    error
                })
            }
            else{
                let requestNames=[];
                console.log(data)
                for(let i=0;i<data.length;i++){
                    let user = await getUserByIdPromisified(data[i].uid);
                    requestNames.push(user);
                }
                console.log(requestNames)
                res.json({
                    message:"successfullt got pending requests",
                    requestNames
                })
            }
        } catch (error) {
            
        }
        
    })
})

// cancel pending request
app.delete("/cancel", async function(req , res){
    // we have to update follwing table and follower table
    let accepterId=req.body.follow_id;
    let requesterId=req.body.uid;
    // updating following table
    const followingSql=`delete from following_table where uid='${requesterId}' and followId='${accepterId}'`;
    connection.query(followingSql , function(error, data){
        if(error){
            res.json({
                message:"failed to accept friend request",
                error
            })
        }
        else{
            // updating follower table
            res.json({
                message:"friend request cancelled successfully- - Inside follwer",
                data
            })
        }
    })
})

app.listen(3000,function(){
    console.log("app is listening at 3000 port !");
})