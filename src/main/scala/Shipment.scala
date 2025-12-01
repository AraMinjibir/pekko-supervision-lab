final case class ShipmentState(volume: String, weight: Int, price: Double)

sealed trait ShipmentCommand
final case class Calculate(volume: String, weight: Int) extends ShipmentCommand
case object GetState extends ShipmentCommand
