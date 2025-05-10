import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FileManager {
    private String currentPath;
    private String fileContent;
    private boolean isModified;

    public void open(String path){
        try{
            fileContent=new String(Files.readAllBytes(Paths.get(path)));
            currentPath=path;
            isModified=false;
            System.out.println("Успешно отворен файл: "+path);

        }
        catch (IOException e){
            System.out.println("Грешка при отваряне на файла. "+e.getMessage());
        }

    }

    public void save(){
        if(currentPath==null){
            System.out.println("Няма отворен файл за запис.");
            return;
        }
        if(fileContent==null){
            System.out.println("Няма съдържание за запис във файла.");
            return;
        }
        try {
            Files.write(Paths.get(currentPath),fileContent.getBytes());
            isModified=false;
            System.out.println("Успешно записан файл:"+currentPath);
        }catch (IOException e){
            System.out.println("Грешка при запис:"+ e.getMessage());
        }
    }

    public void saveAs(String newPath){
        if(fileContent==null){
            System.out.println("Няма съдържание за запис във файла.");
            return;
        }
        try {
            Files.write(Paths.get(newPath),fileContent.getBytes());
            currentPath=newPath;
            isModified=false;
            System.out.println("Успешно записан файл:"+newPath);
        }catch (IOException e) {
            System.out.println("Грешка при запис:"+ e.getMessage());
        }
    }
    public void close(){
        if(isModified){
            System.out.println("Внимание! Има незаписани промени.");
        }
        currentPath=null;
        fileContent=null;
        isModified=false;
        System.out.println("Файлът е затворен");
    }

    public String getFileContent() {
        return fileContent;
    }

    public boolean isModified() {
        return isModified;
    }

    public String getCurrentPath() {
        return currentPath;
    }

    public void setFileContent(String content) {
        this.fileContent = content;
        this.isModified=true;
    }
    public boolean isFileOpen(){
        return currentPath!=null;
    }

}
