package fractals;

import processing.core.PApplet;
import processing.core.PGraphics;


public class Turtle3D {

	private float[] dim;
	private float angle;
	int color;
	
	public Turtle3D(float[] dim, float angle) {
		this.dim = dim;
		this.angle = angle;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public void setPose(float[] position, float orientation, PGraphics p3) {
		p3.translate(position[0], position[1], position[2]);
		p3.rotate(-orientation);
	}
	
	public void scaling(float s) {
		dim[0] *= s;
		dim[1] *= s;
		dim[2] *= s;
	}
	
	public void render(LSystem lsys, PGraphics p3) {
		
		p3.noStroke();
		
		
		float init = 0;
		
		for(int i = 0; i<lsys.getSequence().length();i++) {
			
			char c = lsys.getSequence().charAt(i);
			if( c == 'F' || c == 'G') {
				if(c == 'F' )
					p3.fill(color);
				if(c == 'G' )
					p3.fill(p3.color(156, 102, 68));
				p3.box(dim[0], dim[1], dim[2]);
				p3.translate(dim[0], 0, 0);
			}
			else if(c == '+') p3.rotate(angle);
			else if(c == '-') p3.rotate(-angle);
			else if(c == '*') p3.rotateY(angle);
			else if(c == ':') p3.rotateY(-angle);
			else if(c == '[') p3.pushMatrix();
			else if(c == ']') p3.popMatrix();
		}
		
	}
	
}
