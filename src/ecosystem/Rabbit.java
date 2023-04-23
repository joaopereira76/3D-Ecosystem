package ecosystem;

import Terrain.Bloco;
import Terrain.Terrain;
import aa.DNA;
import constantes.WorldConstants;
import ecosystem.Animal;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class Rabbit extends Animal{

	private PGraphics parent;
	
	PImage front;
	PImage back;
	PImage texture;
	
	public Rabbit(PVector pos, float mass, float radius, PGraphics parent) {
		super(pos, mass, radius, 0, parent);
		this.parent = parent;
		energy = WorldConstants.INI_RABBIT_ENERGY;
		dna = new DNA(random(2f, 5f), random(2f, 4f), random(1f, 4f), (float)(Math.PI * 0.3f), random(0.5f,1f), random(3,5),
		        random(.3f, .6f), random(1f,3f), (float)(Math.PI/8));
		setDNA(dna);
		
		
		
		
	}
	
	public Rabbit(Rabbit r, boolean mutate, int fatherGeneration, PGraphics parent) {
		super(r, mutate,fatherGeneration, parent);
		this.parent = parent;
		energy = WorldConstants.INI_RABBIT_ENERGY;
		dna = new DNA(random(2f, 5f), random(2f, 4f), random(1f, 4f), (float)(Math.PI * 0.3f), random(0.5f,1f), random(3,5),
		        random(.3f, .6f), random(1f,3f), (float)(Math.PI/8));
		setDNA(dna);
	}

	@Override
	public Rabbit  reproduce(boolean mutate) {
		Rabbit child = null;
		if(energy > WorldConstants.RABBIT_ENERGY_TO_REPRODUCE) {
			energy -= WorldConstants.INI_RABBIT_ENERGY;
			child = new Rabbit(this, mutate,getGeneration(), parent);
			if(mutate) child.mutateBehaviors();
		}
		return child;
	}

	@Override
	public void eat(Terrain terrain) {
		Bloco patch = (Bloco)terrain.pixel2Cell(pos.x, pos.y);
		if(patch.getState() == WorldConstants.PatchType.FOOD.ordinal()) {
			energy += WorldConstants.ENERGY_FROM_PLANT_RABBIT;
			patch.setFertile();
		}
		
	}
	@Override
	public void display(PApplet p) {
		
		
	}
	
	public void display(PGraphics p3, Terrain terrain) {
		p3.push();
		p3.pushStyle();
		p3.translate(pos.x, -radius/2-terrain.cellWidth/2, pos.y);
		p3.noStroke();
		p3.fill(210);
		p3.box(radius);
		p3.popStyle();
		p3.pop();
	}

	@Override
	public PImage getImage(PApplet p) {
		return p.loadImage("../imgs/rabbit.png");
	}

	@Override
	public String getName() {
		return "Rabbit";
	}
	
	

}
