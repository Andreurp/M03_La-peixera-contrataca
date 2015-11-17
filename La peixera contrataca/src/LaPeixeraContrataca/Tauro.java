package LaPeixeraContrataca;

import acm.graphics.GImage;

public class Tauro extends Peix{

	public Tauro(int midaFinestaX, int midaFinestaY, boolean mascle) {
		super(midaFinestaX, midaFinestaY, mascle);
		
		
		
	}
	
	@Override
	protected void generaImatge(){
		String img="";
		if(mascle==true){
			if(horizontal==true){
				if(direccio>0){
					img="Taurons/TauroMascleDreta.png";
				}else{
					img="Taurons/TauroMascleEsquerra.png";
				}
			}else{
				if(direccio>0){
					img="Taurons/TauroMascleAbaix.png";
				}else{
					img="Taurons/TauroMascleAmaon.png";
				}
			}
		}else{
			if(horizontal==true){
				if(direccio>0){
					img="Taurons/TauroFamellaDreta.png";
				}else{
					img="Taurons/TauroFamellaEsquerra.png";
				}
			}else{
				if(direccio>0){
					img="Taurons/TauroFamellaAbaix.png";
				}else{
					img="Taurons/TauroFamellaAmon.png";
				}
			}
		}
		imatge=new GImage(img, posicioX,posicioY);
	}
	public int xoca(Tauro t){
		int resultat=0;
		if(this.getPosicio().intersects(t.getPosicio())){
			if(this.getMascle()==t.getMascle()){
				resultat=1;
			}else{
				if(this.getEsteril()==false && t.getEsteril()==false){
					resultat=2;
				}
			}
		}
		return resultat;
	}
	/**
	 * 3 tauro mata peix
	 */
	@Override
	public int xoca(Peix p){
		int resultat=0;
		if(this.getPosicio().intersects(p.getPosicio())){
			resultat=3;
		}
		return resultat;
	}

	//si un peix xoca amb un tauro el peix viu pero si es el revis el peix mort
}
