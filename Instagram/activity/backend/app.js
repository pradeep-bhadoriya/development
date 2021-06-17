const express=require("express");
const mysql=require("mysql");
const config=require("./config/secret")

const app=express();

// Mysql connection
const connection = mysql.createConnection({
  host     : config.HOST,
  user     : config.USER,
  password : config.PWD,
  database : config.DB_NAME,
//   port     : "3306"
});
 
connection.connect();
 
// connection.query('SELECT 1 + 1 AS solution', function (error, results, fields) {
//   if (error) throw error;
//   console.log('The solution is: ', results[0].solution);
// });
connection.query("select * from user_table",function(error,results,fileds){
    console.log(fileds);
})




app.listen(3000,function(){
    console.log("app is listening at 3000 port !");
})