import GUI.*;

import javax.swing.*;

public class ApplicationMain
{
    public static void main(String[] args)
    {
        try {
            new MainWindow();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Unknown error!");
        }

    }
}
