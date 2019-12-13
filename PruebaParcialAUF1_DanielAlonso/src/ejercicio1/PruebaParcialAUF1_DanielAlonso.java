package ejercicio1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/*1.(3.5 puntos) Crea un paquete ejercicio1 y dentro crea una 
 * clase java InsertEquiposLFAlea que almacene equipos de la 
 * Liga de F�tbol Espa�ola en un fichero binario aleatorio, 
 * equiposLFAle.dat, y despu�s muestre el contenido del fichero 
 * por pantalla. 
�	Para cada equipo se deber� solicitar por teclado el nombre del 
equipo, las siglas, la ciudad, el n�mero de veces que ha sido 
campe�n de liga, el n�mero de veces que ha sido subcampe�n y el 
�ltimo a�o que gan� la liga. El nombre tendr� como m�ximo 40 
caracteres, las siglas como m�ximo 5 y la ciudad como m�ximo 15. 
�	Adem�s, para cada equipo se deber� introducir un id �nico y 
consecutivo al �ltimo generado. (Para esto habr� que leer el id 
del �ltimo registro del fichero, si es que tiene alguno) 
�	Cada registro correspondiente a un equipo estar� formado por el 
id, el nombre del equipo, las siglas, la ciudad, el n�mero de veces 
que ha sido campe�n, el n�mero de veces que ha sido subcampe�n y el 
�ltimo a�o que gan�. 
�	El tama�o en bytes correspondiente al registro se deber� 
calcular en funci�n a lo anterior teniendo en cuenta que un entero 
(int) ocupa 4 bytes y un caracter (char) ocupa 2 bytes.
�	El programa no debe machacar el contenido que ya tenga el 
fichero.

Datos:
Real Madrid C. F., RMCF, Madrid, 33, 23, 2017
F. C. Barcelona, FCB, Barcelona, 25, 25, 2018
Atl�tico de Madrid, ATM, Madrid, 10, 9, 2014
Athletic Club, ATHC, Bilbao, 8, 7, 1984
Valencia C. F., VCF, Valencia, 6, 6, 2004
Real Sociedad, RSF, San Sebasti�n, 2, 3, 1982
R. C. D. La Coru�a, RCDC, La Coru�a, 1, 5, 2000
Sevilla F. C., SFC, Sevilla, 1, 4, 1946
Real Betis, RBB, Sevilla, 1, 0, 1935 */

public class PruebaParcialAUF1_DanielAlonso {

	// 1Crear tama�o de los strings que nos diga el enunciado
	static final int TAM_NOMBRE = 40;
	static final int TAM_SIGLAS = 5;
	static final int TAM_CIUDAD = 15;

	// 2Crear el tama�o del registro lo primero el ID(4)
	static final int TAM_REG = 4 + 2 * 40 + 5 * 2 + 15 * 2 + 3 * 4;
	static final String RESPUESTA_SI = "SI";

	public static void main(String[] args) {

		// 3generar metodos de escritura y lectura
		escribirEquipos();
		leerEquipos();

	}

