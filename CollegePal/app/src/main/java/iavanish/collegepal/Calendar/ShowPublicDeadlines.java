
package iavanish.collegepal.Calendar;

import java.util.ArrayList;

import iavanish.collegepal.CommonClasses.Date;

/**
 *
 * @author iavanish
 */

/**
 * 
 * Show public deadlines on a specific date in the calendar
 */

public class ShowPublicDeadlines implements Visitable {

    String courseID;
    String deadlineId;
    String deadlineDate;
    String deadlineDetails;
    String deadlineType;
    int percentage;

    @Override
    //  accept
    public ArrayList findSchedule(Visitor visitor) {
        return null;
    }
}
