import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TranslatorTest extends AnyWordSpec with Matchers {
  "translate" should {
    "find translation" in {
      val translator = Translator(Map("term" -> "translated"))

      translator.translate("term") shouldBe "translated"
    }

    "provide default translation" in {
      val translator = Translator(Map.empty)

      translator.translate("term") shouldBe "term"
    }

  }
}
