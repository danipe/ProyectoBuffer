package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import model.*;
import view.*;

public class GestionEventos {

	private GestionDatos model;
	private LaunchView view;
	private ActionListener actionListener_comparar, actionListener_buscar,
	actionListener_ordenarFichero, actionListener_creaCopia, actionListener_guardarLibro, 
	actionListener_leerLibro, actionListener_leerLibros, actionListener_editarPaginas,
	actionListener_medirPalabras;
	

	public GestionEventos(GestionDatos model, LaunchView view) {
		this.model = model;
		this.view = view;
	}

	public void contol() {
		actionListener_comparar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_compararContenido();
			}
		};
		view.getComparar().addActionListener(actionListener_comparar);

		actionListener_buscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_buscarPalabra();
			}
		};
		view.getBuscar().addActionListener(actionListener_buscar);
		
		actionListener_ordenarFichero = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_ordenarFichero();
			}
		};
		view.getOrdenarFichero().addActionListener(actionListener_ordenarFichero);
		
		actionListener_creaCopia = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				call_copiarFichero();
			}
		};
		view.getCopia().addActionListener(actionListener_creaCopia);
		
		actionListener_guardarLibro = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				call_guardarLibro();				
			}
		};
		view.getBtnGuardarLibro().addActionListener(actionListener_guardarLibro);
		
		actionListener_leerLibro = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				call_leerLibro();
			}
		};
		view.getBtnEncontrarLibro().addActionListener(actionListener_leerLibro);
		
		actionListener_leerLibros = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				call_leerLibros();
			}
		};
		view.getBtnRecuperarLibros().addActionListener(actionListener_leerLibros);
		/*
		 * Ejercico 1:
		 * 
		 * Creamos el actionListener del botón "Editar Páginas" y le asignamos la función a la que tendrá que llamar
		 * */
		actionListener_editarPaginas = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				call_editarPaginas();
			}
		};
		
		view.getBtnEditarPginas().addActionListener(actionListener_editarPaginas);
		
		/*
		 * Ejercicio 2:
		 * 
		 * Creamos el ActionListener del botón "Comprobar palabras" y le asignamos la función que tendrá que llamar
		 * */
		actionListener_medirPalabras = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				call_medirPalabras();
			}
		};
		view.getBtnComprobarPalabras().addActionListener(actionListener_medirPalabras);
		
	}

	private void call_compararContenido() {
		try {
			if(model.compararContenido(view.getFichero1().getText(), view.getFichero2().getText())) {
				view.getTextArea().setText("Los ficheros son iguales");
			}else {
				view.getTextArea().setText("Los ficheros no son iguales");
			}
		} catch (IOException e) {
			view.showError("Hubo un error leyendo los ficheros");
		}
	}

	private void call_buscarPalabra() {
		try {
			int ocurrencia = model.buscarPalabra(view.getFichero1().getText(), view.getPalabra().getText(), view.getPrimera().isSelected());
			if(ocurrencia > 0) {
				view.getTextArea().setText("La palabra "+view.getPalabra().getText()+" se ha encontrado en la linea: "+ocurrencia);
			}else{
				view.showError("La palabra "+view.getPalabra().getText()+" no ha sido encontrada");
			}
		} catch (IOException e) {
			view.showError("Hubo un error al buscar la palabra en el fichero");
		}
	}
	
	private void call_ordenarFichero() {
		try{
			model.ordenarFichero(view.getFichero1().getText(), view.getFichero2().getText(), view.getTipo_orden().isSelected());
			view.getTextArea().setText("El archivo: "+view.getFichero2().getText()+" ha sido generado correctamente");
		}catch(IOException io) {
			view.showError("Ha ocurrido un error, comprueba los campos");

		}
	}
	
	private void call_copiarFichero() {
		try{
			model.copiarFichero(view.getFichero1().getText(), view.getFichero2().getText());
			view.getTextArea().setText("Se ha copiado el archivo: "+view.getFichero1().getText());
		}catch(IOException io) {
			view.showError("Hubo un error copiando el fichero");

		}
	}
	
	private void call_guardarLibro() {
		try {
			if(view.getTextFieldTitulo().getText().length()>0 || view.getTextFieldAutor().getText().length()>0 || view.getTextFieldPubli().getText().length()>0 || view.getTextFieldEditor().getText().length()>0 || view.getTextFieldPaginas().getText().length()>0) {
				model.crearLibro(view.getTextFieldTitulo().getText(), view.getTextFieldAutor().getText(), view.getTextFieldPubli().getText(), view.getTextFieldEditor().getText(), view.getTextFieldPaginas().getText());
				view.getTextArea().setText("Libro \""+view.getTextFieldTitulo().getText()+"\" creado con éxito");
			}else {
				view.showError("Faltan campos por rellenar");
			}
		} catch (FileNotFoundException e) {
			
			view.showError("Hubo un problema creando el fichero");
		} catch (IOException e) {
			
			view.showError("Hubo un problema guardando el libro");
		}
	}
	
	private void call_leerLibro() {
		try {
			if(view.getTextFieldTitulo().getText().length() > 0) {
				Libro l = model.leerLibro(view.getTextFieldTitulo().getText());
				String s = "Detalles libro: \n\n";
				s += "\t Título: "+l.getTitulo()+" \n\n";
				s += "\t Autor: "+l.getAutor()+" \n\n";
				s += "\t Año de publicación: "+l.getPubli()+" \n\n";
				s += "\t Editor: "+l.getEditor()+" \n\n";
				s += "\t Nº de páginas: "+l.getPaginas()+" \n\n";
				view.getTextArea().setText(s);
			} else {
				view.showError("Introduce un titulo");
			}
		} catch (FileNotFoundException e) {
			view.showError("No se encuentra el fichero");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void call_leerLibros() {
		try {
			String s = "Libros: \n\n";
			ArrayList<Libro> libros = model.leerLibros();
			if(libros.size() > 0) {
				for(Libro l: libros) {
					s += "\t - "+l.getTitulo()+"\n\n";
				}
				view.getTextArea().setText(s);
			} else {
				view.getTextArea().setText("No hay ningún libro");
			}
		} catch (FileNotFoundException e) {
			view.showError("Hubo un error leyendo los ficheros");
		} catch (ClassNotFoundException e) {
			view.showError("Hubo un error leyendo los ficheros");
		} catch (IOException e) {
			view.showError("Hubo un error leyendo los ficheros");
		}
	}
	
	private void call_editarPaginas() {
		/*
		 * Ejercicio 1:
		 * 
		 * 
		 * Llamamos a la funcion "editarPaginas" del modelo enviandole el titulo 
		 * del libro que ha introducido el usuario y el numero de páginas que deberá 
		 * tener el libro. Esta función nos devolverá el Libro ya editado o null, según 
		 * si existe el libro o no.
		 * 
		 * */
		try {
			Libro l = model.editarPaginas(view.getTextFieldTitulo().getText(), view.getTextFieldPaginas().getText());
			if(l != null) {
				String s = "Libro actualizado: \n\n";
				s += "\t Título: "+l.getTitulo()+" \n\n";
				s += "\t Autor: "+l.getAutor()+" \n\n";
				s += "\t Año de publicación: "+l.getPubli()+" \n\n";
				s += "\t Editor: "+l.getEditor()+" \n\n";
				s += "\t Nº de páginas: "+l.getPaginas()+" \n\n";
				view.getTextArea().setText(s);
			} else {
				view.showError("El libro no existe");
			}
			
		} catch (ClassNotFoundException e) {
			view.showError("Hubo un problema leyendo el libro");
		} catch (IOException e) {
			view.showError("Hubo un problema leyendo el fichero");
		}
	}
	
	private void call_medirPalabras() {
		/*
		 * Ejercicio 2:
		 * 
		 * Llamamos a la función "medirPalabras" del modelo pasándole el nombre del fichero y la longitud que
		 * deberan superar las palabras.
		 * 
		 * Si el fichero no existe nos devolverá -1.
		 * 
		 * 
		 * */
		try {
			int i = model.medirPalabras(view.getFichero1().getText(), view.getTextFieldPalabras().getText());
			if(i > 0) {
				view.getTextArea().setText("Existen "+i+" palabras con longitud mas grande que "+view.getTextFieldPalabras().getText());
			} else if(i==0) {
				view.showError("No existe ninguna palabra mas grande");
			} else {
				view.showError("No existe el fichero");
			}
		} catch (NumberFormatException e) {
			view.showError("No has introducido un numero correcto");
		} catch (IOException e) {
			view.showError("Hubo un problema leyendo el archivo");
		}
	}

}
