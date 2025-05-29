package command;

import utils.FileHandler;

/**
 * Команда за затваряне на текущия файл.
 * Освобождава ресурси, свързани с отворения файл.
 *
 * @see FileHandler
 * @see Command
 */
public class CloseCommand implements Command{
    private FileHandler fileHandler;

    public CloseCommand(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }
    /**
     * Изпълнява затваряне на файла.
     * Не изисква аргументи.
     *
     * @param args аргументи от командния ред (не се използват)
     */
    @Override
    public void execute(String[] args) {
        fileHandler.close();
    }

    @Override
    public String getDescription() {
        return "Затваряне на файл.";
    }
}
