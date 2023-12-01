object Day1 {
  def main(args: Array[String]): Unit = {
    val input = scala.io.Source.fromFile("input.txt").getLines().toList

    // Part 1
    val part1 = solvePart1(input)
    println(s"Part 1: $part1")

    val part2 = solvePart2(input)
    println(s"Part 2: $part2")
  }

  def solvePart1(input: List[String]): Int = {
    // Map each string in the input list to its calibration value and sum them up
    input.map(calibrationValue).sum
  }

  def calibrationValue(s: String): Int = {
    // Find the first and last digits in the string and convert them to integers
    val firstDigit = s.find(_.isDigit).getOrElse('0')
    val secondDigit = s.reverse.find(_.isDigit).getOrElse('0')
    (firstDigit.toString + secondDigit.toString).toInt
  }

  def solvePart2(input: List[String]): Int = {
    // Flatten the list of calibration values with letters and sum them up
    val values = input.flatMap(calibrationValueWithLetters)
    println(values)
    values.sum
  }

  def findNumber(s: String, findFirst: Boolean): Option[Int] = {
    // Find all matches of numberMapping in the string and their corresponding indices
    val matches = s.indices.flatMap { i =>
      numberMapping.collectFirst {
        case (word, digit) if s.slice(i, i + word.length) == word => (i, digit)
      }
    } ++ s.zipWithIndex.collect {
      case (c, index) if c.isDigit => (index, c.asDigit)
    }

    // Sort the matches by their indices
    val sortedMatches = matches.toList.sortBy(_._1)
    // Return the first or last match
    if (findFirst) sortedMatches.headOption.map(_._2)
    else sortedMatches.lastOption.map(_._2)
  }

  def calibrationValueWithLetters(s: String): Option[Int] = {
    val firstValue = findNumber(s, findFirst = true)
    val lastValue = findNumber(s, findFirst = false)
    println(s"$s: $firstValue, $lastValue")
    (firstValue, lastValue) match {
      case (Some(f), Some(l)) => Some((f.toString + l.toString).toInt)
      case _ => None
    }
  }

  val numberMapping = Map(
    "zero" -> 0,
    "one" -> 1,
    "two" -> 2,
    "three" -> 3,
    "four" -> 4,
    "five" -> 5,
    "six" -> 6,
    "seven" -> 7,
    "eight" -> 8,
    "nine" -> 9
  )
}