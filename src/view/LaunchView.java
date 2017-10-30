package view;

import java.awt.Dimension;

import javax.swing.*;
import java.awt.FlowLayout;

public class LaunchView extends JFrame {

	private JButton comparar,buscar,ordenarFichero,copia;
	private JTextArea textArea;
	private JTextField fichero1,fichero2,palabra;
	private JLabel label_f1,label_f2,label_pal;
	public JTextField getTextFieldTitulo() {
		return textFieldTitulo;
	}

	public void setTextFieldTitulo(JTextField textFieldTitulo) {
		this.textFieldTitulo = textFieldTitulo;
	}

	public JTextField getTextFieldPubli() {
		return textFieldPubli;
	}

	public void setTextFieldPubli(JTextField textFieldPubli) {
		this.textFieldPubli = textFieldPubli;
	}

	public JTextField getTextFieldEditor() {
		return textFieldEditor;
	}

	public void setTextFieldEditor(JTextField textFieldEditor) {
		this.textFieldEditor = textFieldEditor;
	}

	public JTextField getTextFieldPaginas() {
		return textFieldPaginas;
	}

	public void setTextFieldPaginas(JTextField textFieldPaginas) {
		this.textFieldPaginas = textFieldPaginas;
	}

	private JCheckBox primera,tipo_orden;
	
	private JPanel panel;
	private JTextField textFieldTitulo;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField textFieldPubli;
	private JLabel lblNewLabel_3;
	private JTextField textFieldEditor;
	private JLabel lblNewLabel_4;
	private JTextField textFieldPaginas;
	private JTextField textFieldAutor;
	public JTextField getTextFieldAutor() {
		return textFieldAutor;
	}

	public void setTextFieldAutor(JTextField textFieldAutor) {
		this.textFieldAutor = textFieldAutor;
	}

	private JButton btnGuardarLibro;
	private JButton btnRecuperarLibros;
	private JButton btnEncontrarLibro;
	private JButton btnEditarPginas;
	private JLabel lblNPalabras;
	private JTextField textFieldPalabras;
	private JButton btnComprobarPalabras;
	
	public LaunchView() {
		
		setBounds(200,200,917,509);
		setTitle("Proyecto Buffers");	
		panel = new JPanel();
		
		comparar = new JButton("Comparar contenido");
		comparar.setPreferredSize(new Dimension(150, 26));
		buscar = new JButton("Buscar palabra");
		buscar.setPreferredSize(new Dimension(150, 26));
		copia = new JButton("Copiar fichero");
		copia.setPreferredSize(new Dimension(150, 26));
		
		ordenarFichero = new JButton("Ordenar fichero ");
		ordenarFichero.setPreferredSize(new Dimension(150, 26));
					
		fichero1 = new JTextField("",10);
		fichero2 = new JTextField("",10);
		palabra = new JTextField("",10);
		
		label_f1 = new JLabel("Fichero 1:");
		label_f2 = new JLabel("Fichero 2:");
		label_pal = new JLabel("Palabra:");
		
		primera = new JCheckBox("Primera aparición");
		tipo_orden = new JCheckBox("Natural - Inverso");

		textArea = new JTextArea(20, 80);
		textArea.setBounds(50,50,50,50);
		textArea.setEditable(false);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel.add(comparar);
		panel.add(buscar);
		panel.add(ordenarFichero);
		panel.add(copia);
		
		btnComprobarPalabras = new JButton("Comprobar palabras");
		panel.add(btnComprobarPalabras);
		panel.add(label_f1);
		panel.add(fichero1);
		panel.add(label_f2);
		panel.add(fichero2);
		panel.add(label_pal);
		panel.add(palabra);
		
		lblNPalabras = new JLabel("Longitud Palabra");
		panel.add(lblNPalabras);
		
		textFieldPalabras = new JTextField();
		panel.add(textFieldPalabras);
		textFieldPalabras.setColumns(10);
		panel.add(primera);
		panel.add(tipo_orden);
		panel.add(textArea);
		
        // AÃ±adimos el JPanel al JFrame
        this.getContentPane().add(panel);		
        
        lblNewLabel = new JLabel("T\u00EDtulo");
        panel.add(lblNewLabel);
        
        textFieldTitulo = new JTextField();
        panel.add(textFieldTitulo);
        textFieldTitulo.setColumns(10);
        
        lblNewLabel_1 = new JLabel("Autor");
        panel.add(lblNewLabel_1);
        
        textFieldAutor = new JTextField();
        panel.add(textFieldAutor);
        textFieldAutor.setColumns(10);
        
        lblNewLabel_2 = new JLabel("A\u00F1o de publicaci\u00F3n");
        panel.add(lblNewLabel_2);
        
        textFieldPubli = new JTextField();
        panel.add(textFieldPubli);
        textFieldPubli.setColumns(10);
        
        lblNewLabel_3 = new JLabel("Editor");
        panel.add(lblNewLabel_3);
        
        textFieldEditor = new JTextField();
        panel.add(textFieldEditor);
        textFieldEditor.setColumns(10);
        
        lblNewLabel_4 = new JLabel("N\u00BA p\u00E1ginas");
        panel.add(lblNewLabel_4);
        
        textFieldPaginas = new JTextField();
        panel.add(textFieldPaginas);
        textFieldPaginas.setColumns(10);
        
        btnGuardarLibro = new JButton("Guardar libro");
        panel.add(btnGuardarLibro);
        
        btnRecuperarLibros = new JButton("Recuperar libros");
        panel.add(btnRecuperarLibros);
        
        btnEncontrarLibro = new JButton("Encontrar libro");
        panel.add(btnEncontrarLibro);
        
        btnEditarPginas = new JButton("Editar p\u00E1ginas");
        panel.add(btnEditarPginas);
		
	}	
	
