import java.util.Scanner;
import java.io.Reader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;


/**
* Representacion de la estenografía con archivos de texto.
* @author Jesús García Abreu.
* @author José Antonio García Hernández.
* @version 1.0 Enero 2021.
* @since ICC 2021-1.
*/
public class Archivos extends Estenografia{

	/**
	* Metodo para contar cuantas lineas tiene el archivo de texto.
	* @param rutaArchivo el archivo donde se cintaran las lineas.
	* @return el numero de lineas que tiene el archivo de texto.
	*/
	public int lineas(String rutaArchivo){
		int contador = 0; //El contador para guardar cuantas lineas hay en el programa.
		try{
			Reader reader = new FileReader(rutaArchivo); //Leemos el archivo.
			BufferedReader lector = new BufferedReader(reader); //Lo ponemos en el buffer.
			String linea = null;
			while((linea = lector.readLine()) != null){ //Contador para ir leyendo linea por linea del archivo.
				contador++; //Por cada linea sumamos 1 al contador.
			}
		} catch(FileNotFoundException fnfe){
			System.out.println("No se encontró el archivo");
		} catch(IOException ioe){
			System.out.println("Ocurrio una excepcion en la operacion l/0");
		} catch(Exception e){
			System.out.println("Ocurrio un error");
		}
		return contador; //Regresamos el contador.
	}

	/**
	* Metodo para calcular cuantas espacios hay al final de una linea.(Se me ocurrio de forma recursiva lol xD).
	* @param orginal la cadena la cual cpntaremos cuantos espacios tiene al final.
	* @return la cantidad de espacios que hay al final de la linea.
	*/
	public int espaciosAlFinal(String original){
		int contador = 0; //Iniciamos el contador.

		//Caso base. Si la cadena al final no tiene un espacio devolvemos 0.
		if (original.charAt(original.length() - 1) != ' ') {
			return 0;
		}

		//Caso base. Si la cadena al final tiene un espacio devolvemos el contador.
		if (original.charAt(original.length() - 1) == ' ') {
			contador++;
		}

		// Caso recursivo. Le quitamos el ultimo caracter y regresamos el contador mas 0 o el contador.
		return contador + espaciosAlFinal(original.substring(0,original.length() - 1));
	}

	/**
	* Regresa cuantos espacios tiene la ultima linea de un archivo de texto.
	* @param rutaArchivo el archivo de texto.
	* @return los espacios que hay en la ultima linea de un archivo.
	*/
	public int descifraNuloEspacios(String rutaArchivo){
		int contador = 0; //Iniciamos un contador auxiliar que nos dice la ultima linea del archivo de texto.
		int espacios = 0; //Nos dice la cantidad de espacios al final de la ultima linea del archivo de texto.
		try{
			Reader reader = new FileReader(rutaArchivo); //Leemos el archivo.
			BufferedReader lector = new BufferedReader(reader); //Lo ponemos en el buffer.
			String linea = null;
			while((linea = lector.readLine()) != null){ //Recorremos linea por linea.
				contador++; //Contador para ir leyendo linea por linea del archivo.
				if (contador == this.lineas(rutaArchivo)) { //Si estamos en la ultima linea entonces...
					espacios = this.espaciosAlFinal(linea); //guardamos los espacios que estan en la ultima linea.
				}
			}
		} catch(FileNotFoundException fnfe){
			System.out.println("No se encontró el archivo");
		} catch(IOException ioe){
			System.out.println("Ocurrio una excepcion en la operacion l/0");
		} catch(Exception e){
			System.out.println("Ocurrio un error");
		}
		return espacios; //Regresamos los espacios.
	}

