package outros;

public class Aresta {
	
	private String vertice_incial;
	private int tamanho;
	private String vertice_final;
	
	public Aresta(String v_i, int t, String v_f) {
		vertice_incial = v_i;
		tamanho = t;
		vertice_final = v_f;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getVertice_incial() {
		return vertice_incial;
	}
	public int getTamanho() {
		return tamanho;
	}
	public String getVertice_final() {
		return vertice_final;
	}

}
