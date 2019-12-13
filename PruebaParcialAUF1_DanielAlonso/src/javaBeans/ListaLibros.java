package javaBeans;
/*3(4 puntos) Crea un paquete ejercicio3.main y dentro una clase una clase CrearXMLBiblioteca que a partir de los datos obtenidos de 
 * bibliotecaObj.dat y utilizando DOM, genere un archivo biblioteca.xml y a continuación lo muestre por pantalla mediante Xstream, para 
 * ello crea las clases necesarias en el paquete javabean.
El fichero biblioteca.xml debe tener el siguiente aspecto:

<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<biblioteca>
    <libro>
        <titulo>El señor de los anillos</titulo>
        <autor>
            <nombreAutor>J.R.R.Tolkien</nombreAutor>
            <nacionalidad>Sudafricano</nacionalidad>
        </autor>
        <numPaginas>1368</numPaginas>
        <anioPublicacion>1978</anioPublicacion>
    </libro>
    <libro>
        <titulo>El clan del oso cavernario</titulo>
        <autor>
            <nombreAutor>Jean Marie Auel</nombreAutor>
            <nacionalidad>Estadounidense</nacionalidad>
        </autor>
        <numPaginas>564</numPaginas>
        <anioPublicacion>1980</anioPublicacion>
    </libro>
    <libro>
        <titulo>El ocho</titulo>
        <autor>
            <nombreAutor>Katherine Neville</nombreAutor>
            <nacionalidad>Estadounidense</nacionalidad>
        </autor>
        <numPaginas>624</numPaginas>
        <anioPublicacion>1988</anioPublicacion>
    </libro>
</biblioteca>
*/

import java.util.ArrayList;

public class ListaLibros {
	
	private ArrayList<Libro> listaLibrosL;
	
	public ListaLibros() {
		listaLibrosL = new ArrayList<Libro>();
	}
	
	public ArrayList<Libro> getListalibrosL(){
	return listaLibrosL;
	}
}