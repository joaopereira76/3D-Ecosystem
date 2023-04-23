package Testes;

import java.util.ArrayList;
import java.util.List;

import Terrain.Terrain;
import constantes.WorldConstants;
import controlP5.CallbackEvent;
import controlP5.CallbackListener;
import controlP5.ControlFont;
import controlP5.ControlP5;
import ecosystem.Animal;
import ecosystem.Population;
import peasy.PeasyCam;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PImage;

import processing.sound.SoundFile;
import setup.IProcessingApp;


public class TestEcosystem implements IProcessingApp{

	Terrain terrain;
	Population population;
	
	List<Animal> allAnimals;
	
	PFont font;
	
	float camX, camY, camZ; // camera position
	PeasyCam cam;
	float zoom = 40.0f;
	boolean cima = false, baixo = false, esquerda = false, direita = false;
	
	PImage background;
	SoundFile backgroundSound;
	
	private int numM, numR, numS, numF;
	
	int[] colors = new int[4];
	
	Animal atual;
	
	PGraphics p3;
	
	PImage mouse, rabbit, snake, fox;
	
	long tempoInicial, tempoAtual;
	boolean dia = true;
	
	PImage nightSky, daySky;
	
	private boolean visaoCima = true;
	
	private boolean menu = true;
	
	ControlP5 cp5;
	
	
	public void setup(PApplet p) {
		
		colors[0] = p.color(255, 204,102);
		colors[1] = p.color(255, 204,102);
		colors[2] = p.color(0, 102, 0);
		colors[3] = p.color(102, 255, 51);
		
		cp5 = new ControlP5(p);
		
		font = p.createFont("Impact",30);
		ControlFont fontC = new ControlFont(font,30);
		
		p3 = p.createGraphics(p.width, p.height, PConstants.P3D);
		
		try {
			// inicialização do som
			backgroundSound = new SoundFile(p, "../imgs/background.mp3");
			
			backgroundSound.loop();
		} catch (Exception e) {
			System.out.println("Loading sound...");
		}
		
		
		
		mouse  = p.loadImage("../imgs/mouse.png");
		rabbit = p.loadImage("../imgs/rabbit.png");
		snake  = p.loadImage("../imgs/snake.png"); 
		fox    = p.loadImage("../imgs/fox.png");
		
		mouse.resize(100, 100);
		rabbit.resize(100, 100);
		snake.resize(100, 100);
		fox.resize(100, 100);
		
		numM = 5;
		numR = 5;
		numS = 5;
		numF = 5;
		
		
		
		
		nightSky = p.loadImage("../imgs/night.jpg");
		daySky = p.loadImage("../imgs/day.jpg");
		
		nightSky.resize(p.width, p.height);
		daySky.resize(p.width, p.height);
		
		cp5.setFont(fontC);
		p.textFont(font);
		
		cp5.addButton("Start")
		
		.setPosition(350,700)
		.setColorBackground(p.color(173, 193, 120))
		.setColorForeground(p.color(221, 229, 182))
		.onRelease(new CallbackListener() { 
		    
			@Override
			public void controlEvent(CallbackEvent arg0) {
				menu = false;
				terrain = new Terrain(p3, WorldConstants.MAP, 50, 50, WorldConstants.NSTATES, 1, colors,p);
				terrain.initRandomCostum(WorldConstants.PATCH_TYPE_PROB);
				for (int i = 0; i < 2; i++) terrain.majorityRule();
				population = new Population(p3, terrain, numM, numR, numS, numF, p);
				
				allAnimals = population.allAnimals();
				if(allAnimals.size() > 0)
					atual = allAnimals.get(0);
				tempoInicial = System.currentTimeMillis();
			}
		    }
		    )
		.setSize(100, 50);
		
		cp5.addButton("- Rato")
		.setPosition(p.width/2-350,500)
		.setColorBackground(p.color(173, 193, 120))
		.setColorForeground(p.color(221, 229, 182))
		.setLabel("-")
		.onRelease(new CallbackListener() { 
		    
			@Override
			public void controlEvent(CallbackEvent arg0) {
				if(numM > 0)
					numM -=1;
				
			}
		    }
		    )
		.setSize(50, 50);
		
		cp5.addButton("+ Rato")
		.setPosition(p.width/2-300,500)
		.setColorBackground(p.color(173, 193, 120))
		.setColorForeground(p.color(221, 229, 182))
		.setLabel("+")
		.onRelease(new CallbackListener() { 
		    
			@Override
			public void controlEvent(CallbackEvent arg0) {
				if(numM < 10)
					numM +=1;
				
			}
		    }
		    )
		.setSize(50, 50);
		
		cp5.addButton("- Rabbit")
		.setPosition(p.width/2-150,500)
		.setColorBackground(p.color(173, 193, 120))
		.setColorForeground(p.color(221, 229, 182))
		.setLabel("-")
		.onRelease(new CallbackListener() { 
		    
			@Override
			public void controlEvent(CallbackEvent arg0) {
				if(numR > 0)
					numR -=1;
				
			}
		    }
		    )
		.setSize(50, 50);
		
		cp5.addButton("+ Rabbit")
		.setPosition(p.width/2-100,500)
		.setColorBackground(p.color(173, 193, 120))
		.setColorForeground(p.color(221, 229, 182))
		.setLabel("+")
		.onRelease(new CallbackListener() { 
		    
			@Override
			public void controlEvent(CallbackEvent arg0) {
				if(numR < 10)
					numR += 1;
				
			}
		    }
		    )
		.setSize(50, 50);
		
		cp5.addButton("- Snake")
		.setPosition(p.width/2+50,500)
		.setColorBackground(p.color(173, 193, 120))
		.setColorForeground(p.color(221, 229, 182))
		.setLabel("-")
		.onRelease(new CallbackListener() { 
		    
			@Override
			public void controlEvent(CallbackEvent arg0) {
				if(numS > 0)
					numS -= 1;
				
			}
		    }
		    )
		.setSize(50, 50);
		
		cp5.addButton("+ Snake")
		.setPosition(p.width/2+100,500)
		.setColorBackground(p.color(173, 193, 120))
		.setColorForeground(p.color(221, 229, 182))
		.setLabel("+")
		.onRelease(new CallbackListener() { 
		    
			@Override
			public void controlEvent(CallbackEvent arg0) {
				if(numS < 10)
					numS += 1;
				
			}
		    }
		    )
		.setSize(50, 50);
		cp5.addButton("- Fox")
		.setPosition(p.width/2+250,500)
		.setColorBackground(p.color(173, 193, 120))
		.setColorForeground(p.color(221, 229, 182))
		.setLabel("-")
		.onRelease(new CallbackListener() { 
		    
			@Override
			public void controlEvent(CallbackEvent arg0) {
				if(numF > 0)
					numF -= 1;
				
			}
		    }
		    )
		.setSize(50, 50);
		
		cp5.addButton("+ Fox")
		.setPosition(p.width/2+300,500)
		.setColorBackground(p.color(173, 193, 120))
		.setColorForeground(p.color(221, 229, 182))
		
		.setLabel("+")
		.onRelease(new CallbackListener() { 
		    
			@Override
			public void controlEvent(CallbackEvent arg0) {
				if(numF < 10)
					numF += 1;
				
			}
		    }
		    )
		.setSize(50, 50);
	}

