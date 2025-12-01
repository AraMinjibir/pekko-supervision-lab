# Pekko Supervision Lab
A mini project demonstrating fault tolerance, supervision strategies, and state recovery in Apache Pekko Typed.

This project implements a simplified “Shipment Cost Calculator” actor that may crash when invalid data is sent. A supervisor wraps the actor using `Behaviors.supervise` with a `restart` strategy to ensure controlled recovery while preventing restart loops.

This lab is part of a broader Distributed Systems learning roadmap (Pekko + Kafka + Play Framework).

---

## Features

### 1. Faulty Child Actor
The `ShipmentActor`:
- Stores mutable internal state (`ShipmentState`)
- Calculates price using `volume.toInt * weight`
- Crashes intentionally on invalid volume (e.g., `"abc".toInt`)
- Exposes a `GetState` command for debugging

### 2. Supervision Logic
The supervisor:
- Catches `NumberFormatException`
- Applies **Restart** strategy
- Ensures failed messages are **dropped safely**
- Recreates actor from its **initial state**, running `preStart` again

### 3. Clean Restart Behavior
After restart:
- Internal state resets (`volume = "N/A", weight = 0, price = 0.0`)
- System continues receiving messages normally
- No restart loops occur

### 4. Demonstration Main App
The `ShipmentApp` demonstrates:
1. Valid calculation
2. Crash caused by bad input
3. Restart and clean state recovery

All logs clearly show the lifecycle transitions.

---

## Why This Project Matters

This lab demonstrates real production patterns used in Distributed Systems:

- Crash-only design
- Supervisor-driven reliability
- Safe message dropping
- State reset after failure
- Separation of “work” and “recovery” responsibilities

These concepts are fundamental for building large-scale, resilient microservices with Pekko actors.

---


---

## How to Run

1. Install JDK 11 or higher.
2. Clone the repository:  git@github.com:AraMinjibir/pekko-supervision-lab.git
cd pekko-supervision-lab
3. Run with sbt:

