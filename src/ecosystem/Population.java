package ecosystem;

import java.util.ArrayList
;
import java.util.List;

import Terrain.Terrain;
import aa.AvoidObstacle;
import aa.Boid;
import aa.Eye;
import aa.Pursuit;
import aa.Wander;
import constantes.WorldConstants;
import ecosystem.Animal;

import physics.Body;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;


public class Population {
	
	
	private List<Mouse> mouses;
	private List<Rabbit> rabbits;
	private List<Snake> snakes;
	private List<Fox> foxes;
	
	
	private float[] window;
	private boolean mutate = true;
	
	public Population(PGraphics p3, Terrain terrain, int numM, int numR, int numS, int numF, PApplet p ) {
		window = WorldConstants.MAP;
		mouses = new ArrayList<Mouse>();
		rabbits = new ArrayList<Rabbit>();
		snakes = new ArrayList<Snake>();
		foxes = new ArrayList<Fox>();
		
		
		List<Body> obstacles = terrain.getObstacles();
		
		for (int i = 0; i < numM; i++) {
			PVector pos = new PVector(p.random((float)window[0],(float)window[1]),
					p.random((float)window[2],(float)window[3]));
			
			Mouse m = new Mouse(pos, WorldConstants.MOUSE_MASS, WorldConstants.MOUSE_SIZE, p3);
			m.addBehavior(new Wander(1));
			m.addBehavior(new AvoidObstacle(0));
			Eye eye = new Eye(m, obstacles);
			m.setEye(eye);
			mouses.add(m);
			
		}
		
		for (int i = 0; i < numR; i++) {
			PVector pos = new PVector(p.random((float)window[0],(float)window[1]),
					p.random((float)window[2],(float)window[3]));
			
			Rabbit r = new Rabbit(pos, WorldConstants.RABBIT_MASS, WorldConstants.RABBIT_SIZE, p3);
			r.addBehavior(new Wander(1));
			r.addBehavior(new AvoidObstacle(0));
			Eye eye = new Eye(r, obstacles);
			r.setEye(eye);
			rabbits.add(r);
			
		}
		
		
		
		for (int i = 0; i < numS; i++) {
			PVector pos = new PVector(p.random((float)window[0],(float)window[1]),
					p.random((float)window[2],(float)window[3]));
			
			Snake s = new Snake(pos, WorldConstants.SNAKE_MASS, WorldConstants.SNAKE_SIZE, p3);
			
			s.addBehavior(new AvoidObstacle(0));
			Eye eye = new Eye(s, obstacles);
			s.setEye(eye);
			
			s.addBehavior(new Pursuit(1));
			snakes.add(s);
		}
		
		for (int i = 0; i < numF; i++) {
			PVector pos = new PVector(p.random((float)window[0],(float)window[1]),
					p.random((float)window[2],(float)window[3]));
			
			Fox f = new Fox(pos, WorldConstants.FOX_MASS, WorldConstants.FOX_SIZE, p3);
			
			f.addBehavior(new AvoidObstacle(0));
			Eye eye = new Eye(f, obstacles);
			f.setEye(eye);
			f.addBehavior(new Pursuit(1));
			foxes.add(f);
		}

	}
	
	public String getCountAnimals() {
		return "Mouses: " + mouses.size()+"\n"+
			     "Rabbits: "+ rabbits.size()+"\n"+
			     "Snakes: " + snakes.size()+"\n"+
			     "Foxes: "  + foxes.size()+"\n";
	}
	
	public void update(float dt, Terrain terrain, PApplet p) {
		move(terrain, dt);
		eat(terrain, p);
		energy_consumption(dt, terrain);
		reproduce(mutate);
		die();
	}
	
	private void move(Terrain terrain, float dt) {
		for(Mouse a : mouses)
			a.applyBehaviors(dt);
		for(Rabbit a : rabbits)
			a.applyBehaviors(dt);
		for(Snake a : snakes)
			a.applyBehaviors(dt);
		for(Fox a : foxes)
			a.applyBehaviors(dt);
	}
	
