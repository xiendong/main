package seedu.todo.commons.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility methods that deals with time.
 */
public class TimeUtil {
    
    /* Constants */
    private static final String DEADLINE_PREFIX_IN = "in ";
    private static final String DEADLINE_PREFIX_BY = "by ";
    private static final String DEADLINE_PREFIX_SINCE = "since ";
    private static final String DEADLINE_SUFFIX_AGO = " ago";
    
    private static final String HOUR_SINGLE_UNIT = " hour";
    private static final String HOURS_MULTIPLE_UNIT = " hours";
    private static final String MINUTE_SINGLE_UNIT = " minute";
    private static final String MINUTES_MULTIPLE_UNIT = " minutes";
    
    private static final String DUE_NOW = "right now";
    
    /* Variables */
    private final Clock clock;
    
    /**
     * Constructs a TimeUil object using system clock.
     */
    public TimeUtil () {
        this.clock = Clock.systemDefaultZone();
    }
    
    /**
     * Construct a TimeUtil object with a custom clock.
     * Can be used for dependency injection in testing (to override system time)
     */
    public TimeUtil (Clock clock) {
        this.clock = clock;
    }
    
    /**
     * Gets the task deadline expression for the UI.
     * @param endTime ending time
     * @return a formatted deadline String
     */
    public String getTaskDeadlineString(LocalDateTime endTime) {
        assert(endTime != null);
        
        LocalDateTime currentTime = LocalDateTime.now(clock);
        Duration durationCurrentToEnd = Duration.between(currentTime, endTime);
        
        long daysToDeadline = durationCurrentToEnd.toDays();
        long hoursToDeadline = durationCurrentToEnd.toHours();
        long minutesToDeadline = durationCurrentToEnd.toMinutes();
        
        if (currentTime.getYear() < endTime.getYear()) {
            return DEADLINE_PREFIX_BY + endTime.format(DateTimeFormatter.ofPattern("d MMMM yyyy, h:m a"));
        }
        
        if (minutesToDeadline == 0) {
            return DUE_NOW;
        }
        
        if (hoursToDeadline == 0) {
            
            if (minutesToDeadline == 1) {
                return DEADLINE_PREFIX_IN + "1" + MINUTE_SINGLE_UNIT;
            } else {
                return DEADLINE_PREFIX_IN + String.valueOf(minutesToDeadline) + MINUTES_MULTIPLE_UNIT;
            }
                      
        } else if (hoursToDeadline == 1) {
            return DEADLINE_PREFIX_IN + "1" + HOUR_SINGLE_UNIT;
        } else {
            return DEADLINE_PREFIX_IN + String.valueOf(hoursToDeadline) + HOURS_MULTIPLE_UNIT;
        }
    }
    
    
    public String getEventTimeString(LocalDateTime startTime, LocalDateTime endTime) {
        assert(startTime != null);
        assert(endTime != null);
        assert(startTime.isBefore(endTime));
        
        return null;
    }
    
}
