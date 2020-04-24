package com.dzhaven.gql.schema

import com.dzhaven.gql.entities.EntityData
import com.dzhaven.gql.entities.Task
import com.dzhaven.gql.entities.User
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import java.util.concurrent.CompletableFuture

val userDataFetcher = object: DataFetcher<User?> {
  override fun get(environment: DataFetchingEnvironment?): User? {
    val id = environment?.getArgument<Long>("id")
    val user: User? = EntityData.getUser(id ?: 0)
    return user
  }
}

val tasksDataFetcher = object: DataFetcher<MutableList<Task>?> {
  override fun get(environment: DataFetchingEnvironment?): MutableList<Task>? {
    val tasks = EntityData.getTasks()
    return tasks
  }
}

val addTaskFetcher = object: DataFetcher<CompletableFuture<Task?>> {
  override fun get(environment: DataFetchingEnvironment?): CompletableFuture<Task?> {
    val promise: CompletableFuture<Task?> = CompletableFuture.supplyAsync {
      val title = environment?.getArgument<String>("title") ?: ""
      val desc = environment?.getArgument<String?>("desc")
      val addedTask = EntityData.addTask(title, desc)
      return@supplyAsync addedTask
    }
    return promise
  }
}
