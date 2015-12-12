import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class Profile2 extends GCanvas {
	public void display (Person p) {
		removeAll();
		System.out.println("Check1");
		if(p!= null) {
			System.out.println("Check2");
			GLabel nameLabel = new GLabel (p.getName());
			addNameLabel (nameLabel);
			addImage(p.getPicturePath());
		}
	}
	
	private void addNameLabel (GLabel nameLabel) {
		nameLabel.setFont("Dialog-24");
		nameLabel.setColor(Color.BLACK);
		System.out.println("Check3");
		add (nameLabel);	
	}
	
	private void addImage (String path){
		if (path != "") {
			GImage image = new GImage (path);
				image.setSize(200, 200);
				add (image, 20, 20);
		}
		
	}
}