	/**
	* Toma la n-ésima letra de cada palabra para obtener el mensaje
	* oculto.
	*
	* @param rutaArchivo El nombre del archivo que posee el mensaje
	*                    original con n espacios al final de la 
	* 					 última línea de texto.
	* @return El mensaje oculto, sin espacios.
	*/
	public String descifraNulo (String rutaArchivo){
		String auxiliar = ""; //Aqui guardaremos nuestro resultado.
		try{
			Reader reader = new FileReader(rutaArchivo); //Leemos el archivo.
			BufferedReader lector = new BufferedReader(reader); //Lo ponemos en el buffer.
			String linea = null;
			while((linea = lector.readLine()) != null){ //Recorremos linea por linea.
				auxiliar += super.descifraNulo(linea, this.descifraNuloEspacios(rutaArchivo) - 1); //Vamos guardando 
				// los resultados  en auxiliar y los vamos concatenando. Lo hacemos llamando el metodo de la clase 
				// padre que tiene como parametros la cadena, y un numero, que tomara los indices de cada palabra 
				// y los ira concatenando. Asi linea por linea.
			}
		} catch(FileNotFoundException fnfe){
			System.out.println("No se encontró el archivo");
		} catch(IOException ioe){
			System.out.println("Ocurrio una excepcion en la operacion l/0");
		} catch(Exception e){
			System.out.println("Ocurrio un error");
		}
		return auxiliar; //Regresamos la cadena auxiliar(resultado).
	}	

	/**
	* Busca un nombre oculto en u  texto arbitrario ignorando
	* espacios, signos de puntuacion y sin hacer distinciones
	* entre mayúsculas y minúsculas.
	*
	* @param rutaArchivo El nombre del archivo que posee el mensaje
	* 				     donde se hará la búsqueda.
	* @param nombre El nombre que se buscara en el texto.
	* @return true si el nombre está contenido, false en otro caso.
	*/
	public boolean contieneNombre(String rutaArchivo, String nombre){
		boolean resultado = false; //Aqui guardamos nuestro resultado.
		try{
			String auxiliar = ""; //En esta cadena guardaremos el archivo de texto en una sola linea.
			Reader reader = new FileReader(rutaArchivo); //Leemos el archivo.
			BufferedReader lector = new BufferedReader(reader); //Lo ponemos en el buffer.
			String linea = null; 
			while((linea = lector.readLine()) != null){ //Recorremos linea por linea.
				auxiliar += linea; //Concatenamos linea a linea en auxiliar.
			}
			resultado = super.contieneNombre(auxiliar, nombre); //Usamos un metodo de la clase padre que tiene
			//como parametros una cadena que en este caso en nuestra variable auxiliar, y el nombre que pongamos.
		} catch(FileNotFoundException fnfe){
			System.out.println("No se encontró el archivo");
		} catch(IOException ioe){
			System.out.println("Ocurrio una excepcion en la operacion l/0");
		} catch(Exception e){
			System.out.println("Ocurrio un error");
		}
		return resultado; //true en caso de que si este el nombre, false en otro caso.
	}

	/**
	* Reconstruye el mensaje oculto a partir de las palabras
	* especiales que se obtiene al comparar dos textos.
	*
	* @param rutaM La ruta de un archivo con un texto arbitrario.
	* @param rutaE La ruta de un archivo con un texto similar al 
	* 			   primero, pero con algunas letras especiales.
	* @return El mensaje oculto.
	*/
	public String descifraPalabrasMarcadas(String rutaM, String rutaE){
		String resultado = ""; //Aqui guardaremos el resultado(lol pues si).
		try{
			String auxiliar1 = ""; //Aqui guardaremos en una cadena las lineas del archivo de texto.
			Reader reader1 = new FileReader(rutaM); //Leemos el archivo.
			BufferedReader lector1 = new BufferedReader(reader1); //Lo ponemos en el buffer.
			String linea1 = null;
			while((linea1 = lector1.readLine()) != null){ //Recorremos linea por linea.
				auxiliar1 += linea1; //Concatenamos linea a linea en auxiliar.
			}
			String auxiliar2 = ""; //Aqui guardaremos en una cadena las lineas del archivo de texto.
			Reader reader2 = new FileReader(rutaE); //Leemos el archivo.
			BufferedReader lector2 = new BufferedReader(reader2); //Lo ponemos en el buffer.
			String linea2 = null;
			while((linea2 = lector2.readLine()) != null){ //Recorremos linea por linea.
				auxiliar2 += linea2; //Concatenamos linea a linea en auxiliar.
			}
			resultado = super.descifraPalabrasMarcadas(auxiliar1, auxiliar2); //Guardamos en resultado nuestro
			// resultado, usando un metodo en nuestra clase padre que tiene como parametros dos cadenas, y usamos 
			// auxuliar1 y auxiliar2.
		} catch(FileNotFoundException fnfe){
			System.out.println("No se encontró el archivo");
		} catch(IOException ioe){
			System.out.println("Ocurrio una excepcion en la operacion l/0");
		} catch(Exception e){
			System.out.println("Ocurrio un error");
		}
		return resultado; //Regresamos nuestro resultado.
	}

