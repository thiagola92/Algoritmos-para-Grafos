package outros;

import java.util.ArrayList;

public class Algoritmos_com_grafo {

	private Grafo grafo;
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * O grafo sobre o qual usaremos os algoritmos
	 * @param g	Grafo usado
	 */
	public Algoritmos_com_grafo(Grafo g) {
		grafo = g;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Busca em profundidade, essa parte apenas se reponsabiliza de zerar a variavel visitados e mandar cada vertice do grafo para a busca_em_profundidade_caminho(...)
	 * @param nome_do_vertice Nome do Vertice procurado
	 * @return Retorna o Vertice
	 * @Complexidade O(n+m)
	 */
	public Vertice busca_em_profundidade(String nome_do_vertice) {
		ArrayList<Vertice> lista_de_vertices = grafo.getLista_de_vertices();
		grafo.zerar_visitados();
		
		for(int i=0; i < lista_de_vertices.size(); i++) {
			if(lista_de_vertices.get(i).getVisitado() == 0) {
				Vertice resposta = busca_em_profundidade_caminhada(lista_de_vertices.get(i), nome_do_vertice);
				if(resposta != null)
					return resposta;
			}
		}
		
		return null;
	}
	
	/**
	 * Essa parte recebe um vertice e procura um vertice com qual ele liga que não foi visitado ainda. Se o vertice que essa função receber for o vertice procurado ele é retornado.
	 * @param vertice_no_momento Vertice o qual você esta olhando as arestas
	 * @param nome_do_vertice Nome do Vertice procurado
	 * @return Retorna o Vertice
	 * @Complexidade O(n+m)
	 */
	private Vertice busca_em_profundidade_caminhada(Vertice vertice_no_momento, String nome_do_vertice) {
		ArrayList<Aresta> lista_de_arestas = vertice_no_momento.getLista_de_arestas();
		
		vertice_no_momento.setVisitado(1);
		
		if(vertice_no_momento.getNome().equals(nome_do_vertice))
			return vertice_no_momento;
		
		for(int i=0; i < lista_de_arestas.size(); i++) {
			
			if(lista_de_arestas.get(i).getVertice_final().getVisitado() == 0) {
				Vertice resposta = busca_em_profundidade_caminhada(lista_de_arestas.get(i).getVertice_final(), nome_do_vertice);
				if(resposta != null)
					return resposta;
			}
		}
		
		vertice_no_momento.setVisitado(2);
		
		return null;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// TODO busca_em_largura
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Essa função apenas percorre todos os vertices mandando roda um tipo "busca binaria" por um vertice que ainda não tenha percorrido
	 * @return true se tiver ciclo no grafo, false caso não
	 */
	public boolean ciclo() {
		ArrayList<Vertice> lista_de_vertices = grafo.getLista_de_vertices();
		grafo.zerar_visitados();

		for(int i=0; i < lista_de_vertices.size(); i++) {
			if(lista_de_vertices.get(i).getVisitado() == 2)
				continue;
			
			if(ciclo_caminhada(lista_de_vertices.get(i)))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Se visitado == 0, o vertice ainda não foi visitado e por isso devemos rodar a busca para ele.
	 * <br/>Se visitado == 1, é o vertice que estamos executando busca, se você encontrar com outro vertice == 1 então teve loop.
	 * <br/>Se visitado == 2, já executamos a busca nesse vertice e após ele não tem nenhum loop.
	 * @param vertice_no_momento Vertice pelo qual você vai olhar as arestas
	 * @return true se voltou a um vertice atráves dessa busca, false caso não
	 * @Complexidade O(n+m)
	 */
	private boolean ciclo_caminhada(Vertice vertice_no_momento) {
		ArrayList<Aresta> lista_de_arestas = vertice_no_momento.getLista_de_arestas();
		
		vertice_no_momento.setVisitado(1);
		
		for(int i=0; i < lista_de_arestas.size(); i++) {
			
			if(grafo.DIRECIONADO == false && lista_de_arestas.get(i).getVertice_final() == vertice_no_momento)
				continue;
			
			if(lista_de_arestas.get(i).getVertice_final().getVisitado() == 0) {
				if(ciclo_caminhada(lista_de_arestas.get(i).getVertice_final()))
					return true;
			} else if(lista_de_arestas.get(i).getVertice_final().getVisitado() == 1) {
				return true;
			}
		}
		
		vertice_no_momento.setVisitado(2);
		
		return false;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Pega o primeiro vetor e manda visitar os vertices próximos, esses vertices fazem o mesmo. Se após fizer isso todos os vertices tiverem visitados então é conexo
	 * @return true se um vertice desse grafo chegar a todos os outros e todos os outros chegarem a ele, false caso contrario
	 * @Complexidade O(n+m)
	 */
	public boolean conexo() {
		ArrayList<Vertice> lista_de_vertices = grafo.getLista_de_vertices();
		grafo.zerar_visitados();
		
		conexo_caminhada(lista_de_vertices.get(0));
		
		for(int i=0; i < lista_de_vertices.size(); i++) {
			if(lista_de_vertices.get(i).getVisitado() == 0)
				return false;
		}
		
		Grafo grafo_invertido = grafo.inverter();
		lista_de_vertices = grafo_invertido.getLista_de_vertices();
		grafo_invertido.zerar_visitados();

		conexo_caminhada(lista_de_vertices.get(0));
		
		for(int i=0; i < lista_de_vertices.size(); i++) {
			if(lista_de_vertices.get(i).getVisitado() == 0)
				return false;
		}
		
		return true;
	}
	
	/**
	 * Marca todos os vertices ligados como visitados e faz eles fazerem o mesmo.
	 * @param vertice_no_momento
	 * @Complexidade O(n+m)
	 */
	private void conexo_caminhada(Vertice vertice_no_momento) {
		ArrayList<Aresta> lista_de_arestas = vertice_no_momento.getLista_de_arestas();
		
		vertice_no_momento.setVisitado(1);
		
		for(int i=0; i < lista_de_arestas.size(); i++) {
			if(lista_de_arestas.get(i).getVertice_final().getVisitado() == 0)
				conexo_caminhada(lista_de_arestas.get(i).getVertice_final());
		}

		vertice_no_momento.setVisitado(2);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////Exercicios da Lista/////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean Ex1(String nome_do_vertice1, String nome_do_vertice2) {
		Vertice vertice1 = grafo.pegar_vertice(nome_do_vertice1);
		Vertice vertice2 = grafo.pegar_vertice(nome_do_vertice2);

		grafo.zerar_visitados();
		
		for(int i=0; i < vertice2.getLista_de_arestas().size(); i++) {
			
			if(vertice2.getLista_de_arestas().get(i).getVertice_final() == vertice1)
				continue;
			
			if(busca_em_profundidade_caminhada(vertice2.getLista_de_arestas().get(i).getVertice_final(), nome_do_vertice1) != null)
				return true;
			
		}
		
		return false;
	}
	
}
