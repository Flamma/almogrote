import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GrimoireFormatterTest extends AnyWordSpec with Matchers {
  "format (spheres)" should {
    "create a string for spheres higher than 0" in {
      val formatter = GrimoireFormatter()
      formatter.format(Spheres(mind = 2, life = 3)) shouldBe "Mind •• / Life •••"
    }

    "translate sphere names" in {
      val translator = Translator(Map("mind" -> "Mente", "life" -> "Vida"))
      val formatter = GrimoireFormatter(translator)
      formatter.format(Spheres(mind = 2, life = 3)) shouldBe "Mente •• / Vida •••"
    }
  }

  "format (rotes)" should {
    "create a string for a rote without description" in {
      val rote = Rote("Rote name", Spheres(mind=2, life=3))
      val expected = f"Rote name (Mind •• / Life •••)"

      val formatter = GrimoireFormatter()
      formatter.format(rote) shouldBe expected
    }
    "create a string for a rote with description" in {
      val rote = Rote("Rote name", Spheres(mind = 2, life = 3), Some("This is the description..."))
      val expected = f"Rote name (Mind •• / Life •••): This is the description..."

      val formatter = GrimoireFormatter()
      formatter.format(rote) shouldBe expected
    }



  }

  "format (grimoire)" should {
    "create a string with the rotes in increasing power order" in {
      val grim = Grimoire(Set(
        Rote("Middle spell", Spheres(correspondence=2, time=2), Some("This spell is not so bad.")),
        Rote("Powerful spell", Spheres(correspondence=2, time=2, mind=5), Some("This spell is bananas.")),
        Rote("Weak spell", Spheres(correspondence=1), Some("This spell is weak.")),
      ))
      val expected =
        """Weak spell (Correspondence •): This spell is weak.
          |
          |Middle spell (Correspondence •• / Time ••): This spell is not so bad.
          |
          |Powerful spell (Correspondence •• / Mind ••••• / Time ••): This spell is bananas.""".stripMargin

      val formatter = GrimoireFormatter()
      formatter.format(grim) shouldBe expected

    }

    "tranlate shpere names" in {
      val grim = Grimoire(Set(
        Rote("Middle spell", Spheres(correspondence = 2, time = 2), Some("This spell is not so bad.")),
        Rote("Powerful spell", Spheres(correspondence = 2, time = 2, mind = 5), Some("This spell is bananas.")),
        Rote("Weak spell", Spheres(correspondence = 1), Some("This spell is weak.")),
      ))
      val expected =
        """Weak spell (Correspondencia •): This spell is weak.
          |
          |Middle spell (Correspondencia •• / Tiempo ••): This spell is not so bad.
          |
          |Powerful spell (Correspondencia •• / Mente ••••• / Tiempo ••): This spell is bananas.""".stripMargin

      val translator = Translator(Map("correspondence" -> "Correspondencia", "mind" -> "Mente", "time" -> "Tiempo"))
      val formatter = GrimoireFormatter(translator)
      formatter.format(grim) shouldBe expected
    }
  }
}
