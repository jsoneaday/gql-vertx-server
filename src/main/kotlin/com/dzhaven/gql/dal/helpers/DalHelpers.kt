package com.dzhaven.gql.dal.helpers

import io.vertx.kotlin.sqlclient.getConnectionAwait
import io.vertx.kotlin.sqlclient.preparedQueryAwait
import io.vertx.kotlin.sqlclient.queryAwait
import io.vertx.pgclient.PgPool
import io.vertx.sqlclient.Row
import io.vertx.sqlclient.RowSet
import io.vertx.sqlclient.Tuple

class DalHelpers {
  companion object {
    private lateinit var client: PgPool

    fun init(pool: PgPool) {
      client = pool
    }

    suspend fun execPreparedQuery(queryStr: String, args: Tuple): RowSet<Row> {
      val conn = client.getConnectionAwait()
      val rows = conn.preparedQueryAwait(queryStr, args)
      conn.close()
      return rows
    }

    suspend fun execQuery(queryStr: String): RowSet<Row> {
      val conn = client.getConnectionAwait()
      val rows = conn.queryAwait(queryStr)
      conn.close()
      return rows
    }
  }
}
