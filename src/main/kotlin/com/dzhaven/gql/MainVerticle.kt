package com.dzhaven.gql

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future

class MainVerticle : AbstractVerticle() {

  override fun start(startFuture: Future<Void>) {
    vertx.deployVerticle(HttpVerticle())
  }
}
