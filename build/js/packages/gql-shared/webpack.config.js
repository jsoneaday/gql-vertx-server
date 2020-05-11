var config = {
  mode: 'production',
  resolve: {
    modules: [
      "node_modules"
    ]
  },
  plugins: [],
  module: {
    rules: []
  }
};

// entry
config.entry = {
    main: ["/Users/davidchoi/MyContent/Youtube/gql-vertx-server/build/js/packages/gql-shared/kotlin-dce/gql-shared.js"]
};

config.output = {
    path: "/Users/davidchoi/MyContent/Youtube/gql-vertx-server/shared/build/distributions",
    filename: (chunkData) => {
        return chunkData.chunk.name === 'main'
            ? "shared.js"
            : "shared-[name].js";
    },
    library: "shared",
    libraryTarget: "umd",
};

// resolve modules
config.resolve.modules.unshift("/Users/davidchoi/MyContent/Youtube/gql-vertx-server/build/js/packages/gql-shared/kotlin-dce")

// source maps
config.module.rules.push({
        test: /\.js$/,
        use: ["kotlin-source-map-loader"],
        enforce: "pre"
});
config.devtool = 'source-map';

// save evaluated config file
var util = require('util');
var fs = require("fs");
var evaluatedConfig = util.inspect(config, {showHidden: false, depth: null, compact: false});
fs.writeFile("/Users/davidchoi/MyContent/Youtube/gql-vertx-server/shared/build/reports/webpack/gql-shared/webpack.config.evaluated.js", evaluatedConfig, function (err) {});

// Report progress to console
// noinspection JSUnnecessarySemicolon
;(function(config) {
    const webpack = require('webpack');
    const handler = (percentage, message, ...args) => {
        let p = percentage * 100;
        let msg = `${Math.trunc(p / 10)}${Math.trunc(p % 10)}% ${message} ${args.join(' ')}`;
        msg = msg.replace(new RegExp("/Users/davidchoi/MyContent/Youtube/gql-vertx-server/build/js", 'g'), '');;
        console.log(msg);
    };

    config.plugins.push(new webpack.ProgressPlugin(handler))
})(config);
module.exports = config
