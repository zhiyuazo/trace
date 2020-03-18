var webpack = require('webpack')
module.exports = {
    entry: './ES6.js',
    output: {
      filename: 'bundle.js'
    },
    devServer: {
      port:8088
    },
    module: {
      loaders: [
          {
              test: /\.js$/,
              loader: 'babel',
              query:{
                  presets: ['latest']
              }
          }
      ]
    }
  };