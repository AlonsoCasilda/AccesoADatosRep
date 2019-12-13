package javaBeans;
/*2.	(2.5 puntos) Crea un paquete ejercicio2.main y dentro crea una clase java InsertLibrosObj que almacene en un fichero binario secuencial, bibliotecaObj.dat, 5 objetos mediante ArrayList de objetos de tipo Libro.
Crea en un paquete javabean las clases:
Libro: titulo (String), autor (Autor), numPaginas (int), anioPublicacion (int).
Autor: nombre (String), nacionalidad (String).
Con sus correspondientes constructores y métodos de acceso (solo los necesarios). No olvides que van a representar objetos cuya información va a ser transferida a un fichero.
Para comprobar la escritura, después de insertar lee el fichero y muestra su contenido por consola.
*/

import java.io.Serializable;

public class Libro implements Serializable {

	private static final long serialVersionUID = 1L;
	private String titulo;
	private Autor autor;
	private int numPaginas;
	private int anioPublicacion;
	
	public Libro(String titulo, Autor autor, int numPaginas, int anioPublicacion) {
		this.titulo = titulo;
		this.autor = autor;
		this.numPaginas = numPaginas;
		this.anioPublicacion = anioPublicacion;
	}

	@Override
	public String toString() {
		return "Titulo: " + titulo + 
				"\nAutor=" + autor +
				"\nNumero de páginas: " + numPaginas +
				"\nAño dde publicación" + anioPublicacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTitulo() {
		return titulo;
	}

	public Autor getAutor() {
		return autor;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public int getAnioPublicacion() {
		return anioPublicacion;
	}
	
	public String mostrarFormatoXML() {
		return "\n\t<libro>" + 
					"\n\t\t<titulo>" + titulo + "</titulo>"  
				+ "\n\t\t<autor>"
				+ "\n\t\t\t<nombreAutor>" + autor.getNombre() + "</nombreAutor>"
				+ "\n\t\t\t<nacionalidad>" + autor.getNacionalidad() + "</nacionalidad>"
				+ "\n\t\t</autor>"
				+ "\n\t\t<numPaginas>" + numPaginas + "</numPaginas>" 
				+ "\n\t\t<anioPublicacion>" + anioPublicacion + "</anioPublicacion>"
				+ "\n\t</libro>";
		
	}
}
