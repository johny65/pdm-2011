import javax.microedition.lcdui.*;
import java.io.IOException;

public class Imagen {
	
	private int actual, cant;
	private Image ret;
	
	public Imagen() {
		actual = 0;
		cant = 20; //cantidad de imágenes en la carpeta
	}
	
	//carga la imagen
	private void Cargar() {
		try {
			ret = null;
			ret = Image.createImage("/imagen"+actual+".png");
		} catch (IOException e) {
			System.err.println("Error al abrir imagen.");
			ret = null;
		}
	}
	
	//Inicio() devuelve siempre la primera imagen
	public Image Inicio() {
		actual = 0;
		Cargar();
		return ret;
	}
	
	//si estoy parado en la primera imagen
	public boolean Primera() {
		if (actual == 0) return true;
		else return false;
	}
	
	//si estoy parado en la última imagen
	public boolean Ultima() {
		if (actual == cant-1) return true;
		else return false;
	}
	
	//pasa a la siguiente imagen
	public Image Siguiente() {
		actual++;
		Cargar();
		return ret;		
	}
	
	//pasa a la imagen anterior
	public Image Anterior() {
		actual--;
		Cargar();
		return ret;		
	}

}