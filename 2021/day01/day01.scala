import scala.io.BufferedSource

object Main extends App {
  val inputFile: BufferedSource = io.Source.fromResource("input.csv")
  val depthsList: List[Int] = (for (line <- inputFile.getLines()) yield line).toList.map(_.toInt)

  val increasingDepthCount = countDepthIncreases(depthsList)
  println(f"The depth increased $increasingDepthCount times.")

  def countDepthIncreases(depthsList: List[Int]): Int = {
    var count: Int = 0
    for(List(left, right) <- depthsList.sliding(2)) {
      if(left < right) {
        count += 1
      }
    }
    return count
  }
}