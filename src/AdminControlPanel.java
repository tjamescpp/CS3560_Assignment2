import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class AdminControlPanel extends JPanel implements ActionListener, MouseListener {

    private static final AdminControlPanel adminControl = new AdminControlPanel();
    private static boolean initialized = false;

    // text fields
    private JTextField userIdTextField;
    private JTextField groupIdTextField;

    // buttons
    private JButton addUserButton;
    private JButton addGroupButton;
    private JButton openUserViewButton;
    private JButton showUserTotalButton;
    private JButton showGroupTotalButton;
    private JButton showMessageTotalButton;
    private JButton showPositivePercentageButton;

    // panels
    private JPanel topRightPanel;
    private JPanel bottomRightPanel;

    // labels
    private JLabel userIdLabel;
    private JLabel groupIdLabel;

    // class objects
    private static MainFrame mainFrame;
    private Users twitterUser;
    private TreeView treeView;
    private UserViewPanel userView;

    // array lists
    ArrayList<Users> userList;
    ArrayList<UserGroup> groupList;
    ArrayList<String> tweetList;
    ArrayList<String> positiveWordsList;

    // tree components
    DefaultMutableTreeNode root;
    DefaultMutableTreeNode john;
    DefaultMutableTreeNode sally;
    DefaultMutableTreeNode cs3560;
    DefaultMutableTreeNode david;

    DefaultMutableTreeNode selectedNode;
    DefaultTreeModel treeModel;

    public AdminControlPanel() {
    }

    public static synchronized AdminControlPanel getInstance(MainFrame mainFrame) {

        setMainFrame(mainFrame);

        if (initialized) {
            return adminControl;
        } else {
            adminControl.initComponents();
            initialized = true;
            return adminControl;
        }

    }

    private void initComponents() {

        // init admin control panel
        this.setBackground(Color.lightGray);
        this.setBounds(0, 0, 600, 400);
        this.setLayout(null);

        // init array lists
        userList = new ArrayList<Users>();
        groupList = new ArrayList<UserGroup>();
        tweetList = new ArrayList<String>();
        positiveWordsList = new ArrayList<String>();

        // init text areas
        userIdTextField = new JTextField();
        groupIdTextField = new JTextField();

        // init labels
        userIdLabel = new JLabel("User ID");
        groupIdLabel = new JLabel("Group ID");

        // init buttons
        addUserButton = new JButton("Add User");
        addUserButton.addActionListener(this);

        addGroupButton = new JButton("Add Group");
        addGroupButton.addActionListener(this);
        openUserViewButton = new JButton("Open User View");
        openUserViewButton.addActionListener(this);
        showUserTotalButton = new JButton("Show User Total");
        showGroupTotalButton = new JButton("Show Group Total");
        showMessageTotalButton = new JButton("Show Message Total");
        showPositivePercentageButton = new JButton("Show Positive Percentage");

        // init tree compnents
        root = new DefaultMutableTreeNode("Users");
        treeModel = new DefaultTreeModel(root);

        // john = new DefaultMutableTreeNode("John");
        // sally = new DefaultMutableTreeNode("Sally");
        // cs3560 = new DefaultMutableTreeNode("CS3560");
        // david = new DefaultMutableTreeNode("David");

        // tree
        // root.add(john);
        // root.add(sally);
        // cs3560.add(david);
        // root.add(cs3560);

        treeView = new TreeView();
        treeView.setEditable(true);
        treeView.setBounds(0, 0, 150, 400);
        treeView.addMouseListener(this);

        this.add(treeView);

        // panels
        topRightPanel = new JPanel();
        bottomRightPanel = new JPanel();
        addBottomRightPanel();

        // labels
        userIdLabel.setBounds(250, 15, 75, 15);
        groupIdLabel.setBounds(250, 75, 75, 15);

        // text fields
        userIdTextField.setBounds(250, 30, 100, 25);
        groupIdTextField.setBounds(250, 90, 100, 25);

        // buttons
        addUserButton.setBounds(400, 30, 100, 25);
        addGroupButton.setBounds(400, 90, 100, 25);
        openUserViewButton.setBounds(250, 150, 250, 25);
        openUserViewButton.setEnabled(false);

        // add components
        this.add(userIdTextField);
        this.add(groupIdTextField);
        this.add(userIdLabel);
        this.add(groupIdLabel);
        this.add(addUserButton);
        this.add(addGroupButton);
        this.add(openUserViewButton);

    }

    public void addTopRightPanel() {

        topRightPanel.setBackground(Color.darkGray);
        topRightPanel.setBounds(200, 15, 350, 150);
        topRightPanel.setLayout(null);

        // init text fields
        userIdTextField.setBounds(50, 15, 100, 25);
        groupIdTextField.setBounds(50, 55, 100, 25);

        // init buttons
        addUserButton.setBounds(200, 15, 100, 25);
        addGroupButton.setBounds(200, 55, 100, 25);
        openUserViewButton.setBounds(50, 110, 250, 25);

        // add components
        topRightPanel.add(userIdTextField);
        topRightPanel.add(groupIdTextField);
        topRightPanel.add(addUserButton);
        topRightPanel.add(addGroupButton);
        topRightPanel.add(openUserViewButton);

        // add top right panel to admin control panel
        this.add(topRightPanel);
    }

    public void addBottomRightPanel() {

        bottomRightPanel.setBackground(Color.gray);
        bottomRightPanel.setBounds(150, 250, 450, 125);
        bottomRightPanel.setLayout(null);

        showUserTotalButton.setBounds(25, 20, 175, 35);
        showUserTotalButton.addActionListener(this);

        showGroupTotalButton.setBounds(250, 20, 175, 35);
        showGroupTotalButton.addActionListener(this);

        showMessageTotalButton.setBounds(25, 70, 175, 35);
        showMessageTotalButton.addActionListener(this);

        showPositivePercentageButton.setBounds(250, 70, 175, 35);

        // init buttons
        bottomRightPanel.add(showUserTotalButton);
        bottomRightPanel.add(showGroupTotalButton);
        bottomRightPanel.add(showMessageTotalButton);
        bottomRightPanel.add(showPositivePercentageButton);

        // add bottom right panel to admin control panel
        this.add(bottomRightPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == openUserViewButton) {

            // opens user view
            twitterUser = new Users("@" + selectedNode.getUserObject().toString());
            mainFrame.addUserViewPanel(twitterUser);
            System.out.println("Opened User View");

        } else if (e.getSource() == addUserButton) {

            // adds user to tree and user list
            System.out.println("Added user: " + userIdTextField.getText());

            DefaultMutableTreeNode newUserNode = new DefaultMutableTreeNode(userIdTextField.getText());
            Users newUser = new Users(userIdTextField.getText());
            userList.add(newUser);

            treeView.getTreeModel().insertNodeInto(newUserNode,
                    (MutableTreeNode) treeView.getTreeModel().getRoot(), 0);

            userIdTextField.setText("");

        } else if (e.getSource() == addGroupButton) {

            // adds group to tree and group list
            System.out.println("Added group: " + groupIdTextField);

            UserGroup newGroup = new UserGroup(groupIdTextField.getText());
            DefaultMutableTreeNode newGroupNode = new DefaultMutableTreeNode(newGroup.getUserGroupName());
            groupList.add(newGroup);

            UserGroup section1 = new UserGroup("Section1");
            DefaultMutableTreeNode newGroupNode2 = new DefaultMutableTreeNode("Section1");

            newGroup.add(section1);

            section1.add(new Users("David"));
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("David");

            newGroupNode2.add(newNode);
            newGroupNode.add(newGroupNode2);

            treeView.setUserList(newGroup);

            treeView.getTreeModel().insertNodeInto(newGroupNode,
                    (MutableTreeNode) treeView.getTreeModel().getRoot(), 0);

            groupIdTextField.setText("");

        } else if (e.getSource() == showUserTotalButton) {

            JDialog dialog = new JDialog(mainFrame, "Message");
            JLabel totalUsersLabel = new JLabel("Total users: " + (userList.size() + 1));
            dialog.setBounds(100, 100, 200, 100);
            dialog.add(totalUsersLabel);
            dialog.setVisible(true);

        } else if (e.getSource() == showGroupTotalButton) {

            JDialog dialog = new JDialog(mainFrame, "Message");
            JLabel totalUsersLabel = new JLabel("Total users: " + groupList.size());
            dialog.setBounds(100, 100, 200, 100);
            dialog.add(totalUsersLabel);
            dialog.setVisible(true);
        } else if (e.getSource() == showMessageTotalButton) {

            JDialog dialog = new JDialog(mainFrame, "Message");
            JLabel totalUsersLabel = new JLabel("Total tweets: " + tweetList.size());
            dialog.setBounds(100, 100, 200, 100);
            dialog.add(totalUsersLabel);
            dialog.setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == treeView) {
            selectedNode = (DefaultMutableTreeNode) treeView.getSelectionPath().getLastPathComponent();
            openUserViewButton.setEnabled(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public JButton getOpenUserViewButton() {
        return openUserViewButton;
    }

    public void setOpenUserViewButton(JButton openUserViewButton) {
        this.openUserViewButton = openUserViewButton;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public static void setMainFrame(MainFrame newMainFrame) {
        mainFrame = newMainFrame;
    }

    public ArrayList<Users> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<Users> userList) {
        this.userList = userList;
    }

    public ArrayList<UserGroup> getGroupList() {
        return groupList;
    }

    public void setGroupList(ArrayList<UserGroup> groupList) {
        this.groupList = groupList;
    }

    public ArrayList<String> getTweetList() {
        return tweetList;
    }

    public void setTweetList(ArrayList<String> tweetList) {
        this.tweetList = tweetList;
    }

    public ArrayList<String> getPositiveWordsList() {
        return positiveWordsList;
    }

    public void setPositiveWordsList(ArrayList<String> positiveWordsList) {
        this.positiveWordsList = positiveWordsList;
    }

    public UserViewPanel getUserView() {
        return userView;
    }

    public void setUserView(UserViewPanel userView) {
        this.userView = userView;
    }

    public Users findUser(String username) {

        Users user = new Users(username);
        Boolean found = false;

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username)) {
                user = userList.get(i);
                found = true;
            }
        }

        if (found)
            return user;
        else
            return null;

    }

}
