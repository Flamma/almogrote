import cats.effect.IO
import scala.io.Source

object FileGrimoireReader {
  def readGrimoire(path: String): IO[Grimoire] = IO {
    val source = Source.fromResource(path)
    val fileContents = source.getLines.mkString("\n")
    source.close()
    GrimoireYamlParser.parseRotes(fileContents).fold(throw _, identity)
  }
}
