package com.example

object Main {
  def main(args: Array[String]): Unit = {
    val generator = new Generator()
    println(generator.generateName("Buddy", 11))
  }
}