	private static void leerEquipos() {
		try (RandomAccessFile raf = new RandomAccessFile("Ficheros\\librosLFAle.dat", "r");
				Scanner sc = new Scanner(System.in)){
			raf.seek(0);
			
			int id;
			String nombre;
			char[] cNombre = new char[TAM_NOMBRE];
			String siglas;
			char[] cSiglas = new char[TAM_SIGLAS];
			String ciudad;
			char[] cCiudad = new char[TAM_CIUDAD];
			int campeon;
			int subcampeon;
			int ultAnio;
			
			while (raf.getFilePointer()<raf.length()) {
				
				id=raf.readInt();
				
				nombre = leerString(raf, cNombre);
				siglas = leerString(raf, cSiglas);
				ciudad = leerString(raf, cCiudad);
				
				campeon = raf.readInt();
				subcampeon = raf.readInt();
				ultAnio = raf.readInt();
				
				System.out.println("\nID: "+ id + 
						"\nNombre del equipo: " + nombre +
						"\nSiglas del equipo: " + siglas + 
						"\nCiudad del equipo: " + ciudad +
						"\nVeces campe�n: " + campeon +
						"\nVeces Subcampe�n: " + subcampeon +
						"\nUltima vez campe�n: " + ultAnio);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static String leerString(RandomAccessFile raf, char[] array) throws IOException{
		for (int i = 0; i < array.length; i++) {//iteramos sobre el tama�o maximo del nombre
			array[i] = raf.readChar();
		}
		//convertir a string para mostrarlo por consola
		String dato = new String(array);
		
		return dato.trim();
	}

	private static void escribirEquipos() {
		// 4 generamos RandomAccessFile raf = new RandomAccessFile("Ruta", "rw" ) y lo
		// metemos en el() try
		// 5 cambiar excepcion
		// 6 ponemos el Scanner
		try (RandomAccessFile raf = new RandomAccessFile("Ficheros\\equiposLFAle.dat", "rw");
				Scanner sc = new Scanner(System.in)) {

			int id = 0;
			StringBuffer sb;
			String respuesta = RESPUESTA_SI;

			while (respuesta.equalsIgnoreCase(RESPUESTA_SI)) {
				if (raf.length() > 0) {// SI EL FICHERO TIENE CONTENIDO NOS PONEMOS AL FINAL
					raf.seek(raf.length() - TAM_REG);// el seek es donde queremos colocar el puntero y despues leer o
														// escribir, nos ponemos delante del ultimo registro
					id = raf.readInt();
				}

				// para escribir nos ponemos al final y empezar a escribir
				raf.seek(raf.length());
				raf.writeInt(id + 1);// escribir el id + 1

				// necesitamos el strinBuffer para escibir el nombre
				System.out.println("Introduce el nombre del equipo de Futbol");// solicitamos nombre
				sb = new StringBuffer(sc.nextLine());// el sb contiene el nombre introducido por el usuario el
														// stringBuffer permite sabe el tama�o
				sb.setLength(TAM_NOMBRE);// El nombre va a ocupar exactamente el tama�o del nombre
				raf.writeChars(sb.toString());// Escribimos lo que hemos almacenado en el sb

				// pedimos siglas(string)
				System.out.println("Introduce las siglas del equipo");// solicitamos nombre
				sb = new StringBuffer(sc.nextLine());// el sb contiene el nombre introducido por el usuario el
														// stringBuffer permite sabe el tama�o
				sb.setLength(TAM_SIGLAS);// El nombre va a ocupar exactamente el tama�o de las siglas
				raf.writeChars(sb.toString());// Escribimos lo que hemos almacenado en el sb

				// pedimos ciudad(string)
				System.out.println("Introduce la ciudad equipo");// solicitamos nombre
				sb = new StringBuffer(sc.nextLine());// el sb contiene el nombre introducido por el usuario el
														// stringBuffer permite sabe el tama�o
				sb.setLength(TAM_CIUDAD);// El nombre va a ocupar exactamente el tama�o de la ciudad
				raf.writeChars(sb.toString());// Escribimos lo que hemos almacenado en el sb

				// Pedimos campeon int pedimos el dato y escribimos el dato
				System.out.println("Introduce las veces que ha sido campe�n");
				raf.writeInt(Integer.parseInt(sc.nextLine()));// lo escribimos directamente al fichero

				// Pedimos subcampeon int pedimos el dato y escribimos el dato
				System.out.println("Introduce las veces que ha sido Sub campe�n");
				raf.writeInt(Integer.parseInt(sc.nextLine()));// lo escribimos directamente al fichero

				// Pedimos ultimo a�o campeon int pedimos el dato y escribimos el dato
				System.out.println("Introduce el ultimo a�o que ha sido campe�n");
				raf.writeInt(Integer.parseInt(sc.nextLine()));// lo escribimos directamente al fichero
				
				System.out.println("�Desea introducir otro equipo (SI/NO)?");
				respuesta = sc.nextLine().toUpperCase();
			}
		} catch (IOException e) {//
			e.printStackTrace();
		}
	}
}