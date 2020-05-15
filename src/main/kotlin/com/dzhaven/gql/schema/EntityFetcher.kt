package com.dzhaven.gql.schema

import com.dzhaven.gql.entities.EntityData
import com.dzhaven.gql.shared.Task
import com.dzhaven.gql.shared.User
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import io.vertx.core.Vertx
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import java.util.concurrent.CompletableFuture

class DataFetchers(private val vertx: Vertx) {
  init {
    EntityData.init(vertx)
  }

  val userDataFetcher = object : DataFetcher<CompletableFuture<User?>> {
    override fun get(environment: DataFetchingEnvironment?): CompletableFuture<User?> =
      CoroutineScope(vertx.dispatcher()).future {
        val id = environment?.getArgument<Long>("id")
        EntityData.getUser(id ?: 0)
      }
  }

  val tasksDataFetcher = object : DataFetcher<CompletableFuture<ArrayList<Task>>> {
    override fun get(environment: DataFetchingEnvironment?): CompletableFuture<ArrayList<Task>> =
      CoroutineScope(vertx.dispatcher()).future {
        EntityData.getTasks()
      }
  }

  val addTaskFetcher = object : DataFetcher<CompletableFuture<Task?>> {
    override fun get(environment: DataFetchingEnvironment?): CompletableFuture<Task?> =
      CoroutineScope(vertx.dispatcher()).future {
        val title = environment?.getArgument("title") ?: ""
        val desc = environment?.getArgument<String>("desc")
        EntityData.addTask(title, desc)
      }
  }
}
