package com.dzhaven.gql.dal.helpers

import io.vertx.core.AsyncResult
import io.vertx.pgclient.PgPool
import io.vertx.sqlclient.Row
import io.vertx.sqlclient.RowSet
import io.vertx.sqlclient.SqlConnection
import io.vertx.sqlclient.Tuple
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private suspend fun getConnection(pool: PgPool): SqlConnection {
  return suspendCoroutine { continuation ->
    pool.getConnection { conn ->
      if(conn.succeeded()) {
        continuation.resume(conn.result())
      } else {
        continuation.resumeWithException(conn.cause())
      }
    }
  }
}

suspend fun execPreparedQuery(pool: PgPool, queryStr: String, args: Tuple): RowSet<Row> {
  val conn = getConnection(pool)
  return suspendCoroutine { continuation ->
    conn.preparedQuery(queryStr)
      .execute(args) {qr ->
          handleQueryResult(continuation, conn, qr)
      }
  }
}

suspend fun execQuery(pool: PgPool, queryStr: String): RowSet<Row> {
  val conn = getConnection(pool)
  return suspendCoroutine { continuation ->
    conn.query(queryStr)
      .execute {qr ->
        handleQueryResult(continuation, conn, qr)
      }
  }
}

private fun handleQueryResult(
  continuation: Continuation<RowSet<Row>>,
  conn: SqlConnection,
  qr: AsyncResult<RowSet<Row>>) {
  try {
    if (qr.succeeded()) {
      continuation.resume(qr.result())
      println("result ${qr.result()}")
    } else {
      continuation.resumeWithException(qr.cause())
    }
  } catch (ex: Exception) {
    println(ex.message)
  } finally {
    conn.close()
  }
}
