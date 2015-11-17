package LaPeixeraContrataca;

import java.util.Random;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class Peix {
	
	protected boolean mascle;
	protected boolean horizontal;
	protected GImage imatge;
	protected int posicioX;
	protected int posicioY;
	protected int midaFinestraX;
	protected int midaFinestraY;
	protected int direccio;
	protected int velocitat;
	protected boolean esteril;
	protected int contedorEsteril;
	
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
		generaImatge();
	}
	
	protected void generaImatge(){
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
	/**
	 * 
	 * @param p
	 * @return 0 no fer res
	 * 1 mata els dos peixos
	 * 2 cria peix
	 */
	public int xoca(Peix p){
		int resultat=0;
		if(this.getPosicio().intersects(p.getPosicio())){
			if(this.getMascle()==p.getMascle()){
				resultat=1;
			}else{
				if(this.getEsteril()==false && p.getEsteril()==false){
					resultat=2;
				}
			}
		}
		return resultat;
	}
	
	public void setEsteril(boolean esteril) {
		this.esteril = esteril;
		if(esteril==true){
			contedorEsteril=(int)imatge.getBounds().getWidth();
		}
	}
}
