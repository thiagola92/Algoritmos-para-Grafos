package outros;

import java.util.ArrayList;

public class Vertice {
	
	private String nome;
	private int grau_de_entrada;
	private int grau_de_saida;
	private int visitado;
	
	private Grafo grafo;
	private ArrayList<Aresta> lista_de_arestas;
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Um objeto para representar um Vertice
	 * @param n Nome do Vertice, todo Vertice tem um nome
	 */
	public Vertice(String n, Grafo g) {
		grafo = g;
		lista_de_arestas = new ArrayList<>();
		nome = n;
		grau_de_entrada = 0;
		grau_de_saida = 0;
		visitado = 0;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Printa em uma linha todas as arestas desse vertice, o grau de saida e o grau de entrada.
	 * @return String com todas arestas naquele vertice
	 * @Complexidade O(m)
	 */
	public void print_vertice() {
		
		System.out.print("| " + nome + " | --> | ");
		
		for(int i=0; i < lista_de_arestas.size(); i++) {
			System.out.print(lista_de_arestas.get(i).getVertice_inicial().getNome());
			System.out.print("--");
			System.out.print(lista_de_arestas.get(i).getTamanho());
			System.out.print("--");
			System.out.print(lista_de_arestas.get(i).getVertice_final().getNome());
			System.out.print(" | ");
		}
		
		System.out.print("null |");
		System.out.print(" Grau de saida: " + grau_de_saida);
		System.out.print(" Grau de entrada: " + grau_de_entrada);
		
		System.out.println("");
	}
	
	/**
	 * Adiciona uma Aresta ao vertice, se MULTIPLOS_CAMINHOS for false então você não quer varios caminhos saindo do vertice X e levando para o vertice Y. 
	 * @param tamanho_da_aresta Tamanho da Aresta
	 * @param vertice_para_onde_leva Vertice para a qual ela leva
	 * @return true se a Aresta foi adicionada e false caso não
	 * @Complexidade O(m)
	 */
	public boolean add_aresta(int tamanho_da_aresta, Vertice vertice_para_onde_leva) {
		
		
		if(!grafo.ARESTAS_PARALELAS) {
			for(int i=0; i < lista_de_arestas.size(); i++) {
				
				if(lista_de_arestas.get(i).getVertice_final() == vertice_para_onde_leva)
					return false;
			
			}
		}
		
		lista_de_arestas.add(new Aresta(this, tamanho_da_aresta, vertice_para_onde_leva));
		
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

	public int getVisitado() {
		return visitado;
	}

	public void setVisitado(int visitado) {
		this.visitado = visitado;
	}

	public ArrayList<Aresta> getLista_de_arestas() {
		return lista_de_arestas;
	}
	
}
