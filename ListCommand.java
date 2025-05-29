package command;

import manager.AutomatonManager;
/**
 * Команда за извеждане на списък с всички автомати.
 * Показва ID-тата на всички регистрирани автомати.
 *
 * @see AutomatonManager
 * @see Command
 */
public class ListCommand implements Command {

    private final AutomatonManager manager;

    public ListCommand() {
        this.manager = AutomatonManager.getInstance();
    }
    /**
     * Изпълнява извеждането на списъка.
     * Не изисква аргументи.
     *
     * @param args аргументи от командния ред (не се използват)
     */
    @Override
    public void execute(String[] args) {
        manager.list();
    }

    @Override
    public String getDescription() {
        return "Извежда лист от автомати";
    }
}
