package gestionHospital;

public class Sesion {
	public static String usuarioLogueado;

	public static String getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public static void setUsuarioLogueado(String usuarioLogueado) {
		Sesion.usuarioLogueado = usuarioLogueado;
	} 
	
}