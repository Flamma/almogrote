import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FileTranlatorReaderTest extends AnyWordSpec with Matchers {
  "config file" should {
    "be read and parsed" in {
      val translator = FileTranslationReader.read("es").unsafeRunSync()
      translator.translate("correspondence") shouldBe "Correspondencia"
    }
  }
}
