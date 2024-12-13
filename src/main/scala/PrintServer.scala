case class PrintServer(name:String,printersList:List[Printable]) {
  def knowPrinting(p:Printable):Boolean = p.print() match {
    case Left(_) => false
    case _ => true
  }
  def reachPrinters():String= {
    val onlineOkPrinters: List[Printable] = printersList.partition(knowPrinting)._1
    def collectPartialFunction:PartialFunction[Printable,String] ={
      case p:Printable => p.print().getOrElse("")
    }
    val outputs = onlineOkPrinters.collect(collectPartialFunction)
    outputs.foldRight("")(_ + _)
  }
}
