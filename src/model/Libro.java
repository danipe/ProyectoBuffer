package model;

public class Libro implements java.io.Serializable{

	private String titulo, autor, publi, editor, paginas;
	private int id;
	
	public Libro(String titulo, String autor, String publi, String editor, String paginas) {
		this.titulo=titulo;
		this.autor=autor;
		this.publi=publi;
		this.editor=editor;
		this.paginas=paginas;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getPubli() {
		return publi;
	}

	public void setPubli(String publi) {
		this.publi = publi;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getPaginas() {
		return paginas;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}
	
	
}
