import org.apache.pekko.actor.typed.{Behavior, SupervisorStrategy}
import org.apache.pekko.actor.typed.scaladsl.Behaviors

object Supervisor {

  def apply():Behavior[ShipmentCommand] =
    Behaviors.supervise(ShipmentActor()).onFailure[NumberFormatException](
    SupervisorStrategy.restart
  )
}
