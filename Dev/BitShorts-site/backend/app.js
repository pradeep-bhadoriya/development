const express = require("express");
const app = express();
const planRouter = require("./Router/planRouter");
const userRouter = require("./Router/userRouter");
const viewRouter = require("./Router/viewRouter");
const path=require("path");


// it tracks incoming request and if there is data , it will fed that data to req.body
app.use(express.json());

app.use( express.static("public" ))

// set view engine
app.set("view engine" , "pug");
//set view path
app.set("views" , path.join(__dirname,"View"));

app.use("/app/plans",planRouter);
app.use("/app/user",userRouter);

app.use("" , viewRouter);



app.listen(3000, function () {
    console.log("hi welcome to backend!");
})