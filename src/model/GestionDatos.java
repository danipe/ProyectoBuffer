package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GestionDatos {

	public GestionDatos() {

	}


	public BufferedReader openFile(String file) throws FileNotFoundException {
		return new BufferedReader(new FileReader(file));
	}

	public void closeFile(BufferedReader br) throws IOException {
		br.close();
	}
	
	public boolean compararContenido (String fichero1, String fichero2)  throws IOException{
        BufferedReader b1 = openFile(fichero1);
        BufferedReader b2 = openFile(fichero2);
        String s;
        boolean response = true;
        while ((s = b1.readLine()) != null) {
        		if(!s.equals(b2.readLine())) {
        			response = false;
        			break;
        		}
        }
        closeFile(b1);
        closeFile(b2);
        return response;
	}
	
	public int buscarPalabra (String fichero1, String palabra, boolean primera) throws IOException{
        int numeroLineas = 0;
        int ocurrencia = 0;
	    BufferedReader file1 = openFile(fichero1);
	    String s;
	    while ((s = file1.readLine()) != null) {
	    		numeroLineas++;
	    		if(s.equals(palabra)) {
	    			ocurrencia = numeroLineas;
	    			if(primera) {
	    				break;
	    			}
	    		}
	    }
        return ocurrencia;
	}	
	
	public void ordenarFichero (String fichero1, String fichero2, boolean tipo_orden) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(fichero2));
		BufferedReader br = new BufferedReader(new FileReader(fichero1));
		ArrayList<String> lineas = new ArrayList<String>();
		ArrayList<String> ordenadas = new ArrayList<String>();
		String str;
		
		while((str = br.readLine()) != null) {
			lineas.add(str);
		}
		
		System.out.println(lineas.toString());
		while(lineas.size()>ordenadas.size()) {
			for(int i = 0 ; i < lineas.size(); i++) {
				if((str = lineas.get(i))!=null) {
					break;
				}
			}
			for(int i = 0; i<lineas.size(); i++) {
				if(lineas.get(i)!=null) {
					if(tipo_orden) {
						if(str.compareTo(lineas.get(i))<0) {
							str = lineas.get(i);
						}
					} else {
						if(str.compareTo(lineas.get(i))>0) {
							str = lineas.get(i);
						}
					}
					
				}
			}
			ordenadas.add(str);
			bw.append(str+"\n");
			lineas.set(lineas.indexOf(str), null);
		}
		System.out.println(ordenadas.toString());
		bw.close();
		br.close();
	}
	
	public void copiarFichero(String fichero1, String fichero2) throws IOException {
        FileInputStream fin = new FileInputStream(fichero1);       
        FileOutputStream fos = new FileOutputStream(fichero2);
        byte[] b = new byte[9999];
        int bytes = 0;
        
        while( (bytes = fin.read(b)) != -1 ){
        	fos.write(b, 0, bytes);
        }
       
        fin.close();
        fos.close();              
	}
	
	public void serializarObjeto(String fichero1, String fichero2) throws IOException {
        FileInputStream fin = new FileInputStream(fichero1);       
        FileOutputStream fos = new FileOutputStream(fichero2);
       
        byte[] b = new byte[4096];
        int noOfBytes = 0;
        
        while( (noOfBytes = fin.read(b)) != -1 ){
        	fos.write(b, 0, noOfBytes);
        }
       
        fin.close();
        fos.close();              
	}
	
	public int getLibroId() throws FileNotFoundException {
		File fis = new File("libros");
		int id = 0;
		for(File f : fis.listFiles()) {
			if(!f.isHidden()) {
				id++;
			}
		}
		id++;
		return id;
	}
	
	public void crearLibro(String titulo, String autor, String publi, String editor, String paginas) throws FileNotFoundException, IOException {
//		System.out.println(new File("").getAbsolutePath().toString());
//		System.out.println(getLibroId());
		Libro l = new Libro(titulo,autor,publi,editor,paginas);
		File f = new File("libros\\"+titulo);
		if(f.exists()) {
			throw new FileNotFoundException("El archivo ya existe");
		}
		FileOutputStream fos = new FileOutputStream(f);
		
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(l);
		fos.close();
		oos.close();
	}
	
	public Libro leerLibro(String titulo) throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File("libros\\"+titulo);
		if(f.exists()) {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Libro l = (Libro) ois.readObject();
			ois.close();
			fis.close();
			return l;
		} else {
			return null;
		}
	}
	
	public ArrayList<Libro> leerLibros() throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<Libro> libros = new ArrayList<Libro>();
		File f = new File("libros");
		for(File libro: f.listFiles()){
			FileInputStream fis = new FileInputStream(libro);
			ObjectInputStream ois = new ObjectInputStream(fis);
			libros.add((Libro) ois.readObject());
			fis.close();
			ois.close();
		}
		return libros;
	}
	
	public Libro editarPaginas(String libro, String paginas) throws ClassNotFoundException, IOException {
		/*
		 * Ejercicio 1:
		 * 
		 * Creamos un objeto file con el titulo del libro dentro de la carpeta libros y un objeto libro que lo inicializamos a null.
		 * 
		 * Después leemos el objeto, borramos el fichero para volverlo a crear, editamos las páginas a lo que haya introducido
		 * el usuario en el textArea y volvemos a guardar el Libro en el fichero.
		 * 
		 * Hecho esto lo devolvemos.
		 * 
		 * Si el fichero no existe devolvemos null.
		 * */
		Libro l = null;
		File f = new File("libros\\"+libro);
		if(f.exists()) {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			l = (Libro) ois.readObject();
			ois.close();
			fis.close();
			f.delete();
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			l.setPaginas(paginas);
			oos.writeObject(l);
			fos.close();
			oos.close();
		}else {
			return null;
		}
		return l;
	}
	
	public int medirPalabras(String fichero, String palabras) throws NumberFormatException, IOException {
		/*
		 * Ejercicio 2:
		 * 
		 * Iniciamos dos variables int, una nos servirá para guardar la longitud a superar y la otra nos guardará
		 * el número de palabras que superan dicha longitud
		 * 
		 * Por cada linea del fichero comprobamos si la palabra es mas larga que la longitud que introdujo el usuario,
		 * si es así incrementamos en 1 la variable "i".
		 * 
		 * Si el fichero no existe devolvemos -1.
		 * */
		int i = 0;
		int pals = Integer.parseInt(palabras);
		File f = new File(fichero);
		if(f.exists()) {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String s;
			while((s = br.readLine()) != null) {
				if(s.length()>pals) {
					i++;
				}
			}
			br.close();
			fr.close();
		} else {
			return -1;
		}
		return i;
	}
	

}
