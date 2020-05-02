package com.dzhaven.gql.schema

import graphql.GraphQL
import graphql.schema.GraphQLSchema
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import io.vertx.core.Vertx

fun generateRunTimeWiring(vertx: Vertx): RuntimeWiring {
  val dataFetcher = DataFetchers(vertx)
  return RuntimeWiring.newRuntimeWiring()
    .type("Query") {typeWiring ->
      typeWiring
        .dataFetcher("getUser", dataFetcher.userDataFetcher)
        .dataFetcher("getTasks", dataFetcher.tasksDataFetcher)
    }
    .type("Mutation") {typeWiring ->
      typeWiring
        .dataFetcher("addTask", dataFetcher.addTaskFetcher)
    }
    .build()
}

fun getSchemaWiring(vertx: Vertx, schema: String): GraphQLSchema {
  val schemaParser = SchemaParser()
  val typeRegistry = schemaParser.parse(schema)

  val wiring = generateRunTimeWiring(vertx)

  val schemaGenerator = SchemaGenerator()
  return schemaGenerator.makeExecutableSchema(typeRegistry, wiring)
}

fun getGraphQL(vertx: Vertx, schemaStr: String): GraphQL {
  val schema = getSchemaWiring(vertx, schemaStr)
  return GraphQL.newGraphQL(schema).build()
}
