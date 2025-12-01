import org.apache.pekko.actor.TypedActor.context
import org.apache.pekko.actor.typed.Behavior
import org.apache.pekko.actor.typed.scaladsl.Behaviors

object ShipmentActor {

  def apply(): Behavior[ShipmentCommand] = Behaviors.setup{context =>
    var state = ShipmentState("N/A", 0, 0.0)

    Behaviors.receiveMessage{
      case Calculate(volume, weight) =>
        val computed = volume.toInt * weight //Crashes on bad input
        state = state.copy(volume = volume, weight = weight, price = computed)
        context.log.info(s"Updated state: $state")
        Behaviors.same

      case GetState =>
        context.log.info(s"Current state: $state")
        Behaviors.same
    }
  }

}
