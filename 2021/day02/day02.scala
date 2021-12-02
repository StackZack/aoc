import scala.io.BufferedSource

object Main extends App {
  val inputFile: BufferedSource = io.Source.fromFile("input.csv")
  val commandList: List[String] = (for (line <- inputFile.getLines()) yield line).toList

  val submarinePos = processCommands(commandList)
  println("Horizontal and vertical product: %d" format submarinePos._1 * submarinePos._2)

  def processCommands(commandList: List[String]): (Int, Int) = {
    var pos = (0, 0)
    for(command <- commandList) {
      val splitCommand = (command.split(" ")(0), command.split(" ")(1).toInt)
      pos = movePos(pos, splitCommand)
    }
    return pos
  }

  def movePos(pos: (Int, Int), command: (String, Int)): (Int, Int) = {
    command._1 match {
      case "up" => (pos._1, pos._2 - command._2)
      case "down" => (pos._1, pos._2 + command._2)
      case "forward" => (pos._1 + command._2, pos._2)
    }
  }
}