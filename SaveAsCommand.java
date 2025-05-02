public class SaveAsCommand implements Command{
    private FileManager fileManager;

    public SaveAsCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if(args.length<2){
            System.out.println("Нужен е път за локация на запазване на файла.");
            return;
        }
        String path=args[1];//.replaceAll("\"","");
        fileManager.saveAs(path);
    }
}
