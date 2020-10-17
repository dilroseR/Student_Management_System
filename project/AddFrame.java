import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class AddFrame extends JFrame {
Container c;
JLabel lblRno,lblName,lblSub1,lblSub2,lblSub3;
JTextField txtRno,txtName,txtSub1,txtSub2,txtSub3;
JButton btnSave,btnBack;

AddFrame(){
c=getContentPane();
c.setLayout(new FlowLayout());

lblRno=new JLabel("Enter rno:");
txtRno=new JTextField(25);

lblName=new JLabel("Enter name:");
txtName=new JTextField(25);

lblSub1=new JLabel("Enter marks in sub 1:");
txtSub1=new JTextField(25);

lblSub2=new JLabel("Enter marks in sub 2:");
txtSub2=new JTextField(25);

lblSub3=new JLabel("Enter marks in sub 3:");
txtSub3=new JTextField(25);

btnSave=new JButton("Save");
btnBack=new JButton("Back");

c.add(lblRno);		c.add(txtRno);
c.add(lblName);		c.add(txtName);
c.add(lblSub1);		c.add(txtSub1);
c.add(lblSub2);		c.add(txtSub2);
c.add(lblSub3);		c.add(txtSub3);
c.add(btnSave);		c.add(btnBack);

class MyException extends Exception{
public MyException() {}

    public MyException(String message) {
       super(message);
    }
}

ActionListener a1=(ae)->{
MainFrame a=new MainFrame();
dispose();
};
btnBack.addActionListener(a1);


ActionListener a2=(ae)->{
try{
int rno=Integer.parseInt(txtRno.getText());
String name=txtName.getText();
int s1=Integer.parseInt(txtSub1.getText());
int s2=Integer.parseInt(txtSub2.getText());
int s3=Integer.parseInt(txtSub3.getText());

if(rno<0 || Integer.toString(rno)==null)
throw new NumberFormatException();

if(name.isEmpty() || name==null)
throw new IllegalArgumentException();

char c[]=new char[name.length()];
for(int i=0;i<name.length();i++){
char s=name.charAt(i);
if((s>='a' && s<='z') || (s>='A' && s<='Z') )
c[i]=s;
else
throw new IllegalArgumentException();
}

if(name.length()<2)
throw new AssertionError();

if(Integer.toString(s1)==null || Integer.toString(s2)==null || Integer.toString(s3)==null)
throw new NumberFormatException();

if(s1<0 || s1>100 || s2<0 || s2>100 || s3<0 || s3>100)
throw new MyException();

DbHandler.addStudent(rno,name,s1,s2,s3);
}

catch(MyException e3){
JOptionPane.showMessageDialog(c, "marks should be in the range of 0 to 100 ","Wrong input",JOptionPane.ERROR_MESSAGE);
}

catch(NumberFormatException e){
JOptionPane.showMessageDialog(c, "only positive integers in rno field with rno, name, marks fields non-empty","Wrong input",JOptionPane.ERROR_MESSAGE);
}

catch(IllegalArgumentException e2){
JOptionPane.showMessageDialog(c, "name field should contain only alphabets & should not be empty","Empty field",JOptionPane.ERROR_MESSAGE);
}

catch(AssertionError e1){
JOptionPane.showMessageDialog(c, "name should contain a minimum length of 2","Wrong input",JOptionPane.ERROR_MESSAGE);
}
txtRno.setText("");
txtRno.requestFocus();
txtName.setText("");
txtSub1.setText("");
txtSub2.setText("");
txtSub3.setText("");
};
btnSave.addActionListener(a2);

setTitle("Add St.");
setSize(350,350);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}
}