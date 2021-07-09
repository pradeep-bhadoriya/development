let email=document.querySelector("#email")
let password=document.querySelector("#pw")
let loginBtn=document.querySelector(".btn-parent")

loginBtn.addEventListener("click" ,async function(e){
    try {
        e.preventDefault();
    
    if(email && password){
        let obj=await axios.post("http://localhost:3000/app/user/login" , {email:email.value , password:pw.value})
        console.log("login successfully");
        console.log(obj);
    }
    } catch (error) {
        alert(error)
    }
    
})