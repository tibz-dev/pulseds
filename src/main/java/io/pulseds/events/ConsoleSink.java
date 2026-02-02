package io.pulseds.events;

public class ConsoleSink implements EventSink 
{

    @Override
    public void publish(DsEvent e) 
    {
        System.out.printf(
                "%d | t=%d | %s | %s | %s%n",
                e.timestampMs(),
                e.threadId(),
                e.source(),
                e.type(),
                e.message()
        );
    }
}
