import  java.util.*;
public class Automaton {
    private int id;
    private Set <Character> alphabet;
    private Set<State> states;
    private State startState;
    private Set<State> finalStates;
    private Set<Transition> transitions;

    public Automaton(int id, Set<Character> alphabet, Set<State> states, State startState, Set<State> finalStates, Set<Transition> transitions) {
        this.id = id;
        this.alphabet = new HashSet<>(alphabet);
        this.states = new HashSet<>(states);
        this.startState = startState;
        this.finalStates = new HashSet<>(finalStates);
        this.transitions = new HashSet<>(transitions);
    }
}
