var fs = require("fs");
//业务功能
function listCategory() { 
    return "a lot of categorys";
} 
   
function listProduct() { 
    return "a lot of products";
} 

function readFile(){
  var html = fs.readFileSync('how2j.html');
  return html;
}

function writeFile(){
  fs.writeFile('how2java.html', 'hello from how2java');
  return "write successful";
}
exports.listCategory = listCategory; 
exports.listProduct = listProduct;  
exports.readFile = readFile; 
exports.writeFile = writeFile;


//路由功能
function route(handle, pathname) { 
    if (typeof handle[pathname] === 'function') { 
      return handle[pathname](); 
    } else {
      return pathname + ' is not defined';
    } 
  } 
exports.route = route;  