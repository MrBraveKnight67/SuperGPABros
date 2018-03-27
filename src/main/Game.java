package main;

import javax.swing.JFrame;

public class Game {
	public static String[] ranks;
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame("Super GPA Bros");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		ranks = new String[]{"Jasper", "Alex Li", "Faibralin", "Kenneth", "Allen",
				"Measely Warren", "Alexander", "Karthik", "Junghwa", "Bilal",
				"Shreyas (2nd to Kevin Li)", "Rohith/sub-Kevin Li"};
		
	}
	
}