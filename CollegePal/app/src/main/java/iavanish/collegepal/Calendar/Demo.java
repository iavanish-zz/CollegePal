package iavanish.collegepal.Calendar;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by iavanish on 02-May-15.
 */
public class Demo {

    public ArrayList <RepresentableDeadline> schedulePublicDeadlines(ArrayList <RepresentableDeadline> representableDeadlines, int date) {

        Iterator <RepresentableDeadline> iterator = representableDeadlines.iterator();

        ArrayList <RepresentableDeadline> returnVal = new ArrayList <RepresentableDeadline> ();

        while(iterator.hasNext()) {

            RepresentableDeadline tempD = iterator.next();

            int deadlineDate = Integer.parseInt(tempD.deadlineDate.substring(0, tempD.deadlineDate.indexOf('/')));
            int noOfDays = deadlineDate - date;
            if(noOfDays < 1) {
                continue;
            }
            if(!tempD.deadlineType.equalsIgnoreCase("public")) {
                continue;
            }
            if(noOfDays > 1) {
                noOfDays -= 1;
            }
            tempD.percentage = 100 / noOfDays;
            returnVal.add(tempD);

        }

        return returnVal;

    }

    public ArrayList <RepresentableDeadline> schedulePrivateDeadlines(ArrayList<RepresentableDeadline> representableDeadlines, int date) {

        Iterator <RepresentableDeadline> iterator = representableDeadlines.iterator();

        ArrayList <RepresentableDeadline> returnVal = new ArrayList <RepresentableDeadline> ();

        while(iterator.hasNext()) {

            RepresentableDeadline tempD = iterator.next();

            int deadlineDate = Integer.parseInt(tempD.deadlineDate.substring(0, tempD.deadlineDate.indexOf('/')));
            int noOfDays = deadlineDate - date;
            if(noOfDays < 1) {
                continue;
            }
            if(!tempD.deadlineType.equalsIgnoreCase("private")) {
                continue;
            }
            tempD.percentage = 100 / noOfDays;
            returnVal.add(tempD);

        }

        return returnVal;

    }

}
