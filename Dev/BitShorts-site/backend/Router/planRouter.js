const express=require("express");
const { authenticate, authorize } = require("../Controller/authController");
const { createPlan, getAllPlans, planByID, updatePlan, deletePlan } = require("../Controller/planController");


const planRouter=express.Router();

planRouter.route("").get(authenticate , getAllPlans).post(createPlan);
planRouter.route("/:id").get(authenticate , authorize , planByID).patch(updatePlan).delete(deletePlan);

module.exports=planRouter;