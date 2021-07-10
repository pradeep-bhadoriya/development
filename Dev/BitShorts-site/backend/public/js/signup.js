let username=document.querySelector("#name");
let email=document.querySelector("#email");
let pw=document.querySelector("#pw");
let cpw=document.querySelector("#cpw");
let signupBtn=document.querySelector(".btn-parent");

signupBtn.addEventListener("click" , async function(e){
    try {
        console.log("signup button clicked")
        e.preventDefault();
        let userObj={
            "name":username.value,
            "email":email.value,
            "password":pw.value,
            "confirmPassword":cpw.value
        }
        let obj=await axios.post("http://localhost:3000/app/user/signup" , userObj);
        console.log(obj);
    } catch (error) {
        console.log(error);
    }
    
})
