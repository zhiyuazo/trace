var express = require('express');
var path = require('path')
var fs = require("fs");
var multer  = require('multer');


var app = express();


app.get('/', function (req, res) {
   res.send('Hello from express');
});
 
app.get('/abc', function (req, res) {
       res.send('abc page from express');
});

//获取后缀名
function getExtName(fileName){
    var index1=fileName.lastIndexOf(".");
    var index2=fileName.length;
    var extName=fileName.substring(index1+1,index2);
    return extName;
}
  
app.post('/uploadPhoto', function (req, res) {
   //获取上传图片的后缀名
   var extName = getExtName(req.files[0].originalname);
     
   //随机数
   var rundomNumber = Math.ceil(Math.random()*10000000);
   //以随机数作为文件名
   var randomFileName =  rundomNumber + "."+extName;
     
   //创建图片目录
   var imgFolder = __dirname + "/public/img/";
   if(!fs.exists(imgFolder))
       fs.mkdir(imgFolder);
     
   //图片路径
   var imgFile = __dirname + "/public/img/" + randomFileName;
     
   //上传临时文件的路径
   var uploadedTempFilePath = req.files[0].path;
     
   //读取临时文件
   fs.readFile( uploadedTempFilePath, function (err, data) {
       //读取成功之后，复制到图片路径
        fs.writeFile(imgFile, data, function (err) {
            //写成功之后，返回 img元素显示上传之后的图片
              res.writeHead(200, {'Content-Type': 'text/html'});
              res.end("<img src='img/"+randomFileName+"'/>");
       });
   });
})
   
app.use(multer({ dest: path.join(__dirname, 'tmp') }).array('image'));
app.use(express.static(path.join(__dirname, 'public'))) //静态文件路径
var server = app.listen(8088);
