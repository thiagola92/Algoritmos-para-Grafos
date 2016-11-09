package outros;

import java.util.ArrayList;

public class Vertice {
	
	private boolean MULTIPLOS_CAMINHOS = false; // Se você não quiser que tenha duas ou mais arestas levando ao mesmo local só que com tamanhos diferentes
	
	private String nome;
	private int grau_de_entrada;
	private int grau_de_saida;
	
	private ArrayList<Aresta> lista_de_arestas;
	
	/**
	 * Um objeto para representar um Vertice
	 * @param n Nome do Vertice, todo Vertice tem um nome
	 */
	public Vertice(String n) {
		lista_de_arestas = new ArrayList<>();
		nome = n;
		grau_de_entrada = 0;
		grau_de_saida = 0;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Compacta todas arestas daquele vertice em uma String.
	 * @return String com todas arestas naquele vertice
	 */
	public String print_vertice() {
		StringBuffer retorno = new StringBuffer("");
		
		for(int i=0; i < lista_de_arestas.size(); i++) {
			retorno.append(lista_de_arestas.get(i).getVertice_incial());
			retorno.append("--");
			retorno.append(lista_de_arestas.get(i).getTamanho());
			retorno.append("--");
			retorno.append(lista_de_arestas.get(i).getVertice_final());
			retorno.append(" | ");
		}
		
		retorno.append("null |");
		return retorno.toString();
	}
	
	/**
	 * Adiciona uma Aresta ao vertice, se MULTIPLOS_CAMINHOS for false então você não quer varios caminhos saindo do vertice X e levando para o vertice Y. 
	 * @param tamanho_da_aresta Tamanho da Aresta
	 * @param vertice_para_onde_leva Vertice para a qual ela leva
	 * @return true se a Aresta foi adicionada e false caso não
	 */
	public boolean add_aresta(int tamanho_da_aresta, String vertice_para_onde_leva) {
		
		
		if(!MULTIPLOS_CAMINHOS) {
			for(int i=0; i < lista_de_arestas.size(); i++) {
				
				if(lista_de_arestas.get(i).getVertice_final().equals(vertice_para_onde_leva))
					return false;
			
			}
		}
		
		lista_de_arestas.add(new Aresta(nome, tamanho_da_aresta, vertice_para_onde_leva));
		
		return true;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getNome() {
		return nome;
	}

	public int getGrau_de_entrada() {
		return grau_de_entrada;
	}

	public void setGrau_de_entrada(int grau_de_entrada) {
		this.grau_de_entrada = grau_de_entrada;
	}

	public int getGrau_de_saida() {
		return grau_de_saida;
	}

	public void setGrau_de_saida(int grau_de_saida) {
		this.grau_de_saida = grau_de_saida;
	}
	
}
