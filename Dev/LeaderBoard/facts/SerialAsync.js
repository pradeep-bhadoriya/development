let fs=require("fs");

console.log("before");

fs.readFile("./f1.txt",function cb(error,data){
    console.log("Content"+ data);
    fs.readFile("./f2.txt",function cb(error,data){
        console.log("Content"+ data);
        fs.readFile("./f3.txt",function cb(error,data){
            console.log("Content"+ data);
        })
    })
})

// this above desgin   is known as CallBack Hell

console.log("After");