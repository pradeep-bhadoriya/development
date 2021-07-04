const express=require("express");
const { createPlan, getAllPlans, planByID, updatePlan, deletePlan } = require("../Controller/planController");


const planRouter=express.Router();

planRouter.route("").get(getAllPlans).post(createPlan);
planRouter.route("/:id").get(planByID).patch(updatePlan).delete(deletePlan);

module.exports=planRouter;