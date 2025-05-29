package command;

import base.Automaton;
import base.Transition;
import manager.AutomatonManager;

import java.util.List;
/**
 * Команда за извеждане на преходите на автомат.
 */
public class PrintCommand implements Command {
    /**
     * Изпълнява командата за извеждане на преходите.
     * Очаква аргументи във формат: {@code print <id>}
     * @param args аргументи на командата, където args[1] е идентификационният номер на автомата
     */
    private final AutomatonManager automatonManager;

    public PrintCommand(AutomatonManager automatonManager) {
        this.automatonManager = automatonManager;
    }

    @Override
    public void execute(String[] args) {
        if(args.length < 2) {
            throw new IllegalArgumentException("Нужни сa 2 ID-та.");
        }
        try {
            int id = Integer.parseInt(args[1]);
            automatonManager.print(id);
        } catch (NumberFormatException e) {
            System.out.println("Невалидно ID.");
        }
    }

    @Override
    public String getDescription() {
        return "Команда за извеждане на преходите на автомат.";
    }
}
