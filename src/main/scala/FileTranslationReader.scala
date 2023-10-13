import cats.effect.IO
import io.circe.yaml.parser
import io.circe.generic.auto._

import scala.io.Source

object FileTranslationReader {
  def read(languageCode: String): IO[Translator] = IO {
    val source = Source.fromResource(f"i18n/$languageCode.yaml")
    val fileContents = source.getLines.mkString("\n")
    source.close()
    parseTranslator(fileContents).fold(throw _, identity)
  }

  private def parseTranslator(input: String): Either[io.circe.Error, Translator] = {
    parser.parse(input)
      .flatMap { json =>
        json.as[Translator]
      }
  }
}
