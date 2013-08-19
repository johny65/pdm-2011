import javax.microedition.lcdui.*;

public class Formulario extends Form implements CommandListener {

	private ChoiceGroup prioridad, mod;
	private Command ok, cancelar;
	private TextField nombre, ub;
	private DateField fecha;
	private ToDoList padre;
	
	public Formulario(ToDoList p) {
		super("Agregar nueva tarea");
		padre = p;
		nombre = new TextField("Nombre de la tarea:", "", 100, TextField.ANY);
		ub = new TextField("Ubicación:", "", 100, TextField.ANY);
		fecha = new DateField("Fecha:", DateField.DATE);
		append(nombre);
		append(ub);
		append(fecha);
		prioridad = new ChoiceGroup("Prioridad:", Choice.EXCLUSIVE);
		prioridad.append("Alta", null);
		prioridad.append("Normal", null);
		prioridad.append("Baja", null);
		append(prioridad);
		mod = new ChoiceGroup("Otras opciones:", Choice.MULTIPLE);
		mod.append("Recordar con alarma", null);
		mod.append("Enviar correo automático", null);
		mod.append("Eliminar después de vencida", null);
		ok = new Command("Agregar", Command.OK, 1);
		cancelar = new Command("Cancelar", Command.CANCEL, 1);
		append(mod);
		addCommand(ok);
		addCommand(cancelar);
		setCommandListener(this);
	}

	public void commandAction(Command c, Displayable d) {
		if (c == cancelar)
			padre.Volver();
		else if (c == ok){
			boolean b[] = new boolean[3];
			mod.getSelectedFlags(b);
			Tarea t = new Tarea(nombre.getString(), ub.getString(), fecha.getDate(), prioridad.getSelectedIndex(), b);
			padre.addTarea(t);
		}
	}
	
	public void Limpiar() {
		nombre.setString("");
		ub.setString("");
		fecha.setDate(null);
		prioridad.setSelectedIndex(0, true);
		boolean ceros[] = {false, false, false};
		mod.setSelectedFlags(ceros);
		addCommand(ok);
	}
	
	public void CargarTarea(Tarea t) {
		nombre.setString(t.getNombre());
		ub.setString(t.getUbicacion());
		fecha.setDate(t.getFecha());
		prioridad.setSelectedIndex(t.getPrioridad(), true);
		mod.setSelectedFlags(t.getOpciones());
		removeCommand(ok);
	}
}
