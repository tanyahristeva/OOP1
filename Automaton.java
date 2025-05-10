import java.io.Serializable;
import  java.util.*;
public class Automaton implements Serializable {
    private static final long serialVersionUID=1L;
    private int id;
    private Set <Character> alphabet;
    private Set<State> states;
    private State startState;
    private Set<State> finalStates;
    //private Set<Transition> transitions;
    private TransitionManager transitionManager;


    public Automaton(Set<Character> alphabet, Set<State> states, State startState, Set<State> finalStates, TransitionManager transitionManager) {
        this.alphabet = new HashSet<>(alphabet);
        this.states = new HashSet<>(states);
        this.startState = startState;
        this.finalStates = new HashSet<>(finalStates);
       // this.transitions = transitionManager.getTransitions();
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

    public Set<Transition> getTransitions() {
        return transitionManager.getTransitions();
    }

    public State getStartState() {
        return startState;
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

    public void addTransition(Transition transition){
        transitionManager.addTransition(transition);
    }

    public TransitionManager getTransitionManager() {
        return transitionManager;
    }
}
