import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FileGrimoireReaderTest extends AnyWordSpec with Matchers {
  "config file" should {
    "be read and parsed" in {
      FileGrimoireReader.readGrimoire("rotes.yaml").unsafeRunSync()
    }
  }

}
