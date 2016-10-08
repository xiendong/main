package seedu.todo.commons.util;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests TimeUtil class
 * In this test, today is 3 May 2016, 13:15:10
 */
public class TimeUtilTest {
    
    /**
     * A subclass of TimeUtil that provides the ability to override the current system time, 
     * so that time sensitive components can be conveniently tested. 
     */
    private class ModifiedTimeUtil extends TimeUtil {
        
        /**
         * Construct a ModifiedTimeUtil object overriding the current time with Clock object.
         * Is only used for dependency injection in testing time sensitive components.
         */
        private ModifiedTimeUtil (Clock clock) {
            this.clock = clock;
        }
        
        /**
         * Construct a ModifiedTimeUtil object overriding the current time with LocalDateTime object.
         * Is only used for dependency injection in testing time sensitive components.
         */
        public ModifiedTimeUtil (LocalDateTime pseudoCurrentTime) {
            this(Clock.fixed(pseudoCurrentTime.toInstant(ZoneId.systemDefault().getRules().getOffset(pseudoCurrentTime)), ZoneId.systemDefault()));
        }
    }
        
    @Test (expected = AssertionError.class)
    public void getTaskDeadlineString_nullEndTime() {
        TimeUtil timeUtil = new TimeUtil();
        timeUtil.getTaskDeadlineString(null);
    }
    
    @Test
    public void getTaskDeadlineString_dueNow() {
        
        TimeUtil timeUtil = new ModifiedTimeUtil(LocalDateTime.of(2016, Month.MARCH, 20, 12, 00, 00));
        String expectedOutput = "due now";
        
        LocalDateTime inputTime1 = LocalDateTime.of(2016, Month.MARCH, 20, 12, 00, 00);
        String generatedOutput1 = timeUtil.getTaskDeadlineString(inputTime1);
        assertEquals(expectedOutput, generatedOutput1);
        
        LocalDateTime inputTime2 = LocalDateTime.of(2016, Month.MARCH, 20, 12, 00, 59);
        String generatedOutput2 = timeUtil.getTaskDeadlineString(inputTime2);
        assertEquals(expectedOutput, generatedOutput2);
    }
}
