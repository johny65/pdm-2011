import java.util.Date;

public class Tarea {

	private String nombre, ubicacion;
	private Date fecha;
	private int prioridad;
	boolean[] opciones;
	
	public Tarea(String n, String ub, Date f, int p, boolean[] op) {
		nombre = n;
		ubicacion = ub;
		fecha = f;
		prioridad = p;
		opciones = op;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public int getPrioridad() {
		return prioridad;
	}
	
	public boolean[] getOpciones() {
		return opciones;
	}
	
}
