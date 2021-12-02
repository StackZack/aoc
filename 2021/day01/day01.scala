import scala.io.BufferedSource

object Main extends App {
  val inputFile: BufferedSource = io.Source.fromFile("input.csv")
  val depthsList: List[Int] = (for (line <- inputFile.getLines()) yield line).toList.map(_.toInt)

  val increasingDepthCount = countDepthIncreases(depthsList)
  println(f"The depth increased $increasingDepthCount times.")

  val increasingDepthSumCount = countDepthSumIncreases(depthsList)
  println(f"The depth sum increased $increasingDepthSumCount times.")

  def countDepthIncreases(depthsList: List[Int]): Int = {
    var count: Int = 0
    for(List(left, right) <- depthsList.sliding(2)) {
      if(left < right) {
        count += 1
      }
    }
    return count
  }

  def countDepthSumIncreases(depthsList: List[Int]): Int = {
    var count: Int = 0
    var prevWindow: Int = 0
    for(depthWindow <- depthsList.sliding(3)) {
      if(prevWindow != 0 && depthWindow.sum > prevWindow) {
        count += 1
      }
      prevWindow = depthWindow.sum
    }
    return count
  }
}