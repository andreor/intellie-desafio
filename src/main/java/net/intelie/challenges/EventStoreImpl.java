package net.intelie.challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EventStoreImpl implements EventStore {

    private static List<Event> eventos = new ArrayList<>();

    public EventStoreImpl() {

        eventos = Collections.synchronizedList(new ArrayList<Event>());
    }


    @Override
    public void insert(Event event) {
        eventos.add(event);
    }

    @Override
    public void removeAll(String type) {
        eventos.removeIf(event -> event.type().equalsIgnoreCase(type));
    }


    @Override
    public EventIterator query(String type, long startTime, long endTime) {

        List<Event> filteredEvents = Collections
                .synchronizedList(eventos.stream().filter(eventList -> eventList.type().equals(type))
                        .filter(eventList -> eventList.timestamp() >= startTime)
                        .filter(eventList -> eventList.timestamp() < endTime).collect(Collectors.toList()));
        return new EventIteratorImpl(filteredEvents);
    }
}
