package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
        ArrayList<String> stringOnFiles = new ArrayList<String>();
        BufferedReader b1 = openFile(fichero1);
        BufferedReader b2 = openFile(fichero2);
        while ((fichero1 = b1.readLine()) != null) {
        		stringOnFiles.add(fichero1);
        }
        closeFile(b1);

        while ((fichero2 = b2.readLine()) != null) {
            if (stringOnFiles.contains(fichero2)) {
               return true;
            }
        }
        closeFile(b2);
        return false;
	}
	
	public int buscarPalabra (String fichero1, String palabra, boolean primera_aparicion) throws IOException{
        int numeroLineas = 0;
        int ultimaOcurrencia = 0;
        boolean palabraEncontrada = false;
	    BufferedReader file1 = openFile(fichero1);
	    while ((fichero1 = file1.readLine()) != null) {
	    		numeroLineas++;
	    		if(primera_aparicion) {
	        		if(fichero1.equalsIgnoreCase(palabra)) {
	            		closeFile(file1);
	        			return numeroLineas;
	        		}
	    		}else {
	    			if(fichero1.equalsIgnoreCase(palabra)) {
	    				ultimaOcurrencia = numeroLineas;
	    				palabraEncontrada = true;
	    			}
	    		}
	    }
        if(palabraEncontrada) {
	        return ultimaOcurrencia;
        }else {
            return -1;
        }
	}	
	
	public void ordenarFichero (String fichero1, String fichero2, boolean tipo_orden) throws IOException {
		FileOutputStream fos = new FileOutputStream(fichero2);
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
					if(str.compareTo(lineas.get(i))<0) {
						str = lineas.get(i);
					}
				}
			}
			ordenadas.add(str);
			lineas.set(lineas.indexOf(str), null);
		}
		System.out.println(ordenadas.toString());
		fos.close();
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
	
	
	

}