	@Override
	public void draw(PApplet p, float dt) {
		
		if(menu)
			drawMenu(p);
		else {
			cp5.hide();
			
			
			
			
			allAnimals = population.allAnimals();
			circularChooser(allAnimals.size(), atual, 0);
			
			tempoAtual = System.currentTimeMillis();
			//3D
			p3.beginDraw();
			dayNightCycle(p3, tempoAtual-tempoInicial, WorldConstants.CYCLETIME, WorldConstants.VARIACAO);
			camX = PApplet.map(p.mouseX, 0, p.width, -200,200);
			camY = PApplet.map(p.mouseY, 0, p.height, -200,200);
			
			
			if(!visaoCima && allAnimals.size()>0) {
				p3.camera(atual.getPos().x-10, -15, atual.getPos().y-10,atual.getPos().x,0f, atual.getPos().y, 0f, 1f, 0f);
				p3.perspective(90f, (float)(p3.width/p3.height), 
						2, 10000000);
			}
			else {
				p3.camera(0f, -50f, 0f, 0f, 0f, 0f, 0f, 0f, 1f);
				p3.perspective(90f, (float)(p3.width/p3.height), 
						2, 10000000);
			}
				
			terrain.regenerate();
			terrain.display(p3);
			
			population.update(dt, terrain, p);
			population.display(p3, terrain);
			
			p3.push();
			p3.translate(atual.getPos().x, -4, atual.getPos().y);
			p3.rotateX(-PConstants.PI/2);
			drawPyramid(p3, 0.7f, p.color(255,255,255));
			p3.pop();
			p3.endDraw();
			
			p.image(p3,0, 0);
			
			//2D
			String inf = "Generation: "+ atual.getGeneration()+"\n"+
						"Max Speed: "+ String.format("%.2f",atual.getMaxSpeed())+"\n"+
						"Energy: " + String.format("%.2f", atual.getEnergy()); 	
			
			drawCard(p, 120, 120, 200, 200, atual.getImage(p), atual.getName(), inf, p.color(156, 102, 68), p.color(255, 144, 179));
			
			
			
			drawCard(p, p.width-95, 80, 150, 120, population.getCountAnimals(), p.color(156, 102, 68), p.color(255, 144, 179));
							 
		}
		
		
		
		
	}
	
