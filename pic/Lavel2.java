import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
public class Lavel2 extends JFrame implements MouseListener//,ActionListener
{
	Container c;
	JButton yes,no;
	JPanel panel;
	ImageIcon backgroundimage;
	JLabel bird1,bird2,bird3,bird4,bird5,backgroundlabel,bullet,recentbird1,recentbird2,bf1,bf2,bb1,bb2,fall;
	int WIDITH=1500,HEIGHT=900;
	Random r=new Random();
	long time,diff=5000000000L;
	int score=0,lavel;
	BirdFly1 b1,b2;
	public Lavel2()
	{
		//System.out.println("in Lavel2");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		this.setBounds(0,0,WIDITH,HEIGHT);
		
		panel=new JPanel();
		panel.setLayout(null);
		
		b1=new BirdFly1();
		panel.add(b1);
		//b1.setBounds(100,200,271,228);
		
		b2=new BirdFly1();
		panel.add(b2);
		//b2.setBounds(100,200,271,228);
		
		
		backgroundimage=new ImageIcon("background.png");
		backgroundlabel=new JLabel(backgroundimage);
		backgroundlabel.setBounds(0,0,backgroundimage.getIconWidth(),backgroundimage.getIconHeight());
		panel.add(backgroundlabel);
		
		this.add(panel);
		//this.addMouseListener(this);
		setVisible(true);
		
		
		Thread t1=new Thread(new Runnable(){
			public void run(){
			try{
				b1.flyForward();
			}
			catch(Exception e)
			{
					System.out.println("int run of t1");
			}
		}
			
		});
		Thread t2=new Thread(new Runnable(){
			public void run(){
				try{
			b2.flyForward();
				}
				catch(Exception e)
				{
					System.out.println("int run of t2");
				}
			}
			
		});
		t1.start();
		t2.start();
	}
	
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void mouseClicked(MouseEvent me)
	{
		
		//System.out.println("clicked in x : "+me.getX()+" y : "+me.getY());
		//System.out.println("current x : "+currentPositionX+" y : "+currentPositionY);
		int x=me.getX()-b1.currentPositionX;
		int y=me.getY()-b1.currentPositionY-30;
		System.out.println("clicked");
		//bird1
		if(b1.forwardbirdtaken) //when forward bird visible
		{
			//System.out.println("forword bird visible");
			if(b1.isforwardbirdShot(x,y))
			{
				b1.reverse_birdshot_status();
				//System.out.println("bird shot");
				//try{ Thread.sleep(2000); }
				//catch(Exception e) { }
			}
		}
		else
		{
			if(b1.isbackwardbirdShot(x,y))
			{
				b1.reverse_birdshot_status();
				//System.out.println("bird shot");
				
				//try{ Thread.sleep(2000); }
				//catch(Exception e) { }
			}
			
		}
		
		//bird2
		if(b2.forwardbirdtaken) //when forward bird visible
		{
			//System.out.println("forword bird visible");
			if(b2.isforwardbirdShot(x,y))
			{
				b2.reverse_birdshot_status();
				System.out.println("bird shot");
				//try{ Thread.sleep(2000); }
				//catch(Exception e) { }
			}
		}
		else
		{
			if(b2.isbackwardbirdShot(x,y))
			{
				b2.reverse_birdshot_status();
				System.out.println("bird shot");
				
				//try{ Thread.sleep(2000); }
				//catch(Exception e) { }
			}
			
		}
		
	}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	
	
}