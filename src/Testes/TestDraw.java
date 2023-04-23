package Testes;

import processing.core.PApplet;
import setup.IProcessingApp;

public class TestDraw implements IProcessingApp{

	@Override
	public void setup(PApplet p) {
		p.camera(0, 0, 2, 0, 0, 0, 0, 1, 0);

		// Set up the light source
		
		
	}

	@Override
	public void draw(PApplet p, float dt) {
		p.background(200);
		p.noStroke();
		p.fill(0, 255, 0);
		p.box(1);
		for (int i = 0; i < 1; i+=0.1f) {
			for (int j = 0; j < 1; j+=0.1f) {
				p.box(0.05f,0.5f, 0.05f);
			}
			
		}
		
	}

	@Override
	public void mousePressed(PApplet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(PApplet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(PApplet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(PApplet p) {
		// TODO Auto-generated method stub
		
	}

}