	public void drawMenu(PApplet p ) {
		p.background(daySky);
		cp5.show();
		
		p.textAlign(PConstants.CENTER);
		p.textSize(20);
		
		p.pushStyle();
		p.fill(255);
		p.text(numM, p.width/2 - 300, 490);
		p.text(numR, p.width/2 - 100, 490);
		p.text(numS, p.width/2 + 100, 490);
		p.text(numF, p.width/2 + 300, 490);
		p.popStyle();
		
		p.image(mouse, p.width/2 - 350, 370);
		p.image(rabbit, p.width/2 - 150, 360);
		p.image(snake, p.width/2 + 50, 360);
		p.image(fox, p.width/2 + 250, 360);
	}
	
	public void drawCard(PApplet p,int x, int y, int dimx, int dimy, PImage icon,String titulo, String txt, int colorMain, int colorStroke) {
		
		p.strokeWeight(3);
		p.stroke(colorStroke);
		p.fill(colorMain);
		p.rect(x-(dimx/2), y-(dimy/2), dimx, dimy, 28);
		
		icon.resize((int)(dimx * 0.4f), (int)(dimx * 0.4f));
		p.image(icon,- icon.width/2 + x,- icon.height/2 + y - (12*dimy/40));
		
		p.fill(colorStroke);
		p.textAlign(PConstants.CENTER);
		p.textFont(font);
		
		p.textSize(20);
		p.text(titulo, x, y );
		
		p.textSize(15);
		p.text(txt, x , y +20);
	}
	
public void drawCard(PApplet p,int x, int y, int dimx, int dimy, String txt, int colorMain, int colorStroke) {
		
		p.strokeWeight(3);
		p.stroke(colorStroke);
		p.fill(colorMain);
		p.rect(x-(dimx/2), y-(dimy/2), dimx, dimy, 28);
		
		
		
		p.fill(colorStroke);
		p.textAlign(PConstants.CENTER);
		p.textFont(font);
		
		
		p.textSize(15);
		p.text(txt, x , y -35);
	}
	
	private void dayNightCycle(PGraphics p,float diferecaTempo, float cycleTime, float variacaoTotal) {
		float coordInicial = -variacaoTotal/2;
		float adicao = diferecaTempo * variacaoTotal / (cycleTime/2);
		
		if(coordInicial + adicao > coordInicial + variacaoTotal) {
			dia = !dia;
			tempoInicial = System.currentTimeMillis();
		}
			
		
		if(dia) {
			p.background(daySky);
			p.ambientLight(128, 128, 128);
			p.push();
			p.pointLight(252, 229, 112, coordInicial + adicao, -70, coordInicial + adicao);
			p.pop();
		}
		else {
			p.background(nightSky);
			p.ambientLight(100, 100, 100);
			p.push();
			p.pop();
		}
	}
	
	void drawPyramid(PGraphics p, float t, int color) { 

		  p.noStroke();

		  // this pyramid has 4 sides, each drawn as a separate triangle
		  // each side has 3 vertices, making up a triangle shape
		  // the parameter " t " determines the size of the pyramid
		  p.beginShape(PConstants.TRIANGLES);

		  p.fill(color); // Note that each polygon can have its own color.
		  p.vertex(-t, -t, -t);
		  p.vertex( t, -t, -t);
		  p.vertex( 0, 0, t);

		  p.fill(color);
		  p.vertex( t, -t, -t);
		  p.vertex( t, t, -t);
		  p.vertex( 0, 0, t);

		  p.fill(color);
		  p.vertex( t, t, -t);
		  p.vertex(-t, t, -t);
		  p.vertex( 0, 0, t);

		  p.fill(color);
		  p.vertex(-t, t, -t);
		  p.vertex(-t, -t, -t);
		  p.vertex( 0, 0, t);

		  p.endShape();
		}
	
	private void circularChooser(int tamanho, Animal atual, int add) {
		int num =allAnimals.indexOf(atual);
		if(atual.isDead()) {
			System.out.println("AJJJJJ");
			num++;
			if(num >= tamanho)
				num = 0;
			if(num < 0)
				num = tamanho - 1;
		}
		else {
			num+=add;
			if(num >= tamanho)
				num = 0;
			if(num < 0)
				num = tamanho - 1;
		}
		
		if(allAnimals.size()>0)
			this.atual = allAnimals.get(num);
			
		
	}
	

	@Override
	public void mousePressed(PApplet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(PApplet p) {
		if(p.key == 'a')
			circularChooser(allAnimals.size(), atual, -1);
		if(p.key == 'd')
			circularChooser(allAnimals.size(), atual, 1);
		if(p.key == 'c')
			visaoCima = !visaoCima;
		if(p.key == 'q')
			menu = true;
		
	}
	
	

	@Override
	public void mouseReleased(PApplet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(PApplet p) {
		// TODO Auto-generated method stub
		
	}

	
}
