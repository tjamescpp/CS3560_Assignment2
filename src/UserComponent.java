/*
 * Design pattern: Composite
 */

public abstract class UserComponent {
    public void add(UserComponent newUserComponent) {
        throw new UnsupportedOperationException();
    }

    public void remove(UserComponent newUserComponent) {
        throw new UnsupportedOperationException();
    }

    public UserComponent getComponent(int componentIndex) {
        throw new UnsupportedOperationException();
    }

    public String getUsername() {
        throw new UnsupportedOperationException();
    }

    public String getGroupName() {
        throw new UnsupportedOperationException();
    }

    public int getUserId() {
        throw new UnsupportedOperationException();
    }

    public void displayUserInfo() {
        throw new UnsupportedOperationException();
    }
}
