package com.example

import scala.collection.JavaConversions._
import java.io.File
import com.typesafe.config.ConfigFactory

class Generator {

  private val conf = ConfigFactory.parseFile(new File("application.conf"))
  private val firstNames = conf.getStringList("names.first")
  private val lastNames = conf.getStringList("names.last")

  private val unicornFirstNames: Map[Char, String] = ('a' to 'z').toList.zip(firstNames).toMap
  private val unicornLastNames: Map[Int, String] = (1 to 12).toList.zip(lastNames).toMap

  def generateName(firstName: String, birthMonth: Int): Either[String, UnicornName] = {
    firstName.toLowerCase().headOption flatMap { key =>
      unicornFirstNames.get(key) flatMap { unicornFirstName =>
        unicornLastNames.get(birthMonth) map { unicornLastName =>
          UnicornName(unicornFirstName, unicornLastName)
        }
      }
    } match {
      case Some(unicornName) => Right(unicornName)
      case None => Left("Oh no, doesn't look like there is a unicorn name for you! You might just have to work a bit " +
        "harder at being a bit more magic")
    }
  }

  case class UnicornName(firstName: String, lastName: String) {
    override def toString: String = s"$firstName $lastName"
  }
}
