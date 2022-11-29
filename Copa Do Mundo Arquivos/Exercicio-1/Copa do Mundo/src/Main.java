import java.util.Vector;

public class Main {

    //===MAIN===
    public static void main(String[] args) {
        MyIO.setCharset("UTF-8"); //Muda o charset da aplicação para Português

        Vector<Jogo> vetorJogos = new Vector<Jogo>(); //cria vetor de Objeto Jogo

        while(true){ //Lê a entrada enquanto != FIM
            String linha = MyIO.readLine();
            if(linha.equals("FIM")){
                break;
            }
            criaJogo(vetorJogos, linha); //chama construtor do vetor de jogos
        }

        int numBuscas = MyIO.readInt(); //  numero de buscas feitas
        // faz a leitura dos jogos a serem buscados, o valor numBuscas
        for (int i = 0; i < numBuscas; i++) {
            String linha = MyIO.readLine();
            imprimeBusca(vetorJogos, linha);
        }

    }

    private static void criaJogo(Vector<Jogo> vetorJogos, String linha){

        String[] eachElement = linha.split("#"); //separa cada elemento da entrada padrão por #
        Jogo novoJogo = new Jogo(
                Integer.parseInt(eachElement[0]), //ano
                eachElement[1],//etapa
                Integer.parseInt(eachElement[2]), //dia
                Integer.parseInt(eachElement[3]), //mes
                eachElement[4], //seleção1
                Integer.parseInt(eachElement[5]), //placar seleção1
                Integer.parseInt(eachElement[6]), //placar seleção2
                eachElement[7], //seleção2
                eachElement[8] //local
        );
        vetorJogos.add(novoJogo); // adiciona novo jogo ao fim do vetor

    }

    private static void imprimeBusca(Vector<Jogo> vetorJogos, String busca) { //de acordo com a saída padrão
        int dia, mes, ano;

        String[] textoSeparado = busca.split(";"); //separa a saída padrão em dia, mes, ano e uma das seleções que disputou a partida
        String pais = textoSeparado[1]; //um dos times
        textoSeparado = textoSeparado[0].split("/"); //separa a primeira parte em 3 (dia / mes / ano)

        dia = Integer.valueOf(textoSeparado[0]);
        mes = Integer.valueOf(textoSeparado[1]);
        ano = Integer.valueOf(textoSeparado[2]);

        imprimeBusca(vetorJogos, pais, dia, mes, ano);
    }

    private static void imprimeBusca(Vector<Jogo> vetorJogos, String pais, int dia, int mes, int ano) { //método para procurar os time no vetor e imprimí-los
        for (int i = 0; i < vetorJogos.size(); i++)
            if (dia == vetorJogos.get(i).getDia() && mes == vetorJogos.get(i).getMes() && ano == vetorJogos.get(i).getAno() && pais.equals(vetorJogos.get(i).getSelecao1()))
                toString(vetorJogos.get(i));
    }

    private static void toString(Jogo j) { //método toString para imprimir de acordo com a saída padrão
        System.out.println("[Copa " + j.getAno() + "] [" + j.getEtapa() + "] [" + j.getDia() + "/" + j.getMes() + "] [" + j.getSelecao1() + " (" + j.getPlacarSelecao1() + ") x (" + j.getPlacarSelecao2() + ") " + j.getSelecao2() + "] [" + j.getLocal() + "]");
    }


}

