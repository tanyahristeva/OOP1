import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AutomatonManager {
    private static AutomatonManager instance;
    private final Map<Integer, Automaton> automatons=new HashMap<>();

    private AutomatonManager(){};

    public static AutomatonManager getInstance(){
        if(instance==null){
            instance=new AutomatonManager();
        }
        return instance;
    }

    public int addAutomaton(Automaton automaton){
        automatons.put(automaton.getId(), automaton);
        return automaton.getId();
    }

    public Automaton getAutomaton(int id){
        return automatons.get(id);
    }
    public void listAutomatons(){
        System.out.println("Всички автомати: ") ;
       for(Integer id: automatons.keySet()){
           System.out.println("ID: "+id);
       }
    }
    public void print(int id){
        Automaton automaton=automatons.get(id);
        if(automaton==null){
            System.out.println("Автомат с ID - "+id+" не съществува.");
            return;
        }
        Set<Transition> transitions=automaton.getTransitions();
        if(transitions.isEmpty()){
            System.out.println("Автомат с ID - "+id+" няма преходи.");
            return;
        }

        System.out.println("Преходи за автомат с ID - "+id+": ");
        for(Transition transition:transitions){
            System.out.println(transition);
        }
    }

}
