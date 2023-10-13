import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SpheresTest extends AnyWordSpec with Matchers {
  "toMap" should {
    "produce a map" in {
      Spheres(1,2,3,4,5,6,7,8,9).toMap shouldBe Map(
        "correspondence" -> 1,
        "entropy" -> 2,
        "forces" -> 3,
        "life" -> 4,
        "matter" -> 5,
        "mind" -> 6,
        "prime" -> 7,
        "spirit" -> 8,
        "time" -> 9,
      )
    }
  }

  "fromMap" should {
    "produce Spheres" in {
      Spheres.fromMap(Map(
        "correspondence" -> 1,
        "entropy" -> 2,
        "forces" -> 3,
        "life" -> 4,
        "matter" -> 5,
        "mind" -> 6,
        "prime" -> 7,
        "spirit" -> 8,
        "time" -> 9,
      )) shouldBe Spheres(1,2,3,4,5,6,7,8,9)
    }
  }
}
