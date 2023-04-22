import javax.swing.*;
public class InputStaticmethods 
{
    public static String inputString(String message){
        String input = JOptionPane.showInputDialog(null, message);
        return input;
    }

    public static int inputInteger(String message){
        int input = Integer.parseInt(inputString(message));
        return input;
    }

    public static double inputDouble(String message){
        double input = Double.parseDouble(inputString(message));
        return input;
    }
}
