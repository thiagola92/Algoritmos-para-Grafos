package outros;

import java.util.ArrayList;
import java.util.Random;

public class Grafo {
	
	public boolean DIRECIONADO = true;	// Direcionado, a aresta usada para ir não pode ser usada para voltar, encontre outra aresta.
	public boolean LACOS = false;			// Laços, se um vertice pode ligar a ele mesmo usando uma aresta.
	public boolean ARESTAS_PARALELAS = false; // Se você não quiser que tenha duas ou mais arestas levando ao mesmo local só que com tamanhos diferentes
	
	public int ARESTAS_TAM_MIN = 1;		// Tamanho mínimo das arestas aleatórias (você ainda pode acrescentar arestas de qualquer tamanho)
	public int ARESTAS_TAM_MAX = 20;		// Tamanho máximo das arestas aleatórias (você ainda pode acrescentar arestas de qualquer tamanho)
	
	public String PRE_FIX = "Ver";			// Prefixo dos nomes dos vertices criados aleatoriamente
	
	private ArrayList<Vertice> lista_de_vertices;

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Um objeto que simula um grafo.
	 */
	public Grafo() {
		lista_de_vertices = new ArrayList<>();
	}

	/**
	 * Um objeto que simula um grafo.
	 * @param numero_de_vertices Número de vertices que o grafo vai ter, esses vertices seram escolhidos aleatóriamente. Você ainda pode adicionar vertices normalmente após usar esse constructor.
	 * @Complexidade O(n^2)
	 */
	public Grafo(int numero_de_vertices) {
		lista_de_vertices = new ArrayList<>(numero_de_vertices);
		
		for(int i=0; i < numero_de_vertices; i++)
			add_vertice(PRE_FIX + i);
		
		Random r = new Random();
		
		for(int i=0; i < numero_de_vertices; i++) {
			
			int random = r.nextInt(numero_de_vertices);
			for(int j=0; j < random; j++)
				add_aresta(PRE_FIX + i, r.nextInt(ARESTAS_TAM_MAX - ARESTAS_TAM_MIN + 1) + ARESTAS_TAM_MIN, PRE_FIX + r.nextInt(numero_de_vertices));
			
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Printa o grafo no console.
	 * @Complexidade O(n+m)
	 */
	public void print_grafo() {
		Vertice v;
		String s;
		
		for(int i=0; i < lista_de_vertices.size(); i++) {
			
			v = lista_de_vertices.get(i);
			s = "----" + new String(new char[v.getNome().length()]).replace("\0", "-");
			
			System.out.println(s);
			v.print_vertice();
			System.out.println(s);
		}
		
		System.out.println("---------------------------------------------------------");
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Adiciona um vertice ao grafo, sendo que esse vertice não vai ter o mesmo nome que os outros.
	 * @param nome Nome do vertice.
	 * @return true se o vertice foi adiciona com sucesso, false caso não.
	 * @Complexidade O(n)
	 */
	public boolean add_vertice(String nome) {
		
		if(pegar_vertice(nome) != null)
			return false;

		lista_de_vertices.add(new Vertice(nome, this));
		
		return true;
	}
	
	/**
	 * Adiciona uma aresta ao grafo, apenas se existir aquele Vertice inicial e final.
	 * @param vertice_inicial Vertice de onde a Aresta sai
	 * @param tamanho Tamanho da aresta
	 * @param vertice_final Vertice aonde a Aresta chega
	 * @return true se for adicionada com sucesso, caso contrário false
	 * @Complexidade O(n)
	 */
	public boolean add_aresta(String vertice_inicial, int tamanho, String vertice_final) {
		
		boolean vertice_inicial_existe = false;
		boolean vertice_final_existe = false;
		
		Vertice v_inicial = null;
		Vertice v_final = null;
		
		if(!LACOS) {
			if(vertice_inicial.equals(vertice_final))
				return false;
		}
		
		for(int i=0; i < lista_de_vertices.size() && (!vertice_inicial_existe || !vertice_final_existe); i++) {
			
			if(lista_de_vertices.get(i).getNome().equals(vertice_inicial)) {
				v_inicial = lista_de_vertices.get(i);
				vertice_inicial_existe = true;
			}
			
			if(lista_de_vertices.get(i).getNome().equals(vertice_final)) {
				v_final = lista_de_vertices.get(i);
				vertice_final_existe = true;
			}
			
		}
		
		if(vertice_inicial_existe && vertice_final_existe) {
			
			if(v_inicial.add_aresta(tamanho, v_final)) {
				v_inicial.setGrau_de_saida(v_inicial.getGrau_de_saida() + 1);
				v_final.setGrau_de_entrada(v_final.getGrau_de_entrada() + 1);
			}
			
			if(!DIRECIONADO) {
				if(v_final.add_aresta(tamanho, v_inicial)) {
					v_final.setGrau_de_saida(v_final.getGrau_de_saida() + 1);
					v_inicial.setGrau_de_entrada(v_inicial.getGrau_de_entrada() + 1);
				}
			}
			
			return true;
		}
		
		return false;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Como parece que vou precisar pegar um vertice em especial em outras funções, melhor já fazer uma função para isso.
	 * @param nome Nome do vertice que você quer pegar
	 * @return Retorna o vertice ou null se não existir
	 * @Complexidade O(n)
	 */
	public Vertice pegar_vertice(String nome) {
		
		for(int i=0; i < lista_de_vertices.size(); i++) {
			
			if(lista_de_vertices.get(i).getNome().equals(nome))
				return lista_de_vertices.get(i);
			
		}
		
		return null;
	}
	
	/**
	 * Tornar a variável "visitado" de todos os vertices 0.
	 * @Complexidade O(n)
	 */
	public void zerar_visitados() {
		for(int i=0; i < lista_de_vertices.size(); i++) {
			lista_de_vertices.get(i).setVisitado(0);
		}
	}
	
	/**
	 * Percorre esse grafo, ao mesmo tempo que cria um invertido.
	 * @return Grafo invertido
	 * @Complexidade O(n+m)
	 */
	public Grafo inverter() {
		Grafo grafo_invertido = new Grafo();
		
		for(int i=0; i < lista_de_vertices.size(); i++) {
			grafo_invertido.add_vertice(lista_de_vertices.get(i).getNome());
		}

		for(int i=0; i < lista_de_vertices.size(); i++) {

			for(int j=0; j < lista_de_vertices.get(i).getLista_de_arestas().size(); j++) {
				grafo_invertido.add_aresta(lista_de_vertices.get(i).getLista_de_arestas().get(j).getVertice_final().getNome(),
						lista_de_vertices.get(i).getLista_de_arestas().get(j).getTamanho(),
						lista_de_vertices.get(i).getLista_de_arestas().get(j).getVertice_inicial().getNome());
			}
			
		}
		
		return grafo_invertido;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public ArrayList<Vertice> getLista_de_vertices() {
		return lista_de_vertices;
	}
	
}
