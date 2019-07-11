package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;

public class UserInfoFrame extends JFrame{

    public UserInfoFrame() {
        getContentPane().setBackground(Color.DARK_GRAY);
        setTitle("User Info");
        setVisible(true);
        setResizable(false);
        setSize(400, 300);
        getContentPane().setLayout(null);
    }

    public static void showUserInfo (){
        JOptionPane.showMessageDialog(new UserInfoFrame(),
                "I appear as part of the frame!!", "Customized Dialog",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
