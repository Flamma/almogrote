case class Translator(translation: Map[String, String]) {
  def translate(key: String): String = translation.getOrElse(key, key)
}
