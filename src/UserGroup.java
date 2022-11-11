import java.util.ArrayList;
import java.util.Iterator;

/*
 * Design pattern: Composite
 */

public class UserGroup extends UserComponent {
    ArrayList<UserComponent> userList = new ArrayList<UserComponent>();

    String userGroupName;

    public UserGroup(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public ArrayList<UserComponent> getUserComonents() {
        return userList;
    }

    public void setUserList(ArrayList<UserComponent> userList) {
        this.userList = userList;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public void add(UserComponent newUser) {
        userList.add(newUser);
    }

    public void remove(Users user) {
        userList.remove(user);
    }

    public UserComponent getComponent(int componentIndex) {
        return (UserComponent) userList.get(componentIndex);
    }

    public void displayUserInfo() {
        System.out.println(getGroupName() + "\n");

        Iterator<UserComponent> userIterator = userList.iterator();

        while (userIterator.hasNext()) {
            UserComponent userInfo = (UserComponent) userIterator.next();
            userInfo.displayUserInfo();
        }
    }

}
