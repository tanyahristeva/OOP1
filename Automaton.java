import  java.util.*;
public class Automaton {
    private int id;
    private Set <Character> alphabet;
    private Set<State> states;
    private State startState;
    private Set<State> finalStates;
    private Set<Transition> transitions;
    private TransitionManager transitionManager;


    public Automaton(int id, Set<Character> alphabet, Set<State> states, State startState, Set<State> finalStates, Set<Transition> transitions, TransitionManager transitionManager) {
        this.id = id;
        this.alphabet = new HashSet<>(alphabet);
        this.states = new HashSet<>(states);
        this.startState = startState;
        this.finalStates = new HashSet<>(finalStates);
        this.transitions = new HashSet<>(transitions);
        this.transitionManager=transitionManager;
    }
    public Set<State> getStates() {
        return states;
    }

    public int getId() {
        return id;
    }

    public Set<Transition> getTransitions() {
        return transitionManager.getTransitions();
    }

    public Automaton(Set<Character> alphabet){
        if(!AlphabetValidator.isValidAlphabet(alphabet)){
            throw new IllegalArgumentException("Невалидна азбука. Позволените символи са между a и z и от 0 до 9.");
        }
        this.alphabet=alphabet;
    }

    public void addTransition(Transition transition){
        transitionManager.addTransition(transition);
    }
}
