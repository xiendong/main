package seedu.todo.logic.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import static org.junit.Assert.*;

import seedu.todo.commons.exceptions.IllegalValueException;
import seedu.todo.commons.exceptions.ValidationException;
import seedu.todo.logic.parser.ParseResult;
import seedu.todo.model.TodoList;
import seedu.todo.model.task.ImmutableTask;
import seedu.todo.storage.MockStorage;

/**
 * Base test case for testing commands. All command tests should extend this class. 
 * Provides a simple interface for setting up command testing as well as a number 
 * of assertions to inspect the model. 
 */
public abstract class CommandTest {
    private class StubParseResult implements ParseResult {
        public String command; 
        public String positional;
        public Map<String, String> named = new HashMap<>();

        @Override
        public String getComand() {
            return command;
        }

        @Override
        public Optional<String> getPositionalArgument() {
            return Optional.ofNullable(positional);
        }

        @Override
        public Map<String, String> getNamedArguments() {
            return named;
        }
    }

    protected TodoList model;
    protected MockStorage storage;
    protected BaseCommand command;
    protected StubParseResult params;
    
    abstract protected BaseCommand commandUnderTest();

    @Before
    public void setUpCommand() throws Exception {
        storage = new MockStorage();
        model = new TodoList(storage);
        params = new StubParseResult();
        command = commandUnderTest();
    }
    
    /**
     * Returns the task visible in the model at 1-indexed position, mimicking user input
     */
    protected ImmutableTask getTaskAt(int index) {
        return model.getObserveableList().get(index - 1);
    }
    
    /**
     * Asserts that the model has this number of tasks in memory
     */
    protected void assertTotalTaskCount(int size) {
        assertEquals(size, model.getTasks().size());
    }
    
    /**
     * Asserts that the model has this number of tasks visible
     */
    protected void assertVisibleTaskCount(int size) {
        assertEquals(size, model.getObserveableList().size());
    }
    
    /**
     * Asserts that the task exists in memory
     */
    protected void assertTaskExist(ImmutableTask task) {
        if (!model.getTasks().contains(task)) {
            throw new AssertionError("Task not found in model");
        }
    }
    

    /**
     * Asserts that the task does not exist in memory
     */
    protected void assertTaskNotExist(ImmutableTask task) {
        if (model.getTasks().contains(task)) {
            throw new AssertionError("Task found in model");
        }
    }
    
    /**
     * Asserts that the task is visible to the user through the model
     */
    protected void assertTaskVisible(ImmutableTask task) {
        if (!model.getObserveableList().contains(task)) {
            throw new AssertionError("Task is not visible");
        }
    }

    /**
     * Asserts that the task is visible to the user through the model. 
     * This can also mean the task is simply not in memory. Use {@link #assertTaskHidden}
     * to assert that the task exists, but is not visible
     */
    protected void assertTaskNotVisible(ImmutableTask task) {
        if (model.getObserveableList().contains(task)) {
            throw new AssertionError("Task is visible");
        }
    }
    
    /**
     * Asserts that the task exists, but is not visible to the user through 
     * the model
     */
    protected void assertTaskHidden(ImmutableTask task) {
        assertTaskExist(task); 
        assertTaskNotVisible(task);
    }
    
    /**
     * Sets the positional parameter for command execution. Can be chained. 
     */
    protected CommandTest setParameter(String positional) {
        params.positional = positional;
        return this;
    }
    
    /**
     * Sets the named argument for command execution. Can be chained. 
     */
    protected CommandTest setParameter(String flag, String value) {
        params.named.put(flag, value);
        return this;
    }
    
    /**
     * Executes the command
     * @throws IllegalValueException
     * @throws ValidationException 
     */
    protected void execute() throws IllegalValueException, ValidationException {
        command.setArguments(params);
        command.setModel(model);
        command.execute();
    }
}
