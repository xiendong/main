package seedu.todo.logic.commands;

import static org.junit.Assert.*;

import org.junit.Test;

import seedu.todo.model.task.ImmutableTask;
import seedu.todo.testutil.TimeUtil;

public class AddCommandTest extends CommandTest {
    @Override
    protected BaseCommand commandUnderTest() {
        return new AddCommand();
    }

    @Test
    public void testAddTask() throws Exception {
        setParameter("Hello World");
        execute();

        assertCommandSuccess();
        assertTotalTaskCount(1);
        ImmutableTask addedTask = getTaskAt(1);
        assertEquals("Hello World", addedTask.getTitle());
        assertFalse(addedTask.isPinned());
        assertFalse(addedTask.getDescription().isPresent());
        assertFalse(addedTask.getLocation().isPresent());
    }
    
    @Test
    public void testAddTaskWithLocation() throws Exception {
        setParameter("Hello NUS");
        setParameter("l", "NUS");
        execute();
        
        ImmutableTask taskWithLocation = getTaskAt(1);
        assertTotalTaskCount(1);
        assertEquals("Hello NUS", taskWithLocation.getTitle());
        assertFalse(taskWithLocation.isPinned());
        assertFalse(taskWithLocation.getDescription().isPresent());
        assertEquals("NUS", taskWithLocation.getLocation().get());

        assertCommandSuccess();
    }
    
    @Test
    public void testAddTaskWithDescription() throws Exception {
        setParameter("Destroy World");
        setParameter("m", "Remember to get Dynamites on sale!");
        execute();
        
        ImmutableTask taskWithDescription = getTaskAt(1);
        assertTotalTaskCount(1);
        assertEquals("Destroy World", taskWithDescription.getTitle());
        assertEquals("Remember to get Dynamites on sale!", taskWithDescription.getDescription().get());
        assertFalse(taskWithDescription.isPinned());
        assertFalse(taskWithDescription.getLocation().isPresent());

        assertCommandSuccess();
    }
    
    @Test
    public void testAddPinnedTask() throws Exception {
        setParameter("Li Kai's Presentation");
        setParameter("p", null);
        execute();
        
        ImmutableTask pinnedAddedTask = getTaskAt(1);
        assertTotalTaskCount(1);
        assertEquals("Li Kai's Presentation", pinnedAddedTask.getTitle());
        assertTrue(pinnedAddedTask.isPinned());
        assertFalse(pinnedAddedTask.getDescription().isPresent());
        assertFalse(pinnedAddedTask.getLocation().isPresent());

        assertCommandSuccess();
    }
    
    @Test
    public void testAddSingleDate() throws Exception {
        setParameter("Test Task");
        setParameter("d", "tomorrow 9am");
        execute();
        
        ImmutableTask task = getTaskAt(1);
        assertFalse(task.isEvent());
        assertEquals(TimeUtil.tomorrow().withHour(9), task.getEndTime().get());

        assertCommandSuccess();
    }

    @Test
    public void testAddDateRange() throws Exception {
        setParameter("Test Event");
        setParameter("d", "tomorrow 6 to 8pm");
        execute();

        ImmutableTask task = getTaskAt(1);
        assertTrue(task.isEvent());
        assertEquals(TimeUtil.tomorrow().withHour(18), task.getStartTime().get());
        assertEquals(TimeUtil.tomorrow().withHour(20), task.getEndTime().get());
        
        assertCommandSuccess();
    }
    
    @Test
    public void testAddMultipleParameters() throws Exception {
        setParameter("Task 1");
        setParameter("p", null);
        setParameter("l", "COM1");
        setParameter("m", "Useless task");
        execute();
        
        ImmutableTask taskWithParams = getTaskAt(1);
        assertTotalTaskCount(1);
        assertEquals("Task 1", taskWithParams.getTitle());
        assertTrue(taskWithParams.isPinned());
        assertEquals("COM1", taskWithParams.getLocation().get());
        assertEquals("Useless task", taskWithParams.getDescription().get());
        
        assertCommandSuccess();
    }
}
