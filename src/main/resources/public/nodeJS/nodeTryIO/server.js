var http = require("http"); 
var url = require("url"); 
var how2java = require("how2java");
   
function start(route, handle) { 
  console.log("nodeJS service is Starting " + how2java.hello())
  function onRequest(request, response) { 
    var pathname = url.parse(request.url).pathname; 
    var html = route(handle, pathname); 
    response.writeHead(200, {"Content-Type": "text/plain"}); 
    response.write(html); 
    response.end(); 
  } 
   
  http.createServer(onRequest).listen(8088); 
} 
exports.start = start;  