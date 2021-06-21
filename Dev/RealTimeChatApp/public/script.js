{/*
    <div class="chat right">
                <div class="chat-name">Pradeep</div>
                <div class="chat-text">don of the world pradeep</div>
            </div> */
}
let chat = document.querySelector("#input");
let send = document.querySelector("#send");
let chatBox = document.querySelector("#chat-box");
let joinChat=document.querySelector(".join-chat");
let chatName=document.querySelector("#chat-user")
let userNameDiv=document.querySelector(".chat-user-name");
let chatContent=document.querySelector(".chat-content");
let user;

joinChat.addEventListener("click",function(){
    user=chatName.value;
    if(user){
        socket.emit("join-chat",user);
        userNameDiv.classList.add("hide");
        chatContent.classList.remove("hide");
    }
})


send.addEventListener("click",function(){
    let chatMessage=chat.value;
    socket.emit("send-chat",{user,chatMessage});
    if(chatMessage){
        let chatDiv=document.createElement("div");
        chatDiv.classList.add("chat");
        chatDiv.classList.add("right");

        let chatName=document.createElement("div");
        chatName.classList.add("chat-name");
        chatName.innerHTML=user;

        let chatText=document.createElement("div");
        chatText.classList.add("chat-text");
        chatText.innerHTML=chatMessage;

        chatDiv.append(chatName);
        chatDiv.append(chatText);

        chatBox.append(chatDiv);

        chatBox.scrollTop=chatBox.scrollHeight;

        chat.value="";
    }
    else{
        console.log("pradeep");
    }
})