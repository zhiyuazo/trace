var http = require('http');
var url  =  require('url');
var querystring = require('querystring');
var zzyself = require('./zzyself');

zzyself.hi();

var server = http.createServer(zzyself.service);
server.listen(8088);