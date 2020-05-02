package com.dzhaven.gql

import com.dzhaven.gql.schema.getGraphQL
import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.CorsHandler
import io.vertx.ext.web.handler.graphql.GraphQLHandler

class HttpVerticle: AbstractVerticle() {
  override fun start() {
    vertx.fileSystem().readFile("graphqlSchema.graphqls") {handler ->
      if(handler.succeeded()) {
        val router = Router.router(vertx)
        val allowedHeaders: MutableSet<String> = HashSet()
        allowedHeaders.add("Content-Type")
        router
          .route()
          .handler(CorsHandler.create("http://localhost:4000")
            .allowedHeaders(allowedHeaders))

        val schemaStr = handler.result().toString()
        val graphQL = getGraphQL(vertx, schemaStr)
        router.post("/graphql").handler(GraphQLHandler.create(graphQL))

        vertx.createHttpServer().requestHandler(router).listen(9999)
      } else {

      }
    }
  }
}
