package progressBarRace;

import java.awt.Color;
import java.util.Random;

import javax.swing.JProgressBar;

public class ProgressBar extends JProgressBar implements Runnable{

	private static final long serialVersionUID = 1L;
	
	public String name;
	public Color color;
	public int timeTaken;
	private int randomNumber;
	private int finalRandomNumber;
	private Random random;
	
	ProgressBar(String name, Color color, int yBound, int randomNumber) {
		random = new Random();
		
		this.name = name;
		this.randomNumber = randomNumber;
		this.color = color;
		
		this.setValue(0);
		this.setMaximum(300000);
		this.setBounds(50, yBound, 1075, 50);
		this.setForeground(color);
		this.setStringPainted(true);
	}

	@Override
	public void run() {		
		timeTaken = 0;	// creating the variable that counts how long each progress bar takes to complete...
		
		while(this.getValue() != 300000) {
			finalRandomNumber = (int)(randomNumber * random.nextFloat());
																			
			if(random.nextInt(100) == random.nextInt(100)) {	// randomizing how much the bar increases
				finalRandomNumber += random.nextInt(35);		// everytime the loop is executed
			}													// to make each race unique and random...
			
			timeTaken +=1;	// incrementing the variable by 1 every time the loop is executed...
			
			this.setValue(this.getValue() + finalRandomNumber);
			
			try {
				Thread.sleep(5);	// the loop waits five miliseconds	before 
			} 						// executing each time to slow the race down...
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
	}
}
