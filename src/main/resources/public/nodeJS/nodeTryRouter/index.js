var server = require("./server"); 
var routerMedia = require("./routerMedia"); 

var handle = {} 
handle["/listCategory"] = routerMedia.listCategory; 
handle["/listProduct"] = routerMedia.listProduct; 
   
server.start(routerMedia.route, handle);  