	/**
	* Reconstruye el mensaje oculto a partir de las letras
	* especiales que se obtiene al comparar dos textos.
	*
	* @param rutaM La ruta de un archivo con un texto arbitrario.
	* @param rutaE La ruta de un archivo con un texto similar al 
	* 			   primero, pero con algunas letras especiales.
	* @return El mensaje oculto.
	*/
	public String descifraLetrasMarcadas(String rutaM, String rutaE){
		String mensaje = ""; //Aqui guardaremos el resultado.
		try{
			String auxiliar = ""; //Aqui guardaremos en una cadena las lineas del archivo de texto.
			Reader reader = new FileReader(rutaM); //Leemos el archivo.
			BufferedReader lector = new BufferedReader(reader); //Lo ponemos en el buffer.
			String linea = null;
			while((linea = lector.readLine()) != null){ //Recorremos linea por linea.
				auxiliar += linea; //Concatenamos linea a linea en auxiliar.
			}
			String auxiliardos = ""; //Aqui guardaremos en una cadena las lineas del archivo de texto.
			Reader readerdos = new FileReader(rutaE); //Leemos el archivo.
			BufferedReader lectordos = new BufferedReader(readerdos); //Lo ponemos en el buffer.
			String lineados = null;
			while((lineados = lectordos.readLine()) != null){ //Recorremos linea por linea.
				auxiliardos += lineados; //Concatenamos linea a linea en auxiliar.
			}
			mensaje = super.descifraLetrasMarcadas(auxiliardos, auxiliar); //Guardamos en resultado nuestro
			// resultado, usando un metodo en nuestra clase padre que tiene como parametros dos cadenas, y usamos 
			// auxuliar1 y auxiliar2.
		}catch(FileNotFoundException fnfe){
			System.out.println("No se encontró el archivo");
		} catch(IOException ioe){
			System.out.println("Ocurrio una excepcion en la operacion l/0");
		} catch(Exception e){
			System.out.println("Ocurrio un error");
		}

		return mensaje; //Regresamos nuestro resultado.
	}

