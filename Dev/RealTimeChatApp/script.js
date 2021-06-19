{/*
    <div class="chat right">
                <div class="chat-name">Pradeep</div>
                <div class="chat-text">don of the world pradeep</div>
            </div> */
}
let chat = document.querySelector("#input");
let send = document.querySelector("#send");
let chatBox = document.querySelector("#chat-box");

send.addEventListener("click",function(){
    let chatMessage=chat.value;
    if(chatMessage){
        let chatDiv=document.createElement("div");
        chatDiv.classList.add("chat");
        chatDiv.classList.add("right");

        let chatName=document.createElement("div");
        chatName.classList.add("chat-name");
        chatName.innerHTML="Pradeep";

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

    }
})