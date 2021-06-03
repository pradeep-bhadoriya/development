let fs=require("fs");

console.log("before")

let f1KaData=fs.readFileSync("./f1.txt");
console.log("content"+f1KaData);

console.log("after");