	public static void main(String[] args) {
		Archivos archivos = new Archivos();

		Scanner sc = new Scanner(System.in);
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		int num4 = 0;

		do{
			System.out.print("\n[1]Descifra nulo" + 
				"\n[2]Contiene nombre" + 
				"\n[3]Descifra palabras marcadas" + 
				"\n[4]Descifra letras marcadas" + 
				"\n[5]Salir" +
				"\nEscoge una opcion: ");
			int num = 0;
			try{
				num = sc.nextInt();
			} catch(InputMismatchException ime){
				System.out.println("\nNo ingresaste un numero");
			} catch(Exception e){
				System.out.println("\nOcurrio un error");
			}
			sc.nextLine();

			switch(num){
				
				case 1:
					do{
						System.out.print("\n[1]descifranulo1.txt" + 
						"\n[2]descifranulo2.txt" + 
						"\n[3]descifranulo3.txt" + 
						"\n[4]Salir" +
						"\nSelecciona el texto que quieras: ");
						num1 = 0;
						try{
							num1 = sc.nextInt();
						} catch(InputMismatchException ime){
							System.out.println("\nNo ingresaste un numero");
						} catch(Exception e){
							System.out.println("\nOcurrio un error");
						}
						sc.nextLine();
						switch (num1){
							case 1:
								System.out.println("descifranulo1.txt");
								System.out.println(archivos.descifraNulo("descifranulo1.txt"));
								break;
							case 2:
								System.out.println("descifranulo2.txt");
								System.out.println(archivos.descifraNulo("descifranulo2.txt"));
								break;
							case 3:
								System.out.println("descifranulo3.txt");
								System.out.println(archivos.descifraNulo("descifranulo3.txt"));
								break;
						}
					}while(num1 != 4);
					break;
					
				case 2:
					do{
						System.out.print("\n[1]contieneNombre1.txt" + 
						"\n[2]contieneNombre2.txt" + 
						"\n[3]Salir" + 
						"\nSelecciona el texto que quieras: ");
						num2 = 0;
						try{
							num2 = sc.nextInt();
						} catch(InputMismatchException ime){
							System.out.println("\nNo ingresaste un numero");
						} catch(Exception e){
							System.out.println("\nOcurrio un error");
						}
						sc.nextLine();
						switch(num2){
							case 1:
								System.out.println("contieneNombre1.txt");
								System.out.println("Lalo");
								System.out.println(archivos.contieneNombre("contieneNombre1.txt","Lalo"));
								System.out.println("Lala");
								System.out.println(archivos.contieneNombre("contieneNombre1.txt","Lala"));
								System.out.println("Poe");
								System.out.println(archivos.contieneNombre("contieneNombre1.txt","Poe"));
								System.out.println("Laura");
								System.out.println(archivos.contieneNombre("contieneNombre1.txt","Laura"));
								break;
							case 2:
								System.out.println("contieneNombre2.txt");
								System.out.println("Perla");
								System.out.println(archivos.contieneNombre("contieneNombre2.txt","Perla"));
								System.out.println("Rosalba");
								System.out.println(archivos.contieneNombre("contieneNombre2.txt","Rosalba"));
								System.out.println("Nerón");
								System.out.println(archivos.contieneNombre("contieneNombre2.txt","Nerón"));
								System.out.println("Andrea");
								System.out.println(archivos.contieneNombre("contieneNombre2.txt","Andrea"));
								break;
						}
					}while(num2 != 3);
					break;

				case 3:
					do{
						System.out.print("\n[1]descifraPalabrasMarcadas1.txt" + 
						"\n[2]descifraPalabrasMarcadas2.txt" + 
						"\n[3]descifraPalabrasMarcadas3.txt" + 
						"\n[4]Salir" +
						"\nSelecciona el texto que quieras: ");
						num3 = 0;
						try{
							num3 = sc.nextInt();
						} catch(InputMismatchException ime){
							System.out.println("\nNo ingresaste un numero");
						} catch(Exception e){
							System.out.println("\nOcurrio un error");
						}
						sc.nextLine();
						switch (num3){
							case 1:
								System.out.println(archivos.descifraPalabrasMarcadas("descifraPalabrasMarcadas1-E.txt","descifraPalabrasMarcadas1-M.txt"));
								break;
							case 2:
								System.out.println(archivos.descifraPalabrasMarcadas("descifraPalabrasMarcadas2-E.txt","descifraPalabrasMarcadas2-M.txt"));
								break;
							case 3:
								System.out.println(archivos.descifraPalabrasMarcadas("descifraPalabrasMarcadas3-E.txt","descifraPalabrasMarcadas3-M.txt"));
								break;
						}
					}while(num3 != 4);
					break;

				case 4:
					do{
						System.out.print("\n[1]descifraLetrasMarcadas1.txt" + 
						"\n[2]descifraLetrasMarcadas2.txt" + 
						"\n[3]Salir" +
						"\nSelecciona el texto que quieras: ");
						num4 = 0;
						try{
							num4 = sc.nextInt();
						} catch(InputMismatchException ime){
							System.out.println("\nNo ingresaste un numero");
						} catch(Exception e){
							System.out.println("\nOcurrio un error");
						}
						sc.nextLine();
						switch (num4){
							case 1:
								System.out.println(archivos.descifraLetrasMarcadas("descifraLetrasMarcadas1-E.txt","descifraLetrasMarcadas1-M.txt"));
								break;
							case 2:
								System.out.println(archivos.descifraLetrasMarcadas("descifraLetrasMarcadas2-E.txt","descifraLetrasMarcadas2-M.txt"));
								break;
						}
					}while(num4 != 3);
					break;
				case 5:
					return;
				default:
					System.out.println("Escoge un numero entre el 1 y el 5");
					break;
			}
		}while(true);
	}
}