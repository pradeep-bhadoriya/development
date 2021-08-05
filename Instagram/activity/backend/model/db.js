const mysql=require("mysql");
const config=require("../config/secret");
// // Mysql connection
// const connection = mysql.createConnection({
//     host     : config.HOST,
//     user     : config.USER,
//     password : config.PWD,
//     database : config.DB_NAME,
// //   port     : "3306"
// });

// connection.connect();

// // connection.query('SELECT 1 + 1 AS solution', function (error, results, fields) {
// //   if (error) throw error;
// //   console.log('The solution is: ', results[0].solution);
// // });
// connection.query("desc user_table",function(error,results,fileds){
//     console.log(results);
// })

// module.exports=connection;

let connection=null;
function handleDisconnect() {
        connection = mysql.createConnection({
        host     : config.HOST,
        user     : config.USER,
        password : config.PWD,
        database : config.DB_NAME,
    //   port     : "3306"
    }); // Recreate the connection, since
    // the old one cannot be reused.

    connection.connect(function (err) { // The server is either down
        if (err) { // or restarting (takes a while sometimes).
            console.log('error when connecting to db:', err);
            setTimeout(handleDisconnect, 2000); // We introduce a delay before attempting to reconnect,
        } // to avoid a hot loop, and to allow our node script to
    }); // process asynchronous requests in the meantime.
    // If you're also serving http, display a 503 error.
    connection.on('error', function (err) {
        console.log('db error', err);
        if (err.code === 'PROTOCOL_CONNECTION_LOST') { // Connection to the MySQL server is usually
            handleDisconnect(); // lost due to either server restart, or a
        } else { // connnection idle timeout (the wait_timeout
            throw err; // server variable configures this)
        }
    });
}
handleDisconnect();
setInterval(function () {
    connection.query("desc user_table",function(error,results,fileds){
        console.log(results);
    })
}, 5000);

module.exports = connection;