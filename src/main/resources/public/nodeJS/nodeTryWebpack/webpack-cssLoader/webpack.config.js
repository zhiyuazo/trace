var webpack = require('webpack')
module.exports = {
    entry: './a.js',
    output: {
      filename: 'bundle.js'
    },
    devServer: {
      port:8088
    },
    module: {
      loaders: [
          {
            test:/\.css/,
            loader:'style-loader!css-loader'
          }
      ]
    }
  };