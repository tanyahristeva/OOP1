public class OpenCommand implements Command{
    private FileManager fileManager;

    public OpenCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if(args.length<2){
            System.out.println("Нужно е име на файла.");
            return;
        }
        fileManager.open(args[1]);
    }
}
