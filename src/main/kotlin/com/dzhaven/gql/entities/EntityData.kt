package com.dzhaven.gql.entities

import com.dzhaven.gql.shared.User
import com.dzhaven.gql.shared.Task
import com.dzhaven.gql.dal.helpers.execPreparedQuery
import com.dzhaven.gql.dal.helpers.execQuery
import io.vertx.core.Vertx
import io.vertx.pgclient.PgConnectOptions
import io.vertx.pgclient.PgPool
import io.vertx.sqlclient.PoolOptions
import io.vertx.sqlclient.Row
import io.vertx.sqlclient.RowSet
import io.vertx.sqlclient.Tuple

class EntityData {
  companion object {
    private lateinit var connOptions: PgConnectOptions
    private lateinit var client: PgPool

    fun init(vertx: Vertx) {
      connOptions = PgConnectOptions()
      connOptions.apply {
        port = 5432
        host = "10.0.0.32"
        database = "Tasks"
        user = "testuser"
        password = "test123"
      }
      client = PgPool.pool(vertx, connOptions, PoolOptions().setMaxSize(5))
    }

    suspend fun getUser(id: Long): User? {
      val rows = execPreparedQuery(
        client,
      """
        select *
        from "Users"
        where "Id"=$1
      """.trimIndent(),
      Tuple.of(id))

      return getNewUser(rows.first())
    }

    suspend fun getTasks(): ArrayList<Task> {
      val rows = execQuery(
        client,
        """
          select *
          from "Tasks"
        """.trimIndent()
      )
      return getTasks(rows)
    }

    suspend fun addTask(title: String, desc: String?): Task? {
      val addedRows = execPreparedQuery(
        client,
        """
          insert into "Tasks"
          ("Title", "Description")
          values
          ($1, $2)
          returning "Id"
        """.trimIndent(),
        Tuple.of(title, desc)
      )

      val id = addedRows.first().getLong("Id")

      val newTaskRow = execPreparedQuery(
        client,
        """
          select *
          from "Tasks"
          where "Id"=$1
        """.trimIndent(),
        Tuple.of(id)
      )
      return getNewTask(newTaskRow.first())
    }

    private fun getNewUser(row: Row): User {
      return with(row) {
        User(
          id = getLong("Id"),
          name = getString("Name")
        )
      }
    }

    private fun getNewTask(row: Row): Task {
      return with(row) {
        Task(
          id = getLong("Id"),
          title = getString("Title"),
          description = getString("Description")
        )
      }
    }

    private fun getTasks(rows: RowSet<Row>): ArrayList<Task> {
      val tasks = arrayListOf<Task>()
      rows.forEach{ row ->
        tasks.add(getNewTask(row))
      }
      return tasks
    }
  }
}
