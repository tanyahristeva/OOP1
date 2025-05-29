package utils;

import base.Automaton;
import base.State;
import base.Transition;

import java.util.Set;
/**
 * Клас за създаване на парсер за регулярен израз.
 * Поддържа основните операции - конкатенация, обединение(|),
 * звезда на Клини(*) и групиране със скоби.
 *
 */
public class RegexParser {
    private String regex;
    private int pos;
    private char currentChar;

    /**
     * Създава парсер за регулярен израз.
     * @param regex регулярният израз за обработка
     */
    public RegexParser(String regex) {
        this.regex=regex;
        this.pos = 0;
        this.currentChar = pos < regex.length() ? regex.charAt(pos) : '\0';
    }
    /**
     * Парсва регулярния израз и го преобразува в автомат.
     *
     * @return създадения автомат
     * @throws IllegalArgumentException при невалиден синтаксис
     */
    public Automaton parse() {
        return parseExpression();
    }

    /**
     * Обработва изрази с оператор за обединение (|)
     * @return автомат, представящ обединението на подизразите
     */
    private Automaton parseExpression() {
        Automaton term = parseTerm();
        while (currentChar == '|') {
            next();
            Automaton right = parseTerm();
            term = new AutomatonUnion().union(term, right);
        }
        return term;
    }
    /**
     * Обработва конкатенирани подизрази.
     * @return автомат, представящ конкатенацията
     */
    private Automaton parseTerm() {
        Automaton factor = parseFactor();
        while (hasMore() && (isSymbol(currentChar) || currentChar == '(')) {
            Automaton nextFactor = parseFactor();
            factor = new AutomatonConcatenation().concatAutomatons(factor, nextFactor);
        }
        return factor;
    }
    /**
     * Обработва оператор за повторение (*).
     * @return автомат със звезда на Клини
     */
    private Automaton parseFactor() {
        Automaton base = parseBase();
        while (currentChar == '*') {
            next();
            base = new AutomatonKleeneStar().applyKleeneStar(base);
        }
        return base;
    }
    /**
     * Обработва базови елементи: символи или групирани изрази.
     * @return Базов автомат
     */
    private Automaton parseBase() {
        if (currentChar == '(') {
            next();
            Automaton inside = parseExpression();
            if (currentChar != ')') {
                throw new IllegalArgumentException("Липсва затваряща скоба ')'");
            }
            next();
            return inside;
        } else {
            char symbol = currentChar;
            next();
            return createBasicAutomaton(symbol);
        }
    }
    /**
     * Преминава към следващия символ от израза.
     */
    private void next() {
        pos++;
        currentChar = pos < regex.length() ? regex.charAt(pos) : '\0';
    }
    /**
     * Проверява дали има още символи за обработка.
     * @return true ако не е достигнат края на израза
     */
    private boolean hasMore() {
        return pos < regex.length();
    }
/**
 * Проверява дали символът е валиден за азбуката на автомата.
 * Валидни символи са малки латински букви (a-z), цифри (0-9)
 * и специалният символ 'ε' (за ε-преходи)
 * @param c символ за проверка
 * @return true ако символът е: малка латинска буква (a-z), или
 * цифра (0-9), или 'ε'
 */
    private boolean isSymbol(char c) {
        return (c>='a'&&c<='z')||(c>='0'&&c<='9')||c=='ε';
    }
    /**
     * Създава базов автомат, който разпознава един символ.
     * Състои се от две състояния и един преход между тях.
     * @param symbol символът, който автоматът ще разпознава
     * @return автомат, който приема само този символ
     */
    private Automaton createBasicAutomaton(char symbol) {
        State s0 = new State("q0", false);
        State s1 = new State("q1", true);
        Automaton automaton = new Automaton(Set.of(symbol));
        automaton.setStartState(s0);
        automaton.getStates().add(s0);
        automaton.getStates().add(s1);
        automaton.getFinalStates().add(s1);
        automaton.addTransition(new Transition(s0, s1, symbol));
        return automaton;
    }
}

