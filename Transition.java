package base;

import java.io.Serializable;
/**
 * Представя преход между състояния в краен автомат.
 * Съдържа начално състояние, крайно състояние и символ за преход.
 * Поддържа сериализация за съхранение и възстановяване на автомата.
 *
 * @serial include
 */
public class Transition implements Serializable {
    private static final long serialVersionUID=1L;
    private final State startingFrom;
    private final State goingTo;
    private final char symbol;

    /**
     * Създава нов преход между състояния.
     *
     * @param startingFrom начално състояние на прехода
     * @param goingTo крайно състояние на прехода
     * @param symbol символ, активиращ прехода
     */
    public Transition(State startingFrom, State goingTo, char symbol) {
        this.startingFrom = startingFrom;
        this.goingTo = goingTo;
        this.symbol = symbol;
    }
    /**
     * Връща началното състояние на прехода на автомата
     *
     * @return startingFrom
     */
    public State getStartingFrom() {
        return startingFrom;
    }
    /**
     * Връща следващото състояние на прехода на автомата
     *
     * @return goingTo
     */
    public State getGoingTo() {
        return goingTo;
    }
    /**
     * Връща символа на прехода на автомата
     *
     * @return symbol
     */
    public char getSymbol() {
        return symbol;
    }
    /**
     * Проверява дали символът на прехода е 'ε'
     *
     * @return symbol
     */
    public boolean isEpsilon() {
        return symbol=='ε';
    }

    /**
     * Текстово представяне на преходите между състоянията
     *
     * @return форматирани преходи
     */
    @Override
    public String toString() {
        return "От състояние " + startingFrom +
                " към състояние " + goingTo +
                " при символ " + (isEpsilon() ? "ε" : symbol);
    }
}
