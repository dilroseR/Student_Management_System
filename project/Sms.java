import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainFrame extends JFrame{
Container c;
JButton btnAdd,btnView,btnUpdate,btnDelete,btnCharts;


MainFrame(){
c=getContentPane();
c.setLayout(new FlowLayout());

btnAdd=new JButton("Add");
btnView=new JButton("View");
btnUpdate=new JButton("Update");
btnDelete=new JButton("Delete");
btnCharts=new JButton("Charts");

c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);
c.add(btnCharts);

ActionListener a1=(ae)->{
AddFrame a=new AddFrame();
dispose();
};
btnAdd.addActionListener(a1);

ActionListener a2=(ae)->{
ViewFrame a=new ViewFrame();
dispose();
};
btnView.addActionListener(a2);

ActionListener a3=(ae)->{
UpdateFrame a=new UpdateFrame();
dispose();
};
btnUpdate.addActionListener(a3);

ActionListener a4=(ae)->{
DeleteFrame a=new DeleteFrame();
dispose();
};
btnDelete.addActionListener(a4);

ActionListener a5=(ae)->{
DbHandler db=new DbHandler();
db.chartsStudent();
};
btnCharts.addActionListener(a5);




setTitle("S.M.S");
setSize(350,350);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}

class Sms{
public static void main(String args[]){
MainFrame m=new MainFrame();
}
}