	public JTextField getTextFieldPalabras() {
		return textFieldPalabras;
	}

	public void setTextFieldPalabras(JTextField textFieldPalabras) {
		this.textFieldPalabras = textFieldPalabras;
	}

	public JButton getBtnComprobarPalabras() {
		return btnComprobarPalabras;
	}

	public void setBtnComprobarPalabras(JButton btnComprobarPalabras) {
		this.btnComprobarPalabras = btnComprobarPalabras;
	}

	public JButton getBtnEditarPginas() {
		return btnEditarPginas;
	}

	public void setBtnEditarPginas(JButton btnEditarPginas) {
		this.btnEditarPginas = btnEditarPginas;
	}

	public JButton getBtnGuardarLibro() {
		return btnGuardarLibro;
	}

	public void setBtnGuardarLibro(JButton btnGuardarLibro) {
		this.btnGuardarLibro = btnGuardarLibro;
	}

	public JButton getBtnRecuperarLibros() {
		return btnRecuperarLibros;
	}

	public void setBtnRecuperarLibros(JButton btnRecuperarLibros) {
		this.btnRecuperarLibros = btnRecuperarLibros;
	}

	public JButton getBtnEncontrarLibro() {
		return btnEncontrarLibro;
	}

	public void setBtnEncontrarLibro(JButton btnEncontrarLibro) {
		this.btnEncontrarLibro = btnEncontrarLibro;
	}

	public JButton getComparar() {
		return comparar;
	}

	public void setComparar(JButton comparar) {
		this.comparar = comparar;
	}

	public JButton getBuscar() {
		return buscar;
	}

	public void setBuscar(JButton buscar) {
		this.buscar = buscar;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
			
	public JTextField getFichero1() {
		return fichero1;
	}

	public void setFichero1(JTextField fichero1) {
		this.fichero1 = fichero1;
	}

	public JTextField getFichero2() {
		return fichero2;
	}

	public void setFichero2(JTextField fichero2) {
		this.fichero2 = fichero2;
	}

	public void showError(String m){
		JOptionPane.showMessageDialog(this.panel,
			    m,
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
	}

	public JTextField getPalabra() {
		return palabra;
	}

	public void setPalabra(JTextField palabra) {
		this.palabra = palabra;
	}

	public JCheckBox getPrimera() {
		return primera;
	}

	public void setPrimera(JCheckBox primera) {
		this.primera = primera;
	}

	public JButton getOrdenarFichero() {
		return ordenarFichero;
	}

	public void setOrdenarFichero(JButton ordenarFichero) {
		this.ordenarFichero = ordenarFichero;
	}

	public JCheckBox getTipo_orden() {
		return tipo_orden;
	}

	public void setTipo_orden(JCheckBox tipo_orden) {
		this.tipo_orden = tipo_orden;
	}

	public JButton getCopia() {
		return copia;
	}

	public void setCopia(JButton copia) {
		this.copia = copia;
	}
	
	

	

}
