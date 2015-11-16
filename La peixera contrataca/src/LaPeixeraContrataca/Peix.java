package LaPeixeraContrataca;

import java.util.Random;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class Peix {
	
	private boolean mascle;
	private boolean horizontal;
	private GImage imatge;
	private int posicioX;
	private int posicioY;
	private int midaFinestraX;
	private int midaFinestraY;
	private int direccio;
	private int velocitat;
	private boolean esteril;
	private int contedorEsteril;
	
	private Random rand = new Random();
	


	public Peix (int midaFinestaX, int midaFinestaY, boolean mascle) {
		this.mascle=mascle;
		horizontal=rand.nextBoolean();
		posicioX=rand.nextInt(midaFinestaX-100);
		posicioY=rand.nextInt(midaFinestaY-100);
		midaFinestraX=midaFinestaX;
		midaFinestraY=midaFinestaY;
		velocitat=rand.nextInt(11)+5;
		esteril=false;
		contedorEsteril=0;
		
		// Dunar una direcció
		if(rand.nextBoolean()==true){
			direccio=1;
		}else{
			direccio=-1;
		}
		
		//Depen de la direcció agafa una imatge o una altre
		String img="";
		if(mascle==true){
			if(horizontal==true){
				if(direccio>0){
					img="Peixos/PeixMascleDreta.png";
				}else{
					img="Peixos/PeixMascleEsquerra.png";
				}
			}else{
				if(direccio>0){
					img="Peixos/PeixMascleAbaix.png";
				}else{
					img="Peixos/PeixMascleAmon.png";
				}
			}
		}else{
			if(horizontal==true){
				if(direccio>0){
					img="Peixos/PeixFamellaDreta.png";
				}else{
					img="Peixos/PeixFamellaEsquerra.png";
				}
			}else{
				if(direccio>0){
					img="Peixos/PeixFamellaAbaix.png";
				}else{
					img="Peixos/PeixFamellaAmon.png";
				}
			}
		}
		imatge=new GImage(img, posicioX,posicioY);
	}
	
	//Mou peix
	public void mou(){
		if(horizontal==true){
			imatge.move(direccio*velocitat,0);
			
		}else{
			imatge.move(0, direccio*velocitat);
		}
		
		//Quan desaparegi per un custat torna per l'oposat
		if(horizontal){
			if(imatge.getLocation().getX()>midaFinestraX){
				imatge.setLocation(0-imatge.getBounds().getWidth(), posicioY);
			}else if(imatge.getLocation().getX()<0-imatge.getBounds().getWidth()){
				imatge.setLocation(midaFinestraX, posicioY);
			}
		}else{
			if(imatge.getLocation().getY()>midaFinestraY){
				imatge.setLocation(posicioX,0-imatge.getBounds().getHeight());
			}else if(imatge.getLocation().getY()<0-imatge.getBounds().getHeight()){
				imatge.setLocation(posicioX, midaFinestraY);
			}
		}
		
		contedorEsteril=contedorEsteril-velocitat;
		if(contedorEsteril<=0){
			esteril=false;
		}
	}
	public GImage getImatge() {
		return imatge;
	}
	public GRectangle getPosicio(){
		return imatge.getBounds();
	}
	public boolean getMascle(){
		return mascle;
	}
	
	public boolean getEsteril() {
		return esteril;
	}
	public void setEsteril(boolean esteril) {
		this.esteril = esteril;
		if(esteril==true){
			contedorEsteril=(int)imatge.getBounds().getWidth();
		}
	}
}
