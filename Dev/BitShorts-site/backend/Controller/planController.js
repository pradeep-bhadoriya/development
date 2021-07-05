const planModel = require("../Model/plansModel");
// const fs = require("fs");
// const { uuid } = require("uuidv4");
// const path = require("path");
// let plansPath = path.join(__dirname, "..", "Model", "plansModel.json");

async function getAllPlans(req, res) {
    try {
        let allPlans = await planModel.find();
        res.status(200).json({
            message: "got all plans",
            data: allPlans
        })
    }
    catch (error) {
        res.status(501).json({
            message: "No Plan found",
            data: error
        })
    }

    // console.log(__dirname);

}
async function createPlan(req, res) {
    // console.log(uuid());
    let plan = req.body
    // plan.id=uuid();
    // plan.id = uuid();
    // console.log(id)
    // console.log("pradeep is don hai");
    // plans.push(plan);

    // console.log(plansPath);
    // fs.writeFileSync(plansPath, JSON.stringify(plans));
    try {
        let newPlan = await planModel.create(plan);
        res.status(201).json({
            message: "successfully created a plan",
            data: plan
        })
    }
    catch (error) {
        res.status(201).json({
            message: "plan creation failed",
            error: error
        })
    }
}
async function planByID(req, res) {
    let { id } = req.params;
    try {
        let plan = await planModel.findById(id);
        res.status(200).json({
            message: "got plan by id",
            data: plan
        })
    } catch (error) {
        res.status(404).json({
            message: "plan not found with this id ",
            error: error
        })
    }
}
async function updatePlan(req, res) {
    try {
        let { id } = req.params;
        let updateObj = req.body;

        let updatedPlan = await planModel.findByIdAndUpdate(id, updateObj, { new: true })
        res.status(200).json({
            message: "plan  with this id updated",
            data: updatedPlan
        })
    } catch (error) {
        res.status(200).json({
            message: "plan  with this id not updated",
            error
        })
    }
}
async function deletePlan(req, res) {
    try {
        let { id } = req.params;

        let deletedPlan=await planModel.findByIdAndDelete(id);
        res.status(200).json({
            message: "plan with this id deleted",
            data:deletedPlan
        })
    } catch (error) {
        res.status(200).json({
            message: "plan with this id not deleted",
            error
        })
    }
}

module.exports.getAllPlans = getAllPlans;
module.exports.createPlan = createPlan;
module.exports.planByID = planByID;
module.exports.updatePlan = updatePlan;
module.exports.deletePlan = deletePlan;