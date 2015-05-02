package iavanish.collegepal.Calendar;

/**
 * Created by iavanish on 02-May-15.
 */
public interface Visitor {

    //  visit()
    public void schedulePrivateDeadlines(ShowPrivateDeadlines privateDeadlines);

    //  visit()
    public void schedulePublicDeadlines(ShowPublicDeadlines publicDeadlines);

}
