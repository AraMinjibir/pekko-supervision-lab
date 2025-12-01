import org.apache.pekko.actor.typed.ActorSystem

object ShipmentApp extends App {

  val shipmentSystem = ActorSystem[ShipmentCommand](Supervisor(), "ShipmentSystem")

  //First Scenario

  shipmentSystem ! Calculate("56", 12)
  shipmentSystem ! GetState
  Thread.sleep(1000)

  //Second Scenario testing NumberFormatException → crash → restart
  shipmentSystem ! Calculate("ara", 12)

  Thread.sleep(1000)
  // --- Scenario 3: After restart, state resets ---
  shipmentSystem ! GetState  // Should show: volume="N/A", weight=0, price=0.0
  Thread.sleep(1000)


}
