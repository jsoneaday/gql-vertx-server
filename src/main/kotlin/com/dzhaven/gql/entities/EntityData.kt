package com.dzhaven.gql.entities

class EntityData {
  companion object {
    private val users: Array<User> = arrayOf<User>(
      User(1, "jon"),
      User(2, "tim"),
      User(3, "ronny"),
      User(4, "linda"),
      User(5, "pam"),
      User(6, "sam")
    )

    private val tasks: MutableList<Task> = mutableListOf<Task>(
      Task(1, "Pick up groceries", "test 1"),
      Task(2, "Pick up kids", "test 2"),
      Task(3, "Meet chris", "test 3")
    )

    fun getUser(id: Long): User? {
      return users.first { usr ->
        usr.id == id
      }
    }

    fun getTasks(): MutableList<Task>? {
      println("all tasks" + tasks)
      return tasks
    }

    fun addTask(title: String, desc: String?): Task? {
      val newTask = Task(tasks.last().id + 1, title, desc)
      tasks.add(newTask)
      return newTask
    }
  }
}
