class GrimoireFormatter(translator: Translator = Translator(Map.empty)) {
  import translator.translate

  def format(spheres: Spheres): String = {
    spheres
      .toMap
      .filter{ case (k,v) => v > 0}
      .toList
      .map { case (k, v) =>
        f"${translate(k).capitalize} ${List.fill(v)("•").mkString}"
      }
      .mkString(" / ")
  }
  def format(rote: Rote): String = {
    val name = f"${translate(rote.name)}"
    val spheres = f" (${format(rote.spheres)})"
    val description = rote.description.fold("")(d => f": ${translate(d)}")
    val source = rote.source.fold("")(d => f" (${translate(d)})")

    f"$name$spheres$description$source"
  }

  def format(grimoire: Grimoire): String = {
    grimoire.rotes.toList.sorted.map(format).mkString("\n\n")
  }
}
