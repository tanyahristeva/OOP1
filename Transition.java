public class Transition {
    private final State startingFrom;
    private final State goingTo;
    private final String symbol;

    public Transition(State startingFrom, State goingTo, String symbol) {
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

    public String getSymbol() {
        return symbol;
    }

    public boolean isEpsilon() {
        return "ε".equals(symbol);
    }

    @Override
    public String toString() {
        return "От състояние " + startingFrom +
                " към състояние " + goingTo +
                " при символ" + (isEpsilon() ? "ε" : symbol);
    }
}
