import scala.io.BufferedSource

object Main extends App {
  val inputFile: BufferedSource = io.Source.fromFile("input.csv")
  val commandList: List[String] = (for (line <- inputFile.getLines()) yield line).toList

  val (subX, subY) = processCommands(commandList)
  println("Horizontal and vertical product: %d" format subX * subY)
  val (subWithAimX, subWithAimY, _) = processCommandsWithAim(commandList)
  println("Horizontal and vertical product with aim: %d" format subWithAimX * subWithAimY)

  def processCommands(commandList: List[String]): (Int, Int) = {
    var pos = (0, 0)
    for(command <- commandList) {
      val splitCommand = (command.split(" ")(0), command.split(" ")(1).toInt)
      pos = movePos(pos, splitCommand)
    }
    return pos
  }

  def movePos(pos: (Int, Int), command: (String, Int)): (Int, Int) = {
    val (x, y) = pos
    command._1 match {
      case "up" => (x, y - command._2)
      case "down" => (x, y + command._2)
      case "forward" => (x + command._2, y)
    }
  }

  def processCommandsWithAim(commandList: List[String]): (Int, Int, Int) = {
    var pos = (0, 0, 0)
    for(command <- commandList) {
      val splitCommand = (command.split(" ")(0), command.split(" ")(1).toInt)
      pos = movePosWithAim(pos, splitCommand)
    }
    return pos
  }

  def movePosWithAim(pos: (Int, Int, Int), command: (String, Int)): (Int, Int, Int) = {
    val (x, y, z) = pos
    command._1 match {
      case "up" => (x, y, z - command._2)
      case "down" => (x, y, z + command._2)
      case "forward" => (x + command._2, y + (z * command._2), z)
    }
  }
}