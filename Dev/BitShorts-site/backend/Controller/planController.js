const plans=require("../Model/plansModel.json");
const fs=require("fs");
const { uuid }=require("uuidv4");
const path=require("path");
let plansPath=path.join(__dirname,"..","Model","plansModel.json");

function getAllPlans(req, res) {
    res.status(200).json({
        message: "got all plans",
        data: plans
    })
    // console.log(__dirname);

}
function createPlan(req, res) {
    console.log(uuid());
    let plan = req.body
    // plan.id=uuid();
    plan.id = uuid();
    // console.log(id)
    console.log("pradeep is don hai");
    plans.push(plan);
    
    console.log(plansPath);
    fs.writeFileSync(plansPath, JSON.stringify(plans));

    res.status(201).json({
        message: "successfully created a plan",
        data: plans
    })
}
function planByID(req, res) {
    let { id } = req.params;

    let filteredplans = plans.filter(function (plan) {
        return plan.id == id
    })

    if (filteredplans.length) {
        res.status(200).json({
            message: "Success got",
            data: filteredplans
        })
    }
    else {
        res.status(404).json({
            message: "plan not found with this id ",
        })
    }

}
function updatePlan(req, res) {
    let { id } = req.params;
    let updateObj = req.body;

    let filteredplans = plans.filter(function (plan) {
        return plan.id == id
    })

    if (filteredplans.length) {
        let plan = filteredplans[0];
        for (key in updateObj) {
            plan[key] = updateObj[key];
        }
        fs.writeFileSync(plansPath, JSON.stringify(plans));
    }
    else {
        res.status(404).json({
            message: "plan not found with this id ",
        })
    }

}
function deletePlan(req, res) {
    let { id } = req.params;

    let filteredplans = plans.filter(function (plan) {
        return plan.id != id
    })

    if (filteredplans.length == plans.length) {
        res.status(404).json({
            message: "plan not found with this id ",
        })
    }
    else {

        fs.writeFileSync(plansPath, JSON.stringify(filteredplans))
        res.status(200).json({
            message: "Success got",
            data: filteredplans
        })
    }

}

module.exports.getAllPlans=getAllPlans;
module.exports.createPlan=createPlan;
module.exports.planByID=planByID;
module.exports.updatePlan=updatePlan;
module.exports.deletePlan=deletePlan;