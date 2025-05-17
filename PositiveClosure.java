public class PositiveClosure {
    public static Automaton createPositiveClosure(Automaton automaton)  {
        Automaton result=AutomatonOperationHelper.copyAutomaton(automaton, "Позитивна обвивка-");
        for(State finalState:result.getFinalStates()){
            result.addTransition(new Transition(finalState,result.getStartState(),'ε'));
        }
        result.getStartState().setFinal(true);
        result.getFinalStates().add(result.getStartState());
        return result;
    }
}
