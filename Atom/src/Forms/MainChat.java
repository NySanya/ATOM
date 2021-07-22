package Forms;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class MainChat extends JPanel {
    static int count ;
    JPanel jpNorth = new JPanel();
    public JPanel jpCenter = new JPanel();
    JPanel jpSouth = new JPanel();
    JPanel jpEast = new JPanel();
    JPanel jpWest = new JPanel();
    JLabel nameUser ;
    JButton bExit = new JButton();
    MainChat(User u){
        nameUser = new JLabel(u.getName());
        count = 0;

        super.setLayout(new BorderLayout());
        super.add(jpCenter);
        super.add(jpNorth, BorderLayout.NORTH);
        super.add(jpWest,BorderLayout.WEST);
        super.add(jpSouth, BorderLayout.SOUTH);
        super.add(jpEast, BorderLayout.EAST);


        //======================= NORTH============================//
        jpNorth.setLayout(new BorderLayout());
        jpNorth.add(nameUser,BorderLayout.EAST);
        jpNorth.setBorder(BorderFactory.createMatteBorder(0,0,2,0,new Color(253,253,253)));
        nameUser.setForeground(Color.WHITE);
        nameUser.setFont(new Font("Dialog", Font.PLAIN, 20));
        nameUser.setPreferredSize(new Dimension(500,60));
        nameUser.setHorizontalAlignment(JLabel.RIGHT);
        nameUser.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));

        //======================= WEST============================//
        jpWest.setPreferredSize(new Dimension(300,240));
        jpWest.setBorder(BorderFactory.createEmptyBorder());

        try {


            Socket s = new Socket("192.168.0.20",50002);

            DataOutputStream serOut = new DataOutputStream(s.getOutputStream());
            DataInputStream serIn = new DataInputStream(s.getInputStream());

            int i = serIn.readInt();
            int[] id = new int[i];
            String names[] = new String[i];
            for(int j = 0 ; j < i; j++){
                names[j]= serIn.readUTF();
                id[j] = serIn.readInt();
            }



            User[] users = new User[names.length];
            for(int k =0; k< names.length;k++) {
                if( names[k].equals(u.getName())) {
                    u.setId(id[k]);
                }
                users[k]= new User(names[k], id[k] );
            }



            for (int k =0; k< names.length;k++) {
                
                if(!names[k].equals( u.getName())) {
                    jpWest.add(new Contact(users[k], jpCenter,u));
                }
            }

            jpCenter.setLayout(new BorderLayout());


        } catch (IOException e) {
            e.printStackTrace();
        }




        //======================= SOUTH============================//

        jpSouth.setLayout(new BorderLayout());
        bExit.setContentAreaFilled(false);
        bExit.setBorderPainted(false);
        bExit.setIcon(new ImageIcon("lib/exit.png"));
        bExit.setPreferredSize(new Dimension(32,32));
        jpSouth.add(bExit,BorderLayout.EAST);
        //======================= East============================//

        jpEast.setPreferredSize(new Dimension(0,0));

        super.setOpaque(false);
        jpCenter.setOpaque(false);
        jpNorth.setOpaque(false);
        jpSouth.setOpaque(false);
        jpEast.setOpaque(false);
        jpWest.setOpaque(false);

        super.setVisible(true);
        super.revalidate();

    }

    JPanel getRootPanel(){
        return this;
    }
}
