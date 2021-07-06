import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class back extends JFrame implements ActionListener
{
	JLabel name;
	JButton backto,exit;
	JPanel p;
	public back()
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(1800,800);
		
		p=new JPanel();
		p.setLayout(null);
		
		name=new JLabel("WAITING FOR ADMIN'S APPROVAL");
		name.setBounds(400,200,650,50);
		name.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,40));
		p.add(name);
		
		backto=new JButton("BACK TO LOGIN PAGE");
		backto.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,25));
		backto.setBounds(420,550,300,50);
		backto.addActionListener(this);
		p.add(backto);
		
		exit=new JButton("EXIT");
		exit.setBounds(750,550,180,50);
		exit.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,25));
		exit.addActionListener(this);
		p.add(exit);
		
		this.add(p);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource().equals(backto))
		{
			LogIn l=new LogIn();
			l.setVisible(true);
		}
		else if(e.getSource().equals(exit))
		{
			System.exit(0);
		}
	}
}
			
	
	
		
		
		