# PulseDS — Real-Time Data Structure Engine (Java)

PulseDS is a **real-time, event-driven engine** that implements **core data structures from scratch** and exposes their internal behavior under **concurrent load**.

The project focuses on **correctness, invariants, and concurrency**, connecting **academic data-structure theory** with **real-world systems behavior**.

---

## Motivation

In university coursework, data structures are often:
- implemented once,
- tested with static inputs,
- and assumed correct if they compile.

PulseDS was built to go further by answering:

> **What actually happens to a data structure under real-time, concurrent mutation?**

This project allowed me to apply concepts learned in data structures and systems modules and observe how they behave under live execution and contention.

---

## Key Features

### Hand-Written AVL Tree
- Implemented entirely from scratch (no Java Collections internally)
- Manual rotations (LL, RR, LR, RL)
- Height tracking and balance factor logic
- Explicit duplicate handling

### Runtime Invariant Enforcement
After every mutation, the AVL tree validates:
- Binary Search Tree ordering
- AVL balance constraint (|balance factor| ≤ 1)
- Height correctness

Invariant violations are reported as runtime events instead of crashing the system.

### Event-Driven Architecture
Every significant operation emits structured events:
- Node creation
- Tree rotations
- Insert operations
- Invariant violations
- Engine heartbeat

This allows the internal behavior of the data structure to be observed in real time.

### Real-Time Execution
- Continuous engine heartbeat
- Operations execute while the system is running
- Event streams reflect live structural changes

### Concurrent Load Simulation
- Multiple worker threads perform inserts concurrently
- Thread safety enforced at the data structure boundary
- Demonstrates correctness under contention

---

## Architecture Overview

engine/  
├── Engine — lifecycle & heartbeat  
├── HeartbeatScheduler — real-time pulse  
└── ConcurrentLoadEngine — multi-threaded stress testing  

structures/  
├── RealTimeAvl — AVL tree implementation  
├── AvlNode — raw node structure  
└── AvlInvariantChecker — correctness enforcement  

events/  
├── EventType — single source of truth  
├── DsEvent — structured event model  
└── ConsoleSink — live event output  

---

## How to Run

### Requirements
- Java 17+
- Maven

### Build
mvn clean compile

### Run
java -cp target/classes io.pulseds.Main

### Sample Output
AVL | NODE_CREATE | Created node 42  
AVL | ROTATE_R | Right rotation at 60  
AVL | INSERT | Inserted 17  
ENGINE | HEARTBEAT | Pulse  

If a structural issue occurs:
AVL | VIOLATION | AVL_BALANCE | Node 30 has balanceFactor=2

---

## What This Project Demonstrates

- Strong understanding of tree-based data structures  
- Ability to translate theoretical concepts into correct systems code  
- Practical handling of concurrency and synchronization  
- Use of invariants to enforce correctness at runtime  
- Clean, extensible engine-style architecture  

---

## Technologies & Concepts

- Java 17  
- Maven  
- AVL Trees  
- Invariant Checking  
- Event-Driven Design  
- Concurrency & Synchronization  
- Real-Time Execution  

---
