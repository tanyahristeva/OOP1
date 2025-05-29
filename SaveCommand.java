package command;

import utils.FileHandler;

/**
 * Команда за запазване на текущо отворения автомат във файл.
 * Използва FileHandler за извършване на операцията запазване.
 * Работи само ако има активно отворен файл.
 *
 * @see FileHandler
 * @see Command
 */
public class SaveCommand implements Command{
    private final FileHandler fileHandler;

    public SaveCommand(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }
    /**
     * Изпълнява операцията запазване на текущия файл.
     * Извежда съобщение за успех или грешка.
     *
     * @param args аргументи от командния ред (не се използват в тази команда)
     *
     * @example Пример за използване:
     */
    @Override
    public void execute(String[] args) {
        fileHandler.save();
    }

    @Override
    public String getDescription() {
        return "Команда за запазване на текущо отворения автомат във файл.";
    }
}
