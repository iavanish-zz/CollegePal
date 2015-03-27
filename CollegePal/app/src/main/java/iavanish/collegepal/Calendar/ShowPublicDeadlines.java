
package iavanish.collegepal.Calendar;

import iavanish.collegepal.CommonClasses.Date;

/**
 *
 * @author iavanish
 */

/**
 * 
 * Show public deadlines on a specific date in the calendar
 */

public class ShowPublicDeadlines extends ShowDetailsOfDate {
    
    public ShowPublicDeadlines(Date date) {
        super(date);
    }
    
    @Override
    protected void showPrivateDeadLines() {
        super.showPrivateDeadLines();
    }
    
}
