import io.circe.{Json, ParsingFailure}
import io.circe.generic.auto._
import io.circe.yaml.parser

object GrimoireYamlParser {
  def parseRotes(input: String): Either[io.circe.Error, Grimoire] = {
    parser.parse(input)
    .flatMap { json =>
      json.as[YGrimoire].map(_.toGrimoire)
    }
  }


  case class YRote(
    name: String,
    spheres: Map[String, Int],
    description: Option[String] = None,
    source: Option[String] = None,
  ) {
    def toRote: Rote = {
      def toSpheres(map: Map[String, Int]): Spheres = Spheres (
        correspondence = map.getOrElse("correspondence", 0),
        forces = map.getOrElse("forces", 0),
        entropy = map.getOrElse("entropy", 0),
        life = map.getOrElse("life", 0),
        matter = map.getOrElse("matter", 0),
        mind = map.getOrElse("mind", 0),
        prime = map.getOrElse("prime", 0),
        spirit = map.getOrElse("spirit", 0),
        time = map.getOrElse("time", 0),
      )

      Rote(name, toSpheres(spheres), description, source)
    }
  }

  case class YGrimoire(rotes: Set[YRote]) {
    def toGrimoire: Grimoire = Grimoire(rotes.map(_.toRote))
  }

}
