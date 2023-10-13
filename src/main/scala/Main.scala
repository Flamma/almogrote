import cats.effect.IO

object Main extends App {
  def run(cmd: Commander): IO[String] = {
    for {
      translator <- FileTranslationReader.read("es")
      grim <- FileGrimoireReader.readGrimoire("rotes.yaml")
      spheres <- cmd.askForSpheres()
    } yield {
      val formatter = GrimoireFormatter(translator)
      formatter.format(grim.filter(spheres))
    }
  }

  val output = run(StdCommandLineCommander).unsafeRunSync()
  println(output)
}
