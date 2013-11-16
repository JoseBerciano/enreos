import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Enreos {

    private final String SEPARADOR = "######################";

    public Enreos() {
    }

    public static void main(final String[] args) {

	final Enreos enreos = new Enreos();
	enreos.separarFichero2("s:\\Incis.txt");

    }

    /**
     * Lee un fichero y trocea su contenido, escribiendo cada trozo en un
     * fichero nuevo.
     * 
     * @param fileInputString
     *            Fichero que se leerá para separar en varios sub ficheros.
     */

    @SuppressWarnings("resource")
    public void separarFichero(final String fileInputString) {

	final File archivo = new File(fileInputString);
	FileReader fileReader = null;
	BufferedReader bufferReader = null;

	String linea = null;
	boolean primerSeparador = true;
	PrintWriter fileOut = null;

	try {

	    fileReader = new FileReader(archivo);
	    bufferReader = new BufferedReader(fileReader);

	    while ((linea = bufferReader.readLine()) != null) {
		if (!linea.isEmpty()) {
		    final Scanner scanner = new Scanner(linea);
		    if (scanner.next().equals(SEPARADOR)) {
			if (!primerSeparador) {
			    fileOut.close();
			} else {
			    primerSeparador = false;
			}
			// Construir el nombre del fichero de salida
			final StringBuilder stringB = new StringBuilder();
			while (scanner.hasNext()) {
			    final String word = scanner.next();
			    if (!word.equals(SEPARADOR)) {
				stringB.append(word + " ");
			    } else {
				fileOut = new PrintWriter("s:\\" + stringB.toString().trim()
					+ ".txt");
			    }
			}
		    } else {
			fileOut.println(linea);
		    }
		} else {
		    fileOut.println("\n");
		}
	    }
	} catch (final IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		fileOut.close();
		fileReader.close();
		bufferReader.close();
	    } catch (final Exception ex) {
		ex.printStackTrace();
	    }
	}
    }

    public void separarFichero2(final String fileInputString) {

	final File archivo = new File(fileInputString);
	FileReader fileReader = null;
	BufferedReader bufferReader = null;

	String linea = null;
	boolean primerSeparador = true;
	FileWriter fileOut = null;

	try {

	    fileReader = new FileReader(archivo);
	    bufferReader = new BufferedReader(fileReader);

	    while ((linea = bufferReader.readLine()) != null) {
		if (!linea.isEmpty()) {
		    final Scanner scanner = new Scanner(linea);
		    if (scanner.next().equals(SEPARADOR)) {
			if (!primerSeparador) {
			    fileOut.close();
			} else {
			    primerSeparador = false;
			}
			// Construir el nombre del fichero de salida
			final StringBuilder stringB = new StringBuilder();
			while (scanner.hasNext()) {
			    final String word = scanner.next();
			    if (!word.equals(SEPARADOR)) {
				stringB.append(word + " ");
			    } else {

				fileOut = new FileWriter("s:\\" + stringB.toString().trim()
					+ ".txt");
			    }
			}
		    } else {
			fileOut.write(linea + "\r\n");
		    }
		} else {
		    fileOut.write("\r\n");
		}
	    }
	} catch (final IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		fileOut.close();
		fileReader.close();
		bufferReader.close();
	    } catch (final Exception ex) {
		ex.printStackTrace();
	    }
	}
    }
}
