package javaBeans;
/*2.	(2.5 puntos) Crea un paquete ejercicio2.main y dentro crea una clase java InsertLibrosObj que almacene en un fichero binario secuencial, bibliotecaObj.dat, 5 objetos mediante ArrayList de objetos de tipo Libro.
Crea en un paquete javabean las clases:
Libro: titulo (String), autor (Autor), numPaginas (int), anioPublicacion (int).
Autor: nombre (String), nacionalidad (String).
Con sus correspondientes constructores y métodos de acceso (solo los necesarios). No olvides que van a representar objetos cuya información va a ser transferida a un fichero.
Para comprobar la escritura, después de insertar lee el fichero y muestra su contenido por consola.
*/

import java.io.Serializable;

public class Autor implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String nacionalidad;
	
	public Autor(String nombre, String nacionalidad) {
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + " Nacionalidad: " + nacionalidad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}
	
	public String mostrarFormatoXML() {
		return "\n\t\t\t<Nombre>" + nombre + "</nombre>" 
				+ "\n\t\t\t<nacionalidad>" + nacionalidad + "</nacionalidad>";
	}
	
}
