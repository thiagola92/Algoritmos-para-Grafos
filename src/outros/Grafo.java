package outros;

import java.util.ArrayList;
import java.util.Random;

public class Grafo {
	
	private boolean DIRECIONADO = false;	// Direcionado, a aresta usada para ir não pode ser usada para voltar, encontre outra aresta.
	private boolean LACOS = false;			// Laços, se um vertice pode ligar a ele mesmo usando uma aresta.
	
	private int ARESTAS_TAM_MIN = 1;		// Tamanho mínimo das arestas aleatórias (você ainda pode acrescentar arestas de qualquer tamanho)
	private int ARESTAS_TAM_MAX = 20;		// Tamanho máximo das arestas aleatórias (você ainda pode acrescentar arestas de qualquer tamanho)
	
	private String PRE_FIX = "Ver";			// Prefixo dos nomes dos vertices criados aleatoriamente
	
	private ArrayList<Vertice> lista_de_vertices;
	
	/**
	 * Um objeto que simula um grafo.
	 */
	public Grafo() {
		lista_de_vertices = new ArrayList<>();
	}

	/**
	 * Um objeto que simula um grafo.
	 * @param numero_de_vertices Número de vertices que o grafo vai ter, esses vertices seram escolhidos aleatóriamente. Você ainda pode adicionar vertices normalmente após usar esse constructor.
	 */
	public Grafo(int numero_de_vertices) {
		lista_de_vertices = new ArrayList<>(numero_de_vertices);
		
		for(int i=0; i < numero_de_vertices; i++)
			add_vertice(PRE_FIX + i);
		
		Random r = new Random();
		
		for(int i=0; i < numero_de_vertices; i++) {
			
			int random = r.nextInt(numero_de_vertices);
			for(int j=0; j < random; j++)
				add_aresta(PRE_FIX + i, r.nextInt(ARESTAS_TAM_MAX - ARESTAS_TAM_MIN + 1) + ARESTAS_TAM_MIN, PRE_FIX + random);
			
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Printa o grafo no console.
	 */
	public void print_grafo() {
		Vertice v;
		String s;
		
		for(int i=0; lista_de_vertices != null && i < lista_de_vertices.size(); i++) {
			
			v = lista_de_vertices.get(i);
			s = "----" + new String(new char[v.getNome().length()]).replace("\0", "-");
			
			System.out.println(s);
			System.out.println("| " + v.getNome() + " | --> | " + v.print_vertice());
			System.out.println(s);
		}
		
		System.out.println("---------------------------------------------------------");
	}
	
	/**
	 * Adiciona um vertice ao grafo, sendo que esse vertice não vai ter o mesmo nome que os outros.
	 * @param nome Nome do vertice.
	 * @return true se o vertice foi adiciona com sucesso, false caso não.
	 */
	public boolean add_vertice(String nome) {
		
		for(int i=0; lista_de_vertices != null && i < lista_de_vertices.size(); i++) {
			
			if(lista_de_vertices.get(i).getNome().equals(nome))
				return false;
			
		}

		lista_de_vertices.add(new Vertice(nome));
		
		return true;
	}
	
	/**
	 * Adiciona uma aresta ao grafo, apenas se existir aquele Vertice inicial e final.
	 * @param vertice_inicial Vertice de onde a Aresta sai
	 * @param tamanho Tamanho da aresta
	 * @param vertice_final Vertice aonde a Aresta chega
	 * @return true se for adicionada com sucesso, caso contrário false
	 */
	public boolean add_aresta(String vertice_inicial, int tamanho, String vertice_final) {
		
		boolean vertice_inicial_existe = false;
		boolean vertice_final_existe = false;
		
		Vertice v = null;
		Vertice v2 = null;
		
		if(!LACOS) {
			if(vertice_inicial.equals(vertice_final))
				return false;
		}
		
		for(int i=0; lista_de_vertices != null && i < lista_de_vertices.size(); i++) {
			
			if(lista_de_vertices.get(i).getNome().equals(vertice_inicial)) {
				v = lista_de_vertices.get(i);
				vertice_inicial_existe = true;
			}
			
			if(lista_de_vertices.get(i).getNome().equals(vertice_final)) {
				v2 = lista_de_vertices.get(i);
				vertice_final_existe = true;
			}
			
		}
		
		if(vertice_inicial_existe && vertice_final_existe) {
			
			v.add_aresta(tamanho, vertice_final);
			
			if(!DIRECIONADO)
				v2.add_aresta(tamanho, vertice_inicial);
			
			return true;
		}
		
		return false;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
