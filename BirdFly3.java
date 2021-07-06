import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
public class BirdFly3 extends JLabel implements MouseListener
{
	ImageIcon recentbird1,recentbird2,bf1,bf2,bb1,bb2,fall;
	int WIDITH=1800,HEIGHT=900;
	int currentPositionX,currentPositionY;
	Random r=new Random();
	long time,diff=5000000000L;
	boolean inframe=false,forwardbirdtaken=false,backwardbirdtaken=false,birdshot=false;
	static int score=0,lavel=3;
	
	public BirdFly3(int l)
	{
		lavel =l;
		//setSize(271,228);
		//System.out.println("in birdfly");
		bf1=new ImageIcon("bird3forward1.png");
		bf2=new ImageIcon("bird3forward2.png");
		bb1=new ImageIcon("bird3backward1.png");
		bb2=new ImageIcon("bird3backward2.png");
		fall=new ImageIcon("bird3fall.png");
		time=System.nanoTime();
		setIcon(bf1);
		//System.out.println("in birdfly done");
		this.addMouseListener(this);
		
	}
	public synchronized void reverse_bird_taken()
	{
		if(forwardbirdtaken) forwardbirdtaken=false;
		else forwardbirdtaken=true;
		if(backwardbirdtaken) backwardbirdtaken=false;
		else backwardbirdtaken=true;
	}
	public synchronized void reverse_inframe_status()
	{
		if(inframe) 
		{
			System.out.println(Thread.currentThread().getId()+"out of frame");
			inframe=false;
		}
		else 
		{
			System.out.println(Thread.currentThread().getId()+" is inframe");
			inframe=true;
			
		}
	}
	public synchronized void reverse_birdshot_status()
	{
		//System.out.println(Thread.currentThread().getId()+" shot");
		if(birdshot) birdshot=false;
		else birdshot=true;
	}
	public synchronized void update_score(int s)
	{
		score+=s;
	}
	
