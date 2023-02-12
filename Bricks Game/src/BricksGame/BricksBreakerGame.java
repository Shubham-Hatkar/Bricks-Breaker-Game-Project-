package BricksGame;

import javax.swing.JFrame;
import javax.swing.*;

public class BricksBreakerGame 
{
	public static void main(String[] args)
	{
		JFrame obj = new JFrame();
		GamePlay gameplay = new GamePlay();
		obj.setBounds(10,10,700,600); // Width = 700, height = 600
		obj.setResizable(false);
		obj.setTitle("BricksBreaker");
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameplay);
	}
}
