import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class LogIn extends JFrame implements MouseListener,ActionListener
{

	JLabel username,password,invaliduser,invalidpw,selectcatagory,newuser,name,userid,pw,emptyuser,emptypw;
	JTextField usernametf,nametf,useridtf;
	JPasswordField passwordf,pwf;
	JButton login,exit,register;
	JPanel loginpanel,registerpanle;
	JRadioButton admin,player;
	ButtonGroup bg;
	public LogIn()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setBounds(10,10,1800,900);
		
		loginpanel=new JPanel();
		loginpanel.setLayout(null);
		
		
		//admin player radio button
		admin = new JRadioButton("Admin");
		admin.setFont(new Font("arial",Font.BOLD,15));
		admin.setBounds(400,50,100,30);
		loginpanel.add(admin);
		
		player= new JRadioButton("Player");
		player.setFont(new Font("arial",Font.BOLD,15));
		player.setBounds(400,95,100,30);
		loginpanel.add(player);
		
		//radio button group
		bg=new ButtonGroup();
		bg.add(admin);
		bg.add(player);
		
		//show massage if radio button not selected
		selectcatagory=new JLabel("*select catagory");
		selectcatagory.setBounds(550,95,200,30);
		selectcatagory.setFont(new Font("arial",Font.BOLD,15));
		selectcatagory.setForeground(Color.RED);
		selectcatagory.setVisible(false);
		loginpanel.add(selectcatagory);
		
		
		//user label
		username=new JLabel("User Name : ");
		username.setBounds(400,180,200,30);
		username.setFont(new Font("arial",Font.BOLD,15));
		loginpanel.add(username);
		
		//user input textfield
		usernametf=new JTextField();
		usernametf.setBounds(510,180,200,30);
		usernametf.setFont(new Font("arial",Font.PLAIN,15));
		loginpanel.add(usernametf);
		
		//show user name invalid massage
		invaliduser=new JLabel("*invalid user name");
		invaliduser.setBounds(730,180,200,30);
		invaliduser.setFont(new Font("arial",Font.BOLD,15));
		invaliduser.setForeground(Color.RED);
		invaliduser.setVisible(false);
		loginpanel.add(invaliduser);
		
		//show empty user name massage
		emptyuser=new JLabel("*enter user name");
		emptyuser.setBounds(730,180,200,30);
		emptyuser.setFont(new Font("arial",Font.BOLD,15));
		emptyuser.setForeground(Color.RED);
		emptyuser.setVisible(false);
		loginpanel.add(emptyuser);
		
		//password label
		password=new JLabel("PassWord : ");
		password.setBounds(400,250,200,30);
		password.setFont(new Font("arial",Font.BOLD,15));
		loginpanel.add(password);
		
		//password field input
		passwordf=new JPasswordField();
		passwordf.setBounds(510,250,200,30);
		passwordf.setFont(new Font("arial",Font.PLAIN,15));
		loginpanel.add(passwordf);
		
		//show pw invalid massage
		invalidpw=new JLabel("*invalid password");
		invalidpw.setBounds(730,250,200,30);
		invalidpw.setForeground(Color.RED);
		invalidpw.setFont(new Font("arial",Font.BOLD,15));
		invalidpw.setVisible(false);
		loginpanel.add(invalidpw);
		
		//show pw empty massage
		emptypw=new JLabel("*enter password");
		emptypw.setBounds(730,250,200,30);
		emptypw.setForeground(Color.RED);
		emptypw.setFont(new Font("arial",Font.BOLD,15));
		emptypw.setVisible(false);
		loginpanel.add(emptypw);
		
		
		
		//login button
		login=new JButton("LOGIN");
		login.setBounds(400,350,100,30);
		login.setFont(new Font("arial",Font.BOLD,15));
		login.addMouseListener(this);
		login.addActionListener(this);
		loginpanel.add(login);
		
		
		
		exit=new JButton("exit");
		exit.setBounds(600,350,100,30);
		exit.setFont(new Font("arial",Font.BOLD,15));
		exit.addMouseListener(this);
		exit.addActionListener(this);
		loginpanel.add(exit);
		
		//new user label
		newuser=new JLabel("new here?");
		newuser.setBounds(400,400,100,30);
		newuser.setFont(new Font("arial",Font.BOLD,15));
		loginpanel.add(newuser);
		
		//register button
		register=new JButton("register");
		register.setBounds(500,410,100,20);
		register.setFont(new Font("arial",Font.BOLD,15));
		register.addMouseListener(this);
		register.addActionListener(this);
		//login.setVisible(true);
		loginpanel.add(register);
		
		this.add(loginpanel);
	
		setVisible(true);
	
	}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void mouseClicked(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void actionPerformed(ActionEvent e)
	{
		invaliduser.setVisible(false);
		invalidpw.setVisible(false);
		selectcatagory.setVisible(false);
		emptypw.setVisible(false);
		emptyuser.setVisible(false);
		if(e.getSource().equals(login))
		{
			if(player.isSelected())
			{
				
				if(!(usernametf.getText().equals("")))
				{
					//System.out.println("select");
					if(!(passwordf.getText().equals("")))
					{
						updateInDB();
					}
					else emptypw.setVisible(true);;
				}
				else emptyuser.setVisible(true);
			}
			
			else if(admin.isSelected())
			{
				if(!(usernametf.getText().equals("")))
				{
					//System.out.println("select");
					if(!(passwordf.getText().equals("")))
					{
						update2InDB();
					}
					else emptypw.setVisible(true);;
				}
				else emptyuser.setVisible(true);
			}
			else
				selectcatagory.setVisible(true);
			
			
		}
		else if(e.getSource().equals(exit))
		{
			System.exit(0);
		}
		else if(e.getSource().equals(register))
		{
			setVisible(false);
			Registration r =new Registration(this);
		}
	}
	
	public void updateInDB()
	{
		//System.out.println(userId);
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
					
                String userId = rs.getString("userid");
                String password = rs.getString("password");
				String status = rs.getString("status");
				int level=rs.getInt("level");

				
				//if(userId==Integer.parseInt(usernametf.getText()))
				if(userId.equals(usernametf.getText()))
				{
							
					//System.out.println("usser id correct");

					//String s=userId.rs.("password");
					//if(password==Integer.parseInt(passwordf.getText().toString()))
					if(password.equals(passwordf.getText()))
					{
						setVisible(false);
						player_interface ls=new player_interface(status,level);
								
					}
					else invalidpw.setVisible(true);
					
				}
				else invaliduser.setVisible(true);

				
			}
		}
		catch(Exception e){}
		try
		{
			System.out.println("error");
			st.close();
			con.close();
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	public void update2InDB()
	{
		//System.out.println(userId);
		//double prevBalance=0, newBalance=0;
		String query = "SELECT `userid`,`password` FROM `admin`;";     
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
                String password = rs.getString("password");
				
				//if(userId==Integer.parseInt(usernametf.getText()))
				if(userId.equals(usernametf.getText()))
				{
							
					//System.out.println("usser id correct");

					//String s=userId.rs.("password");
					//if(password==Integer.parseInt(passwordf.getText().toString()))
					if(password.equals(passwordf.getText()))
					{
						setVisible(false);
						admin_interface ls=new admin_interface();
								
					}
					else invalidpw.setVisible(true);
					
				}
				else invaliduser.setVisible(true);

				
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
	
	
	
	
	
}