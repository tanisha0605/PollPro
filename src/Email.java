import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class Email extends JFrame {
    private JTextField toField;
    private JTextField subjectField;
    private JTextArea messageArea;

    public Email() {
        setTitle("Email Sender");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel toLabel = new JLabel("To:");
        toField = new JTextField();
        JLabel subjectLabel = new JLabel("Subject:");
        subjectField = new JTextField();
        JLabel messageLabel = new JLabel("Message:");
        messageArea = new JTextArea();

        JButton sendButton = new JButton("Send");

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendEmail();
            }
        });

        panel.add(toLabel);
        panel.add(toField);
        panel.add(subjectLabel);
        panel.add(subjectField);
        panel.add(messageLabel);
        panel.add(new JScrollPane(messageArea));
        panel.add(sendButton);

        add(panel);
    }

    private void sendEmail() {
        final String username = "your_email@gmail.com"; // Your email address
        final String password = "your_password"; // Your email password

        String to = toField.getText();
        String subject = subjectField.getText();
        String message = messageArea.getText();

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Use the SMTP server of your email provider (e.g., smtp.yourprovider.com)
        props.put("mail.smtp.port", "587"); // Port for TLS

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(username));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            emailMessage.setSubject(subject);
            emailMessage.setText(message);

            Transport.send(emailMessage);

            JOptionPane.showMessageDialog(this, "Email sent successfully!");
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(this, "Email could not be sent. Please check your credentials and try again.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Email().setVisible(true);
            }
        });
    }
}
