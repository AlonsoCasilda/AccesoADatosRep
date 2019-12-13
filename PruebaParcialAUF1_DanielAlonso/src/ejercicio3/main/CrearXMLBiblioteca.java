package ejercicio3.main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import javaBeans.Autor;
import javaBeans.Libro;
import javaBeans.ListaLibros;

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
public class CrearXMLBiblioteca {

	public static void main(String[] args) {
		crearXMLDOM();
		
		leerXMLXStream();

	}

	private static void leerXMLXStream() {

		XStream xs = new XStream();
		
		xs.addPermission(NoTypePermission.NONE);
		xs.addPermission(NullPermission.NULL);
		xs.addPermission(PrimitiveTypePermission.PRIMITIVES);
		
		Class[] clases = {ListaLibros.class ,Libro.class, Autor.class };//poner las clases de las que depende
		
		xs.allowTypes(clases);
		
		xs.allowTypesByWildcard(new String[] {"javaBeans.*"});//donde estan las clases
		
		xs.alias("biblioteca",ListaLibros.class);
		xs.alias("libro",Libro.class);
		xs.alias("autor",Autor.class);
		
		xs.addImplicitCollection(ListaLibros.class, "listaLibrosL");
		
		
		try (FileInputStream fis = new FileInputStream("Ficheros\\bibliotecaOBJ.xml");){
			ListaLibros listaObj = (ListaLibros) xs.fromXML(fis); 
		
			//recorremos la lectura con for
			for (int i = 0; i < listaObj.getListalibrosL().size(); i++) {
				System.out.println(listaObj.getListalibrosL().get(i).mostrarFormatoXML());
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private static void crearXMLDOM() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Ficheros\\bibliotecaObj.dat"));) {

			// obtener el builder y poder parsear de la lectura delbinario al xml
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			
			Document document = implementation.createDocument(null, "biblioteca", null);//el nombre que le damos al nodo raiz
			document.setXmlVersion("1.0"); //Siempre es esta version de xml
			
			boolean continuar = true;
			Libro libro;
			//crear los elementos segun necesitemos
			Element elementLibro;
			Element elementTitulo;
			Element elementAutor;
			Element elementNombre;
			Element elementNacionalidad;
			Element elementNumPaginas;
			Element elementAnio;
			
			Text texto;
			
			while (continuar) {
				try {
					
				libro = (Libro)ois.readObject();
				
				elementLibro = document.createElement("libro");//crear el elemento libro
				document.getDocumentElement().appendChild(elementLibro);//que depende del documento
				
				texto = document.createTextNode(String.valueOf(libro.getTitulo()));//meter el texto con un get de la clase
				elementTitulo = document.createElement("titulo");//cremaos el elemento titulo
				elementLibro.appendChild(elementTitulo);//decirle que depende del anterior (titulo)
				elementTitulo.appendChild(texto);
				
				elementAutor = document.createElement("autor");//creamos el elemento autor
				document.getDocumentElement().appendChild(elementLibro);//depende del libro
				elementLibro.appendChild(elementAutor);//depende del anterior 
				
				texto = document.createTextNode(String.valueOf(libro.getAutor().getNombre()));
				elementNombre = document.createElement("nombre");
				elementAutor.appendChild(elementNombre);//elemetoNombre esta dento de elementoAutor
				elementNombre.appendChild(texto);
				
				texto = document.createTextNode(String.valueOf(libro.getAutor().getNacionalidad()));
				elementNacionalidad = document.createElement("nacionalidad");
				elementAutor.appendChild(elementNacionalidad);
				elementNacionalidad.appendChild(texto);
				
				texto = document.createTextNode(String.valueOf(libro.getNumPaginas()));
				elementNumPaginas = document.createElement("numPaginas");
				elementLibro.appendChild(elementNumPaginas);
				elementNumPaginas.appendChild(texto);
				
				texto = document.createTextNode(String.valueOf(libro.getAnioPublicacion()));
				elementAnio = document.createElement("anioPublicacion");
				elementLibro.appendChild(elementAnio);
				elementAnio.appendChild(texto);
				
				} catch (EOFException e) {
					continuar=false;
				}	
			}
			
			Source source  = new DOMSource(document); //Permite crear el fichero xml como tal, le dice la fuente de los datos
			Result result = new StreamResult(new File("Ficheros\\bibliotecaOBJ.xml"));//Le indicas la ruta resultante
			Transformer transformer = TransformerFactory.newInstance().newTransformer();//Transforma la estructura a un fichero
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");//Indica q va indentado xq hay suelementos
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");//Método para archivo xml
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");//4 indica los espacios de la indentacion
			transformer.transform(source, result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}