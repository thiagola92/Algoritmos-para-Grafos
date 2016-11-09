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
			Vertice resposta = busca_em_profundidade_caminhada(lista_de_vertices.get(i), nome_do_vertice);
			if(resposta != null)
				return resposta;
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
		
		return null;
	}
	
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
	
	private boolean ciclo_caminhada(Vertice vertice_no_momento) {
		ArrayList<Aresta> lista_de_arestas = vertice_no_momento.getLista_de_arestas();
		
		vertice_no_momento.setVisitado(1);
		
		for(int i=0; i < lista_de_arestas.size(); i++) {
			
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
}
