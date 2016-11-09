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
	 * Essa parte recebe um vertice e procura um vertice com qual ele liga que n�o foi visitado ainda. Se o vertice que essa fun��o receber for o vertice procurado ele � retornado.
	 * @param vertice_no_momento Vertice o qual voc� esta olhando as arestas
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
	
	/**
	 * Essa fun��o apenas percorre todos os vertices mandando roda um tipo "busca binaria" por um vertice que ainda n�o tenha percorrido
	 * @return true se tiver ciclo no grafo, false caso n�o
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
	 * Se visitado == 0, o vertice ainda n�o foi visitado e por isso devemos rodar a busca para ele.
	 * <br/>Se visitado == 1, � o vertice que estamos executando busca, se voc� encontrar com outro vertice == 1 ent�o teve loop.
	 * <br/>Se visitado == 2, j� executamos a busca nesse vertice e ap�s ele n�o tem nenhum loop.
	 * <br/>Notar que caso o grafo seja n�o direcionado, sempre vai ter ciclo pois uma aresta pode servir para ir e voltar.
	 * @param vertice_no_momento Vertice pelo qual voc� vai olhar as arestas
	 * @return true se voltou a um vertice atr�ves dessa busca, false caso n�o
	 * @Complexidade O(n+m)
	 */
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