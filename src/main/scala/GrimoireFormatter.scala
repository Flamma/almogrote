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
  def format(rote: Rote): String =
    f"${translate(rote.name)} (${format(rote.spheres)})${rote.description.fold("")(d => f": ${translate(d)}")}"

  def format(grimoire: Grimoire): String = {
    grimoire.rotes.toList.sorted.map(format).mkString("\n\n")
  }
}
