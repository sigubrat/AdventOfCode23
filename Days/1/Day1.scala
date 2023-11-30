object Day1 {
  def main(args: Array[String]): Unit = {
    val input = scala.io.Source.fromFile("input.txt").getLines().toList

    // Part 1
    val part1 = solvePart1(input)
    println(s"Part 1: $part1")

    // Part 2
    val part2 = solvePart2(input)
    println(s"Part 2: $part2")
  }

  def solvePart1(input: List[String]): Int = {
    // Your solution for Part 1 goes here
    // Example: input.map(_.toInt).sum
    0
  }

  def solvePart2(input: List[String]): Int = {
    // Your solution for Part 2 goes here
    // Example: input.flatMap(_.split(",")).filter(_.nonEmpty).size
    0
  }
}