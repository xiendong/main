package seedu.todo.commons.core;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.todo.commons.core.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConfigTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "App title : Uncle Jim's Discount To-do List\n" +
                "Current log level : INFO\n" +
                "Preference file Location : preferences.json\n" +
                "Local data file location : data/todolist.xml\n" +
                "Todo List name : My Todo List";

        assertEquals(defaultConfigAsString, new Config().toString());
    }

    @Test
    public void equalsMethod(){
        Config defaultConfig = new Config();
        assertFalse(defaultConfig.equals(null));
        assertTrue(defaultConfig.equals(defaultConfig));
    }


}
