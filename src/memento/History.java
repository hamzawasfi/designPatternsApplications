package memento;

import memento.Iterator.Iterator;
import memento.Iterator.ListIterator;

import java.util.ArrayList;
import java.util.List;

public class History {
    private List<EditorState> states = new ArrayList<>();

    public void addState(EditorState state){
        states.add(state);
    }

    public EditorState getState(int index){
        return states.get(index);
    }

    public Iterator createIterator(){
        return new ListIterator(this);
    }

    public List<EditorState> getStates() {
        return states;
    }
}
