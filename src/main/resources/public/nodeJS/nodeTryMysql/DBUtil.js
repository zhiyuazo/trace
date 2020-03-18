var mysql      = require("mysql");
var connection;
 
function openConnection(){
    connection = mysql.createConnection({
          host     : "127.0.0.1",
          user     : "root",
          password : "admin",
          database : "alistA"
        });
    connection.connect();
}
function closeConnection(){
    connection.end();  
}
 
function showAll(){
    openConnection();
    var  sql = "SELECT * FROM type_copy ORDER BY  id";
    connection.query(sql,function (err, results) {
        if(err){
            console.log("[SELECT ERROR] - ",err.message);
            return;
        }
        if(results)
        {
            for(var i = 0; i < results.length; i++)
            {
                console.log("%d\t%s\t%s", results[i].id, results[i].Tvalue,results[i].Prompt);
            }
        }  
    });
    closeConnection();     
}

function add(Tvalue,Prompt){
    openConnection();
    var params = [null,Tvalue,Prompt,'DEFAULT'];  
    var  sql = "insert into type_copy (id,Tvalue,Prompt,Tlabel) values (?,?,?,?)";
    connection.query(sql,params,function (err, result) {
        if(err){
         console.log("[INSERT ERROR] - ",err.message);
         return;
        }       
       console.log("insert success, the generated id is:",result.insertId);       
    });
    closeConnection(); 
}
 
function remove(id){
    openConnection();
    var params = [id]; 
    var  sql = "delete from type_copy where id = ?";
    connection.query(sql,params,function (err, result) {
        if(err){
         console.log("[REMOVE ERROR] - ",err.message);
         return;
        }       
       console.log("remove id=%d success ",id);       
    });
    closeConnection(); 
}


exports.showAll = showAll;
exports.add = add;
exports.remove = remove;