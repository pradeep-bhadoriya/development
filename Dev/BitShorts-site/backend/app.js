const express = require("express");
const app = express();
const planRouter = require("./Router/planRouter");
const userRouter = require("./Router/userRouter");


// it tracks incoming request and if there is data , it will fed that data to req.body
app.use(express.json());

app.use("/app/plans",planRouter);
app.use("/app/user",userRouter);



app.listen(3000, function () {
    console.log("hi welcome to backend!");
})