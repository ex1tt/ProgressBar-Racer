package progressBarRace;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class RaceFrame extends JFrame{
	

	private static final long serialVersionUID = 1L;
	
	private Random random = new Random();
	private int[] timeTaken = new int[5];
	private String[] finalPlaces = new String[5];
	private String[] displayPlaces = new String[5];
	private Color[] colorPlaces = new Color[5];

	private ProgressBar bar = new ProgressBar("Red", Color.RED, 60, random.nextInt(150, 225));
	private ProgressBar bar2 = new ProgressBar("Blue",Color.BLUE, 120, random.nextInt(150, 225));
	private ProgressBar bar3 = new ProgressBar("Green", new Color(198, 198, 64), 180, random.nextInt(150, 225));
	private ProgressBar bar4 = new ProgressBar("Pink", Color.pink, 240, random.nextInt(150, 225));
	private ProgressBar bar5 = new ProgressBar("Orange", new Color(206, 110, 0), 300, random.nextInt(150, 225));
	
	private Thread t1;
	private Thread t2;
	private Thread t3;
	private Thread t4;
	private Thread t5;
	
	private Label winningMessage;
	
	RaceFrame() throws InterruptedException {
		this.setTitle("ProgressBar Racer");
		this.setSize(1200, 500);
		this.setLayout(null);

		winningMessage = new Label();
		winningMessage.setBounds(465, 380, 500, 50);
		winningMessage.setFont(new Font("Verdana", Font.BOLD, 30));
		
		this.add(bar);
		this.add(bar2);
		this.add(bar3);
		this.add(bar4);
		this.add(bar5);
		this.add(winningMessage);
		this.setVisible(true);
		
		startSimulation();	// starts the race...
	}

	private void startSimulation() throws InterruptedException {
		t1 = new Thread(bar);	// adding all progressbars to there
		t2 = new Thread(bar2);	// own individual thread...
		t3 = new Thread(bar3);
		t4 = new Thread(bar4);		
		t5 = new Thread(bar5);
		
		t1.start();	// starting all threads...
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
		waitTillFinished(t1,t2,t3,t4,t5);	// waiting till all 5 threads have completed before
											// continuing to execute main thread...
		timeTaken[0] = bar.timeTaken;
		timeTaken[1] = bar2.timeTaken;	// adding the time each thread took to complete
		timeTaken[2] = bar3.timeTaken;	// into 1 array..
		timeTaken[3] = bar4.timeTaken;
		timeTaken[4] = bar5.timeTaken;
		Arrays.sort(timeTaken);	// sorting the array from lowest integer to highest
								// meaning "timeTaken[0]" would represent the winner/fastet time completed...
		checkFinalPosition(bar);	
		checkFinalPosition(bar2);	// checking the final position of
		checkFinalPosition(bar3);	// each thread based on how long
		checkFinalPosition(bar4);	// they took to complete...
		checkFinalPosition(bar5);	
		
		winningMessage.setText("(" + finalPlaces[0] + ")" +  " is the winner!");
		winningMessage.setForeground(colorPlaces[0]);
		
	}


	private void waitTillFinished(Thread t1,Thread t2,Thread t3,Thread t4,Thread t5) throws InterruptedException {
				t1.join();
				t2.join();
				t3.join();
				t4.join();
				t5.join();
	}

	private void checkFinalPosition(ProgressBar barr) {
		int place = 0;
		
		for(int i = 0; barr.timeTaken!=timeTaken[i]; i++) {	// looping through each (time variable) in
				place = i + 1;								// the array until one matches with said thread
		} 													// and adding it to the corrosponding place in the array...
		finalPlaces[place] = barr.name;
		displayPlaces[place] = Integer.toString(place +1);
		colorPlaces[place] = barr.color;
		
		barr.setString(displayPlaces[place]);	// finally setting the string of each thread/progressBar
												// to there finishing places when all code is finished executing...
	}
}	