	private void eat(Terrain terrain, PApplet p) {
		//Mouses
		for(Mouse m : mouses)
			m.eat(terrain);
		
		//Rabbits
		for(Rabbit r : rabbits)
			r.eat(terrain);
		
		List<Boid> listaAux = new ArrayList<Boid>();
		listaAux.addAll(mouses);
		listaAux.addAll(rabbits);
		//Snakes
		for( Snake s : snakes) {
			double distanciamin = 1000;
			int index=0;
			
			
			Boid maisPerto = null;
			for (Boid b : listaAux) {
				double dist = s.getDist(s, b);
				//System.out.println(dist);
				
				if(dist<distanciamin) {
					maisPerto = b;
					distanciamin = dist;
				}
				
			}
			System.out.println(s.eye.target.getPos().x+" "+s.eye.target.getPos().y);
			if(listaAux.size()>0) {
				s.eye.target.setPos(new PVector(maisPerto.getPos().x, maisPerto.getPos().y));
				if(distanciamin < WorldConstants.SNAKE_SIZE + 1) {
					listaAux.remove(maisPerto);
					try {
						mouses.remove(maisPerto);
					}catch (Exception e) {}
					try {
						rabbits.remove(maisPerto);
					}catch (Exception e) {}
					s.energy += WorldConstants.ENERGY_FROM_MOUSE_SNAKE;
				}
					
			}
			else {
				PVector newPos = new PVector(p.random(WorldConstants.MAP[0], WorldConstants.MAP[1]), p.random(WorldConstants.MAP[2], WorldConstants.MAP[3]));
				
				if(p.random(0, 1f) < 0.001f) {
					
					s.eye.target.setPos(newPos);
					
				}
			}
			
		}
		//Foxes
		for( Fox f : foxes) {
			double distanciamin = 1000;
			int index=0;
			
			if(p.random(0f,1f) < 0.1f)
				f.eat(terrain);
			
			Boid maisPerto = null;
			for (Boid b : listaAux) {
				double dist = f.getDist(f, b);
				//System.out.println(dist);
				
				if(dist<distanciamin) {
					maisPerto = b;
					distanciamin = dist;
				}
				
				
			}
			if(listaAux.size()>0) {
				f.eye.target.setPos(new PVector(maisPerto.getPos().x, maisPerto.getPos().y));
				if(distanciamin < WorldConstants.SNAKE_SIZE + 1) {
					listaAux.remove(maisPerto);
					try {
						mouses.remove(maisPerto);
					}catch (Exception e) {}
					try {
						rabbits.remove(maisPerto);
					}catch (Exception e) {}
					f.energy += WorldConstants.ENERGY_FROM_MOUSE_SNAKE;
				}
					
			}
			else {
				PVector newPos = new PVector(p.random(WorldConstants.MAP[0], WorldConstants.MAP[1]), p.random(WorldConstants.MAP[2], WorldConstants.MAP[3]));
				
				if(p.random(0, 1f) < 0.001f) {
					
					f.eye.target.setPos(newPos);
					
				}
					
			}
			
	}
		
		
		
		
		
	}
	
	private void energy_consumption(float dt, Terrain terrain) {
		for(Mouse a : mouses)
			a.energy_consumption(dt, terrain);
		for(Snake a : snakes)
			a.energy_consumption(dt, terrain);
		for(Rabbit a : rabbits)
			a.energy_consumption(dt, terrain);
		for(Fox a : foxes)
			a.energy_consumption(dt, terrain);

	}
	
	private void die() {
		for(int i=mouses.size()-1; i>=0; i--) {
			Mouse a = mouses.get(i);
			if(a.die()) {
				a.dead();
				mouses.remove(a);
			}
		}
		
		for(int i=rabbits.size()-1; i>=0; i--) {
			Rabbit a = rabbits.get(i);
			if(a.die()) {
				a.dead();
				rabbits.remove(a);
			}
		}
		
		for(int i=snakes.size()-1; i>=0; i--) {
			Snake a = snakes.get(i);
			if(a.die()) {
				a.dead();
				snakes.remove(a);
			}
		}
		
		for(int i=foxes.size()-1; i>=0; i--) {
			Fox a = foxes.get(i);
			if(a.die()) {
				a.dead();
				foxes.remove(a);
			}
		}
		
	}
	
	private void reproduce(boolean mutate) {
		for(int mv=mouses.size()-1; mv>=0; mv--) {
			Mouse m = mouses.get(mv);
			Mouse child = m.reproduce(mutate);
			if(child != null) {
				mouses.add(child);
			}
		}
		
		for(int rv=rabbits.size()-1; rv>=0; rv--) {
			Rabbit m = rabbits.get(rv);
			Rabbit child = m.reproduce(mutate);
			if(child != null) {
				rabbits.add(child);
			}
		}
		
		
		for(int sv=snakes.size()-1; sv>=0; sv--) {
			Snake s = snakes.get(sv);
			Snake child = s.reproduce(mutate);
			if(child != null) {
				snakes.add(child);
			}
		}
		
		for(int fv=foxes.size()-1; fv>=0; fv--) {
			Fox f = foxes.get(fv);
			Fox child = f.reproduce(mutate);
			if(child != null) {
				foxes.add(child);
			}
		}
		
	}
	
	public List<Animal> allAnimals() {
		List<Animal> allAnimals = new ArrayList<Animal>();
		allAnimals.addAll(mouses);
		allAnimals.addAll(rabbits);
		allAnimals.addAll(snakes);
		allAnimals.addAll(foxes);
		
		return allAnimals;
	}
	
	public void display (PGraphics p3, Terrain terrain) {
		for(Mouse a : mouses)
			a.display(p3, terrain);
		for(Rabbit a : rabbits)
			a.display(p3, terrain);
		for(Snake a : snakes)
			a.display(p3, terrain);
		for(Fox a : foxes)
			a.display(p3, terrain);
	}
	
}
