import java.awt.BorderLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

    AdminControlPanel admin;
    UserViewPanel userView;
    Users twitterUser;

    public MainFrame() {
        this.setTitle("Admin Control");
        this.pack();
        this.setSize(600, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        admin = AdminControlPanel.getInstance(this);
        addAdminControlPanel();
    }

    public void addAdminControlPanel() {

        admin.setVisible(true);
        this.add(admin);
    }

    public void backToAdminControlPanel() {

        this.remove(userView);
        this.add(admin);
        this.repaint();
        this.revalidate();
        admin.getOpenUserViewButton().setEnabled(false);
    }

    public void addUserViewPanel(Users twitterUser) {
        this.twitterUser = twitterUser;
        // userView = UserViewPanel.getInstance(this);
        userView = new UserViewPanel();
        userView.setTwitterUser(twitterUser);
        userView.setVisible(true);
        userView.setUserId(this.twitterUser.getUsername());

        admin.setUserView(userView);
        userView.setAdmin(admin);
        // this.setTitle("User View");
        // this.remove(admin);
        // this.add(userView);
        // this.repaint();
        // this.revalidate();
    }
}