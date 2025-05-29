package command;

import java.io.IOException;

/**
 * Интерфейс за команди в програмата.
 * Дефинира основния метод за изпълнение на команда с аргументи.
 */
public interface Command {
    /**
     * Изпълнява командата с подадените аргументи.
     *
     * @param args масив от аргументи за командата
     */
    void execute(String[] args) throws IOException;

    String getDescription();
}
