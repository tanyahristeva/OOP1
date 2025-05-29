package base;

import validator.AlphabetValidator;
import manager.TransitionManager;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Краен автомат - недетерминиран или детерминиран с възможност за ε-преходи.
 * Автоматът съдържа състояния, преходи, начално състояние, финални състояния и азбука от допустими символи.
 * Поддържа операции за добавяне на преходи, достъп до - състояния, азбука, ID, преходи, начално и финално състояние.
 * Поддържа сериализация за съхранение и възстановяване на автомата.
 */
public class Automaton implements Serializable {
    private static final long serialVersionUID=1L;
    private int id;
    private Set <Character> alphabet;
    private Set<State> states;
    private State startState;
    private Set<State> finalStates;
    private TransitionManager transitionManager;

    /**
     * Създава автомат с пълна информация – състояния, стартово и финални състояния, преходи и азбука.
     *
     * @param alphabet азбуката на автомата
     * @param states множеството от състояния
     * @param startState началното състояние
     * @param finalStates множеството от финални състояния
     * @param transitionManager обект, управляващ преходите
     */

    public Automaton(Set<Character> alphabet, Set<State> states, State startState, Set<State> finalStates, TransitionManager transitionManager) {
        this.alphabet = new HashSet<>(alphabet);
        this.states = new HashSet<>(states);
        this.startState = startState;
        this.finalStates = new HashSet<>(finalStates);
        this.transitionManager=transitionManager;

    }
    /**
     * Връща множеството от състояния на автомата.
     *
     * @return множество от състояния
     */
    public Set<State> getStates() {
        return states;
    }
    /**
     * Връща уникалния идентификатор на автомата.
     *
     * @return ID на автомата
     */
    public int getId() {
        return id;
    }
    /**
     * Задава уникалния идентификатор на автомата.
     *
     * @param id новият ID на автомата
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Връща списък с всички преходи на автомата.
     *
     * @return списък с преходи
     */
    public List<Transition> getTransitions() {
        return transitionManager.getTransitions();
    }
    /**
     * Връща азбуката на автомата.
     *
     * @return множество от символи в азбуката
     */
    public Set<Character> getAlphabet() {
        return alphabet;
    }
    /**
     * Връща началното състояние на автомата.
     *
     * @return началното състояние
     */
    public State getStartState() {
        return startState;
    }
    /**
     * Задава началното състояние на автомата.
     *
     * @param startState новото начално състояние
     */
    public void setStartState(State startState) {
        this.startState = startState;
    }
    /**
     * Задава множеството от финални състояния на автомата.
     *
     * @param finalStates новото множество от финални състояния
     */
    public void setFinalStates(Set<State> finalStates) {
        this.finalStates = finalStates;
    }
    /**
     * Връща множеството от финални състояния на автомата.
     *
     * @return множество от финални състояния
     */
    public Set<State> getFinalStates() {
        return finalStates;
    }
    /**
     * Създава празен автомат само с дадена азбука. Валидира символите от азбуката.
     * Използва се за постепенно изграждане на автомат - може да се задават компоненти след създаване.
     *
     * @param alphabet азбуката на автомата
     * @throws IllegalArgumentException ако азбуката съдържа невалидни символи
     */
    public Automaton(Set<Character> alphabet){
        if(!AlphabetValidator.isValidAlphabet(alphabet)){
            throw new IllegalArgumentException("Невалидна азбука. Позволените символи са между a и z и от 0 до 9.");
        }

        this.alphabet = new HashSet<>(alphabet);
        this.states = new HashSet<>();
        this.finalStates = new HashSet<>();
        this.transitionManager = new TransitionManager();
    }
    /**
     * Добавя преход към автомата.
     *
     * @param transition преходът, който ще се добави
     */
    public void addTransition(Transition transition) {
        transitionManager.addTransition(transition);
    }
    /**
     * Връща обекта, който управлява преходите на автомата.
     *
     * @return обект от тип TransitionManager
     */
    public TransitionManager getTransitionManager() {
        return transitionManager;
    }
}
