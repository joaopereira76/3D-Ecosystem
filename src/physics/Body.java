package physics;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Body  extends Mover{
	public int color;
	protected float radius;
	PImage img;
	
	public Body(PVector pos, PVector vel, float mass, float radius, int color) {
		super(pos, vel, mass);
		this.color = color;
		this.radius = radius;
	}
	
	public Body(PVector pos) {
		super(pos, new PVector(), 0f);
	}

	
	public void setImg(PImage img) {
		this.img = img;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public void setRadius(float radius) {
		this.radius = radius;
	}
	
	
	public void display(PApplet p) {
		/*
		if(img == null) {
			p.pushStyle();
			float[] pp = plt.getPixelCoord(pos.x, pos.y);
			float[] r = plt.getDimInPixel(radius, radius);
			p.noStroke();
			p.fill(color);
			p.circle(pp[0], pp[1], 2*r[0]);
			p.popStyle();
		}
		else {
			float[] pp = plt.getPixelCoord(pos.x, pos.y);
			p.pushMatrix();
			p.translate(pp[0], pp[1]);
			p.image(img,-img.width/2,-img.height/2);
			p.popMatrix();
		}
		*/
		
	}
}
