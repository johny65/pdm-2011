//clase Jugador

//Sólo guarda la cantidad de vidas que tiene el jugador
//y el nivel en el que está

public class Jugador {
	
	private int vidas, nivel;
	
	public Jugador(int v) {
		vidas = v;
		nivel = 1;
	}
	
	public void SumarVida() { vidas++; }
	public void QuitarVida() { vidas--; }
	public int getVidas() { return vidas; }
	public void SubirNivel() { nivel++; }
	public int getNivel() { return nivel; }
	
}
