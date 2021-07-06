import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class admin_interface extends JFrame implements ActionListener
{
	JButton label1,label2;
	JLabel label3,label4;
	JPanel panelselect,panel;
	JFrame f1;

	
	public admin_interface()
	{
		f1=new JFrame();
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setResizable(true);
		f1.setBounds(10,10,1800,900);
		f1.setVisible(true);
		
		

		panelselect=new JPanel();
		panelselect.setLayout(null);
		
		label1=new JButton("Signup Request");
		label1.setBounds(500,250,300,70);
		label1.setFont(new Font("arial",Font.BOLD,25));
		label1.addActionListener(this);
		panelselect.add(label1);
		
		label2=new JButton("Level Request");
		label2.setBounds(500,450,300,70);
		label2.setFont(new Font("arial",Font.BOLD,25));
		label2.addActionListener(this);
		panelselect.add(label2);
		
		
		
		
		
		f1.add(panelselect);
		

	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(label1))
		{
			f1.setVisible(false);
			sign_up_approve ap=new sign_up_approve();
		}
		else if(e.getSource().equals(label2))
		{
			f1.setVisible(false);
			level_approve p=new level_approve();
		}
	}
	
	
	/*public void db2()
	{
		String query = "SELECT `userid`,`name`,`password`,`status`,`level`,`highscore` FROM `pro`;";     
        Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop1","root","");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeQuery(query);//getting result
			System.out.println("results received");
					
			while(rs.next())
			{
					
                String userId = rs.getString("userid");
				int level=rs.getInt("level");
				//String status = rs.getString("status");
				//approve ls=new approve(userId,level);
			}
		}
		catch(Exception e){}
		try
		{
			st.close();
			con.close();
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	*/
	
}