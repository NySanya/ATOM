package Forms;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MainForm extends JFrame {

    private static JPanel rootPanel = new JPanel();
    private  Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public MainForm() throws IOException {
        super("ATOM");
        super.setMinimumSize(new Dimension(900,600));
        super.setLayout(new BorderLayout());
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createUIComponents(ImageIO.read(new File("lib/back.jpg")));
        rootPanel.add(new InPanel());
        rootPanel.setPreferredSize(new Dimension(900,600));

        super.add(rootPanel);
        super.setVisible(true);
        super.revalidate();
        super.repaint();

    }

    public static void setRP(JPanel j){
        rootPanel.removeAll();
        rootPanel.setLayout(new GridLayout());
        rootPanel.add(j);
        rootPanel.revalidate();
        rootPanel.repaint();

    }

   public void createUIComponents( BufferedImage img){
        rootPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(img,0,0,(int)dim.getWidth(),(int)dim.getHeight(), this);
            }
        };
   }





}
