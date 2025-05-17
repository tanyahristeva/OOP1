import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AutomatonConcatenation {
    public Automaton concatAutomatons(Automaton first, Automaton second) {

        Automaton result=new Automaton(AutomatonOperationHelper.createCombinedAlphabet(first,second));


        Map<State, State> firstMap = AutomatonOperationHelper.copyStates(first,result,"A1-",false);
        Map<State, State> secondMap = AutomatonOperationHelper.copyStates(second,result,"A2-",true);
        result.setStartState(firstMap.get(first.getStartState()));

        for (State finalState : second.getFinalStates()) {
            result.getFinalStates().add(secondMap.get(finalState));
        }
        AutomatonOperationHelper.copyTransitions(first, result, firstMap);
        AutomatonOperationHelper.copyTransitions(second, result, secondMap);

        addEpsilonTransitions(first, second, result, firstMap, secondMap);
        return result;
    }

    private void addEpsilonTransitions(Automaton first,Automaton second, Automaton result, Map<State,State> firstMap,Map<State,State>secondMap) {
        State secondStart = secondMap.get(second.getStartState());
        for (State finalState : first.getFinalStates()) {
            State firstFinal = firstMap.get(finalState);
            result.addTransition(new Transition(firstFinal, secondStart, 'ε'));
        }
    }
}
