var path = require('path');
var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  mode: 'development',
  watch: true,
  resolve: {
    extensions: ['.js', '.vue']
  },
  output: {
    path: __dirname,
    publicPath: '/'
  },
  resolve: {
        alias: {
            'vue$': 'vue/dist/vue.esm.js'
        },
        extensions: ['*', '.js', '.vue', '.json']
    },
  module: {
    rules: [{
        test: /\.vue$/,
        loader: 'vue-loader',
        options: {
          loaders: {}
          // other vue-loader options go here
        }
      },
      {
        test: /\.js?$/,
        exclude: /(node_modules)/,
        use: 'babel-loader'
      },
      {
        test: /\.css?$/,
        //                exclude: /(node_modules)/,
        use: ["vue-style-loader", "style-loader", "css-loader"]
      }
    ]
  },
  plugins: [new HtmlWebpackPlugin({
    template: './src/index.html'
  })],
  devServer: {
    historyApiFallback: true,
  },
  externals: {
    // global app config object
    config: JSON.stringify({
      apiUrl: 'http://localhost:8080/api'
    })
  }
}
