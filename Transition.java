import java.io.Serializable;

public class Transition implements Serializable {
    private static final long serialVersionUID=1L;
    private final State startingFrom;
    private final State goingTo;
    private final char symbol;

    public Transition(State startingFrom, State goingTo, char symbol) {
        this.startingFrom = startingFrom;
        this.goingTo = goingTo;
        this.symbol = symbol;
    }

    public State getStartingFrom() {
        return startingFrom;
    }

    public State getGoingTo() {
        return goingTo;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean isEpsilon() {
        return symbol=='ε';
    }


    @Override
    public String toString() {
        return "От състояние " + startingFrom +
                " към състояние " + goingTo +
                " при символ" + (isEpsilon() ? "ε" : symbol);
    }
}
