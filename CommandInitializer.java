package command;

import manager.AutomatonManager;
import utils.FileHandler;

import java.nio.channels.CancelledKeyException;

/**
 * Инициализатор на командите в програмата.
 * Регистрира всички налични команди в CommandRegistry.
 */
public class CommandInitializer {
    /**
     * Инициализира и регистрира всички команди.
     *
     * @param fileHandler файловия мениджър за командите, които го изискват
     */
    public static void initializeCommands(FileHandler fileHandler){

        AutomatonManager automatonManager=AutomatonManager.getInstance();
        CommandRegistry.registerCommand(CommandNames.LIST,new ListCommand());
        CommandRegistry.registerCommand(CommandNames.PRINT,new PrintCommand(automatonManager));
        CommandRegistry.registerCommand(CommandNames.EMPTY ,new EmptyCommand(automatonManager));
        CommandRegistry.registerCommand(CommandNames.DETERMINISTIC,new DeterministicCommand(automatonManager));
        CommandRegistry.registerCommand(CommandNames.RECOGNIZE,new RecognizeCommand(automatonManager));
        CommandRegistry.registerCommand(CommandNames.OPEN,new OpenCommand(fileHandler,automatonManager));
        CommandRegistry.registerCommand(CommandNames.SAVE,new SaveCommand(fileHandler));
        CommandRegistry.registerCommand(CommandNames.CLOSE,new CloseCommand(fileHandler));
        CommandRegistry.registerCommand(CommandNames.SAVEAS,new SaveAsCommand(fileHandler));
        CommandRegistry.registerCommand(CommandNames.HELP,new HelpCommand());
        CommandRegistry.registerCommand(CommandNames.EXIT,new ExitCommand(fileHandler));
        CommandRegistry.registerCommand(CommandNames.SERIALIZE,new SaveSerializedAutomatonCommand(automatonManager));
        CommandRegistry.registerCommand(CommandNames.LOAD,new LoadCommand(automatonManager));
        CommandRegistry.registerCommand(CommandNames.CONCAT,new ConcatCommand(automatonManager));
        CommandRegistry.registerCommand(CommandNames.UNION,new UnionCommand(automatonManager));
        CommandRegistry.registerCommand(CommandNames.FINITE,new FiniteLanguageCommand(automatonManager));
        CommandRegistry.registerCommand(CommandNames.MUTATOR,new DeterministicMutatorCommand(automatonManager));
        CommandRegistry.registerCommand(CommandNames.REG,new RegCommand(automatonManager));
        CommandRegistry.registerCommand(CommandNames.UN,new PositiveClosureCommand(automatonManager));
    }
}
