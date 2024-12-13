class UnableToPrint extends Printable {

  override def print(): Either[String, String] = Left("Don't know how to print")


}
