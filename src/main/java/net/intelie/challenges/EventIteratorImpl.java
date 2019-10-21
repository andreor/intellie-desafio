package net.intelie.challenges;

import java.util.ArrayList;
import java.util.List;

public class EventIteratorImpl implements EventIterator {

    public EventIteratorImpl(List<Event> eventos) {
        this.eventos = eventos;
    }

    List<Event> eventos;
    int index = 0;

    @Override
    public boolean moveNext() {
        index++;
        return index < eventos.size();


    }

    @Override
    public Event current() {
        if(index == -1 || index >= eventos.size()) {
            return eventos.get(index);
        }
        return null;
    }

    @Override
    public void remove() {
        if(index == -1 || index >= eventos.size()) {
               eventos.remove(index);
        }

    }


    @Override
    public void close() throws Exception {
        System.out.println("Closing resource.");
    }
}
