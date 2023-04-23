package ecosystem;

import Terrain.Bloco;
import Terrain.Terrain;
import aa.DNA;
import constantes.WorldConstants;
import ecosystem.Animal;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;


public class Mouse extends Animal{

	private PGraphics parent;

	
	private PImage img;
	
	
	
	public Mouse(PVector pos, float mass, float radius, PGraphics p3) {
		super(pos, mass, radius, 0, p3);
		this.parent = p3;
		energy = WorldConstants.INI_MOUSE_ENERGY;
		dna = new DNA(random(1f, 4f), random(0.2f, 0.5f), random(0.5f, 2f), (float)(Math.PI * 0.3f), random(0.5f,1f), random(3,5),
		        random(.3f, .6f), random(1f,3f), (float)(Math.PI/8));
		setDNA(dna);
		
	}
	
	

	public Mouse(Mouse m, boolean mutate, int fatherGeneration, PGraphics parent) {
		super(m, mutate,fatherGeneration, parent);
		this.parent = parent;

		energy = WorldConstants.INI_MOUSE_ENERGY;

		dna = new DNA(random(1f, 4f), random(0.2f, 0.5f), random(0.5f, 2f), (float)(Math.PI * 0.3f), random(0.5f,1f), random(3,5),
		        random(.3f, .6f), random(1f,3f), (float)(Math.PI/8));
		setDNA(dna);
	}

	@Override
	public Mouse reproduce(boolean mutate) {
		Mouse child = null;
		if(energy > WorldConstants.MOUSE_ENERGY_TO_REPRODUCE) {
			energy = WorldConstants.MOUSE_ENERGY_TO_REPRODUCE;
			child = new Mouse(this, mutate, getGeneration(), parent);
			if(mutate) child.mutateBehaviors();
		}
		return child;
	}

	@Override
	public void eat(Terrain terrain) {
		Bloco patch = (Bloco)terrain.pixel2Cell(pos.x, pos.y);
		if(patch.getState() == WorldConstants.PatchType.FOOD.ordinal()) {
			energy += WorldConstants.ENERGY_FROM_PLANT_MOUSE;
			patch.setFertile();
		}
		
	}
	
	@Override
	public PImage getImage(PApplet p) {
		return p.loadImage("../imgs/mouse.png");
	}
	
	
	@Override
	public void display(PApplet p) {
		
		p.push();
		p.pushStyle();
		p.translate(pos.x, -radius, pos.y);
		p.stroke(0);
		p.fill(p.color(176, 137, 104));
		p.box(radius);
		p.popStyle();
		p.pop();
	}
	
	public void display(PGraphics p3, Terrain terrain) {
		
		p3.push();
		p3.pushStyle();
		p3.translate(pos.x, -radius/2-terrain.cellWidth/2, pos.y);
		p3.stroke(0);
		p3.fill(p3.color(176, 137, 104));
		p3.box(radius);
		p3.popStyle();
		p3.pop();
	}



	@Override
	public String getName() {
		return "Mouse";
	}
}


