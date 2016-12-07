package outros;

import java.util.ArrayList;
import java.util.Comparator;

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
	 * Busca em profundidade em todos os vertices.
	 * @param nome_do_vertice_procurado Nome do vertice procurado
	 * @return Retorna o vertice ou null se não encontrou
	 * @Complexidade O(n+m)
	 */
	public Vertice busca_em_profundidade(String vertice_onde_fazer_a_busca, String nome_do_vertice_procurado) {
		grafo.set_all_visitados(0);
		
		return busca_em_profundidade_caminhada(grafo.pegar_vertice(vertice_onde_fazer_a_busca), nome_do_vertice_procurado);
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
	
	/**
	 * Busca em largura começando do vertice escolhido
	 * @param vertice_onde_fazer_a_busca Nome do vertice onde iniciar a busca
	 * @param nome_do_vertice_procurado Nome do vertice procurado
	 * @return Retorna o vertice ou null se não encontrou
	 * @Complexidade O(n+m)
	 */
	public Vertice busca_em_largura(String vertice_onde_fazer_a_busca, String nome_do_vertice_procurado) {
		grafo.set_all_visitados(0);
		
		return busca_em_largura_caminhada(grafo.pegar_vertice(vertice_onde_fazer_a_busca), nome_do_vertice_procurado);
	}
	
	/**
	 * Iria acabar ficando muita coisa de botar só em uma função então separei em duas, mesmo que essa não use recursão
	 * @param vertice_no_momento Vertice que esta olhando
	 * @param nome_do_vertice Nome do vertice procurado
	 * @return Vertice procurando, caso contrário null
	 * @Complexidade O(n+m)
	 */
	private Vertice busca_em_largura_caminhada(Vertice vertice_no_momento, String nome_do_vertice) {
		ArrayList<Vertice> fila_de_espera = new ArrayList<>();

		fila_de_espera.add(vertice_no_momento);
		
		while(fila_de_espera.size() != 0) {
			fila_de_espera.get(0).setVisitado(1);
			
			if(fila_de_espera.get(0).getNome().equals(nome_do_vertice))
				return fila_de_espera.get(0);

			for(int i=0; i < fila_de_espera.get(0).getLista_de_arestas().size(); i++) {
				if(fila_de_espera.get(0).getLista_de_arestas().get(i).getVertice_final().getVisitado() == 0)
					fila_de_espera.add(fila_de_espera.get(0).getLista_de_arestas().get(i).getVertice_final());
			}
			
			fila_de_espera.get(0).setVisitado(2);
			fila_de_espera.remove(0);
		}
		
		return null;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Confere se o grafo tem ciclo.
	 * @return true se tiver ciclo, false caso não
	 * @Complexidade O(n+m)
	 */
	public boolean ciclo() {
		ArrayList<Vertice> lista_de_vertices = grafo.pegar_vertices();
		grafo.set_all_visitados(0);

		for(int i=0; i < lista_de_vertices.size(); i++) {
			if(lista_de_vertices.get(i).getVisitado() == 2)
				continue;
			
			if(ciclo_caminhada(lista_de_vertices.get(i)))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Essa função apenas percorre todos os vertices mandando rodar um tipo "busca em profundidade" por um vertice que ainda não tenha percorrido. <br/>
	 * Se visitado == 0, o vertice ainda não foi visitado e por isso devemos rodar a busca para ele. <br/>
	 * Se visitado == 1, é o vertice que estamos executando busca, se você encontrar com outro vertice == 1 então teve loop. <br/>
	 * Se visitado == 2, já executamos a busca nesse vertice e após ele não tem nenhum loop.
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
	 * Descobre se o grafo é fortemente conexo.
	 * Chama fortemente_conexo_caminhada(), depois inverte o grafo e chama fortemente_conexo_caminhada().
	 * @return true se for fortemente conexo, false caso contrário
	 * @Complexidade O(n+m)
	 */
	public boolean fortemente_conexo(String vertice_a_verificar) {
		ArrayList<Vertice> lista_de_vertices = grafo.pegar_vertices();
		Vertice vertice = grafo.pegar_vertice(vertice_a_verificar);
		grafo.set_all_visitados(0);
		
		fortemente_conexo_caminhada(vertice);
		
		for(int i=0; i < lista_de_vertices.size(); i++) {
			if(lista_de_vertices.get(i).getVisitado() == 0)
				return false;
		}
		
		Grafo grafo_invertido = grafo.inverter();
		lista_de_vertices = grafo_invertido.pegar_vertices();
		grafo_invertido.set_all_visitados(0);

		fortemente_conexo_caminhada(vertice);
		
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
	private void fortemente_conexo_caminhada(Vertice vertice_no_momento) {
		ArrayList<Aresta> lista_de_arestas = vertice_no_momento.getLista_de_arestas();
		
		vertice_no_momento.setVisitado(1);
		
		for(int i=0; i < lista_de_arestas.size(); i++) {
			if(lista_de_arestas.get(i).getVertice_final().getVisitado() == 0)
				fortemente_conexo_caminhada(lista_de_arestas.get(i).getVertice_final());
		}

		vertice_no_momento.setVisitado(2);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Indiferente se o grafo for direcionado ou não, vai retornar um não direcionado.
	 * @return Um grafo não direcionado com as arestas de menor custo sem fazer ciclo
	 * @Complexidade O(nm+m^2)
	 */
	public Grafo kruskal() {
		Grafo new_grafo = new Grafo();
		ArrayList<Aresta> lista_de_arestas = grafo.pegar_arestas();
		
		// O(n)
		for(int i=0; i < grafo.pegar_vertices().size(); i++) {
			new_grafo.add_vertice(grafo.pegar_vertices().get(i).getNome());
			new_grafo.pegar_vertices().get(i).setVisitado(i);
		}
		
		// Depende do algoritmo de ordenação, provavel que seja O(mlogm)
		lista_de_arestas.sort(new Comparator<Aresta>() {

			@Override
			public int compare(Aresta aresta1, Aresta aresta2) {
				return aresta1.getTamanho() - aresta2.getTamanho();
			}
			
		});
		
		// O(m(n+m))
		for(int i=0, j=0; i < lista_de_arestas.size() && j < new_grafo.pegar_vertices().size(); i++) {
			
			// O(n)
			Vertice vertice1 = new_grafo.pegar_vertice(lista_de_arestas.get(i).getVertice_inicial().getNome());
			Vertice vertice2 = new_grafo.pegar_vertice(lista_de_arestas.get(i).getVertice_final().getNome());

			if(vertice1.getVisitado() != vertice2.getVisitado()) {
				j++;
				
				new_grafo.add_aresta(
						lista_de_arestas.get(i).getVertice_inicial().getNome(),
						lista_de_arestas.get(i).getTamanho(),
						lista_de_arestas.get(i).getVertice_final().getNome()
						);
				
				new_grafo.add_aresta(
							lista_de_arestas.get(i).getVertice_final().getNome(),
							lista_de_arestas.get(i).getTamanho(),
							lista_de_arestas.get(i).getVertice_inicial().getNome()
							);
				
				//O(n+m)
				new_grafo.set_visitado_em_profundidade(vertice1, vertice1.getVisitado());
				new_grafo.set_visitado_em_profundidade(vertice2, vertice1.getVisitado());
			}
		}
		
		return new_grafo;
	}
	
}
