case class Rote(
  name: String,
  spheres: Spheres,
  description: Option[String] = None,
  source: Option[String] = None,
)

object Rote {
  implicit val ordering: Ordering[Rote] = Ordering.by[Rote, Int](_.spheres.toMap.map(_._2).sum)
}

case class Spheres(
  correspondence: Int = 0,
  entropy: Int = 0,
  forces: Int = 0,
  life: Int = 0,
  matter: Int = 0,
  mind: Int = 0,
  prime: Int = 0,
  spirit: Int = 0,
  time: Int = 0
) {
  def toMap: Map[String, Int] = Map(
    "correspondence" -> correspondence,
    "entropy" -> entropy,
    "forces" -> forces,
    "life" -> life,
    "matter" -> matter,
    "mind" -> mind,
    "prime" -> prime,
    "spirit" -> spirit,
    "time" -> time,
  )
}

object Spheres {
  def fromMap(map: Map[String, Int]): Spheres = Spheres (
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
}
