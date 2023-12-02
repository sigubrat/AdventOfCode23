object Day2 {
  def main(args: Array[String]): Unit = {
    val input = scala.io.Source.fromFile("input.txt").getLines().toList

    // Part 1
    val part1 = solvePart1(input)
    println(s"Part 1: $part1")

    // Part 2
    val part2 = solvePart2(input)
    println(s"Part 2: $part2")
  }

  def parseString(s: String): List[Map[String, Int]] = {
    val parts = s.split(":")
    val rounds = parts(1).split(";")
    rounds.map { round =>
      round.split(",").flatMap { item =>
        val parts = item.trim.split(" ")
        if (parts.length == 2) Some(parts(1) -> parts(0).toInt) else None
      }.toMap
    }.toList
  }

  def solvePart1(input: List[String]): Int = {
    val maxValues = Map("red" -> 12, "green" -> 13, "blue" -> 14)
    input.indices.flatMap { i =>
      val rounds = parseString(input(i))
      if (rounds.forall(_.forall { case (color, count) => count <= maxValues.getOrElse(color, count) })) Some(i + 1) else None
    }.sum
  }

  def solvePart2(input: List[String]): Int = {
    input.map { game =>
      val rounds = parseString(game)
      val maxCounts = rounds.flatMap(_.toList).groupBy(_._1).mapValues(_.map(_._2).max)
      maxCounts.values.product
    }.sum
  }
}
