package com.dzhaven.gql.schema

import graphql.GraphQL
import graphql.schema.GraphQLSchema
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser

fun generateRunTimeWiring(): RuntimeWiring {
  return RuntimeWiring.newRuntimeWiring()
    .type("Query") {typeWiring ->
      typeWiring
        .dataFetcher("getUser", userDataFetcher)
        .dataFetcher("getTasks", tasksDataFetcher)
    }
    .type("Mutation") {typeWiring ->
      typeWiring
        .dataFetcher("addTask", addTaskFetcher)
    }
    .build()
}

fun getSchemaWiring(schema: String): GraphQLSchema {
  val schemaParser = SchemaParser()
  val typeRegistry = schemaParser.parse(schema)

  val wiring = generateRunTimeWiring()

  val schemaGenerator = SchemaGenerator()
  return schemaGenerator.makeExecutableSchema(typeRegistry, wiring)
}

fun getGraphQL(schemaStr: String): GraphQL {
  val schema = getSchemaWiring(schemaStr)
  return GraphQL.newGraphQL(schema).build()
}
