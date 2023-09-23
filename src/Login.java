import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login {

    int id;

    public void loginView() throws SQLException {
        SQLManage manage = new SQLManage();

        JFrame frame = new JFrame();
        frame.setSize(600, 300);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.white);
        frame.setLocation(400, 200);
        frame.setTitle("Login");

        JLabel username = new JLabel("Username");
        username.setBounds(300, 60, 100, 20);
       frame.add(username);

        JTextField userText = new JTextField();
        userText.setBounds(380, 60, 150, 20);
        frame.add(userText);

        JLabel password = new JLabel("Password");
        password.setBounds(300, 100, 100, 20);
        frame.add(password);

        JPasswordField passText = new JPasswordField();
        passText.setBounds(380, 100, 150, 20);
        frame.add(passText);

        JButton login = new JButton("Login");
        Color customcolor=new Color(2, 60, 88);
        login.setForeground(customcolor);
        login.setBounds(300, 180, 100, 20);
        frame.add(login);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = passText.getText();
                if(username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please Enter All Details!!!", "Warning Message", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    try {
                        SQLManage manage= new SQLManage();
                        id = manage.authUser(username, password);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    if (id == -1) {
                        JOptionPane.showMessageDialog(frame, "No User Found!!!", "Warning Message", JOptionPane.WARNING_MESSAGE);
                    }
                    else if(id == 0) {
                        JOptionPane.showMessageDialog(frame, "Wrong Password!!!", "Warning Message", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        MainPage mainPage = new MainPage();
                        try {
                            mainPage.mainPageView(id);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        frame.dispose();
                    }
                }
            }
        });

        JButton signup = new JButton("Signup");
        signup.setForeground(customcolor);
        signup.setBounds(420, 180, 100, 20);
        frame.add(signup);
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp signup = new SignUp();
                signup.signUpView();
            }
        });
        JButton guest = new JButton("Attend as Guest(voter)");
        guest.setForeground(customcolor);
        guest.setBounds(300, 150, 220, 20);
        frame.add(guest);
        guest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String surveyCode = JOptionPane.showInputDialog("Enter the Survey Code : ");
                try {
                    if(!surveyCode.isEmpty() && surveyCode.length() == 5) {
                        if(manage.check(surveyCode)) {
                            Guest guest = new Guest();
                            guest.guestView(surveyCode);
                        }
                        else {
                            JOptionPane.showMessageDialog(frame, "No Survey Available!!!", "Warning Message", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                catch(Exception e2) {

                }
            }
        });
        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("icon/PollPro.jpg"));
        Image profile2 = profileOne.getImage().getScaledInstance(250, 300, Image.SCALE_DEFAULT);
        ImageIcon profile1 = new ImageIcon(profile2);
        JLabel profileLabel = new JLabel(profile1);
        profileLabel.setBounds(5, 5, 250, 250);
        frame.add(profileLabel);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
