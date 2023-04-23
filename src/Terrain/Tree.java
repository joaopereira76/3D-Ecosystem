package Terrain;

import java.sql.Time;
import java.time.Clock;

import fractals.LSystem;
import fractals.Rule;
import fractals.Turtle3D;
import processing.core.PApplet;
import processing.core.PGraphics;

public class Tree {
	
	Rule[] rules = {new Rule('F',"G[+F]G[-F]G[*F]G[:F]+F"), new Rule('G',"GG")};
	private LSystem lsys;
	private Turtle3D turtle;
	float[] dim = new float[3];
	private float[] startingPos = {0,0,0};
	float timeToGrow;
	int maxGeneration;
	float scaling;
	
	int generation = 0;
	
	long tempoInicial;
	
	
	
	float[] position;
	
	
	public Tree(float treeGrow, int maxGeneration, float scaling, float[] position) {
		
		
		this.timeToGrow = treeGrow;
		
		this.maxGeneration = maxGeneration;
		this.scaling = scaling;
		
		tempoInicial = System.currentTimeMillis();
		
		this.position = position;
		
		dim[0] = 1;
		dim[1] = 0.2f;
		dim[2] = 0.2f;
		
		lsys = new LSystem("F", rules);
		turtle = new Turtle3D(dim, PApplet.radians(20));
	}
	
	public void draw(PGraphics p3) {
		
		turtle.setColor(p3.color(255, 173, 198));
		
		p3.push();
		p3.translate(position[0], position[1], position[2]);
		turtle.setPose(startingPos, PApplet.radians(90), p3);
		turtle.render(lsys, p3);
		p3.pop();
		
		
		
		if(System.currentTimeMillis() - tempoInicial> timeToGrow && generation <= maxGeneration) {
			lsys.nextGeneration();
			turtle.scaling(scaling);
			tempoInicial = System.currentTimeMillis();
			generation++;
		}
		
	}

}
