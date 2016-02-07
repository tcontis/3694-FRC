package org.MasterSamurai.Applications;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MainJFrame extends JFrame {
	public static void main(String[] argv) throws Exception{
		//Set visible
		new  MainJFrame().setVisible(true);
	}
	
	private MainJFrame(){
		//Set title and setup layout
		super("Autonomous Chooser");
		setSize(500, 200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		//Setup elements
		JLabel label1 = new JLabel("Input Defense Option");
		JTextField textfield = new JTextField();
		
		//Add textfield key detector and set size
		textfield.addKeyListener(new MyKeyListener());
		textfield.setColumns(45);
		
		//Add Layout
		add(label1);
		add(textfield);
		

	}
}
