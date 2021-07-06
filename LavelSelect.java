import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class LavelSelect extends JFrame implements MouseListener,ActionListener
{
	JButton label1,label2,label3,label4;
	JPanel panelselect;
	public LavelSelect()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setBounds(10,10,1800,900);
		
		panelselect=new JPanel();
		panelselect.setLayout(null);
		
		//label1
		label1=new JButton("label1");
		label1.setBounds(800,100,100,30);
		label1.setFont(new Font("arial",Font.BOLD,15));
		label1.addMouseListener(this);
		label1.addActionListener(this);
		//labelsetVisible(true);
		panelselect.add(label1);
		
		//label2
		label2=new JButton("label2");
		label2.setBounds(800,200,100,30);
		label2.setFont(new Font("arial",Font.BOLD,15));
		label2.addMouseListener(this);
		label2.addActionListener(this);
		//labelsetVisible(true);
		panelselect.add(label2);
		
		//label3
		label3=new JButton("label3");
		label3.setBounds(800,300,100,30);
		label3.setFont(new Font("arial",Font.BOLD,15));
		label3.addMouseListener(this);
		label3.addActionListener(this);
		//labelsetVisible(true);
		panelselect.add(label3);
		
		//label4
		label4=new JButton("label4");
		label4.setBounds(800,400,100,30);
		label4.setFont(new Font("arial",Font.BOLD,15));
		label4.addMouseListener(this);
		label4.addActionListener(this);
		//labelsetVisible(true);
		panelselect.add(label4);
		
		this.add(panelselect);
		
		setVisible(true);
	}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void mouseClicked(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
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