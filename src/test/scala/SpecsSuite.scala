import org.scalatest._
import flatspec._
import matchers._

import scala.collection.immutable

/**
 * a tests suite with ScalaTest using BDD style!!
 * use trait Matchers and extendd AntFlatSpec
 */
class SpecsSuite extends AnyFlatSpec with should .Matchers {

  "empty Printers List" should "print nothing!!" in {
    val noPrintersFound: List[Printable] = List.empty
    val printServer = PrintServer("empty", noPrintersFound)
    printServer.reachPrinters() should be(empty)
  }

  "Printers List with failed printers" should  "print nothing" in {
    val koPrintersList : List[Printable] = List(new UnableToPrint(),new UnableToPrint())
    val printServer = PrintServer("ko", koPrintersList)
    printServer.reachPrinters() should be (empty)
  }

  "Printers List with one OK Printer" should "print a message containing beautiful" in {
    val nonEmptyPrintersList: List[Printable] = List(new PrintToScreen())
    val printServer = PrintServer("OK", nonEmptyPrintersList)
    printServer.reachPrinters() should be("Beautiful printing")
  }

  "Printting with a Printers List with  one OK Printer" should "contain a single Right element" in {
    val nonEmptyPrintersList: List[Printable] = List(new PrintToScreen())
    val printServer = PrintServer("OK", nonEmptyPrintersList)
    printServer.printersList.map(p =>p.print()) should be (List(Right("Beautiful printing")))
  }


  "Printing with at least a Printers List with  one OK Printer" should "return a non empty list of right objects" in {
    val nonEmptyPrintersList: List[Printable] =
      List(new UnableToPrint(),new  PrintToScreen(),
        new UnableToPrint(),new PrintToScreen())
    val printServer = PrintServer("OK", nonEmptyPrintersList)
    def knowPrinting(p:Printable):Boolean = p.print() match {
      case Left(_) => false
      case _ => true
    }
    printServer.printersList.partition(knowPrinting)._1 should not be(empty)
  }
}
