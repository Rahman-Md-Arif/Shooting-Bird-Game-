import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class sign_up_approve extends JFrame implements ActionListener
{

	JPanel panelselect;
	JLabel name,app,id2;
	String u;
	JComboBox combo,combo2;
	String v;
	int m;
	JRadioButton r1;
	JButton ok;
	
	public sign_up_approve()
	{
		db();
	}
	
	public void db()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setBounds(10,10,1800,900);
		setVisible(true);
		panelselect=new JPanel();
		panelselect.setLayout(null);
		
		ok=new JButton("Ok");
		ok.setBounds(500,570,80,40);
		panelselect.add(ok);
		ok.addActionListener(this);
		setVisible(true);

		
		this.add(panelselect);
		

		
	
		System.out.println("nom");
		int m=100;
		
		String query = "SELECT `userid`,`name`,`password`,`status`,`level`,`highscore` FROM `pro`;";     
        Connection con=null;
        Statement st = null;
		ResultSet rs = null;
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop1","root","");
			System.out.println("connection done");
			st = con.createStatement();
			System.out.println("statement created");
			rs = st.executeQuery(query);
			System.out.println("results received");
			
				
				int i=0;
				while(rs.next())
				{
					
					if(i<10)
					{
						
						String userId = rs.getString("userid");
						String status = rs.getString("status");
						if(status.equals("invalid"))
						{
					
							this.setVisible(true);
					
							name=new JLabel("USER ID");
							name.setBounds(430,30,170,30);
							name.setFont(new Font("arial",Font.BOLD,30));
							panelselect.add(name);

							r1 = new JRadioButton(userId);
							r1.setBounds(450,m,80,30);
							panelselect.add(r1);
							
							String s3[] ={"invalid","valid"};
							combo2 = new JComboBox(s3);
							combo2.setBounds(600, m, 80, 30);
							panelselect.add(combo2);
							m+=40;
							
		
							this.add(panelselect);
							i++;
							
						}
					
					}
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
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(ok))
		{
			this.setVisible(false);
		    new admin_interface();
			
			updateInDB();
			
		}
	}
	
	
	public void updateInDB()
	{
		System.out.println();
		//double prevBalance=0, newBalance=0;
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
                //String userId = rs.getString("UserID");
                //String password = rs.getString("Password");
				String userId = rs.getString("userid");
				String status = rs.getString("status");
				//System.out.println("way");

				if(r1.isSelected())
				{
					if(r1.getText().equals(userId))
					{
						String s2=combo2.getSelectedItem().toString();
						String s3=r1.getText();
								//System.out.println(s2);
								//System.out.println(s3);
	

					}
				}
			}
		}
		catch(Exception e){}
		try
		{
			System.out.println(".");
			query = "UPDATE pro SET status = "+combo2.getSelectedItem().toString()+" where userid = "+r1.getText().toString();
			System.out.println("yes");
			System.out.println(query);
			System.out.println(combo2.getSelectedItem().toString());
			System.out.println(r1.getText());

			st.executeUpdate(query);
			System.out.println("kiiii");

			st.close();
			con.close();
			rs.close();
			

		}
		catch(Exception e){System.out.println(e.getMessage());}
	}
	
}
	

	