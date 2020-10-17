import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DeleteFrame extends JFrame{
Container c;
JLabel lblRno;
JButton btnSave,btnBack;
JTextField txtRno;

DeleteFrame(){
c=getContentPane();
c.setLayout(new FlowLayout());

lblRno=new JLabel("Enter rno:");
txtRno=new JTextField(25);
btnSave=new JButton("Save");
btnBack=new JButton("Back");

c.add(lblRno);  c.add(txtRno);
c.add(btnSave);	c.add(btnBack);

ActionListener a1=(ae)->{
MainFrame a=new MainFrame();
dispose();
};
btnBack.addActionListener(a1);

ActionListener a2=(ae)->{
try{
int rno=Integer.parseInt(txtRno.getText());

if(rno<0 || Integer.toString(rno)==null)
throw new NumberFormatException();

DbHandler.deleteStudent(rno);
}
catch(NumberFormatException e){
JOptionPane.showMessageDialog(c, "only positive integers in rno field & should be non-empty","Wrong input",JOptionPane.ERROR_MESSAGE);
}
txtRno.setText("");
txtRno.requestFocus();

};
btnSave.addActionListener(a2);

setTitle("Delete St.");
setSize(350,350);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}
}