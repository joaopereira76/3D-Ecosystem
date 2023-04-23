package aa;

import processing.core.PVector;

public class Brake extends Behavior{

	public Brake(float weight) {
		super(weight);
		// TODO Auto-generated constructor stub
	}

	@Override
	public PVector getDesiredVelocity(Boid me) {
		return new PVector();
	}

}
