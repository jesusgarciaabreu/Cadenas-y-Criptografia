import java.util.Scanner;

/**
* Clase que implementa métodos para realizar acciones de 
* criptografía y estenografía
* @author García Hernández José Antonio
* @author García Abreu Jesús
* @version 1.0
*/

public class Estenografia{

	/**
	* Toma la n-e sima letra de cada palabra para obtener el mensaje
	* oculto .
	*
	* @param original El mensaje original .
	* @param n El  ındice que se tiene que tomar de cada palabra .
	* @return El mensaje oculto , sin espacios .
	*/
	public String descifraNulo ( String original , int n ){
		String resultado = "";
		int auxiliar = 0;
		for (int j = 0; j < original.length(); j++) {
			if (original.charAt(j) == ' ') {
				auxiliar = 0;
			}else{
				if (auxiliar == n) {
					resultado = resultado + original.charAt(j);
					auxiliar = 0;
					while(original.charAt(j) != ' '){
						auxiliar = 0;
						++j;
						if (j >= original.length()) {
							break;
						}
					}
				}else{
					++auxiliar;
				}
			}
		}
		return resultado;
	}

	/**
	* Toma la n-esima letra de cada palabra para obtener el mensaje
	* oculto .
	*
	* @param original El mensaje original con n espacios al final .
	* @return El mensaje oculto , sin espacios .
	*/
	public String descifraNulo ( String original ){
		String resultado = "";
		int n = 0;
		for(int i = 0; i < original.length(); i++){
			if(original.charAt(i) == ' '){
				n++;
			}else{
				n = 0;
			}
		}

		int auxiliar = 0;
		for (int j = 0; j < original.length(); j++) {
			if (original.charAt(j) == ' ') {
				auxiliar = 0;
			}else{
				if (auxiliar == n) {
					resultado = resultado + original.charAt(j);
					auxiliar = 0;
					while(original.charAt(j) != ' '){
						auxiliar = 0;
						++j;
						if (j >= original.length()) {
							break;
						}
					}
				}else{
					++auxiliar;
				}
			}
		}
		return resultado;
	}

	/**
	* Busca un nombre oculto en un texto arbitrario ignorando
	* espacios , signos de puntuacion y sin hacer distinciones
	* entre mayusculas y minusculas .
	*
	* @param mensaje Una cadena arbitraria donde se hara la
	* busqueda .
	* @param nombre El nombre que se buscara en el texto .
	* @return true si el mensaje esta contenido , false en otro
	* caso .
	*/
	public boolean contieneNombre (String mensaje ,String nombre ){
		String cadena = "";
    	String nombreM = "";
   		String resultado = "";
   		String resultadoM = "";
   		cadena = mensaje.toLowerCase();
   		nombreM = nombre.toLowerCase();

   		for (int k = 0; k < nombreM.length() ; k++) {
        	resultadoM = resultadoM + nombreM.charAt(k);

        	if (nombreM.charAt(k) == 'á') {
            	resultadoM = resultadoM.replace('á','a');
        	}
        	if (nombreM.charAt(k) == 'é') {
            	resultadoM = resultadoM.replace('é','e');
        	}
        	if (nombreM.charAt(k) == 'í') {
            	resultadoM = resultadoM.replace('í','i');
        	}
        	if (nombreM.charAt(k) == 'ó') {
            	resultadoM = resultadoM.replace('ó','o');
        	}
        	if (nombreM.charAt(k) == 'ú') {
            	resultadoM = resultadoM.replace('ú','u');
        	}   
    	}


   		for (int j = 0; j < cadena.length() ; j++) {
   			if (cadena.charAt(j) != ' ' && cadena.charAt(j) != ',' && cadena.charAt(j) != ';'
   				&& cadena.charAt(j) != '.' && cadena.charAt(j) != '-' && cadena.charAt(j) != ':') {
        		resultado = resultado + cadena.charAt(j);

            	if (cadena.charAt(j) == 'á') {
            		resultado = resultado.replace('á','a');
            	}
            	if (cadena.charAt(j) == 'é') {
            		resultado = resultado.replace('é','e');
            	}
            	if (cadena.charAt(j) == 'í') {
            		resultado = resultado.replace('í','i');
            	}
            	if (cadena.charAt(j) == 'ó') {
            		resultado = resultado.replace('ó','o');
            	}
            	if (cadena.charAt(j) == 'ú') {
            		resultado = resultado.replace('ú','u');
            	}
    		}     
    	}
        
    	int restriccion = resultado.length() - resultadoM.length();
		boolean verificador=false;
		for (int i = 0; i <= restriccion && !verificador ; i++) {
	  	verificador = resultadoM.equalsIgnoreCase(resultado.substring(i,i + resultadoM.length()));
		} 
		return verificador;
	}

