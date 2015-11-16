package LaPeixeraContrataca;

import java.util.ArrayList;
import java.util.List;

public class Peixera {
	
	private static final int Nº_Peixos = 6;
	List<Peix>peixos=new ArrayList<Peix>();
	List<Peix>morts=new ArrayList<>();
	List<Peix>bebe=new ArrayList<>();
	
	private int nPeixos;
	
	public Peixera(){
		nPeixos=0;
		//crear peixos
		for(int i=0; i<Nº_Peixos; i++){
			boolean sexe=(nPeixos%2==0);
			
			Peix p = new Peix (1000,600,sexe);
			peixos.add(p);
			nPeixos++;
		}
	}
	public void mouPeixos(){
		for(Peix p:peixos){
			p.mou();
		}
		//mata o cria
		bebe.clear();
		morts.clear();
		for(Peix p: peixos){
			for(Peix q: peixos){
				if(!p.equals(q)){
					//si son del mateix sexe es maten
					if(p.getPosicio().intersects(q.getPosicio())){
						if(p.getMascle()==q.getMascle()){
							morts.add(q);
							morts.add(p);
						}else{
							//si son de diferent sexe crien 
							if(p.getEsteril()==false && q.getEsteril()==false){
								boolean sexe=(nPeixos%2==0);
								Peix b = new Peix (1000,600,sexe);
								bebe.add(b);
								nPeixos++;
								p.setEsteril(true);
								q.setEsteril(true);
							}
						}
					}
				}
			}
		}
		//eliminar els peixos morts de la llista de peixos
		for (Peix m: morts){
			peixos.remove(m);
		}
		//afageix el bebe a la llista peixos
		for (Peix b: bebe){
			peixos.add(b);
		}
		
	}
	
	public List<Peix> getPeixos(){
		return peixos;
	}
	public List<Peix> getMorts(){
		return morts;
	}
	public List<Peix> getBebes(){
		return bebe;
	}
	//para el joc
	public boolean gameOver(){
		return(peixos.size()<=1);
	}
}
