package com.dzhaven.gql.schema

import com.dzhaven.gql.entities.*
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import io.vertx.core.Vertx
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import java.util.concurrent.CompletableFuture

class DataFetchers(private val vertx: Vertx) {
  init {
    EntityData.init(vertx)
  }

  val userDataFetcher = object : DataFetcher<CompletableFuture<UserEntity?>> {
    override fun get(environment: DataFetchingEnvironment?): CompletableFuture<UserEntity?> =
      CoroutineScope(Dispatchers.IO).future {
        val id = environment?.getArgument<Long>("id")
        EntityData.getUser(id ?: 0)
      }
  }

  val tasksDataFetcher = object : DataFetcher<CompletableFuture<ArrayList<TaskEntity>>> {
    override fun get(environment: DataFetchingEnvironment?): CompletableFuture<ArrayList<TaskEntity>> =
      CoroutineScope(Dispatchers.IO).future {
        EntityData.getTasks()
      }
  }

  val addTaskFetcher = object : DataFetcher<CompletableFuture<TaskEntity?>> {
    override fun get(environment: DataFetchingEnvironment?): CompletableFuture<TaskEntity?> =
      CoroutineScope(Dispatchers.IO).future {
        val title = environment?.getArgument("title") ?: ""
        val desc = environment?.getArgument<String>("desc")
        EntityData.addTask(title, desc)
      }
  }
}
