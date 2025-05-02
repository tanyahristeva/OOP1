public class ExitCommand implements Command{
    private FileManager fileManager;

    public ExitCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if(fileManager.isFileOpen()&&fileManager.isModified()){
            System.out.println("Има незаписани промени!");
            return;
        }
        System.out.println("Изход от програмата...");
        System.exit(0);
    }
}
