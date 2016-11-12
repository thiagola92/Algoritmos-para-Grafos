package outros;

public class Main {

	public static void main(String[] args) {
		Grafo g = new Grafo();
		Algoritmos_com_grafo ag = new Algoritmos_com_grafo(g);
		
		//g.DIRECIONADO= false;
		
		g.add_vertice("Ver1");
		g.add_vertice("Ver2");
		g.add_vertice("Ver3");
		g.add_vertice("Ver4");
		
		g.add_aresta("Ver1", 0, "Ver2");
		g.add_aresta("Ver2", 0, "Ver3");
		g.add_aresta("Ver3", 0, "Ver4");
		g.add_aresta("Ver4", 0, "Ver1");
		
		g.print_grafo();

		
		Grafo g_invertido;
		g_invertido = g.inverter();
		g_invertido.print_grafo();
		
		if(ag.busca_em_profundidade("Ver1", "Ver4") == null)
			System.out.println("Nao encontrou");
		else
			ag.busca_em_profundidade("Ver1", "Ver4").print_vertice();
		
		if(ag.ciclo())
			System.out.println("Tem ciclo");
		else
			System.out.println("Nao tem ciclo");
		
		if(ag.fortemente_conexo("Ver1"))
			System.out.println("Chega a todos os vertices e todos os vertices chegam a ele (conexo)");
		else
			System.out.println("Nao é fortemente conexo");
		
		if(ag.busca_em_largura("Ver1", "Ver4") == null)
			System.out.println("Nao encontrou");
		else
			ag.busca_em_largura("Ver1", "Ver4").print_vertice();
		
		
		//System.out.println(ag.Ex1("Ver1", "Ver2"));
		//System.out.println(ag.Ex2("Ver2"));
	}

}
