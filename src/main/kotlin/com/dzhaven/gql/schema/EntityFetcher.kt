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
    return EntityData.getUser(id ?: 0)
  }
}

val tasksDataFetcher = object: DataFetcher<MutableList<Task>?> {
  override fun get(environment: DataFetchingEnvironment?): MutableList<Task>? {
    return EntityData.getTasks()
  }
}

val addTaskFetcher = object: DataFetcher<CompletableFuture<Task?>> {
  override fun get(environment: DataFetchingEnvironment?): CompletableFuture<Task?> {
    return CompletableFuture.supplyAsync {
      val title = environment?.getArgument("title") ?: ""
      val desc = environment?.getArgument<String?>("desc")
      return@supplyAsync EntityData.addTask(title, desc)
    }
  }
}
