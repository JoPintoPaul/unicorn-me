package com.example

class Generator {

  case class UnicornName(firstName: String, lastName: String) {
    override def toString(): String = s"$firstName $lastName"
  }

  def generateName(firstName: String, birthMonth: Int): Either[String, UnicornName] = {
    firstName.toLowerCase().headOption match {
      case None => Left("Your first name is too short, must contain at least one letter")
      case Some(key) => unicornFirstNames.get(key) match {
        case Some(unicornFirstName) =>
          unicornLastNames.get(birthMonth) match {
            case Some(unicornLastName) => Right(UnicornName(unicornFirstName, unicornLastName))
            case None => Left("Oh no, doesn't look like there is a unicorn name for you! You might just have to work a bit " +
              "harder at being a bit more magic")
          }
        case None => Left("Oh no, doesn't look like there is a unicorn name for you! You might just have to work a bit " +
          "harder at being a bit more magic")
      }
    }
  }

  def generateNameTwo(firstName: String, birthMonth: Int): Either[String, UnicornName] = {
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

  private val firstNames = List(
    "Perky",
    "Bubbles",
    "Phoenix",
    "Shiny",
    "Bracken",
    "Sunshine",
    "Chipper",
    "Twinkle",
    "Sunny",
    "Jolly",
    "Colourful",
    "Happy",
    "Merry",
    "Crazy",
    "Awesome",
    "Starlight,",
    "Twilight",
    "Rainbow",
    "Magnificient",
    "Princess",
    "Giddy",
    "Lovely",
    "Dandelion",
    "Fancy",
    "Buttercup",
    "Sassy"
  )

  private val lastNames = List(
    "Twinkle Toes",
    "Sugar Socks",
    "Dainty Eyes",
    "Happy Feet",
    "Snowy Hooves",
    "Floating Bubbles",
    "Summer Dream",
    "Blue Berry",
    "Silver Moon",
    "Golden Tail",
    "Fancy Feet",
    "Candy Reins"
  )

  private val unicornFirstNames: Map[Char, String] = ('a' to 'z').toList.zip(firstNames).toMap
  private val unicornLastNames: Map[Int, String] = (1 to 12).toList.zip(lastNames).toMap
}
