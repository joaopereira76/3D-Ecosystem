package aa;

import java.util.ArrayList;
import java.util.List;
import constantes.WorldConstants;
import physics.Body;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

public class Boid extends Body{
	
	public DNA dna;
	protected List<Behavior> behaviors;
	public Eye eye;
	protected float phiWander;
	private float[] window;
	private float sumWeights;
	PImage img, imgI;
	
	protected Boid(PVector pos, float mass, float radius, int color, PGraphics p3) {
		super(pos, new PVector(), mass, radius, color);
		dna = new DNA(random(5f, 10f), random(4f, 7f), random(0.5f, 2f), (float)(Math.PI * 0.3f), random(0.5f,1f), random(3,5),
		        random(.3f, .6f), random(1f,3f), (float)(Math.PI/8));
		behaviors = new ArrayList<Behavior>();
		
		window = WorldConstants.MAP;
		
	}
	
	public void setEye(Eye eye) {
		this.eye = eye;
	}
	
	public Eye getEye() {
		return this.eye;
	}
	
	private void updateSumWeights() {
		sumWeights = 0;
		for(Behavior beh : behaviors)
			sumWeights += beh.getWeight(); 
	}
	
	public void addBehavior(Behavior behavior) {
		behaviors.add(behavior);
		updateSumWeights();
	}
	
	public void removeBehavior(Behavior behavior) {
		if(behaviors.contains(behavior))
			behaviors.remove(behavior);
		updateSumWeights();
	}
	
	public void mutateBehaviors() {
		for(Behavior behavior : behaviors) {
			if(behavior instanceof AvoidObstacle) {
				behavior.weight += DNA.random(-0.5f,  0.5f);
				behavior.weight = Math.max(0,  behavior.weight);
				
			}
		}
		updateSumWeights();
	}
	
	
	public DNA getDNA() {
		return dna;
	}
	
	public void setDNA(DNA dna) {
		this.dna = dna;
	}
	
	
	
	public List<Behavior> getBehaviors(){
		return behaviors;
	}

	public float getVelAtual() {
		return (float) Math.sqrt((vel.x*vel.x) + (vel.y*vel.y));
	}
	
	
	
	public void applyBehavior(int i, float dt) {
		if(eye != null) eye.look();
		Behavior behavior = behaviors.get(i);
		PVector vd = behavior.getDesiredVelocity(this);
		move(dt, vd);
	}

	public void applyBehaviors(float dt) {
		if(eye != null) eye.look();
		
		PVector vd = new PVector();
		for(Behavior behavior : behaviors) {
			PVector vdd = behavior.getDesiredVelocity(this);
			vdd.mult(behavior.getWeight()/sumWeights);
			vd.add(vdd);
		}
		move(dt, vd);
		
	}
	
	public void removeBehaviors() {
		behaviors = new ArrayList<Behavior>();
	}
	
	private void move(float dt, PVector vd) {
		vd.normalize().mult(dna.maxSpeed);
		PVector fs = PVector.sub(vd, vel);
		applyForce(fs.limit(dna.maxForce));
		super.move(dt);
		if (pos.x < window[0])
			pos.x += window[1] - window[0];
		if(pos.y < window[2])
			pos.y += window[3] - window[2];
		if(pos.x >= window[1])
			pos.x -= window[1] - window[0];
		if(pos.y >= window[3])
			pos.y -= window[3]- window[2];
		
	}
	
	public float getAngulo() {
		return -vel.heading();
	}
	
	@Override
	public void display(PApplet p) {
		/*
		float[] pp = plt.getPixelCoord(pos.x, pos.y);
		if(img == null) {
			setShape(p, plt);
			p.pushMatrix();
			p.translate(pp[0], pp[1]);
			p.rotate(-vel.heading());
			p.shape(shape);
			p.popMatrix();
		}
		else {
			
			p.pushMatrix();
			p.translate(pp[0], pp[1]);
			
			p.rotate((float) (-vel.heading() + Math.PI/2));
			if((-vel.heading() < Math.PI/2 && -Math.PI/2 < -vel.heading())){
				
					p.scale(-1,1);
					p.image(img,-img.width/2,-img.height/2);
				
				
			}
			else {
				
				p.image(img,-img.width/2,-img.height/2);
			}
					
			
			
			
			p.popMatrix();
		}
		*/
		
		
	}
	
	public float random(float min, float max) {
        return (float) (min + (max - min)*Math.random());
    }
	
	public double getDist(Boid boid1, Boid boid2){
		
		float x = boid2.getPos().x - boid1.getPos().x;
		float y = boid2.getPos().y - boid1.getPos().y;
		
		double dist = Math.sqrt((x*x)+(y*y));
		return dist;
	}
}
