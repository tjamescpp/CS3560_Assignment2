import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/*
 * Design patterns: Observer and Composite
 * Note: This class is an observable
 */

public class Users extends UserComponent {

    private String username;
    private String userGroup;
    private String tweet = "";
    private ArrayList<Users> followersList;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private SocialMediaFeed feed;

    public Users() {

    }

    public Users(String username) {
        this.username = username;
        followersList = new ArrayList<Users>();
        feed = new SocialMediaFeed();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        support.firePropertyChange("status", this.tweet, tweet);
        this.tweet = tweet;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void userInfo() {
        System.out.println("User info: \n" + getUsername() + "\n" + getUserGroup());
    }

    public ArrayList<Users> getFollowersList() {
        return followersList;
    }

    public void setFollowersList(ArrayList<Users> followersList) {
        this.followersList = followersList;
    }

    public SocialMediaFeed getFeed() {
        return feed;
    }

    public void setFeed(SocialMediaFeed feed) {
        this.feed = feed;
    }

}
