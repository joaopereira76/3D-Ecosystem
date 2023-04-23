package ecosystem;

import Terrain.Bloco;
import Terrain.Terrain;
import aa.DNA;
import constantes.WorldConstants;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class Fox extends Animal{
	
	private PGraphics parent;	
	PImage img;
	
	public Fox(PVector pos, float mass, float radius, PGraphics p3) {
		super(pos, mass, radius, 0, p3);
		this.parent = p3;
		energy = WorldConstants.INI_FOX_ENERGY;
		dna = new DNA(random(5f, 6f), random(8f, 10f), random(5, 15f), (float)(Math.PI * 0.3f), random(3f,10f), random(3,5),
		        random(.3f, .6f), random(1f,3f), (float)(Math.PI/8));
		setDNA(dna);
		
	}
	
	public Fox(Fox s, boolean mutate, int fatherGeneration, PGraphics parent) {
		super(s, mutate,fatherGeneration, parent);
		this.parent = parent;
		energy = WorldConstants.INI_FOX_ENERGY;
		dna = new DNA(random(5f, 10f), random(3f, 5f), random(5f, 15f), (float)(Math.PI * 0.3f), random(3f,10f), random(3,5),
		        random(.3f, .6f), random(1f,3f), (float)(Math.PI/8));
		setDNA(dna);
	}

	@Override
	public Fox reproduce(boolean mutate) {
		Fox child = null;
		if(energy > WorldConstants.FOX_ENERGY_TO_REPRODUCE) {
			energy = WorldConstants.INI_FOX_ENERGY;
			child = new Fox(this, mutate, getGeneration(), parent);
			if(mutate) child.mutateBehaviors();
		}
		return child;
	}

	
	

	@Override
	public void eat(Terrain terrain) {
		Bloco patch = (Bloco)terrain.pixel2Cell(pos.x, pos.y);
		if(patch.getState() == WorldConstants.PatchType.FOOD.ordinal()) {
			energy += WorldConstants.ENERGY_FROM_PLANT_FOX;
			patch.setFertile();
		}
	}
	
	@Override
	public void display(PApplet p) {
		
		p.push();
		p.pushStyle();
		p.translate(pos.x, -radius*2, pos.y);
		p.stroke(0);
		p.fill(p.color(251, 133, 0));
		p.box(radius);
		p.popStyle();
		p.pop();
	}
	
	public void display(PGraphics p3, Terrain terrain) {
		
		p3.push();
		p3.pushStyle();
		p3.translate(pos.x, -radius/2-terrain.cellWidth/2, pos.y);
		p3.stroke(0);
		p3.fill(p3.color(251, 133, 0));
		p3.box(radius);
		p3.popStyle();
		p3.pop();
	}

	@Override
	public PImage getImage(PApplet p) {
		return p.loadImage("../imgs/Fox.png");
	}

	@Override
	public String getName() {
		return "Fox";
	}
}
