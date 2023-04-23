package constantes;

import processing.core.PImage;

public class WorldConstants {
	
	//Camera
	
	//World
	public final static float[] MAP = {-50,50,-50,50};
	
	//Light
	public final static float VARIACAO = 140;
	public final static float CYCLETIME = 60000;
	
	
	//Terrain
	public final static int NROWS = 50;
	public final static int NCOLS = 50;
	public static enum PatchType{
		EMPTY, OBSTACLE, FERTILE, FOOD
	}
	
	public final static double[] PATCH_TYPE_PROB = {0.15f, 0.15f, 0.2f, 0.5f};
	public final static int NSTATES = PatchType.values().length;
	public static int[] TERRAIN_IMGS = new int[NSTATES];
	public final static float[] REGENERATION_TIME = {5.f,15.f};
	public final static float[] GROW_TIME = {10.f,150.f};
	
	//Mouse Population
	public final static float MOUSE_SIZE = 0.8f;
	public final static float MOUSE_MASS = 0.02f;
	public final static int INI_MOUSE_POPULATION = 30;
	public final static float INI_MOUSE_ENERGY = 5f;
	public final static float ENERGY_FROM_PLANT_MOUSE = 3f;
	public final static float MOUSE_ENERGY_TO_REPRODUCE = 15f;
	
	//Rabbit Population
	public final static float RABBIT_SIZE = 1f;
	public final static float RABBIT_MASS = 1.5f;
	public final static int INI_RABBIT_POPULATION = 10;
	public final static float INI_RABBIT_ENERGY = 10f;
	public final static float ENERGY_FROM_PLANT_RABBIT = 10f;
	public final static float RABBIT_ENERGY_TO_REPRODUCE = 45f;
	
	//Snake Population
	public final static float SNAKE_SIZE = 1f;
	public final static float SNAKE_MASS = 1.5f;
	public final static int INI_SNAKE_POPULATION = 20;
	public final static float INI_SNAKE_ENERGY = 30f;
	public final static float ENERGY_FROM_MOUSE_SNAKE = 15f;
	public final static float ENERGY_FROM_RABBIT_SNAKE = 20f;
	public final static float SNAKE_ENERGY_TO_REPRODUCE = 50f; 
	
	//Fox Population
	public final static float FOX_SIZE                = 1f;
	public final static float FOX_MASS                = 3f;
	public final static int   INI_FOX_POPULATION      = 20;
	public final static float INI_FOX_ENERGY          = 60f;
	public final static float ENERGY_FROM_PLANT_FOX   = 8f;
	public final static float ENERGY_FROM_MOUSE_FOX   = 15f;
	public final static float ENERGY_FROM_RABBIT_FOX  = 30f;
	public final static float FOX_ENERGY_TO_REPRODUCE = 100f; 
	
}
