public class Transition {
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
}
