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
	private ActionListener actionListener_comparar, actionListener_buscar,actionListener_ordenarFichero, actionListener_creaCopia, actionListener_guardarLibro, actionListener_leerLibro, actionListener_leerLibros;

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
	}

	private void call_compararContenido() {
		try {
			if(model.compararContenido(view.getFichero1().getText(), view.getFichero2().getText())) {
				view.getTextArea().setText("Los dos archivos tienen las mismas palabras");
			}else {
				view.showError("Los dos archivos no tienen las mismas palabras");
			}
		} catch (IOException e) {
			view.showError("Ha ocurrido un error, revise los campos");
		}
	}

	private void call_buscarPalabra() {
		//Codigo de errores: 
		// -1 - palabra no encontrada
		int status;
		try {
			status = model.buscarPalabra(view.getFichero1().getText(), view.getPalabra().getText(), view.getPrimera().isSelected());
			if(status != -1) {
				view.getTextArea().setText("La palabra: "+view.getPalabra().getText()+" ha sido encontrada en la linea"+status);
			}else{
				view.showError("La palabra "+view.getPalabra().getText()+" no ha sido encontrada");
			}
		} catch (IOException e) {
			view.showError("Ha ocurrido un error, lo lamentamos");
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
			view.getTextArea().setText("El archivo: "+view.getFichero2().getText()+" ha sido generado correctamente");
		}catch(IOException io) {
			view.showError("Ha ocurrido un error, comprueba los campos");

		}
	}
	
	private void call_guardarLibro() {
		try {
			if(view.getTextFieldTitulo().getText().length()>0 || view.getTextFieldAutor().getText().length()>0 || view.getTextFieldPubli().getText().length()>0 || view.getTextFieldEditor().getText().length()>0 || view.getTextFieldPaginas().getText().length()>0) {
				model.crearLibro(view.getTextFieldTitulo().getText(), view.getTextFieldAutor().getText(), view.getTextFieldPubli().getText(), view.getTextFieldEditor().getText(), view.getTextFieldPaginas().getText());
				view.getTextArea().setText("Libro \""+view.getTextFieldTitulo().getText()+"\" creado con �xito");
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
				s += "\t T�tulo: "+l.getTitulo()+" \n\n";
				s += "\t Autor: "+l.getAutor()+" \n\n";
				s += "\t A�o de publicaci�n: "+l.getPubli()+" \n\n";
				s += "\t Editor: "+l.getEditor()+" \n\n";
				s += "\t N� de p�ginas: "+l.getPaginas()+" \n\n";
				view.getTextArea().setText(s);
			} else {
				view.showError("Introduce un titulo");
			}
		} catch (FileNotFoundException e) {
			view.showError("No se encuentra el fichero");
		} catch (ClassNotFoundException e) {
			view.showError("Hubo un error leyendo el fichero");
		} catch (IOException e) {
			view.showError("Hubo un error leyendo el fichero");
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
				view.getTextArea().setText("No hay ning�n libro");
			}
		} catch (FileNotFoundException e) {
			view.showError("Hubo un error leyendo los ficheros");
		} catch (ClassNotFoundException e) {
			view.showError("Hubo un error leyendo los ficheros");
		} catch (IOException e) {
			view.showError("Hubo un error leyendo los ficheros");
		}
	}

}