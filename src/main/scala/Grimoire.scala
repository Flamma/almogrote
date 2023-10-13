case class Grimoire(rotes: Set[Rote]) {
  def filter(spheres: Spheres): Grimoire = Grimoire(
    rotes.filter(rote =>
      rote.spheres.correspondence <= spheres.correspondence &&
      rote.spheres.entropy <= spheres.entropy &&
      rote.spheres.forces <= spheres.forces &&
      rote.spheres.life <= spheres.life &&
      rote.spheres.matter <= spheres.matter &&
      rote.spheres.mind <= spheres.mind &&
      rote.spheres.prime <= spheres.prime &&
      rote.spheres.spirit <= spheres.spirit &&
      rote.spheres.time <= spheres.time
    )
  )
}
