import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PollPro extends JFrame {
    PollPro(){
        ImageIcon imageicon=new ImageIcon(ClassLoader.getSystemResource("icon/PollPro.jpg"));
        Image image1=imageicon.getImage().getScaledInstance(500,500,Image.SCALE_DEFAULT);
        ImageIcon image2=new ImageIcon(image1);
        JLabel imagelabel=new JLabel(image2);
        add(imagelabel);
        setSize(500,500);
        setVisible(true);
        setLocation(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try{
            Thread.sleep(2000);
            setVisible(false);
            new Login();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }



    public static void main(String args[]) throws SQLException {
        new PollPro();
        Login login = new Login();
        login.loginView();
    }
}
