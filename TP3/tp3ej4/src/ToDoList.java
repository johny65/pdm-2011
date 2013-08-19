//Guía TP 3 - Ejercicio 4
//Alumno: Juan Bertinetti

import java.util.Vector;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class ToDoList extends MIDlet implements CommandListener {

	private Display pantalla;
	private Formulario add;
	private List lista;
	private Command agregar, salir;
	private Vector tareas;
	private boolean pausado;
	
	public ToDoList() {
		pausado = false;
	}
	
	public void startApp() {
		if (!pausado){
			add = new Formulario(this);
			agregar = new Command("Agregar tarea", Command.ITEM, 1);
			salir = new Command("Salir", Command.EXIT, 1);
			lista = new List("Lista de tareas", List.IMPLICIT);
			lista.addCommand(agregar);
			lista.addCommand(salir);
			lista.setCommandListener(this);
			tareas = new Vector();
			pantalla = Display.getDisplay(this);
			pantalla.setCurrent(lista);
		}
		else pausado = false;
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	protected void pauseApp() {
		pausado = true;
	}

	public void commandAction(Command c, Displayable d) {
		if (c == salir)
			destroyApp(true);
		else if (c == agregar){
			add.Limpiar();
			pantalla.setCurrent(add);
		}
		else { //mostrar tarea
			add.CargarTarea((Tarea)tareas.elementAt(lista.getSelectedIndex()));
			pantalla.setCurrent(add);
		}
	}

	public void Volver() {
		pantalla.setCurrent(lista);
	}
	
	public void addTarea(Tarea t) {
		tareas.addElement(t);
		lista.append(((Tarea)tareas.lastElement()).getNombre(), null);
		Volver();
	}
}
