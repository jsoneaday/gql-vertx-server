{
  mode: 'production',
  resolve: {
    modules: [
      '/Users/davidchoi/MyContent/Youtube/gql-vertx-server/build/js/packages/gql-shared/kotlin-dce',
      'node_modules'
    ]
  },
  plugins: [],
  module: {
    rules: [
      {
        test: /\.js$/,
        use: [
          'kotlin-source-map-loader'
        ],
        enforce: 'pre'
      }
    ]
  },
  entry: {
    main: [
      '/Users/davidchoi/MyContent/Youtube/gql-vertx-server/build/js/packages/gql-shared/kotlin-dce/gql-shared.js'
    ]
  },
  output: {
    path: '/Users/davidchoi/MyContent/Youtube/gql-vertx-server/shared/build/distributions',
    filename: [Function: filename],
    library: 'shared',
    libraryTarget: 'umd'
  },
  devtool: 'source-map'
}