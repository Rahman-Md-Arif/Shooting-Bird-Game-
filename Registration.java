import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Registration extends JFrame implements ActionListener
{
	JLabel name,userid,pw,m1,m2,m3;
	JTextField nametf,useridtf;
	JPasswordField pwf;
	JButton back,signup;
	JPanel signuppanle;
	LogIn l;
	public Registration(LogIn p)
	{
		l=p;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(10,10,1800,900);
		
		signuppanle=new JPanel();
		signuppanle.setLayout(null);
		
		
		
		
		m1=new JLabel("*enter Nmae");
		m1.setBounds(700,140,180,30);
		m1.setForeground(Color.RED);
		m1.setFont(new Font("arial",Font.BOLD,15));
		m1.setVisible(false);
		signuppanle.add(m1);
		
		
		
		m2=new JLabel("*enter user Id");
		m2.setBounds(700,250,200,30);
		m2.setFont(new Font("arial",Font.BOLD,15));
		m2.setForeground(Color.RED);
		m2.setVisible(false);
		signuppanle.add(m2);
		
		m3=new JLabel("*enter password");
		m3.setBounds(700,350,200,30);
		m3.setForeground(Color.RED);
		m3.setFont(new Font("arial",Font.BOLD,15));
		m3.setVisible(false);
		signuppanle.add(m3);
		
		//name label in registration
		name=new JLabel("enter name : ");
		name.setBounds(400,100,200,30);
		name.setFont(new Font("arial",Font.BOLD,15));
		signuppanle.add(name);
		
		//name input text field
		nametf=new JTextField();
		nametf.setBounds(600,100,200,30);
		nametf.setFont(new Font("arial",Font.PLAIN,15));
		signuppanle.add(nametf);
		
		
		//username in registration
		userid=new JLabel("User Id : ");
		userid.setBounds(400,200,200,30);
		userid.setFont(new Font("arial",Font.BOLD,15));
		signuppanle.add(userid);
		
		//user input textfield
		useridtf=new JTextField();
		useridtf.setBounds(600,200,200,30);
		useridtf.setFont(new Font("arial",Font.PLAIN,15));
		signuppanle.add(useridtf);
	
		//user label
		pw=new JLabel("Pass Word : ");
		pw.setBounds(400,300,200,30);
		pw.setFont(new Font("arial",Font.BOLD,15));
		signuppanle.add(pw);
		
		//user label
		pwf=new JPasswordField();
		pwf.setBounds(600,300,200,30);
		pwf.setFont(new Font("arial",Font.BOLD,15));
		signuppanle.add(pwf);
		
		back=new JButton("Back");
		back.setBounds(500,500,80,50);
		back.addActionListener(this);
		signuppanle.add(back);
		
		signup=new JButton("Sign Up");
		signup.setBounds(600,500,80,50);
		signup.addActionListener(this);
		signuppanle.add(signup);
		
		this.add(signuppanle);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		//String buttonClicked = ae.getActionCommand();
		
		m1.setVisible(false);
		m2.setVisible(false);
		m3.setVisible(false);
		if(e.getSource().equals(back))
		{
		   this.setVisible(false);
		   l.setVisible(true);
		}
		else if(e.getSource().equals(signup))
		{
			if(!(nametf.getText().equals("")))
				{
					if(!(useridtf.getText().equals("")))
					{
						if(!(pwf.getText().equals("")))
						{
							insertIntoDB();
							back b=new back();
							b.setVisible(true);
							
							
						}
						else
							m3.setVisible(true);
					}
					else
						m2.setVisible(true);
				}
				else
					m1.setVisible(true);
		}


	}

	
	public void insertIntoDB()
	{
		
		
		String query = "INSERT INTO pro VALUES ('"+nametf.getText()+"','"+useridtf.getText()+"','"+pwf.getText()+"','"+"invalid"+"','0');";
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop1","root", "");
			Statement stm = con.createStatement();
			stm.execute(query);
			System.out.println("jishnu");

			stm.close();
			con.close();
					
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
    }


}