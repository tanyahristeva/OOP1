package command;

import base.Automaton;
import manager.AutomatonManager;
import serialization.AutomatonSerializer;
/**
 * Команда за зареждане на сериализиран автомат от файл.
 * Възстановява автомат, запазен чрез сериализация.
 *
 * @see AutomatonSerializer
 * @see AutomatonManager
 * @see Command
 */
public class LoadCommand implements Command {
    private final AutomatonManager automatonManager;

    public LoadCommand(AutomatonManager automatonManager) {
        this.automatonManager = automatonManager;
    }
    /**
     * Изпълнява зареждане на автомат.
     * Очаква аргумент: {@code load <файл>}
     *
     * @param args аргументи от командния ред, където args[1] е името на файла
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Нужно е име на файл.");
        }

        String file = args[1];
        try {
            Automaton automaton = AutomatonSerializer.loadSingleAutomaton(file);
            if (automaton != null) {
                automatonManager.addAutomaton(automaton);
            }
            if (automaton != null) {
                System.out.println("Автоматът беше успешно зареден с ID: " + automaton.getId());
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Грешка при прочитането на автомата: " + e.getMessage());
            }
    }

    @Override
    public String getDescription() {
        return "Зареждане на сериализиран автомат.";
    }
}

