import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GrimoireYamlParserTest extends AnyWordSpec with Matchers {
  "YamlRoteReader" should {
    "parse rotes string" in {
      val expected = Grimoire(Set(
        Rote("Absorber daño agravado", Spheres(life = 3)),
        Rote("Adaptarse a un entorno", Spheres(life = 2)),
        Rote("Adaptar a otro a un entorno", Spheres(life = 3)),
      ))

      val result = GrimoireYamlParser.parseRotes(
        """
          |rotes:
          |  - name: Absorber daño agravado
          |    spheres:
          |      life: 3
          |  - name: Adaptarse a un entorno
          |    spheres:
          |      life: 2
          |  - name: Adaptar a otro a un entorno
          |    spheres:
          |      life: 3
          |""".stripMargin)

      result.fold({ e => println("MAL")
        throw e
      }, _ shouldBe expected)
    }
    "parse combination spheres rotes" in {
      val expected = Grimoire(Set(
        Rote("Animar cadaver o partes", Spheres(life = 2, prime = 2)),
        Rote("Animar huesos y restos", Spheres(matter = 2, prime = 2))
      ))

      val result = GrimoireYamlParser.parseRotes(
        """
          |rotes:
          |  - name: Animar cadaver o partes
          |    spheres:
          |      life: 2
          |      prime: 2
          |  - name: Animar huesos y restos
          |    spheres:
          |      matter: 2
          |      prime: 2
          |
          |""".stripMargin)

      result.fold({ e =>
        println("MAL")
        throw e
      }, _ shouldBe expected)
    }

    "parse rotes with description" in {
      val expected = Grimoire(Set(
        Rote("Ilusiones físicas", Spheres(forces = 2, prime = 2), Some("admite mayor nivel de esfera (ver manual)")),
        Rote("Ilusiones envolventes", Spheres(forces = 4, mind = 4, prime = 4), Some("admite mayor nivel de esfera (ver manual)")),
      ))

      val result = GrimoireYamlParser.parseRotes(
        """
          |rotes:
          |  - name: Ilusiones físicas
          |    spheres:
          |      forces: 2
          |      prime: 2
          |    description: admite mayor nivel de esfera (ver manual)
          |  - name: Ilusiones envolventes
          |    spheres:
          |      forces: 4
          |      mind: 4
          |      prime: 4
          |    description: admite mayor nivel de esfera (ver manual)
          |""".stripMargin)

      result.fold({ e =>
        println("MAL")
        throw e
      }, _ shouldBe expected)
    }
  }
}
