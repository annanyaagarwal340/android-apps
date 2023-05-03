import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;

public class form1 extends JFrame implements ActionListener {
    JLabel l1,l2,l3,l4,l5,l6;
    JTextField t1,t2;
    JRadioButton r1,r2,r3,r4,r5;
    ButtonGroup bg,bg1,bg2;
    JCheckBox checkBox1,checkBox2,checkBox3,checkBox4;
    JButton b;
    form1(){
        setLayout(null);
        setTitle("form1");
        l1=new JLabel("FORM 1");
        l1.setFont(new Font("Raleway", Font.BOLD, 38));
        l1.setBounds(350,10,200,30);
        add(l1);

        l2=new JLabel("NAME:");
        l2.setFont(new Font("Raleway", Font.BOLD, 20));
        l2.setBounds(30,100,200,20);
        add(l2);

        t1=new JTextField();
        t1.setFont(new Font("Raleway", Font.ITALIC, 15));
        t1.setBounds(105,100,200,30);
        t1.setBackground(Color.YELLOW);
        add(t1);


        l3=new JLabel("ROLL NUMBER:");
        l3.setFont(new Font("Raleway", Font.BOLD, 20));
        l3.setBounds(30,167,200,20);
        add(l3);

        t2=new JTextField();
        t2.setFont(new Font("Raleway", Font.ITALIC, 15));
        t2.setBounds(200,165,200,30);
        t2.setBackground(Color.CYAN);
        add(t2);

        l4=new JLabel("SELECT YOUR COURSE:");
        l4.setFont(new Font("Raleway", Font.BOLD, 20));
        l4.setBounds(30,240,300,20);
        add(l4);

        r1=new JRadioButton("BCA");
        r2=new JRadioButton("MCA");
        r3=new JRadioButton("BTech");
        r1.setBounds(30,270,200,30);
        r1.setBackground(Color.CYAN);
        r2.setBounds(30,300,200,30);
        r2.setBackground(Color.ORANGE);
        r3.setBounds(30,330,200,30);
        r3.setBackground(Color.green);
        bg=new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        add(r1);
        add(r2);
        add(r3);

        l5=new JLabel("SELECT PROGRAMMING LANGUAGE:");
        l5.setFont(new Font("Raleway", Font.BOLD, 20));
        l5.setBounds(30,390,400,20);
        add(l5);

        checkBox1 = new JCheckBox("C++");
        checkBox1.setBounds(30,420, 100,20);
        checkBox1.setBackground(Color.WHITE);
        checkBox2 = new JCheckBox("Java");
        checkBox2.setBounds(30,450, 100,20);
        checkBox2.setBackground(Color.magenta);
        checkBox3 = new JCheckBox("Python");
        checkBox3.setBounds(30,480, 100,20);
        checkBox3.setBackground(Color.ORANGE);
        checkBox4 = new JCheckBox("SQL");
        checkBox4.setBounds(30,510, 100,20);
        checkBox4.setBackground(Color.green);
//        bg2 = new ButtonGroup();
//        bg2.add(checkBox1);
//        bg2.add(checkBox2);
//        bg2.add(checkBox3);
        add(checkBox1);
        add(checkBox2);
        add(checkBox3);
        add(checkBox4);

        l6=new JLabel("SELECT YOUR SECTION:");
        l6.setFont(new Font("Raleway", Font.BOLD, 20));
        l6.setBounds(30,560,300,20);
        add(l6);

        r4=new JRadioButton("A");
        r5=new JRadioButton("B");
        r4.setBounds(30,590,200,30);
        r4.setBackground(Color.green);
        r5.setBounds(30,615,200,30);
        r5.setBackground(Color.WHITE);
        bg1=new ButtonGroup();
        bg1.add(r4);
        bg1.add(r5);
        add(r4);
        add(r5);

        b=new JButton("Submit");
        b.setBounds(330,650,100,30);
        b.setBackground(Color.CYAN);
        b.addActionListener(this);
        add(b);

        getContentPane().setBackground(Color.PINK);
        setSize(800,850);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String name=t1.getText().toString();
        String roll_number=t2.getText().toString();
        String course;
        if(r1.isSelected()){
            course="BCA";
        } else if(r2.isSelected()) {
            course="MCA";
        }else{
            course="Btech";
        }
        String lang="";
        if(checkBox1.isSelected()){
            lang+=" C++";
        }if(checkBox2.isSelected()) {
            lang+=" Java";
        }if(checkBox3.isSelected()){
            lang+=" Python";
        }if(checkBox4.isSelected()){
            lang+=" SQL";
        }
        String section;
        if(r4.isSelected()){
            section="A";
        }else{
            section="B";
        }
        if (e.getSource() == b) {
//            System.out.println(name+", "+roll_number+", "+course+","+lang+", "+section);
            JOptionPane.showMessageDialog(null, "You entered:\n" + "Name: "+name + "\n" + "Roll Number: "+ roll_number + "\n" + "Course: "+ course + "\n" + "Language: "+ lang + "\n" + "Section: "+ section);
            Connections c2=new Connections();
            try {
                //c2.s.executeUpdate("insert into Student values(" + name + "," + roll_number + "," + course + "," + lang + "," + section + ");");
                c2.s.execute("insert into Student values('"+name+"', '"+roll_number+"', '"+course+ "', '"+lang+"', '"+section+ "');");
                ResultSet rs = c2.s.executeQuery("Select *from Student");

                //retreiving data from resultset
                while(rs.next()) {
                    System.out.print("Name: "+rs.getString("name")+", ");
                    System.out.print("Roll Number: "+rs.getInt("roll_number")+", ");
                    System.out.print("Course: "+rs.getString("course")+", ");
                    System.out.print("Language: "+rs.getString("lang")+", ");
                    System.out.print("Section: "+rs.getString("section"));
                    System.out.println();
                }
            }catch(Exception e1){
                System.out.println(e1);
            }
        }
    }
    public static void main(String[] args) {
            form1 f=new form1();
    }
}
