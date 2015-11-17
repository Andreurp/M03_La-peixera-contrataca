package LaPeixeraContrataca;

import java.awt.Color;
import java.util.List;

import acm.program.GraphicsProgram;

public class Main extends GraphicsProgram{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() {
		//mida de la pantalla i color fons
	    setSize(1000, 600);
	    setBackground(Color.cyan);
	}
	
	public void run(){
		
		//crea la paixera
		Peixera aquari=new Peixera();
		List<Peix>peixos=aquari.getPeixos();
		List<Tauro>taurons=aquari.getTaurons();
		//dibuixar peixos
		for(Peix p: peixos){
			add(p.getImatge());
		}
		for(Tauro t: taurons){
			add(t.getImatge());
		}
		
		while(!aquari.gameOver()){
			aquari.mou();
			//eliminar de la pantalla els morts
			for(Peix p:	aquari.getMorts()){
				remove(p.getImatge());
			}
			//afagir a la pantalla els bebes
			for(Peix p:	aquari.getBebes()){
				add(p.getImatge());
			}
			for(Tauro t: aquari.getTauronsMorts()){
				remove(t.getImatge());
			}
			for(Tauro t: aquari.getTauronsBebes()){
				add(t.getImatge());
			}
			pause(100);
		}
	}
}
