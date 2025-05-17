import java.util.Map;

public class AutomatonUnion {
    public Automaton union(Automaton first, Automaton second) {
    Automaton result=new Automaton(AutomatonOperationHelper.createCombinedAlphabet(first,second));

    Map<State,State>firstMap=AutomatonOperationHelper.copyStates(first,result,"A1-",true);
    Map<State,State>secondMap=AutomatonOperationHelper.copyStates(second,result,"A2-",true);

    State newStart=new State("Union Start",false);
    result.getStates().add(newStart);
    result.setStartState(newStart);

    AutomatonOperationHelper.copyTransitions(first,result,firstMap);
    AutomatonOperationHelper.copyTransitions(second,result,secondMap);
    result.addTransition(new Transition(newStart,firstMap.get(first.getStartState()),'ε'));
    result.addTransition(new Transition(newStart,secondMap.get(second.getStartState()),'ε'));
    return result;
    }
}
