package ecosystem;

import Terrain.Terrain;
import aa.DNA;
import constantes.WorldConstants;
import ecosystem.Animal;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;


public class Snake extends Animal{
	
	private PGraphics parent;	
	PImage img;
	
	public Snake(PVector pos, float mass, float radius, PGraphics p3) {
		super(pos, mass, radius, 0, p3);
		this.parent = p3;
		energy = WorldConstants.INI_SNAKE_ENERGY;
		dna = new DNA(random(3f, 4f), random(5f, 8f), random(1f, 4f), (float)(Math.PI * 0.3f), random(1f,3f), random(3,5),
		        random(.3f, .6f), random(1f,3f), (float)(Math.PI/8));
		setDNA(dna);
		
	}
	
	public Snake(Snake s, boolean mutate, int fatherGeneration, PGraphics parent) {
		super(s, mutate,fatherGeneration, parent);
		this.parent = parent;
		energy = WorldConstants.INI_SNAKE_ENERGY;
		dna = new DNA(random(3f, 4f), random(5f, 8f), random(1f, 4f), (float)(Math.PI * 0.3f), random(1f,3f), random(3,5),
		        random(.3f, .6f), random(1f,3f), (float)(Math.PI/8));
		setDNA(dna);
	}

	@Override
	public Snake reproduce(boolean mutate) {
		Snake child = null;
		if(energy > WorldConstants.SNAKE_ENERGY_TO_REPRODUCE) {
			energy = WorldConstants.INI_MOUSE_ENERGY;
			child = new Snake(this, mutate, getGeneration(), parent);
			if(mutate) child.mutateBehaviors();
		}
		return child;
	}

	
	

	@Override
	public void eat(Terrain terrain) {
		
	}
	
	@Override
	public void display(PApplet p) {
		
		p.push();
		p.pushStyle();
		p.translate(pos.x, -radius*2, pos.y);
		p.stroke(0);
		p.fill(p.color(102, 255, 102));
		p.box(radius);
		p.popStyle();
		p.pop();
	}
	
	public void display(PGraphics p3, Terrain terrain) {
		
		p3.push();
		p3.pushStyle();
		p3.translate(pos.x, -radius/2-terrain.cellWidth/2, pos.y);
		p3.stroke(0);
		p3.fill(p3.color(102, 255, 102));
		p3.box(radius);
		p3.popStyle();
		p3.pop();
	}

	@Override
	public PImage getImage(PApplet p) {
		return p.loadImage("../imgs/snake.png");
	}

	@Override
	public String getName() {
		return "Snake";
	}
}
