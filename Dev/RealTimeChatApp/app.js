let express = require('express');
let app = express();
let http = require('http');
let server = http.createServer(app);
let { Server } = require("socket.io");
let io = new Server(server);

app.use(express.static("public"))
users=[];
app.get('/', (req, res) => {
    res.sendFile(__dirname + '/index.html');
});

io.on('connection', function (socket) {
    console.log(`${socket.id}  connected`);
    socket.on("join-chat",function(name){
        socket.broadcast.emit("user-joined",name);
        users.push({id:socket.id,name:name});
    })

    socket.on("send-chat",function(userObj){
        socket.broadcast.emit("receive-chat",userObj);
    })

    socket.on("disconnect",function(){
        let user=users.filter(function(userObj){
            return userObj.id==socket.id;
        });

        if(user){
            socket.broadcast.emit("leave",user[0].name);
        }

        users=users.filter(function(userObj){
            return userObj.id!=socket.id;
        })

    })


});

let port=process.env.PORT || 3000;

server.listen(port, () => {
    console.log('listening on *:3000');
});