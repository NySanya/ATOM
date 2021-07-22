import Forms.MainForm;
import java.awt.*;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
    try{

        MainForm atom = new MainForm();
        Image img = Toolkit.getDefaultToolkit().getImage("lib/logo.png");
        atom.setIconImage (img);
        }catch (Exception e){
            System.out.print(e.getMessage());
        }

    }
}
