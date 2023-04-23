 package ecosystem;

import Terrain.Bloco;
import Terrain.Terrain;
import aa.Behavior;
import aa.Boid;
import aa.DNA;
import aa.Eye;
import constantes.WorldConstants;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;


public abstract class Animal extends Boid implements IAnimal{

	public float energy;
	public boolean dead = false;
	int generation;
	
	protected Animal(PVector pos, float mass, float radius, int color, PGraphics p3) {
		super(pos, mass, radius, color, p3);
		generation = 0;
		
	}
	
	protected Animal(Animal a, boolean mutate,int fatherGeneration, PGraphics p) {
		super(a.pos, a.mass, a.radius, a.color, p);
		for(Behavior b : a.behaviors) {
			this.addBehavior(b);
		}
		if ( a.eye != null) {
			eye = new Eye(this, a.eye);
		}
		dna = new DNA(a.dna,mutate);
		generation = fatherGeneration + 1;
	}
	
	@Override
	public boolean die() {
		
		return ( energy < 0);
	}
	
	
	public void energy_consumption(float dt, Terrain terrain) {
		energy -= dt;
		energy -= mass*Math.pow(vel.mag(), 2)*dt/10;
		Bloco patch = (Bloco)terrain.pixel2Cell(pos.x, pos.y);
		if( patch.getState() == WorldConstants.PatchType.OBSTACLE.ordinal()) {
			energy -= 50*dt;
		}
		
	}
	
	public void dead() {
		dead = true;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public int getGeneration() {
		return generation;
	}
	
	public float getMaxSpeed() {
		return dna.maxSpeed;
	}
	
	public float getEnergy() {
		return energy;
	}
	
	
	
	public abstract PImage getImage(PApplet p);
	
	public abstract String getName();
	
	public void eat(Terrain terrain) {
		
	}
}
