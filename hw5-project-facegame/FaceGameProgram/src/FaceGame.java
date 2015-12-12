import acm.program.*;
import acm.graphics.*;
import acm.util.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class FaceGame extends Program{
	private Profile2 canvas = new Profile2();
	
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	
	private JButton b1 = new JButton("Update Status");
	private JButton b2 = new JButton("Search!");
	private JButton b3 = new JButton("Add Profile Picture");
	private JButton b4 = new JButton("Play Game");
	private JButton b5 = new JButton("Add Friends");
	

	private JTextField tf1 = new JTextField("New Status", 15);
	private JTextField tf2 = new JTextField("Search Friends", 15);
	private JTextField tf3 = new JTextField("New Friends", 15);
	private JTextField tf4 = new JTextField("Picture Path", 15);

	public void init() {
		this.setSize(1200, 800);
		addComponents();
	}
	
	private void addComponents(){
		add(canvas,CENTER);
		p1.setBackground(new Color(0.9f, 1.0f, 1.0f));
		
		p1.add(tf1);
		p1.add(b1);

		p1.add(tf3);
		p1.add(b5);
		// added the image path input Textfield
		p1.add(tf4);
		p1.add(b3);

		p2.setBackground(new Color(0.9f, 0.9f, 0.9f));
		p2.add(tf2);
		p2.add(b2, BorderLayout.SOUTH);

		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.WEST);

		
		char[] pw = {'c','c'};
		
		Person p = new Person ("Fanglin Lu", "fanglinlu0811@gmail.com", pw);
		p.setPicturePath("penguin.png");
		
		Profile2 pf = new Profile2 ();
		pf.display(p);
	
	}

	
	
}
