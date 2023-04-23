package aa;

import processing.core.PVector;

public class Flee extends Behavior{

	public Flee(float weight) {
		super(weight);
		
	}
	
	@Override
	public PVector getDesiredVelocity(Boid me) {
		return PVector.sub(me.getPos(), me.eye.target.getPos());
	}

}
