package com.dzhaven.gql

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.kotlin.coroutines.CoroutineVerticle

class MainVerticle : CoroutineVerticle() {

  override suspend fun start() {
    vertx.deployVerticle(HttpVerticle())
  }
}
