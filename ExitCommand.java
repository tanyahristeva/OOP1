package command;

import utils.FileHandler;

import java.io.IOException;

/**
 * Команда за изход от програмата.
 * Проверява за незаписани промени преди изход.
 *
 * @see FileHandler
 * @see Command
 */
public class ExitCommand implements Command{
    private FileHandler fileHandler;

    public ExitCommand(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }
    /**
     * Изпълнява изход от програмата.
     * Не изисква аргументи.
     * @param args аргументи от командния ред (не се използват)
     */
    @Override
    public void execute(String[] args) throws IOException {
        if(fileHandler.isFileOpen()&& fileHandler.isModified()){
           throw new IOException("Има незаписани данни за файлове.");
        }
        System.out.println("Изход от програмата...");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "Команда за изход.";
    }
}
