package memento.Iterator;

import memento.History;

public class ArrayIterator implements Iterator {

    private History history;
    private int index;

    public ArrayIterator(History history){
        this.history = history;
    }

    @Override
    public boolean hasNext() {
        return (index < history.getStates().size());
    }

    @Override
    public Object current() {
        return history.getStates().get(index);
    }

    @Override
    public void next() {
        index++;
    }
}