import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AutomatonOperationHelper {


    public static Set<Character> createCombinedAlphabet(Automaton a1, Automaton a2) {
        Set<Character> alphabet = new HashSet<>(a1.getAlphabet());
        alphabet.addAll(a2.getAlphabet());
        return alphabet;
    }

    public static Map<State,State> copyStates(Automaton source, Automaton target, String prefix,boolean addToFinal) {
        Map<State, State> map = new HashMap<>();
        for (State state : source.getStates()) {
            State copy = new State(prefix + state.getName(), state.isFinal());
            target.getStates().add(copy);
            if (addToFinal && state.isFinal()) {
                target.getFinalStates().add(copy);
            }
            map.put(state, copy);
        }
        return map;

    }
    public static void copyTransitions(Automaton source, Automaton target, Map<State,State>map) {
        for(Transition t: source.getTransitions()){
            State from=map.get(t.getStartingFrom());
            State to=map.get(t.getGoingTo());
            target.addTransition(new Transition(from,to, t.getSymbol()));
        }
    }
    public static Automaton copyAutomaton(Automaton source, String prefix){
        Automaton copy=new Automaton(new HashSet<>(source.getAlphabet()));
        Map<State,State>map=copyStates(source,copy,prefix,true);
        copy.setStartState(map.get(source.getStartState()));
        copyTransitions(source,copy,map);
        return copy;

        }



}
