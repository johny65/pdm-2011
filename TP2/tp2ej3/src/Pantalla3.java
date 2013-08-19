import javax.microedition.lcdui.*;

public class Pantalla3 extends List implements CommandListener {
	
	private Menu mid; //comunicación con Principal
	private Alert al;
	
	public Pantalla3(Menu m) {
		super("Juegos", List.IMPLICIT);
		mid = m;
		al = new Alert("Información");
		al.setType(AlertType.INFO); 
		al.setTimeout(2000);
		append("Buscaminas", null);
		append("Sudoku", null);
		append("XIII", null);
		append("Volver a Principal", null);
		setCommandListener(this);
	}
	
	public void commandAction(Command c, Displayable d){
		switch (getSelectedIndex()){
			case 0: //buscaminas
				al.setString("Se eligió Buscaminas");
				mid.MostrarAlerta(al, this);
				break;
			case 1: //sudoku
				al.setString("Se eligió Sudoku");
				mid.MostrarAlerta(al, this);
				break;
			case 2: //xiii
				al.setString("Se eligió XIII");
				mid.MostrarAlerta(al, this);
				break;
			case 3: //volver
				mid.Volver();
				break;
		}
	}
	
}
