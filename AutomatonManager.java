package manager;

import base.Automaton;
import base.Transition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Мениджър за управление на автоматите в приложението.
 * Реализира Singleton шаблон за глобален достъп.
 * Поддържа създаване, извличане и извеждане на автомати.
 */
public class AutomatonManager {
    private static AutomatonManager instance;
    private final Map<Integer, Automaton> automatons=new HashMap<>();
    private int nextID=0;
    /**
     * Частен конструктор (Singleton шаблон)
     */
    private AutomatonManager(){};
    /**
     * Връща единствената инстанция на AutomatonManager.
     *
     * @return единствената инстанция
     */
    public static AutomatonManager getInstance(){
        if(instance==null){
            instance=new AutomatonManager();
        }
        return instance;
    }
    /**
     * Добавя нов автомат в мениджъра.
     *
     * @param automaton автоматът за добавяне
     * @return ID на новия автомат
     */
    public int addAutomaton(Automaton automaton){
        int id=nextID++;
        automaton.setId(id);
        automatons.put(id, automaton);
        return id;
    }
    /**
     * Връща автомат по ID.
     *
     * @param id ID на автомата
     * @return автоматът или null, ако не съществува
     */
    public Automaton getAutomaton(int id){
        return automatons.get(id);
    }
    /**
     * Извежда списък с всички автомати по ID.
     */
    public void list(){
        System.out.println("Всички автомати: ") ;
       for(Integer id: automatons.keySet()){
           System.out.println("ID: "+id);
       }
    }
    /**
     * Извежда информация за конкретен автомат.
     *
     * @param id ID на автомата
     */
    public void print(int id){
        Automaton automaton=automatons.get(id);
        if(automaton==null){
             throw new IllegalArgumentException("Автомат с ID не съществува."+id);
        }
        List<Transition> transitions=automaton.getTransitions();
        if(transitions.isEmpty()){
            throw new IllegalArgumentException("Автомат с ID няма преходи");
        }

        System.out.println("Преходи за автомат с ID - "+id+": ");
        for(Transition transition:transitions){
            System.out.println(transition);
        }
    }

}