	public void takeforwardbird()
	{
		//System.out.println("thread : "+Thread.currentThread().getId()+" is taking forward bird");
		reverse_bird_taken();
		recentbird1=bf1;
		recentbird2=bf2;
	}
	public void takebackwardbird()
	{
		//System.out.println("thread : "+Thread.currentThread().getId()+" is taking backward bird");
		reverse_bird_taken();
		recentbird1=bb1;
		recentbird2=bb2;
	}
	public void flyForward()
	{
		//System.out.println("thread : "+Thread.currentThread().getId()+" in fly forward");
		time=System.nanoTime();
		takeforwardbird();
		if(inframe)
		{
			if(!r.nextBoolean() && System.nanoTime()-time>diff) flyForwardUp();
			else flyForwardDown();
		}
		reverse_inframe_status();
		currentPositionX=r.nextInt(1400);
		if(currentPositionX==0)
		{
			//position x in left border (x=0) recentbird should appera from left border
			//position y may be in full up to down (0,upper)/(0,0) to (0,lower)/(0,900)
			currentPositionY=r.nextInt(900);
			
			if(!r.nextBoolean() && System.nanoTime()-time>diff) flyForwardDown();
			else flyForwardUp();
			
		}
		else 
		{
			//position of x is not in  border, recentbird should arrive from lower border of upper border
			if(!r.nextBoolean()) 
			{
				//appera in lower border
				//can fly up not down
				currentPositionY=900;
				flyForwardUp();
			}
			else
			{
				//appera in upper border
				//can fly down not up
				currentPositionY=0;
				flyForwardDown();
			}
		}	
	}
	public void flyBackward()
	{
		//System.out.println("thread : "+Thread.currentThread().getId()+" in fly backward");
		time=System.nanoTime();
		//System.out.println("comes in flyBackward : "+System.nanoTime());
		takebackwardbird();
		if(inframe)
		{
			if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackwardUp();
			else flyBackwardDown();
		}
		reverse_inframe_status();
		currentPositionX=401+r.nextInt(1400);
		if(currentPositionX==1800)
		{
			//position x in right border (x=1800) recentbird should appera from right border
			//position y may be in full up to down (1800,upper)/(1800,0) to (1800,lower)/(1800,900)
			currentPositionY=r.nextInt(900);
			
			if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackwardDown();
			else flyBackwardUp();
			
		}
		else 
		{
			//position of x is not in border, recentbird should arrive from lower border of upper border
			if(r.nextBoolean()) 
			{
				//appera in lower border
				//can fly up not down
				currentPositionY=900;
				flyBackwardUp();
			}
			else
			{
				//appera in upper border
				//can fly down not up
				currentPositionY=0;
				flyBackwardDown();
			}
		}
			
	}
	public void flyForwardUp()
	{
		//System.out.println("thread : "+Thread.currentThread().getId()+" in fly forward Up");
		for(int i=currentPositionX;i<WIDITH+250;)
		{
			for(int j=currentPositionY;j>-100;)
			{
				if(!is_inframe()) 
				{
					if(r.nextBoolean()) flyForward();
					else flyBackward();
				}
				setIcon(recentbird1);
				setBounds(i,j,271,228);
				//System.out.println(i+" "+j);
				// //System.out.println(i+" "+j);
				try{ Thread.sleep(300); }
				catch(Exception e) { System.out.println("sleep exception");}
				
				
				if(birdshot) dropBird();
				
				i+=30; j-=30;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackward();
				//else flyForwardDown();
				
				if(!is_inframe()) 
				{
					if(r.nextBoolean()) flyForward();
					else flyBackward();
				}
				setIcon(recentbird2);
				setBounds(i,j,271,228);
				//System.out.println(i+" "+j);
				try{ Thread.sleep(300); }
				catch(Exception e) { System.out.println("sleep exception");}
				
				if(birdshot) dropBird();
				
				i+=30; j-=30;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackward();
				//else flyForwardDown();
				
				
				
				
			}
			if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackward();
			else flyForwardDown();
			
		}
	}
	public void flyForwardDown()
	{
		//System.out.println("thread : "+Thread.currentThread().getId()+" in fly forward down");
		for(int i=currentPositionX;i<WIDITH+250;)
		{
			
			for(int j=currentPositionY;j<HEIGHT+100;)
			{
				if(!is_inframe()) 
				{
					if(r.nextBoolean()) flyForward();
					else flyBackward();
				}
				setIcon(recentbird1);
				setBounds(i,j,271,228);
				//System.out.println(i+" "+j);
				try{ Thread.sleep(300); }
				catch(Exception e) { System.out.println("sleep exception");}
				
				if(birdshot) dropBird();
				
				i+=30; j+=30;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackward();
				//else flyForwardUp();
				
				if(!is_inframe()) 
				{
					if(r.nextBoolean()) flyForward();
					else flyBackward();
				}
				setIcon(recentbird2);
				setBounds(i,j,271,228);
				//System.out.println(i+" "+j);
				try{ Thread.sleep(300); }
				catch(Exception e) { System.out.println("sleep exception");}
				
				if(birdshot) dropBird();
				
				i+=30; j+=30;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackward();
				//else flyForwardUp();
			}
			if(r.nextBoolean() && System.nanoTime()-time>diff) flyBackward();
			else flyForwardUp();
		}
		
	}
	
