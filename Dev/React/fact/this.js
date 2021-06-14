let obj={
    name:"pradeep",
    sayHi:function(){
        console.log("inside sayHi");
        console.log(this);
        inside=()=>{//   Arrow function -> take obj from it upper scope
            console.log("inside inside function");
            console.log("==========")
            console.log(this);
        }
        inside();
    }
}

// function fun(){
//     console.log(this);
// }

console.log(obj.sayHi());