import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/*
 * Desing pattern: Observer
 * Note: This class is the observer
 */
public class SocialMediaFeed implements PropertyChangeListener {
    private ArrayList<String> tweets;

    public SocialMediaFeed() {
        tweets = new ArrayList<>();
    }

    public void printStatuses() {
        tweets.forEach(System.out::println);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        tweets.add((String) event.getNewValue());
    }

    public ArrayList<String> getTweets() {
        return tweets;
    }

    public void setTweets(ArrayList<String> tweets) {
        this.tweets = tweets;
    }

    public void addTweets(String tweet) {
        tweets.add(tweet);
    }

}
