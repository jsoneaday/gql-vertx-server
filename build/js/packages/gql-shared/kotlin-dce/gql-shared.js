(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'gql-shared'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'gql-shared'.");
    }root['gql-shared'] = factory(typeof this['gql-shared'] === 'undefined' ? {} : this['gql-shared'], kotlin);
  }
}(this, function (_, Kotlin) {
  'use strict';
  var Kind_CLASS = Kotlin.Kind.CLASS;
}));

//# sourceMappingURL=gql-shared.js.map
