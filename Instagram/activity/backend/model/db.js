const mysql=require("mysql");
const config=require("../config/secret");
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
connection.query("desc user_table",function(error,results,fileds){
    console.log(results);
})

module.exports=connection;