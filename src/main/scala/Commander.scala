import cats.effect.IO
import cats.implicits.*

import scala.annotation.tailrec
import scala.io.StdIn
import scala.util.Try

trait Commander {
  def askForSpheres(): IO[Spheres]
}

trait CommandLineCommander extends Commander {
  def askInput(message: String): IO[String]
  def printMessage(message: String): IO[Unit]

  def validateSphereValue(value: String): Option[Int] = {
    val intOption = value match {
      case "" => Some(0)
      case v => Try(v.toInt).toOption
    }
    intOption.filter(i => i >= 0 &&  i <= 10)
  }

  private def askSphere(sphereName: String): IO[Int] = {
    askInput(sphereName).flatMap{ input =>
      val intOption = validateSphereValue(input)
      if (intOption.isDefined)
        IO(intOption.get)
      else
        askSphere((sphereName))
    }
  }

  override def askForSpheres(): IO[Spheres] = for {
    _       <- printMessage("Introduce las esferas (deja vacío para el valor 0)")
    spheres <- askEachSphere()
  } yield spheres

  def askEachSphere(): IO[Spheres] = List(
      "correspondence",
      "entropy",
      "forces",
      "life",
      "matter",
      "mind",
      "prime",
      "spirit",
      "time",
    ).map { sphereName =>
      askSphere(sphereName).map((sphereName, _))
    }
    .sequence.map{ list =>Spheres.fromMap(list.toMap)}
}

object StdCommandLineCommander extends CommandLineCommander {
  override def askInput(message: String): IO[String] = IO {
    print(f"$message: ")
    StdIn.readLine()
  }

  override def printMessage(message: String): IO[Unit] = IO(println(message))
}