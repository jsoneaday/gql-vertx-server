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
  function Task(id, title, description) {
    this.id = id;
    this.title = title;
    this.description = description;
  }
  Task.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Task',
    interfaces: []
  };
  Task.prototype.component1 = function () {
    return this.id;
  };
  Task.prototype.component2 = function () {
    return this.title;
  };
  Task.prototype.component3 = function () {
    return this.description;
  };
  Task.prototype.copy_9z4lgu$ = function (id, title, description) {
    return new Task(id === void 0 ? this.id : id, title === void 0 ? this.title : title, description === void 0 ? this.description : description);
  };
  Task.prototype.toString = function () {
    return 'Task(id=' + Kotlin.toString(this.id) + (', title=' + Kotlin.toString(this.title)) + (', description=' + Kotlin.toString(this.description)) + ')';
  };
  Task.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.id) | 0;
    result = result * 31 + Kotlin.hashCode(this.title) | 0;
    result = result * 31 + Kotlin.hashCode(this.description) | 0;
    return result;
  };
  Task.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.id, other.id) && Kotlin.equals(this.title, other.title) && Kotlin.equals(this.description, other.description)))));
  };
  function User(id, name) {
    this.id = id;
    this.name = name;
  }
  User.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'User',
    interfaces: []
  };
  User.prototype.component1 = function () {
    return this.id;
  };
  User.prototype.component2 = function () {
    return this.name;
  };
  User.prototype.copy_a4hdmt$ = function (id, name) {
    return new User(id === void 0 ? this.id : id, name === void 0 ? this.name : name);
  };
  User.prototype.toString = function () {
    return 'User(id=' + Kotlin.toString(this.id) + (', name=' + Kotlin.toString(this.name)) + ')';
  };
  User.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.id) | 0;
    result = result * 31 + Kotlin.hashCode(this.name) | 0;
    return result;
  };
  User.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.id, other.id) && Kotlin.equals(this.name, other.name)))));
  };
  function printMe(msg) {
    console.log(msg);
  }
  var package$com = _.com || (_.com = {});
  var package$dzhaven = package$com.dzhaven || (package$com.dzhaven = {});
  var package$gql = package$dzhaven.gql || (package$dzhaven.gql = {});
  var package$shared = package$gql.shared || (package$gql.shared = {});
  package$shared.Task = Task;
  package$shared.User = User;
  package$shared.printMe_61zpoe$ = printMe;
  Kotlin.defineModule('gql-shared', _);
  return _;
}));

//# sourceMappingURL=gql-shared.js.map
