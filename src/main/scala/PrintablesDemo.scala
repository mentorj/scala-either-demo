object PrintablesDemo extends App {
  val objectsToPrint :List[Printable] =
    List(new UnableToPrint(),
      new PrintToScreen(),
      new UnableToPrint()
    )

  val extractPrintables : PartialFunction[Printable,String] = {
    case p:Printable if  (p.print().isRight)
        => p.print().getOrElse("Boum")
    }

  val printableObjects : List[String] = objectsToPrint.collect(extractPrintables)

  def knowPrinting(p:Printable):Boolean = p.print() match {
    case Left(_) => false
    case _ => true
  }

  val nonPrintableObjects: List[Printable] = objectsToPrint.partition(knowPrinting)._2
  println(printableObjects)
  println(nonPrintableObjects)

}
