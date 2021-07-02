const express = require("express");
const plans = require("./db/plans.json")
const app = express();
const fs = require("fs")
const { uuid }=require("uuidv4");

// it tracks incoming request and if there is data , it will fed that data to req.body
app.use(express.json());

// get all plans
app.get("/app/allplans", function (req, res) {
    res.status(200).json({
        message: "got all plans",
        data: plans
    })
})

// create a plan
app.post("/app/allplans", function (req, res) {
    console.log(uuid());
    let plan = req.body
    plan.id=uuid();
    // console.log(id)
    console.log("pradeep is don hai");
    plans.push(plan);
    fs.writeFileSync("./db/plans.json", JSON.stringify(plans));

    res.status(201).json({
        message: "successfully created a plan",
        data: plans
    })
})


// get plan by id
app.get("/app/allplans/:id", function(req,res){
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

})


// update a plan
app.patch("/app/allplans/:id", function(req,res){
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

})


// delete a plan
app.delete("/app/allplans/:id", function(req,res){
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

})



app.listen(3000, function () {
    console.log("hi welcome to backend!");
})