import java.io.Serializable;
import  java.util.*;
public class Automaton implements Serializable {
    private static final long serialVersionUID=1L;
    private int id;
    private Set <Character> alphabet;
    private Set<State> states;
    private State startState;
    private Set<State> finalStates;
    private TransitionManager transitionManager;


    public Automaton(Set<Character> alphabet, Set<State> states, State startState, Set<State> finalStates, TransitionManager transitionManager) {
        this.alphabet = new HashSet<>(alphabet);
        this.states = new HashSet<>(states);
        this.startState = startState;
        this.finalStates = new HashSet<>(finalStates);
        this.transitionManager=transitionManager;

    }
    public Set<State> getStates() {
        return states;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Transition> getTransitions() {
        return transitionManager.getTransitions();
    }

    public Set<Character> getAlphabet() {
        return alphabet;
    }

    public State getStartState() {
        return startState;
    }

    public void setStartState(State startState) {
        this.startState = startState;
    }

    public void setFinalStates(Set<State> finalStates) {
        this.finalStates = finalStates;
    }

    public Set<State> getFinalStates() {
        return finalStates;
    }

    public Automaton(Set<Character> alphabet){
        if(!AlphabetValidator.isValidAlphabet(alphabet)){
            throw new IllegalArgumentException("Невалидна азбука. Позволените символи са между a и z и от 0 до 9.");
        }

        this.alphabet = new HashSet<>(alphabet);
        this.states = new HashSet<>();
        this.finalStates = new HashSet<>();
        this.transitionManager = new TransitionManager();
    }

    public void addTransition(Transition transition) {
        transitionManager.addTransition(transition);
    }

    public TransitionManager getTransitionManager() {
        return transitionManager;
    }
}
