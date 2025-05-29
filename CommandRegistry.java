package command;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * Регистър за управление на командите в програмата.
 * Поддържа регистрация, търсене и изпълнение на команди.
 */
public class CommandRegistry {
    private static Map<CommandNames,Command> commands=new HashMap<>();
    //private static Map<String, String> commandDescriptions=new HashMap<>();
    /**
     * Регистрира нова команда.
     *
     * @param name име на командата
     * @param command инстанция на командата
     * //@param description описание на командата
     */
    public static void registerCommand(CommandNames name, Command command){

        commands.put(name,command);
        //commandDescriptions.put(name,description);
    }
    /**
     * Връща команда по име.
     *
     * @param name име на командата
     * @return командата или null ако не съществува
     */
    public static Command getCommand(CommandNames name){
        return commands.get(name);
    }

    /**
     * Връща описание на команда.
     *
     * @param name име на командата
     * @return описание или "Няма описание" ако командата не е регистрирана
     */
   public static String getCommandDescription(CommandNames name){
        Command command = commands.get(name);
        if(command!=null){
            return command.getDescription();
        }else{
            return "Няма описание.";
        }
   }
    /**
     * Връща множество с всички поддържани команди.
     *
     * @return множество с имената на командите
     */
   public static Set<CommandNames> getSupportedCommands(){
        return commands.keySet();
   }
}
