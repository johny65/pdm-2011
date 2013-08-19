public class IP {
	private String ip;
	public IP() {}
	public void setIP(String s) {
		ip = s;
	}
	public boolean ComprobarIP() {
		String aux;
		// primer filtro: contar los puntos
		int puntos = 0;
		for (int i = 0; i < ip.length(); ++i){
			if (ip.charAt(i) == '.')
				puntos++;
		}
		if (puntos != 3) {
			return false;
		}
		// comprobar los números:
		int ini = 0, fin, num;
		for (int i = 0; i < 4; ++i) {
			if (i != 3)
				fin = ip.indexOf('.', ini);
			else
				fin = ip.length();
			aux = ip.substring(ini, fin);
			if (aux.length() > 3) {
				return false;
			}
			try {
				num = Integer.parseInt(aux);
			} catch (java.lang.NumberFormatException e) {
				// no son números!
				num = -1;
			}
			//System.out.println(num);
			if (num < 0 || num > 255) {
				return false;
			}
			ini = fin + 1;
		}
		return true;
	}
}
