import scala.io.Source
import scala.io.StdIn.readLine

class translator {

  val filePath = "C:\\Users\\g.olko\\IdeaProjects\\language-teacher\\dictionary"
  val listOfWords: List[String] = Source.fromFile(filePath)
    .getLines()
    .flatMap(_.split("\\W+")).toList
  val dictionary = listOfWords.foldLeft(Map.empty[String,String]){(z,w)=>
    if(listOfWords.indexOf(w)%2 == 0){
      z + (w -> listOfWords(listOfWords.indexOf(w)+1))
    }else{
      z
    }
  }
  var globalScore = Map.empty[String,Int]
  val l = List(("ksiazka", "book"), ("drzwi", "door"), ("okno", "window"))
  def askingLoop()={
    var x = ""
    while(x != "q"){
      val gameScore = askForTranslation(dictionary.toList)
      globalScore =  gameScore.foldLeft(globalScore){(globalScore,w) =>
       globalScore + (w._1 -> (globalScore.getOrElse(w._1, 0) + w._2))
      }
      globalScore.foreach(println)
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
    //wordList.foreach(println)
    wordList
  }
}