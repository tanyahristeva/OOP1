import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DeterminismChecker {

    public static boolean isDeterministic(Automaton automaton){
        Map<String, Set<String>> transitionMap=new HashMap<>();
        for(Transition t: automaton.getTransitions()){
            if(t.getSymbol().equals("ε")){
                return false;
            }
            String key=t.getStartingFrom().getName();
            Set<String> used;

            if(transitionMap.containsKey(key)){
                used=transitionMap.get(key);
            }else{
                used=new HashSet<>();
            }

            if(!used.add(t.getSymbol())){
                return false;
            }
            transitionMap.put(key,used);
        }
        return true;
    }
}
