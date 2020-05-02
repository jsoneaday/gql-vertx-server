package com.dzhaven.gql.schema

import com.dzhaven.gql.entities.*
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import io.vertx.core.Vertx
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.CompletableFuture

class DataFetchers(private val vertx: Vertx) {
  init {
    EntityData.init(vertx)
  }

  val userDataFetcher = object : DataFetcher<CompletableFuture<UserEntity?>> {
    override fun get(environment: DataFetchingEnvironment?): CompletableFuture<UserEntity?> {
      val future = CompletableFuture<UserEntity?>()
      CoroutineScope(Dispatchers.IO).launch{
        val id = environment?.getArgument<Long>("id")
        future.complete(EntityData.getUser(id ?: 0))
      }
      return future
    }
  }

  val tasksDataFetcher = object : DataFetcher<CompletableFuture<ArrayList<TaskEntity>>> {
    override fun get(environment: DataFetchingEnvironment?): CompletableFuture<ArrayList<TaskEntity>> {
      val future = CompletableFuture<ArrayList<TaskEntity>>()
      CoroutineScope(Dispatchers.IO).launch {
        future.complete(EntityData.getTasks())
      }
      return future
    }
  }

  val addTaskFetcher = object : DataFetcher<CompletableFuture<TaskEntity?>> {
    override fun get(environment: DataFetchingEnvironment?): CompletableFuture<TaskEntity?> {
      val future = CompletableFuture<TaskEntity?>()
      CoroutineScope(Dispatchers.IO).launch {
        val title = environment?.getArgument("title") ?: ""
        val desc = environment?.getArgument<String>("desc")
        future.complete(EntityData.addTask(title, desc))
      }
      return future
    }
  }
}
