package utils;

import base.Automaton;
import base.State;
import base.Transition;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * Клас, предоставящ метод за проверка дали даден автомат е детерминиран.
 * Един автомат е детерминиран, ако:
 * - няма ε-преходи;
 * - за всяко състояние и символ от азбуката има най-много един изходящ преход.
 */
public class DeterminismChecker {
    /**
     * Проверява дали даден автомат е детерминиран.
     *
     * @param automaton автомат, който се проверява
     * @return true ако е детерминиран, false в противен случай
     */
    public static boolean isDeterministic(Automaton automaton){
        Map<State, Set<Character>> transitionMap=new HashMap<>();
        for(Transition t: automaton.getTransitions()){
            if(t.isEpsilon()){
                return false;
            }
            State key=t.getStartingFrom();
            Set<Character> used;

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
