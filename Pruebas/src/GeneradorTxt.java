import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GeneradorTxt {

	private final String SEPARADOR = "######################";

	public GeneradorTxt() {
	}

	/**
	 * Lee un fichero trocea su contenido, escribiendo cada trozo en un fichero nuevo.
	 * 
	 * @param fileInputString
	 *            Fichero que se leerá para separar en varios sub ficheros.
	 */
	@SuppressWarnings("resource")
	void separarFichero(final String fileInputString) {

		final File archivo = new File(fileInputString);
		FileReader fileReader = null;
		BufferedReader bufferReader = null;

		String linea = null;
		boolean primerSeparador = true;
		PrintWriter out = null;

		try {

			fileReader = new FileReader(archivo);
			bufferReader = new BufferedReader(fileReader);

			while ((linea = bufferReader.readLine()) != null) {
				if (!linea.isEmpty()) {
					final Scanner scanner = new Scanner(linea);
					if (scanner.next().equals(SEPARADOR)) {
						if (!primerSeparador) {
							out.close();
						}
						else {
							primerSeparador = false;
						}
						// Construir el nombre del fichero de salida
						final StringBuilder stringB = new StringBuilder();
						while (scanner.hasNext()) {
							final String word = scanner.next();
							if (!word.equals(SEPARADOR)) {
								stringB.append(word + " ");
							}
							else {
								out = new PrintWriter("s:\\" + stringB.toString().trim() + ".txt");
							}
						}
					}
					else {
						out.println(linea);
					}
				}
				else {
					out.println("\n");
				}
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				fileReader.close();
				bufferReader.close();
			} catch (final Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
