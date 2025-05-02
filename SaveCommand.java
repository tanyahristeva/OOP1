public class SaveCommand implements Command{
    private FileManager fileManager;

    public SaveCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        fileManager.save();
    }
}
