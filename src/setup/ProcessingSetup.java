package setup;

import processing.core.PApplet; 


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import Testes.TestDraw;
import Testes.TestEcosystem;
import Testes.TestTerrain;




public class ProcessingSetup extends PApplet{
	
	private static JFrame frame;
	private static JComboBox comboBox;
	public static IProcessingApp app;
	private int lastUpdate;
	String programa;
	
	public void settings() {
		
		//size(800,800);
		
		//3D
		size(800,800, P2D);
		
		noSmooth();
		
	}
	
	public void setup() {
		
		System.out.println(app);
		app.setup(this);
		lastUpdate = millis();
		this.programa = null;
	}
	
	public void setPrograma(String programa) {
		this.programa = programa;
			
		//app = new TestTerrain();
		//app = new TestDraw();
		app = new TestEcosystem();
		
		
		PApplet.main(ProcessingSetup.class);

		
	}
	
	public void draw() {
		int now = millis();
		float dt = (now - lastUpdate)/1000f;
		lastUpdate = now;
		app.draw(this, dt);
		
	}
	
	public void mousePressed() {
		app.mousePressed(this);
	}
	
	public void mouseReleased() {
		app.mouseReleased(this);
	}
	
	public void mouseDragged() {
		app.mouseDragged(this);
	}
	
	public void keyPressed() {
		app.keyPressed(this);
	}
	
	
	
}
