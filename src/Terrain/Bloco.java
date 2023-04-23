package Terrain;

import java.util.ArrayList;
import java.util.Arrays;

import constantes.WorldConstants;
import constantes.WorldConstants.PatchType;
import fractals.LSystem;
import fractals.Rule;
import fractals.Turtle3D;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import tools.Histogram;

public class Bloco {
	private int row, col;
	protected int state;
	private Bloco[] neighbors;
	protected Terrain terrain;
	private Histogram hist;
	
	private long eatenTime;
	private int timeToGrow;
	
	PImage empty;
	PImage obstacle;
	PImage fertile;
	PImage food;
	
	float altura;
	
	int[] colors = new int[4];
	
	float[] position = new float[3];
	
	Tree tree;
	float treeGrow;
	
	public Bloco(PGraphics p3,Terrain terrain, int row, int col, int timeToGrow, PApplet p) {
		
		this.terrain = terrain;
		this.row = row;
		this.col = col;
		this.state = 0;
		this.neighbors = null;	
		
		this.timeToGrow = timeToGrow;
		eatenTime = System.currentTimeMillis();
		
		position[0] = terrain.getWorld()[0]+col*terrain.cellWidth;
		position[1] = 0;
		position[2] = terrain.getWorld()[2]+row*terrain.cellHeight;
		
		altura = p.random(-0.2f,0.2f);
		
		colors[0] = p.color(108, 88, 76);
		colors[1] = p.color(58, 90, 64);
		colors[2] = p.color(58, 90, 64);
		colors[3] = p.color(173, 193, 120);
		
		treeGrow = p.random(WorldConstants.GROW_TIME[0]*1000, WorldConstants.GROW_TIME[1]*1000);
		
		
		
		
		

	}
	
	//Básico
	public void setNeighbors(Bloco[] neigh) {
		this.neighbors = neigh;
	}
	
	public Bloco[] getNeighbors() {
		return neighbors;
	}
	
	public void setState(int state) {
		this.state = state;
		if(state == 1){
			tree = new Tree(treeGrow , 2, 0.8f, position);
		}
	}
	
	public int getState() {
		return state;
	}
	
	//Majority Rule
	public void computeHistogram() {
		 Bloco[] neighbors = getNeighbors();
		 int[] data = new int[neighbors.length];
		 for (int i = 0; i < neighbors.length; i++) {
			data[i] = neighbors[i].getState();
		}
		hist = new Histogram(data, terrain.nStates);
	 }
	 
	 public boolean applyMajorityRule() {
		 int mode = hist.getMode(0);
		 boolean changed = false;
		 if (getState() != mode) {
			 setState(mode);
			 changed = true;
		 }
		 return changed;
	 }
	 
	 //Ecossistema
	 public void setFertile() {
			state = PatchType.FERTILE.ordinal();
			eatenTime = System.currentTimeMillis();
		}
		
		public void regenerate() {
			if( state == PatchType.FERTILE.ordinal() && System.currentTimeMillis() > (eatenTime + timeToGrow))
				state = PatchType.FOOD.ordinal();
		}
	 
	 public void display(PGraphics p3) {
		 
		
		 
		p3.push();
		p3.translate(terrain.getWorld()[0]+col*terrain.cellWidth, altura, terrain.getWorld()[2]+row*terrain.cellHeight);
		p3.noStroke();
		p3.fill(colors[state]);
		p3.box(terrain.cellWidth);
		p3.pop();
		if(state == PatchType.OBSTACLE.ordinal()) {
			tree.draw(p3);
		}
		if(state == PatchType.FOOD.ordinal()) {
			
		}
		
	}
	 
	 
	
}
