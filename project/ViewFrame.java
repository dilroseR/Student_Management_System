import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;


class ViewFrame extends JFrame{
Container c;
JButton btnBack;
TextArea taData;

ViewFrame(){
c=getContentPane();
c.setLayout(new FlowLayout());

taData=new TextArea(12,50);
btnBack=new JButton("Back");

c.add(taData);		c.add(btnBack);

ActionListener a1=(ae)->{
MainFrame a=new MainFrame();
dispose();
};
btnBack.addActionListener(a1);

String data=DbHandler.viewStudent();
taData.setText(data);

setTitle("View St.");
setSize(400,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}