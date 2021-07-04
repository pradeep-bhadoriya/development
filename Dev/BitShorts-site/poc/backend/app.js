const express = require("express");
const plans = require("./db/plans.json")
const users=require("./db/users.json")
const app = express();
const fs = require("fs")
const { uuid }=require("uuidv4");
const mongoose =require("mongoose");


mongoose.connect("mongodb+srv://admin:admin@cluster0.m0xy1.mongodb.net/test?retryWrites=true&w=majority",{ useNewUrlParser: true , useUnifiedTopology: true  } ).then((db)=>{
    console.log(db);
})

// Schema
let planSchema=new mongoose.Schema({
    name:String,
    price:Number
})

//schema will compile into collection
const planModel=mongoose.model("plancollection",planSchema);

planModel.create({
    name:"Vegan",
    price:20
}).then((plan)=>{
    console.log(plan);
})
.catch((error)=>{
    console.log(error);
})

// app.use(function(req, res, next){     // middleware
//     console.log("before espress");
//     next();
// })
// it tracks incoming request and if there is data , it will fed that data to req.body
// app.use(express.json());

// app.use(function(req, res, next){     // middleware
//     console.log("after espress");
//     next();
// })


//########################## USER #########################################
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
    fs.writeFileSync("./db/users.json", JSON.stringify(users));
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
        fs.writeFileSync("./db/users.json", JSON.stringify(users));
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
        
        fs.writeFileSync("./db/users.json",JSON.stringify(filteredusers))
        res.status(200).json({
            message:"Success got",
            data:filteredusers
        })
    }

}

// get all users
app.get("/app/users",getAllUsers);
// create a user
app.post("/app/users",createUser);
// get plan by id
app.get("/app/users/:id",userByID)
// update a plan
app.patch("/app/users/:id", updateUser)
// delete a plan
app.delete("/app/users/:id", deleteUser)

//############################ PLANS ##############################################
function getAllPlans(req, res) {
    res.status(200).json({
        message: "got all plans",
        data: plans
    })
}
function createPlan(req, res) {
    console.log(uuid());
    let plan = req.body
    // plan.id=uuid();
    plan.id=uuid();
    // console.log(id)
    console.log("pradeep is don hai");
    plans.push(plan);
    fs.writeFileSync("./db/plans.json", JSON.stringify(plans));

    res.status(201).json({
        message: "successfully created a plan",
        data: plans
    })
}
function planByID(req,res){
    let {id}=req.params;

    let filteredplans=plans.filter(function(plan){
        return plan.id==id
    })

    if(filteredplans.length){
        res.status(200).json({
            message:"Success got",
            data:filteredplans
        })
    }
    else{
        res.status(404).json({
            message:"plan not found with this id ",
        })
    }

}
function updatePlan(req,res){
    let {id}=req.params;
    let updateObj=req.body;

    let filteredplans=plans.filter(function(plan){
        return plan.id==id
    })

    if(filteredplans.length){
        let plan=filteredplans[0];
        for(key in updateObj){
            plan[key]=updateObj[key];
        }
        fs.writeFileSync("./db/plans.json", JSON.stringify(plans));
    }
    else{
        res.status(404).json({
            message:"plan not found with this id ",
        })
    }

}
function deletePlan(req,res){
    let {id}=req.params;

    let filteredplans=plans.filter(function(plan){
        return plan.id!=id
    })

    if(filteredplans.length==plans.length){
        res.status(404).json({
            message:"plan not found with this id ",
        })
    }
    else{
        
        fs.writeFileSync("./db/plans.json",JSON.stringify(filteredplans))
        res.status(200).json({
            message:"Success got",
            data:filteredplans
        })
    }

}

// get all plans
app.get("/app/plans", getAllPlans)
// create a plan
app.post("/app/plans", createPlan)
// get plan by id
app.get("/app/plans/:id",planByID)
// update a plan
app.patch("/app/plans/:id", updatePlan)
// delete a plan
app.delete("/app/plans/:id", deletePlan)



app.listen(3000, function () {
    console.log("hi welcome to backend!");
})