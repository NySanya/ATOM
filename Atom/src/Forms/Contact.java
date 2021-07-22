package Forms;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Contact extends JPanel implements MouseListener, MouseMotionListener {
    DialogPanel dp = new DialogPanel();
    User I ;
    User out;
    JPanel jp ;
    boolean b = false;
    static int c = 0;
    Contact(User names, JPanel j, User Iid){
        I = new User(Iid.getName(),Iid.getId());
        out = new User(names.getName(),names.getId());
        jp = j;
        c++;
        super.setBorder(BorderFactory.createLineBorder(null,2));
        JLabel name = new JLabel(names.getName());
        name.setFont(new Font("Dialog", Font.PLAIN, 18));
        name.setForeground(Color.WHITE);
        name.setHorizontalAlignment(SwingConstants.CENTER);
        super.setFocusable(false);
        super.setLayout(new BorderLayout());

        setPreferredSize(new Dimension(300,60));
        super.setBackground(new Color(101,166,209));
        setVisible(true);
        super.setBounds(0,0,0,0);
        name.setPreferredSize(new Dimension(250,60));
        super.add(name);

        addMouseListener( this);
        addMouseMotionListener( this);


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.setBackground(Color.BLUE);
        jp.removeAll();
        paintMessage();
        jp.add(dp);
        jp.revalidate();
        jp.repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.setBackground(new Color(101,166,209));
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

        if(b =true){
            super.setBackground(new Color(101,166,209));
            b= false;
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(!b) {
            super.setBackground(Color.GRAY);
            super.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    void paintMessage(){
        try{

            Socket s1 = new Socket("192.168.0.20",50000);
            DataOutputStream serOut1 = new DataOutputStream(s1.getOutputStream());
            serOut1.writeUTF("PRINTM");

            Socket s = new Socket("192.168.0.20",50003);

            DataOutputStream serOut = new DataOutputStream(s.getOutputStream());
            DataInputStream serIn = new DataInputStream(s.getInputStream());

            serOut.writeInt(I.getId());
            serOut.writeInt(out.getId());


            int countMessage = serIn.readInt();
            String str[] = new String[countMessage];

            for(int i = 0 ; i < countMessage; i++){
                 str[i] = serIn.readUTF();
            }


            for(int i = 0;i < countMessage ; i++){
                int n = Integer.parseInt(str[i].substring(0,1)) ;
                String strOUT = str[i].substring(2,str[i].length());
                if(n == out.getId()){
                    dp.inMessage(strOUT);

                }
                if(n == I.getId()){
                    dp.outMessage(strOUT);

                }
            }

        }catch (Exception e){}

    }
}
