package ejercicio2.main;

/*2.(2.5 puntos) Crea un paquete ejercicio2.main y dentro crea una clase java InsertLibrosObj que 
 * almacene en un fichero binario secuencial, bibliotecaObj.dat, 5 objetos mediante ArrayList 
 * de objetos de tipo Libro.
Crea en un paquete javabean las clases:
Libro: titulo (String), autor (Autor), numPaginas (int), anioPublicacion (int).
Autor: nombre (String), nacionalidad (String).
Con sus correspondientes constructores y métodos de acceso (solo los necesarios). 
No olvides que van a representar objetos cuya información va a ser transferida a un fichero.
Para comprobar la escritura, después de insertar lee el fichero y muestra su contenido por consola.
*/

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javaBeans.Autor;
import javaBeans.Libro;

public class InsertLibrosObj {

	public static void main(String[] args) {

		escribirFichero();
		leerFichero();

	}

	private static void leerFichero() {

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Ficheros\\bibliotecaObj.dat"));
				){
			
		//Empezamos con la lectura
			Libro libro; //ir almacenando lo que lee la variable
			boolean continuar = true;
			
			while (continuar) {
				try {
					libro = (Libro)ois.readObject();
					System.out.println(libro);//Añadir los toString a los javabeans
				} catch (EOFException e) {
					// TODO Auto-generated catch block
					continuar=false;
				}
			}
			
		} catch (Exception e) { //Ambiar a Exception
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}

	private static void escribirFichero() {

		Libro [] arrayLibros = {
				new Libro("Paco en Pacolandia",new Autor("Paco", "Pacolandia"),10, 3),
				new Libro("Paco en Pacolandia",new Autor("Paco", "Pacolandia"),10, 3),
				new Libro("Paco en Pacolandia",new Autor("Paco", "Pacolandia"),10, 3),
				new Libro("Paco en Pacolandia",new Autor("Paco", "Pacolandia"),10, 3),
				new Libro("Paco en Pacolandia",new Autor("Paco", "Pacolandia"),10, 3)};
		
		//Binario secuencial escribir en el fichero
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Ficheros\\bibliotecaObj.dat"));) {//metemos en el parentesis del catch
			for (int i = 0; i < arrayLibros.length; i++) {
				oos.writeObject(arrayLibros[i]);
			}
		} catch (IOException e) {//dejamos solo al IO
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
