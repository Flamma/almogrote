import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GrimoireTest extends AnyWordSpec with Matchers{
  "filter" should {
    "filter out rotes that require not learned spheres" in {
      val grim = Grimoire(Set(
        Rote("in", Spheres(mind = 2)),
        Rote("out", Spheres(matter = 2)),
      ))

      val spheres = Spheres(mind=2)

      grim.filter(spheres) shouldBe Grimoire(Set(
        Rote("in", Spheres(mind = 2)),
      ))
    }

    "filter out rotes that require higher spheres" in {
      val grim = Grimoire(Set(
        Rote("in", Spheres(mind = 2)),
        Rote("out", Spheres(mind = 3)),
      ))

      val spheres = Spheres(mind = 2)

      grim.filter(spheres) shouldBe Grimoire(Set(
        Rote("in", Spheres(mind = 2)),
      ))
    }

    "not filter out rotes that do not require higher spheres" in {
      val grim = Grimoire(Set(
        Rote("in1", Spheres(mind = 1)),
        Rote("in2", Spheres(mind = 2)),
      ))

      val spheres = Spheres(mind = 2)

      grim.filter(spheres) shouldBe Grimoire(Set(
        Rote("in1", Spheres(mind = 1)),
        Rote("in2", Spheres(mind = 2)),
      ))
    }

  }
}
