public interface Principal {
	
	//para avisarle que puede pasar a la pantalla siguiente
	public void Siguiente();
	
	//para pasarle la opción elegida
	public void GuardarOpcion(String s);

	//para decirle que detenga la aplicación
	public void Salir();
}
