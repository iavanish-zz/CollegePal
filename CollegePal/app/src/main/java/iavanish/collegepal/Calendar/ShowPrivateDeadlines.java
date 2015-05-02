
package iavanish.collegepal.Calendar;

import java.util.ArrayList;

import iavanish.collegepal.CommonClasses.Date;

/**
 *
 * @author iavanish
 */

/**
 * 
 * Show private deadlines on a specific date in the calendar
 */

public class ShowPrivateDeadlines implements Visitable {

    String courseID;
    String deadlineId;
    String deadlineDate;
    String deadlineDetails;
    String deadlineType;
    int percentage;

    @Override
    //  accept()
    public ArrayList findSchedule(Visitor visitor) {
        return null;
    }
}