	/**
	* Reconstruye el mensaje oculto a partir de las palabras
	* especiales que se obtienen al comparar dos textos .
	*
	* @param m Un texto arbitrario .
	* @param e Un texto similar al primero , pero con algunas
	* palabras especiales .
	* @return El mensaje oculto .
	*/
	public String descifraPalabrasMarcadas(String m, String e){
		String aux1 = "";
		String aux2 = "";
		String res = ""; 
		int i = 0;
		int j = 0;
		while(i < m.length() || j < e.length()){

			while (i < m.length() && m.charAt(i) == ' '){
				++i;
			}

			while (j < e.length() && e.charAt(j) ==' '){
				++j;
			}

			while (i < m.length() && m.charAt(i) != ' '){
				aux1 += m.charAt(i);
				++i;
			}

			while (j < e.length() && e.charAt(j) != ' '){
				aux2 += e.charAt(j);
				++j;
			}

			if(!(aux1.equals(aux2))){
				res += aux1;
				res += " ";
			}
			aux1 = "";
			aux2 = "";
		}
		res = res.substring(0,res.length() - 1);
		return res.toLowerCase();
	}

	/**
	* Reconstruye el mensaje oculto a partir de las letras
	* especiales que se obtienen al comparar dos textos .
	*
	* @param m Un texto arbitrario .
	* @param e Un texto similar al primero , pero con algunas
	* letras especiales .
	* @return El mensaje oculto .
	*/
	public String descifraLetrasMarcadas ( String m , String e ){
		String nuevo = "";
    	String palabra = "";
    	for (int i = 0; i < m.length() || i < e.length() ;i++) {
    		nuevo  = nuevo + m.charAt(i);
    		if (m.charAt(i) != e.charAt(i)) {
    			palabra = palabra + e.charAt(i);
    		}
    	}
    	return palabra;
	}

	public static void main(String[] args){
		Estenografia prueba = new Estenografia();

		Scanner sc = new Scanner(System.in);
		int eleccion = 0;

		do{
			System.out.println("Escoge un metodo para realizar ó ingresa 6 para salir");
			System.out.println("[1] Descifra Nulo\n"+"[2] Descifra Nulo -Automático-\n"+"[3] Contiene Nombre\n"+"[4] Descifra Palabras Marcadas\n"+"[5] Descifra Letras Marcadas\n"+"[6] Salir");
			eleccion = sc.nextInt();
			sc.nextLine();

				switch(eleccion){
					case 1:
					System.out.print("Ingresa una cadena (Debes poner un punto al final): ");
					String original = sc.nextLine();
					System.out.print("Ingresa una numero: ");
					int n = sc.nextInt();
					System.out.println(prueba.descifraNulo(original,n));
					break;

					case 2:
					System.out.print("Ingresa una cadena (Debes poner un punto al final): ");
					String original2 = sc.nextLine();
					System.out.println(prueba.descifraNulo(original2));
					break;
					
					case 3:
					System.out.print("Ingresa la primer cadena: ");
					String mensaje = sc.nextLine();
					System.out.print("Ingresa el nombre: ");
					String nombre = sc.nextLine();
					System.out.println(prueba.contieneNombre(mensaje,nombre));
					break;

					case 4:
					System.out.print("Ingresa la primer cadena (Debes poner un punto al final): ");
					String m = sc.nextLine();
					System.out.print("Ingresa la segunda cadena (Debes poner un punto al final): ");
					String e = sc.nextLine();
					System.out.println(prueba.descifraPalabrasMarcadas(m,e));
					break;

					case 5:
					System.out.print("Ingresa la primer cadena: ");
					String m2 = sc.nextLine();
					System.out.print("Ingresa la segunda cadena: ");
					String e2 = sc.nextLine();
					System.out.println(prueba.descifraLetrasMarcadas(m2, e2));
					break;

					case 6:
					System.out.println("\nElegiste salir unu ");
					System.out.println("Entendible, ten un lindo día");
					break;
				}
		}while(eleccion !=  6);
	}
}