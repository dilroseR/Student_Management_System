import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;
import java.io.*;

class DbHandler extends JFrame{
public static void addStudent(int rno,String name,int s1,int s2,int s3){
Configuration cfg=new Configuration();
cfg.configure("hibernate.cfg.xml");
SessionFactory sfact=cfg.buildSessionFactory();
Session s=null;
Transaction t=null;

try{
s=sfact.openSession();
System.out.println("connected");
t=s.beginTransaction();

Student1 stu=new Student1(rno,name,s1,s2,s3);
s.save(stu);
t.commit();
JOptionPane.showMessageDialog(new JDialog(),"record added");
}
catch(Exception e){
JOptionPane.showMessageDialog(new JDialog(),"add issue" + e);
t.rollback();
}
finally{
s.close();
System.out.println("disconnected");
}
}

public static String viewStudent(){

Configuration cfg=new Configuration();
cfg.configure("hibernate.cfg.xml");
SessionFactory sfact=cfg.buildSessionFactory();
Session s=null;
String data="";
try{
s=sfact.openSession();
System.out.println("connected");

java.util.List<Student1> stu=new ArrayList<Student1>();
stu=s.createQuery("from Student1").list();
for(Student1 m:stu){
String f=(m.getRno() + "  " + m.getName() + "  " + m.getS1() + "  " + m.getS2() + "  " + m.getS3());
data=data + f +"\n";
}
}
catch(Exception e){
JOptionPane.showMessageDialog(new JDialog(),"view issue" + e);
}
finally{
s.close();
System.out.println("disconnected");
}
return data;
}

public static void updateStudent(int rno,String name,int s1,int s2,int s3){
Configuration cfg=new Configuration();
cfg.configure("hibernate.cfg.xml");
SessionFactory sfact=cfg.buildSessionFactory();
Session s=null;
Transaction t=null;

try{
s=sfact.openSession();
System.out.println("connected");
t=s.beginTransaction();
Student1 stu=(Student1)s.get(Student1.class,rno);

if(stu!=null){
stu.setName(name);
stu.setS1(s1);
stu.setS2(s2);
stu.setS3(s3);
t.commit();
JOptionPane.showMessageDialog(new JDialog(),"record updated");
}
else
JOptionPane.showMessageDialog(new JDialog(),"record doesn't exist");
}
catch(Exception e){
JOptionPane.showMessageDialog(new JDialog(),"update issue" + e);
t.rollback();
}
finally{
s.close();
System.out.println("disconnected");
}
}

public static void deleteStudent(int rno){
Configuration cfg=new Configuration();
cfg.configure("hibernate.cfg.xml");
SessionFactory sfact=cfg.buildSessionFactory();
Session s=null;
Transaction t=null;

try{
s=sfact.openSession();
t=s.beginTransaction();
System.out.println("connected");

Student1 stu=(Student1)s.get(Student1.class,rno);
if(stu!=null){
s.delete(stu);
t.commit();
JOptionPane.showMessageDialog(new JDialog(),"record deleted");
}
else
JOptionPane.showMessageDialog(new JDialog(),"record doesn't exist");
}
catch(Exception e){
JOptionPane.showMessageDialog(new JDialog(),"delete issue" + e);
t.rollback();
}
finally{
s.close();
System.out.println("disconnected");
}
}


public void chartsStudent()
{

DefaultCategoryDataset d1 = new DefaultCategoryDataset();

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact = cfg.buildSessionFactory();
Session s = null;

try
{
s = sfact.openSession();
System.out.println("connected");
s.beginTransaction();

java.util.List<Student1> student1list = new ArrayList<>();
student1list = s.createQuery("from Student1").list();
			
for(Student1 b : student1list)
{
d1.setValue(b.getS1(),b.getName(),"Sub1");
d1.setValue(b.getS2(),b.getName(),"Sub2");
d1.setValue(b.getS3(),b.getName(),"Sub3");			
}
JFreeChart chart = ChartFactory.createBarChart("Student Performance","Subjects","Marks",d1,PlotOrientation.VERTICAL,true,true,false);
ChartPanel cp = new ChartPanel(chart);
setContentPane(cp);	
try
{
				
int width = 400;
int height = 400;
File marks = new File("marks.jpeg");
ChartUtilities.saveChartAsJPEG(marks,chart,width,height);
}
catch(IOException e)
{ 
System.out.println(e);
}
setTitle("Marksheet");
setSize(500,400);
setLocationRelativeTo(null);
setVisible(true);
}
catch(Exception e)
{
System.out.println(e.getMessage());
}
finally
{
s.close();
System.out.println("disconnected");
}
}
}