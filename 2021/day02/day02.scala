import scala.io.BufferedSource

object Main extends App {
  val inputFile: BufferedSource = io.Source.fromFile("input.csv")
  val commandList: List[String] = (for (line <- inputFile.getLines()) yield line).toList

  val (posX, posY) = sumCommands(commandList)
  println("Horizontal and vertical product: %d" format posX * posY)
  val (posWithAimX, posWithAimY, _) = sumCommandsWithAim(commandList)
  println("Horizontal and vertical product with aim: %d" format posWithAimX * posWithAimY)

  def sumCommands(commandList: List[String]): (Int, Int) = {
    val commandListSplit = commandList.map(command => (command.split(" ")(0), command.split(" ")(1).toInt))
    val positions = commandListSplit.map(command => movePos((command._1, command._2)))
    return positions.foldLeft(0, 0)((sumPos, pos) => (sumPos._1 + pos._1, sumPos._2 + pos._2))
  }

  def movePos(command: (String, Int)): (Int, Int) = {
    val (direction, distance) = command
    (direction, distance) match {
      case ("up", _) => (0, 0 - distance)
      case ("down", _) => (0, 0 + distance)
      case ("forward", _) => (0 + distance, 0)
    }
  }

  def sumCommandsWithAim(commandList: List[String]): (Int, Int, Int) = {
    var pos = (0, 0, 0)
    for(command <- commandList) {
      val splitCommand = (command.split(" ")(0), command.split(" ")(1).toInt)
      pos = movePosWithAim(pos, splitCommand)
    }
    return pos
  }

  def movePosWithAim(pos: (Int, Int, Int), command: (String, Int)): (Int, Int, Int) = {
    val (x, y, z) = pos
    val (direction, distance) = command
    (direction, distance) match {
      case ("up", _) => (x, y, z - distance)
      case ("down", _) => (x, y, z + distance)
      case ("forward", _) => (x + distance, y + (z * distance), z)
    }
  }
}