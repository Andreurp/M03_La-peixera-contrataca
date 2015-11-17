package LaPeixeraContrataca;

import java.util.ArrayList;
import java.util.List;

public class Peixera {

	private static final int N_Peixos = 8;
	private static final int N_Taurons = 3;
	List<Peix> peixos = new ArrayList<Peix>();
	List<Peix> morts = new ArrayList<>();
	List<Peix> bebe = new ArrayList<>();
	List<Tauro> taurons = new ArrayList<>();
	List<Tauro> tauronsMorts = new ArrayList<>();
	List<Tauro> tauronsBebe = new ArrayList<>();

	private int nPeixos;
	private int nTaurons;

	public Peixera() {
		nPeixos = 0;
		nTaurons = 0;
		// crear peixos
		for (int i = 0; i < N_Peixos; i++) {
			boolean sexe = (nPeixos % 2 == 0);
			Peix p = new Peix(1000, 600, sexe);
			peixos.add(p);
			nPeixos++;
		}
		for (int i = 0; i < N_Taurons; i++) {
			boolean sexe = (nTaurons % 2 == 0);
			Tauro t = new Tauro(1000, 600, sexe);
			taurons.add(t);
			nTaurons++;
		}
	}

	public void mou() {
		for (Peix p : peixos) {
			p.mou();
		}
		for (Tauro t : taurons) {
			t.mou();
		}
		// mata o cria
		bebe.clear();
		morts.clear();
		tauronsBebe.clear();
		tauronsMorts.clear();
		for (Peix p : peixos) {
			for (Peix q : peixos) {
				if (!p.equals(q)) {
					switch (p.xoca(q)) {
					case 0:
						// no cal fer res
						break;
					case 1:
						// mata
						morts.add(q);
						morts.add(p);
						break;
					case 2:
						// cria
						boolean sexe = (nPeixos % 2 == 0);
						Peix b = new Peix(1000, 600, sexe);
						bebe.add(b);
						nPeixos++;
						p.setEsteril(true);
						q.setEsteril(true);
						break;
					default:
						break;
					}
				}
			}
		}
		for (Tauro t : taurons) {
			for (Tauro u : taurons) {
				if (!t.equals(u)) {
					switch (t.xoca(u)) {
					case 0:
						// no cal fer res
						break;
					case 1:
						// mata
						tauronsMorts.add(u);
						tauronsMorts.add(t);
						break;
					case 2:
						// cria
						boolean sexe = (nTaurons % 2 == 0);
						Tauro b = new Tauro(1000, 600, sexe);
						tauronsBebe.add(b);
						nTaurons++;
						t.setEsteril(true);
						u.setEsteril(true);
						break;
					default:
						break;
					}
				}
			}
		}

		for (Tauro t : taurons) {
			for (Peix p : peixos) {
				if (t.xoca(p) == 3) {
					morts.add(p);
				}
			}
		}

		// eliminar els peixos morts de la llista de peixos
		for (Peix m : morts) {
			peixos.remove(m);
		}
		// afageix el bebe a la llista peixos
		for (Peix b : bebe) {
			peixos.add(b);
		}
		// eliminar els taurons morts de la llista de taurons
		for (Tauro m : tauronsMorts) {
			taurons.remove(m);
		}
		// afageix el bebe a la llista taurons
		for (Tauro b : tauronsBebe) {
			taurons.add(b);
		}

	}

	public List<Peix> getPeixos() {
		return peixos;
	}

	public List<Peix> getMorts() {
		return morts;
	}

	public List<Peix> getBebes() {
		return bebe;
	}

	// para el joc
	public boolean gameOver() {
		return ((peixos.size()+taurons.size()) <= 1);
	}

	public List<Tauro> getTaurons() {
		return taurons;
	}

	public List<Tauro> getTauronsMorts() {
		return tauronsMorts;
	}

	public List<Tauro> getTauronsBebes() {
		return tauronsBebe;
	}
}
