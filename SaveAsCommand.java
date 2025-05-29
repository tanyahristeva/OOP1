package command;

import utils.FileHandler;

/**
 * Команда за записване на файл в директория чрез подаден път.
 * @see FileHandler
 * @see Command
 */
public class SaveAsCommand implements Command{
    private final FileHandler fileHandler;

    public SaveAsCommand(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }
    /**
     * Изпълнява записване на файл в директория чрез подаден път.
     * Очаква аргументи във формат: {@code saveas <път>}
     *
     * @param args масив с аргументи, където args[1] е ID, args[2] е думата
     */
    @Override
    public void execute(String[] args) {
        if(args.length<2){
            throw new IllegalArgumentException("Нуjen e put do lokaciq za file");
        }
        String path=args[1];
        fileHandler.saveAs(path);
    }

    @Override
    public String getDescription() {
        return "Команда за записване на файл в директория чрез подаден път.";
    }
}
