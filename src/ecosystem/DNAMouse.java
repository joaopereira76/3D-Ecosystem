package ecosystem;


public class DNAMouse{
	public float maxSpeed;
    public float maxForce;
    public float visionDistance;
    public float visionSafeDistance;
    public float visionAngle;
    public float deltaTPursuit;
    public float radiusArrive;
    public float deltaTWander;
    public float radiusWander;
    public float deltaPhiWander;

    public DNAMouse() {
        //Physics
        maxSpeed = random(1f, 3f);
        maxForce = random(4f, 7f);
        //Vision
        visionDistance = random(0.5f, 2f);
        visionSafeDistance = 0.25f * visionDistance;
        visionAngle = (float)(Math.PI * 0.3f);
        //Pursuit
        deltaTPursuit = random(0.5f,1f);
        //Arrive
        radiusArrive = random(3,5);
        //Wander
        deltaTWander = random(.3f, .6f);
        radiusWander = random(1f,3f);
        deltaPhiWander = (float)(Math.PI/8);
    }
    
    public DNAMouse(DNAMouse dna, boolean mutate) {
    	maxSpeed = dna.maxSpeed;
    	maxForce = dna.maxForce;
    	
    	visionDistance = dna.visionDistance;
    	visionSafeDistance = dna.visionSafeDistance;
    	visionAngle = dna.visionAngle;
    	
    	deltaTPursuit = dna.deltaTPursuit;
    	radiusArrive = dna.radiusArrive;
    	
    	deltaTWander = dna.deltaTWander;
    	deltaPhiWander = dna.deltaPhiWander;
    	radiusWander = dna.radiusWander;
    	
    	if (mutate) mutate();
    }
    
    private void mutate() {
    	maxSpeed += random(-0.05f, 0.05f);
    	maxSpeed = Math.max(0, maxSpeed);
    	
    	visionDistance += random(-0.1f,0.1f);
    	visionDistance = Math.max(0, visionDistance);
    	visionDistance = Math.min(visionDistance, 5f);
    	
    }
    
    public static float random(float min, float max) {
        return (float) (min + (max - min)*Math.random());
    }
}
