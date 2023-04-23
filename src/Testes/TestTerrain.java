package Testes;

import Terrain.Terrain;
import constantes.WorldConstants;
import processing.core.PApplet;
import setup.IProcessingApp;


public class TestTerrain implements IProcessingApp {

	Terrain terrain;
	
	float camX, camY, camZ; // camera position
	
	@Override
	public void setup(PApplet p) {
		
		int[] colors = {p.color(255, 204,102), p.color(127,127,127), p.color(0, 102, 0), p.color(102, 255, 51)};
		//terrain = new Terrain(p, WorldConstants.MAP, 50, 50, WorldConstants.NSTATES, 1, colors);
		terrain.initRandomCostum(WorldConstants.PATCH_TYPE_PROB);
	}

	@Override
	public void draw(PApplet p, float dt) {
		p.background(0);
		camX = PApplet.map(p.mouseX, 0, p.width, -200,200);
		camY = PApplet.map(p.mouseY, 0, p.height, -200,200);
		p.camera(camX, camY, (float)(p.height/15.0) / PApplet.tan((float) (PApplet.PI*30.0 / 180.0)),0f,0f, 0f, 0f, 1f, 0f);
		
		terrain.regenerate();
		//terrain.display(p);
		
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
