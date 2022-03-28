import scala.io.StdIn.readLine

class translator {

  val l = List(("ksiazka", "book"), ("drzwi", "door"), ("okno", "window"))
  def askingLoop()={
    var x = ""
    while(x != "q"){
      askForTranslation(l)
      x = readLine("Do u want to exit? press q to exit: ")
    }
  }
  def askForTranslation(ls: List[(String, String)]) = {
    var score = 0

    val wordList = ls.foldLeft(Map.empty[String, Int]) {
      (z,w) => {
        val answer = readLine("give translation of " + w._1 + " ")
        if (answer == w._2) {
          score += 1
          z + (w._2 -> (z.getOrElse(answer,0)+1))
        } else {
          println(s"wrong answer $answer, correct answer is: ${w._2}")
          z
        }
      }
    }
   // println(s"final score: $score / ${ls.length}")
    wordList.foreach(println)
  }
}