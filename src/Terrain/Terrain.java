package Terrain;

import java.util.ArrayList;
import java.util.List;

import constantes.WorldConstants;
import physics.Body;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;
import tools.CustomRandomGenerator;




public class Terrain {

	protected int nrows;
	protected int ncols;
	protected int nStates;
	private int radiusNeigh;
	protected Bloco[][] cells;
	public float cellWidth;
	public float cellHeight;
	float[] world;
	
	int[] colors;
	
	public Terrain(PGraphics p3, float[] world, int nrows, int ncols, int nStates, int radiusNeigh, int[] colors, PApplet p) {
		this.colors = colors;
		this.nrows = nrows;
		this.ncols = ncols;
		this.nStates = nStates;
		this.radiusNeigh = radiusNeigh;
		cells = new Bloco[nrows][ncols];
		this.world = world;
		colors = new int[nStates];
		cellWidth = (world[1]-world[0])/ncols;
		cellHeight = (world[3]-world[2])/ncols;;
		createCells(p3, p);
		
		
	}
	
	//Básico
	public float[] getWorld() {
		return world;
	}
	
	protected void createCells(PGraphics p3, PApplet p) {
		int minRT = (int) (WorldConstants.REGENERATION_TIME[0]*1000);
		int maxRT = (int) (WorldConstants.REGENERATION_TIME[1]*1000);
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				int timeToGrow = (int) (minRT+(maxRT-minRT)*Math.random());
				cells[i][j] = new Bloco(p3,this, i, j, timeToGrow, p);
			}
			
		}
		setMooreNeighbors();
	}
	
	public void display(PGraphics p3) {
		for(int i=0;i<nrows;i++) {
			for(int j=0;j<ncols;j++) {
				cells[i][j].display(p3);
			}
		}
	}

	public int[] getStateColors() {
		return colors;
	}
	
	
	protected void setMooreNeighbors() {
		
		int NN = (int) Math.pow(2 *radiusNeigh + 1, 2);
		for(int i=0;i<nrows;i++) {
			for(int j=0;j<ncols;j++) {
				Bloco[] neigh = new Bloco[NN];
				int n = 0;
				for(int ii=-radiusNeigh;ii<=radiusNeigh;ii++) {
					int row = (i + ii + nrows) % nrows;
					for(int jj=-radiusNeigh;jj<=radiusNeigh;jj++) {
						int col = (j + jj + ncols) % ncols;
						neigh[n++] = cells[row][col];
					}
				}
				cells[i][j].setNeighbors(neigh);
			}
		}
	}
	
	//Majoruty Rule
	public boolean majorityRule() {
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				((Bloco) cells[i][j]).computeHistogram();
			}
		}
		
		boolean anyChanged = false;
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				boolean changed =
						((Bloco) cells[i][j]).applyMajorityRule();
				if(changed) anyChanged = true;
				
			}
			
		}
		return anyChanged;
	}
	
	public void initRandomCostum(double[] pmf) {
		CustomRandomGenerator crg = new CustomRandomGenerator(pmf);
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				cells[i][j].setState(crg.getRandomClass());
			}
		}
	}
	
	
	
	public void regenerate() {
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				((Bloco)cells[i][j]).regenerate();
			}
		}
	}
	
	public List<Body> getObstacles(){
		List<Body> bodies = new ArrayList<Body>();
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				if(cells[i][j].getState() == WorldConstants.PatchType.OBSTACLE.ordinal()) {
					Body b = new Body(this.getCenterCell(i,j));
					bodies.add(b);
				}
			}
		}
		return bodies;
	}
	
	public PVector getCenterCell(int row, int col) {
		float x = (col + 0.5f) * cellWidth;
		float y = (row + 0.5f) * cellHeight;
		return new PVector((float)x, (float)y);
		
	}
	
	
	public Bloco pixel2Cell(float x, float y) {
		int row = (int)((y-world[2])/cellHeight);
		int col = (int)((x-world[0])/cellWidth);
		if (row >= nrows) row = nrows - 1;
		if (col >= ncols) col = ncols - 1;
		if(row < 0) row = 0;
		if(col < 0) col = 0;
		return cells[row][col];
	}
	
	
	
}
