import java.util.HashSet;
import java.util.Set;

public class RecognizeChecker {
    private TransitionManager transitionManager;
    private State startState;
    private Set<State> finalStates;

    public RecognizeChecker(TransitionManager transitionManager, State startState, Set<State> finalStates) {
        this.transitionManager = transitionManager;
        this.startState = startState;
        this.finalStates = finalStates;
    }

    public Set<State> getEpsilonReachableStates(Set<State> states) {
    Set<State> epsilonReachableStates=new HashSet<>(states);
    boolean changed;

    do{
        changed=false;
        Set<State> newStates=new HashSet<>(epsilonReachableStates);
        for(State state:epsilonReachableStates){
            for(State next:transitionManager.getTargetTransitions(state,'ε')){
                if(!epsilonReachableStates.contains(next)){
                    newStates.add(next);
                    changed=true;
                }
            }
        }
        epsilonReachableStates=newStates;
    }while(changed);

    return  epsilonReachableStates;
    }

    public boolean recognize(String word) {
        Set<State> startStates = new HashSet<>();
        startStates.add(startState);
        Set<State> currentStates = getEpsilonReachableStates(startStates);

        for (char c : word.toCharArray()) {
            Set<State> nextStates = new HashSet<>();
            for (State state : currentStates) {
                for (State target : transitionManager.getTargetTransitions(state, c)) {
                    Set<State> targetSet = new HashSet<>();
                    targetSet.add(target);
                    nextStates.addAll(getEpsilonReachableStates(targetSet));
                }
            }
            currentStates = nextStates;
        }

        for (State state : currentStates) {
            if (finalStates.contains(state)) {
                return true;
            }
        }

        return false;
    }


    public static boolean recognize(Automaton automaton, String word) {
        RecognizeChecker checker = new RecognizeChecker(
                automaton.getTransitionManager(),
                automaton.getStartState(),
                automaton.getFinalStates()
            );
            return checker.recognize(word);
    }

}

