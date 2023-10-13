import cats.effect.IO
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.mutable.ListBuffer

class CommanderTest extends AnyWordSpec with Matchers {
  "askForSpheres" should {
    "create Spheres with the input values" in {
      val commander = new SequenceCommandLineCommander(Seq("1", "2", "3", "4", "5", "6", "7", "8", "9"))

      commander.askForSpheres().unsafeRunSync() shouldBe Spheres(1,2,3,4,5,6,7,8,9)
    }
    "create Spheres with 0 value when there is no input for a sphere" in {
      val commander = new SequenceCommandLineCommander(Seq("1", "", "3", "", "5", "", "7", "", "9"))

      commander.askForSpheres().unsafeRunSync() shouldBe Spheres(1, 0, 3, 0, 5, 0, 7, 0, 9)
    }

    "retry when malformed input" in {
      val commander = new SequenceCommandLineCommander(Seq("1", "a", "2", "-1", "3", "11", "4", "", "", "", "", ""))

      val spheres = commander.askForSpheres().unsafeRunSync()

      spheres shouldBe Spheres(1, 2, 3, 4, 0, 0, 0, 0, 0)
    }

  }

  class SequenceCommandLineCommander(commands: Seq[String]) extends CommandLineCommander {
    var i = 0
    val output: ListBuffer[String] = ListBuffer.empty[String]

    override def askInput(message: String): IO[String] =  {
      output.append(message)

      IO{
        val input = commands(i)
        i = (i + 1) % commands.size

        input
      }
    }

    override def printMessage(message: String): IO[Unit] = {
      output.append(message)
      IO(println(message))
    }
  }

}
