package edu.ucsb.cs56.w16.drawings.andrewhuang.advanced;

import java.awt.Shape;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import edu.ucsb.cs56.w16.drawings.utilities.ShapeTransforms;
/**
 * Andrew Huang's animation, built off of Andrewberl's sample
 *
 * @author Andrew Huang
 * @version for CS56, W16
 */


public class AnimatedPictureViewer {

    private DrawPanel panel = new DrawPanel();
    
    Thread anim;   
    private int rad = 200;    
    private int x = 200;
    private int y = 200;
    private double rotate = 1;
    private double theta = 0;
    private int dxA = 0;
    private int dxB = 166;
    private int dxC = 166*2;
    private int dxD = -166;

    private int dxA1 = 500-166;
    private int dxB1 = 500-166*2;
    private int dxC1 = 500;
    private int dxD1 = 0;
    
    private int speed = 1;
    private int redd = 0;
    private int greenn =0;
    private int bluee =0;
    public static void main (String[] args) {
	new AnimatedPictureViewer().go();
    }
    public void go() {
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	frame.getContentPane().add(panel);
	frame.setSize(500,510);
	frame.setTitle("Andrew Huang's Watch Ad");
	frame.setVisible(true);
      
	frame.getContentPane().addMouseListener(new MouseAdapter() {
		public void mouseEntered(MouseEvent e){
		    System.out.println("Play");
		    anim = new Animation();
		    anim.start();
		}
		
		public void mouseExited(MouseEvent e){        
		    System.out.println("Pause");
		    // Kill the animation thread
		    anim.interrupt();
		    while (anim.isAlive()){}
		    anim = null;         
		    panel.repaint();        
		}

		public void mouseClicked(MouseEvent e){
		    if(speed >= 5)
			{
			    speed = 0;
			}
		    speed++;
		    System.out.print(speed);
		    System.out.println("x speed");
		}
	    });
      
    } // go()

    class DrawPanel extends JPanel {
	public void paintComponent(Graphics g) {

	    Graphics2D g2 = (Graphics2D) g;

	    // Clear the panel first
	    g2.setColor(Color.white);
	    g2.fillRect(0,0,this.getWidth(), this.getHeight());
	    
	    Color myColor = new Color(redd,greenn,bluee);
	    g2.setColor(myColor);
	    Watch test = new Watch(x, y, 100,50);
	    Shape t = ShapeTransforms.rotatedCopyOf(test,rotate);
	    //t = ShapeTransforms.scaledCopyOf(t,0.5,0.5);
	    g2.draw(t);
	    
	    g2.setColor(Color.RED);
	    Watch a = new Watch(dxA,0,166,10);
	    g2.draw(a);
	    Watch b = new Watch(dxB,0,166,10);  
	    g2.setColor(Color.BLUE);
	    g2.draw(b);
	    Watch c = new Watch(dxC,0,166,10);
	    g2.setColor(Color.MAGENTA);
	    g2.draw(c);
	    Watch d = new Watch(dxD,0,166,10);  
	    g2.setColor(Color.ORANGE);
	    g2.draw(d);

	    g2.setColor(Color.RED);
	    Watch a2 = new Watch(dxA1,489,166,10);
	    g2.draw(a2);
	    Watch b2 = new Watch(dxB1,489,166,10);  
	    g2.setColor(Color.BLUE);
	    g2.draw(b2);
	    Watch c2 = new Watch(dxC1,489,166,10);
	    g2.setColor(Color.MAGENTA);
	    g2.draw(c2);
	    Watch d2 = new Watch(dxD1,489,166,10);  
	    g2.setColor(Color.ORANGE);
	    g2.draw(d2);
	}
    }
    
    class Animation extends Thread {
	public void run() {
	    try {
		while (true) {
		    // moves in circular motion
		    theta += Math.toRadians(0.5*speed);
		    x = (int)(200 + 100*Math.sin(theta));
		    y = (int)(200 + 100*Math.cos(theta));
		    
		    rad -= 1;

		    //rotates center watch
		    rotate += Math.toRadians(2*speed);		    
		    
		    //top bannner
		    dxA+=1*speed;
		    if(dxA >=500)
			dxA = -166;		    
		    dxB+=1*speed;
		    if(dxB >=500)
			dxB = -166;		    
		    dxC+=1*speed;
		    if(dxC >=500)
			dxC = -166;		    
		    dxD+=1*speed;
		    if(dxD >=500)
			dxD = -166;		    
		    
		    //bottom banner
		    dxA1-=1*speed;
		    if(dxA1 <= -166)
			dxA1 = 500;		    
		    dxB1-=1*speed;
		    if(dxB1 <= -166)
			dxB1 = 500;		    
		    dxC1-=1*speed;
		    if(dxC1 <= -166)
			dxC1 = 500;		    
		    dxD1-=1*speed;
		    if(dxD1 <= -166)
			dxD1 = 500;
		    
		    //random color
		    redd+=1;
		    if(redd>=256)
			redd=0;
		    greenn+=3;
		    if(greenn>=256)
			greenn = 0;
		    bluee+=2;
		    if(bluee>=256)
			bluee = 0;

		    panel.repaint();
		    Thread.sleep(20);
		}
	    } catch(Exception ex) {
		if (ex instanceof InterruptedException) {
		    // Do nothing - expected on mouseExited
		} else {
		    ex.printStackTrace();
		    System.exit(1);
		}
	    }
	}
    }
    
}