	public void flyBackwardUp()
	{
		//System.out.println("thread : "+Thread.currentThread().getId()+" in fly backward Up");
		for(int i=currentPositionX;i>-100;)
		{
			for(int j=currentPositionY;j>-100;)
			{
				if(!is_inframe()) 
				{
					if(r.nextBoolean()) flyForward();
					else flyBackward();
				}
				
				setIcon(recentbird1);
				setBounds(i,j,271,228);
				//System.out.println(i+" "+j);
				try{ Thread.sleep(300); }
				catch(Exception e) { System.out.println("sleep exception");}
				
				if(birdshot) dropBird();
				
				i-=30; j-=30;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyForward();
				//else flyBackwardDown();
				
				if(!is_inframe()) 
				{
					if(r.nextBoolean()) flyForward();
					else flyBackward();
				}
				
				setIcon(recentbird2);
				setBounds(i,j,271,228);
				//System.out.println(i+" "+j);
				try{ Thread.sleep(300); }
				catch(Exception e) { System.out.println("sleep exception");}
				
				if(birdshot) dropBird();
				
				i-=30; j-=30;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyForward();
				//else flyBackwardDown();
			}
			if(r.nextBoolean() && System.nanoTime()-time>diff) flyForward();
			else flyBackwardDown();
		}
	}
	public void flyBackwardDown()
	{
		//System.out.println("thread : "+Thread.currentThread().getId()+" in fly backward Down");
		for(int i=currentPositionX;i>-100;)
		{
			for(int j=currentPositionY;j<HEIGHT+100;)
			{
				if(!is_inframe()) 
				{
					if(r.nextBoolean()) flyForward();
					else flyBackward();
				}
				setIcon(recentbird1);
				setBounds(i,j,271,228);
				//System.out.println(i+" "+j);
				try{ Thread.sleep(300); }
				catch(Exception e) { System.out.println("sleep exception");}
				
				if(birdshot) dropBird();
				
				i-=30; j+=30;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyForward();
				//else flyBackwardUp();
				if(!is_inframe()) 
				{
					if(r.nextBoolean()) flyForward();
					else flyBackward();
				}
				
				setIcon(recentbird2);
				setBounds(i,j,271,228);
				//System.out.println(i+" "+j);
				try{ Thread.sleep(300); }
				catch(Exception e) { System.out.println("sleep exception");}
				
				if(birdshot) dropBird();
				
				i-=30; j+=30;
				currentPositionX=i;
				currentPositionY=j;
				if(r.nextBoolean() && System.nanoTime()-time>diff) flyForward();
				//else flyBackwardUp();
			}
			if(r.nextBoolean() && System.nanoTime()-time>diff) flyForward();
			else flyBackwardUp();
		}
	}
	boolean isbackwardbirdShot(int x,int y)
	{
		//System.out.println("thread : "+Thread.currentThread().getId()+" is checking shot");
		if(x>=11 && x <=95  && y>=60 && y<=128)
		{
			update_score(lavel*200);
			return true;
		}
		if(x>=65 && x <=140  && y>=60 && y<=160)
		{
			update_score(lavel*100);
			return true;			
		}
		if(x>=130 && x <=180  && y>=95 && y<=170)
		{
			update_score(lavel*100);
			return true;
		}
		
		return false;
	}
	boolean isforwardbirdShot(int x,int y)
	{
		//System.out.println("thread : "+Thread.currentThread().getId()+" is checking");
		if(x>=206 && x <=260  && y>=65 && y<=124)
		{
			update_score(lavel*200);
			return true;
		}
		if(x>=95 && x <=140  && y>=96 && y<=161)
		{
			update_score(lavel*100);
			return true;
		}
		if(x>=138 && x <=210 && y>=58 && y<=158)
		{
			update_score(lavel*100);
			return true;
		}
		return false;
		
	}
	public boolean is_inframe()
	{
		//System.out.println("thread : "+Thread.currentThread().getId()+" checking in frame");
		if(currentPositionX<-270 || currentPositionX>WIDITH || currentPositionY<-240 || currentPositionY>HEIGHT)
		{
			
			reverse_inframe_status(); 
			return false;
		}
		else return true;
	}
	public void dropBird()
	{
		System.out.println("thread : "+Thread.currentThread().getId()+" dropping");
		recentbird1=fall;
		setIcon(fall);
		//System.out.println("dropping");
		for(int j=currentPositionY;j<HEIGHT+100;j+=1)
		{
			//System.out.println(currentPositionX+" "+j);
			setBounds(currentPositionX,j,271,228);
			try{ Thread.sleep(5); }
			catch(Exception e) { System.out.println("sleep exception");}
				
		}
		reverse_inframe_status();
		birdshot=false;
		if(r.nextBoolean()) flyForward();
		else flyBackward();
	}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void mouseClicked(MouseEvent me)
	{
		
		System.out.println("clicked in x : "+me.getX()+" y : "+me.getY());
		//System.out.println("current x : "+currentPositionX+" y : "+currentPositionY);
		int x=me.getX();
		int y=me.getY();
		System.out.println("clicked");
		//bird1
		if(forwardbirdtaken) //when forward bird visible
		{
			//System.out.println("forword bird visible");
			if(isforwardbirdShot(x,y))
			{
				reverse_birdshot_status();
				//System.out.println("bird shot");
				//try{ Thread.sleep(2000); }
				//catch(Exception e) { }
			}
		}
		else
		{
			if(isbackwardbirdShot(x,y))
			{
				reverse_birdshot_status();
				//System.out.println("bird shot");
				
				//try{ Thread.sleep(2000); }
				//catch(Exception e) { }
			}
			
		}
		
		
	}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	
}