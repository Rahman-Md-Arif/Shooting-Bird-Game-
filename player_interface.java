import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class player_interface extends JFrame implements ActionListener
{
	JButton label1,label2,label3,label4;
	JPanel panelselect;
	JLabel l,l2;
	String s;
	int n;
	public player_interface(String s,int n)
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setBounds(10,10,1800,900);
		
		panelselect=new JPanel();
		panelselect.setLayout(null);
		
		
		
		label1=new JButton("label1");
		label1.setBounds(800,100,100,30);
		label1.setFont(new Font("arial",Font.BOLD,15));
		label1.addActionListener(this);
		label1.setEnabled(false);
		panelselect.add(label1);
		
		label2=new JButton("label2");
		label2.setBounds(800,200,100,30);
		label2.setFont(new Font("arial",Font.BOLD,15));
		label2.addActionListener(this);
		label2.setEnabled(false);
		panelselect.add(label2);
		
		
		label3=new JButton("label3");
		label3.setBounds(800,300,100,30);
		label3.setFont(new Font("arial",Font.BOLD,15));
		label3.addActionListener(this);
		label3.setEnabled(false);
		panelselect.add(label3);
		
		label4=new JButton("label4");
		label4.setBounds(800,400,100,30);
		label4.setFont(new Font("arial",Font.BOLD,15));
		label4.addActionListener(this);
		label4.setEnabled(false);
		panelselect.add(label4);
		
		this.s=s;
		this.n=n;
		System.out.println(s);
		System.out.println(n);

		if(s.equals("valid"))
		{
			l=new JLabel("YOU CAN PLAY UP TO LEVEL "+n);
			l.setBounds(70,200,550,100);
			l.setFont(new Font("arial",Font.BOLD,35));
			panelselect.add(l);
			
			if(n==0){}
			else if(n==1)
				label1.setEnabled(true);
			else if(n==2)
			{
				label1.setEnabled(true);
				label2.setEnabled(true);
			}
			else if(n==3)
			{
				label1.setEnabled(true);
				label2.setEnabled(true);
				label3.setEnabled(true);
			}
			else if(n==4)
			{
				label1.setEnabled(true);
				label2.setEnabled(true);
				label3.setEnabled(true);
				label4.setEnabled(true);
			}
		}
		else if(s.equals("invalid"))
		{
			l2=new JLabel("YOU ARE NOT ALLOWED TO PLAY THIS GAME");
			l2.setBounds(70,200,600,100);
			l2.setFont(new Font("arial",Font.BOLD,25));
			//l2.setVisible(false);
			panelselect.add(l2);
		}
		this.add(panelselect);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(label1))
		{
			Lavel l=new Lavel(1);
		}
		if(e.getSource().equals(label2))
		{
			Lavel l=new Lavel(2);
		}
		if(e.getSource().equals(label3))
		{
			Lavel l=new Lavel(3);
			
		}
		if(e.getSource().equals(label4))
		{
			Lavel l=new Lavel(4);
		}
		
	}

}