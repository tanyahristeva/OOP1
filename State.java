package base;

import java.io.Serializable;
import java.util.Objects;

/**
 * Представя състояние на краен автомат.
 * Състоянието има име и флаг дали е финално.
 * Използва се като част от автомат.
 * Поддържа сериализация за съхранение и възстановяване на автомата.
 *
 * @serial include
 */
public class State implements Serializable {
    private static final long serialVersionUID=1L;

    private final String name;
    private boolean isFinal;

    /**
     * Създава ново състояние със зададени параметри
     *
     *@param name име на състоянието
     *@param isFinal флаг за финалност на състоянието
     */
    public State(String name, boolean isFinal) {
        this.name = name;
        this.isFinal = isFinal;
    }
    /**
     * Връща името на състоянието на автомата
     *
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * Връща булева променлива затова, че състоянието на автомата е финално
     *
     * @return isFinal
     */
    public boolean isFinal() {
        return isFinal;
    }

    /**
     * Задава състоянието на автомата като финално
     *
     * @param aFinal финално състояние
     */
    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(name, state.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}

