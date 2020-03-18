var server = require("./server"); 
var routerMedia = require("./routerMedia"); 

var handle = {} 
handle["/listCategory"] = routerMedia.listCategory; 
handle["/listProduct"] = routerMedia.listProduct; 
handle["/readFile"] = routerMedia.readFile; 
handle["/writeFile"] = routerMedia.writeFile;  
   
server.start(routerMedia.route, handle);  