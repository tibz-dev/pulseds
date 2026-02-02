package io.pulseds;

import io.pulseds.engine.ConcurrentLoadEngine;
import io.pulseds.engine.Engine;
import io.pulseds.engine.HeartbeatScheduler;
import io.pulseds.events.ConsoleSink;
import io.pulseds.structures.RealTimeAvl;

public class Main {

    public static void main(String[] args) throws Exception {

        ConsoleSink console = new ConsoleSink();

        Engine engine = new Engine(console);
        HeartbeatScheduler heartbeat = new HeartbeatScheduler();

        RealTimeAvl avl = new RealTimeAvl(console);

        // Start engine + heartbeat
        engine.start();
        heartbeat.start(engine, 500);

        // Start concurrent load: 4 worker threads, every 50ms submit 50 inserts
        ConcurrentLoadEngine load = new ConcurrentLoadEngine(4);
        load.start(avl, 50, 50);

        // Run for 5 seconds
        Thread.sleep(5000);

        // Stop all
        load.stop();
        heartbeat.stop();
        engine.stop();
    }
}
