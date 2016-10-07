package seedu.todo.storage;

import java.io.IOException;
import java.util.Optional;

import seedu.todo.commons.exceptions.DataConversionException;
import seedu.todo.model.ImmutableTodoList;
import seedu.todo.model.UserPrefs;

public class MockStorage implements Storage {

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return Optional.empty();
    }

    @Override
    public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
        // Does nothing 
    }

    @Override
    public String getTodoListFilePath() {
        return null;
    }

    @Override
    public Optional<ImmutableTodoList> readTodoList() {
        return Optional.empty();
    }

    @Override
    public Optional<ImmutableTodoList> readTodoList(String filePath) {
        return Optional.empty();
    }

    @Override
    public void saveTodoList(ImmutableTodoList todoList) {
        // Does nothing
    }

    @Override
    public void saveTodoList(ImmutableTodoList todoList, String filePath) {
        // Does nothing
    }

}
