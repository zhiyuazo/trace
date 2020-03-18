var webpack = require('webpack')
module.exports = {
    entry: './webpackDemo.js',
    output: {
      filename: 'bundle_cfg_method.js'
    },
    plugins:[
      new webpack.HotModuleReplacementPlugin()
    ],
    devServer: {
      port:8088,
      inline:true,
      hot:true
    }

  };