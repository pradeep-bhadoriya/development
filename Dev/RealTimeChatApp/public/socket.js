socket.on("user-joined",function(name){
    let joinedDiv=document.createElement("div");
    joinedDiv.classList.add("chat");
    joinedDiv.classList.add("join");
    joinedDiv.innerHTML=`${name} joined the chat`;
    chatBox.append(joinedDiv);
    chatBox.scrollTop=chatBox.scrollHeight;
})

socket.on("receive-chat",function(userObj){
    let chatDiv=document.createElement("div");
        chatDiv.classList.add("chat");
        chatDiv.classList.add("left");

        let chatName=document.createElement("div");
        chatName.classList.add("chat-name");
        chatName.innerHTML=userObj.user;

        let chatText=document.createElement("div");
        chatText.classList.add("chat-text");
        chatText.innerHTML=userObj.chatMessage;

        chatDiv.append(chatName);
        chatDiv.append(chatText);

        chatBox.append(chatDiv);
        chatBox.scrollTop=chatBox.scrollHeight;
})

socket.on("leave",function(name){
    let leaveDiv=document.createElement("div");
    leaveDiv.classList.add("chat");
    leaveDiv.classList.add("leave");
    leaveDiv.innerHTML=`${name} left the chat`;
    chatBox.append(leaveDiv);
    chatBox.scrollTop=chatBox.scrollHeight;
})