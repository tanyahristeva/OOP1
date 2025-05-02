public class CloseCommand implements Command{
    private FileManager fileManager;

    public CloseCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        fileManager.close();
    }
}
