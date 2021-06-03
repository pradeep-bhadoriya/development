let fs=require("fs");

console.log("before")

fs.readFile("./f1.txt",cb);

function cb(error,data){
    console.log("content"+data);
}
console.log("after");