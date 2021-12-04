import scala.io.BufferedSource

object Main extends App {
  val inputFile: BufferedSource = io.Source.fromFile("input.csv")
  val binaryList: List[String]= (for (line <- inputFile.getLines()) yield line).toList

  val binaryGroupByIndex = binaryList.map(
    binaryString => binaryString.zipWithIndex.map{
      case(num, i) => (i, num)
  }).flatten.groupBy(_._1)
}