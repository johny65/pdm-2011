//Guía TP 5 - Ejercicio 2
//Alumno: Juan Bertinetti

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreNotOpenException;

public class BaseDatos2 extends MIDlet implements CommandListener {

	private Display pantalla;
	private List main, vista;
	private TextBox nuevo;
	private Command salir, volver, add, borrar;
	private RecordStore rs;
	private int indices[];
	
	public void startApp() {
		salir = new Command("Salir", Command.EXIT, 1);
		volver = new Command("Volver", Command.BACK, 1);
		add = new Command("Agregar", Command.OK, 1);
		borrar = new Command("Eliminar", Command.ITEM, 1);
		main = new List("RecordStore", List.IMPLICIT);
		main.append("Ver RecordStore", null);
		main.append("Agregar nuevo record", null);
		main.addCommand(salir);
		main.setCommandListener(this);
		try {
			rs = RecordStore.openRecordStore("RSEj2", true, RecordStore.AUTHMODE_ANY, true);
		} catch (Exception e) {
			System.err.println("Error al abrir el RecordStore");
			destroyApp(true);
		}
		vista = new List("Records almacenados", List.MULTIPLE);
		vista.addCommand(borrar);
		vista.addCommand(volver);
		vista.setCommandListener(this);
		nuevo = new TextBox("Agregar nuevo record", "", 100, TextField.ANY);
		nuevo.addCommand(add);
		nuevo.addCommand(volver);
		nuevo.setCommandListener(this);
		pantalla = Display.getDisplay(this);
		pantalla.setCurrent(main);
	}

	public void destroyApp(boolean unconditional) {
		try {
			rs.closeRecordStore();
		} catch (Exception e) {
			System.err.println("Error al cerrar RecordStore ¬¬...");
		}
		notifyDestroyed();
	}

	protected void pauseApp() {}

	public void VerRecordStore() {
		RecordEnumeration enum;
		try {
			enum = rs.enumerateRecords(null, null, false);
		} catch (RecordStoreNotOpenException e) {
			System.err.println("Error al enumerar records");
			return;
		}
		vista.deleteAll();
		byte[] datos;
		String sdatos;
		indices = new int[enum.numRecords()];
		int i=0;
		while (enum.hasNextElement()){
			try {
				indices[i] = enum.nextRecordId();
				datos = rs.getRecord(indices[i]);
				sdatos = new String(datos);
			} catch (Exception e) {
				System.err.println("Error al recuperar siguiente record");
				sdatos = "<error>";
			}
			vista.append(sdatos, null);
			i++;
		}
		pantalla.setCurrent(vista);
	}
	
	public void AgregarNuevoRecord() {
		//agregar para que ya queden ordenados
		String s = nuevo.getString(), aux, auxant;
		int id, idant;
		try {
			rs.addRecord(s.getBytes(), 0, s.length());
			RecordEnumeration enum = rs.enumerateRecords(null, null, false);
			idant = enum.nextRecordId();
			auxant = new String(rs.getRecord(idant));
			while (enum.hasNextElement()){
				id = enum.nextRecordId();
				aux = new String(rs.getRecord(id));
				if (auxant.compareTo(aux) > 0){
					//cambiar el orden
					rs.setRecord(id, auxant.getBytes(), 0, auxant.length());
					rs.setRecord(idant, aux.getBytes(), 0, aux.length());
				}
				else
					auxant = aux;
				idant = id;
			}
		} catch (Exception e) {
			System.err.println("Error al agregar record...");
		}
		pantalla.setCurrent(main);
	}
	
	public void EliminarRecords() {
		boolean b[] = new boolean[vista.size()];
		vista.getSelectedFlags(b);
		for (int i=0; i<b.length; ++i){
			if (b[i]){
				try {
					rs.deleteRecord(indices[i]);
				} catch (Exception e) {
					System.err.println("Error al borrar record");
				}
			}
		}
		pantalla.setCurrent(main);
	}
	
	public void commandAction(Command c, Displayable d) {
		if (c == salir)
			destroyApp(true);
		else if (c == volver)
			pantalla.setCurrent(main);
		else if (c == add)
			AgregarNuevoRecord();
		else if (c == borrar)
			EliminarRecords();
		else if (d == main) {
			if (main.getSelectedIndex() == 0) //listar recordstore
				VerRecordStore();
			else if (main.getSelectedIndex() == 1){ //agregar record
				nuevo.setString("");
				pantalla.setCurrent(nuevo);
			}
		}
	}

}
