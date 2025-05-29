package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Клас за управление на файлови операции.
 * Позволява отваряне, запазване, запазване с ново име и затваряне на файлове.
 * Следи за промени в съдържанието.
 */

public class FileHandler {
    private String currentPath;
    private String fileContent;
    private boolean isModified;
    private File currentFile;

    /**
     * Отваря файл и зарежда неговото съдържание като текст.
     *
     * @param path пълният път до файла
     */
    public void open(String path){
        try{
            currentFile=new File(path);
            fileContent=new String(Files.readAllBytes(Paths.get(path)));
            currentPath=path;
            isModified=false;
            System.out.println("Успешно отворен файл: "+path);

        }
        catch (IOException e){
            System.out.println("Грешка при отваряне на файла. "+e.getMessage());
        }

    }
    /**
     * Записва текущото съдържание във файла, ако има отворен файл.
     * Ако няма път или съдържание, показва съобщение за грешка.
     */
    public void save(){
        if(currentFile==null){
            System.out.println("Няма отворен файл за запис.");
            return;
        }
        if(fileContent==null){
            System.out.println("Няма съдържание за запис във файла.");
            return;
        }
        try {
            Files.write(currentFile.toPath(),fileContent.getBytes());
            isModified=false;
            System.out.println("Успешно записан файл:"+currentFile.getAbsolutePath());
        }catch (IOException e){
            System.out.println("Грешка при запис:"+ e.getMessage());
        }
    }
    /**
     * Записва текущото съдържание във файл на нова локация.
     *
     * @param newPath новият път за запис
     */
    public void saveAs(String newPath){
        if(fileContent==null){
            System.out.println("Няма съдържание за запис във файла.");
            return;
        }
        try {
            currentFile=new File(newPath);
            Files.write(currentFile.toPath(),fileContent.getBytes());
            currentPath=newPath;
            isModified=false;
            System.out.println("Успешно записан файл:"+newPath);
        }catch (IOException e) {
            System.out.println("Грешка при запис:"+ e.getMessage());
        }
    }
    /**
     * Затваря текущия файл. Предупреждава, ако има незаписани промени.
     */
    public void close(){
        if(isModified){
            System.out.println("Внимание! Има незаписани промени.");
        }
        currentFile=null;
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
