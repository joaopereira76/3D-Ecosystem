package aa;

public class DNA {
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

    public DNA(float maxSpeed, float maxForce, float visionDistance, float visionAngle
    		,float deltaTPursuit, float radiusArrive, float deltaTWander, float radiusWander, float deltaPhiWander) {
        //Physics
        this.maxSpeed = maxSpeed;
        this.maxForce = maxForce;
        //Vision
        this.visionDistance = visionDistance;
        visionSafeDistance = 0.25f * visionDistance;
        this.visionAngle = visionAngle;
        //Pursuit
        this.deltaTPursuit = deltaTPursuit;
        //Arrive
        this.radiusArrive = radiusArrive;
        //Wander
        this.deltaTWander = deltaTWander;
        this.radiusWander = radiusWander;
        this.deltaPhiWander = deltaPhiWander;
    }
    
    public DNA(DNA dna, boolean mutate) {
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
    	maxSpeed += random(-0.2f, 0.2f);
    	maxSpeed = Math.max(0, maxSpeed);
    }
    
    public static float random(float min, float max) {
        return (float) (min + (max - min)*Math.random());
    }
    
    /*
    public static void downMaxSpeed() {
        maxSpeed -= 1;
        if(maxSpeed<0) {
        	maxSpeed = 0;
        }
    }
    public static void upMaxSpeed() {
        maxSpeed += 1;
    }
    */
}
