class PrintToScreen extends Printable {

  override def print(): Either[String, String] = Right("Beautiful printing")
}
