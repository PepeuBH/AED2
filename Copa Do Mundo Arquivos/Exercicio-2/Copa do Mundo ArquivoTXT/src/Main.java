import java.util.Vector;
import java.io.*;

class Jogo {
    private int dia;
    private int mes;
    private int ano;
    private String etapa;
    private String selecao1;
    private String selecao2;
    private int placarSelecao1;
    private int placarSelecao2;
    private String local;

    //===GETS AND SETS===
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getSelecao1() {
        return selecao1;
    }

    public void setSelecao1(String selecao1) {
        this.selecao1 = selecao1;
    }

    public String getSelecao2() {
        return selecao2;
    }

    public void setSelecao2(String selecao2) {
        this.selecao2 = selecao2;
    }

    public int getPlacarSelecao1() {
        return placarSelecao1;
    }

    public void setPlacarSelecao1(int placarSelecao1) {
        this.placarSelecao1 = placarSelecao1;
    }

    public int getPlacarSelecao2() {
        return placarSelecao2;
    }

    public void setPlacarSelecao2(int placarSelecao2) {
        this.placarSelecao2 = placarSelecao2;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }


    //===CONSTRUTOR NORMAL===
    public Jogo(int ano, String etapa, int dia, int mes, String selecao1, int placarSelecao1, int placarSelecao2, String selecao2, String local) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.etapa = etapa;
        this.selecao1 = selecao1;
        this.selecao2 = selecao2;
        this.placarSelecao1 = placarSelecao1;
        this.placarSelecao2 = placarSelecao2;
        this.local = local;
    }

    //===CONSTRUTOR VAZIO===
    public Jogo() {
    }

}


//======================================================================================



//===LEITURA DE ARQUIVO===
class ArquivoTXTLeitura {
    private BufferedReader arquivo;

    public void abrirArquivo(String nomeArquivo) {
        try {
            arquivo = new BufferedReader(new FileReader(nomeArquivo));
        } catch (FileNotFoundException excecao) {
            System.out.println("Arquivo nao encontrado");
        }
    }

    public void fecharArquivo() {
        try {
            arquivo.close();
        } catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo de leitura: " + excecao);
        }
    }

    public String lerLinhaArquivo() {
        String linha = null;
        try {
            linha = arquivo.readLine();
        } catch (EOFException excecao) { //Excecao de final de arquivo.
            linha = null;
        } catch (IOException excecao) {
            System.out.println("Erro de leitura: " + excecao);
            linha = null;
        } finally {
            return linha;
        }
    }
}



//======================================================================================


//===ESCRITA DE ARQUIVO===
class ArquivoTextoEscrita {

    private BufferedWriter saida;

    ArquivoTextoEscrita(String nomeArquivo) {

        try {
            saida = new BufferedWriter(new FileWriter(nomeArquivo));
        } catch (FileNotFoundException excecao) {
            System.out.println("Arquivo nao encontrado");
        } catch (IOException excecao) {
            System.out.println("Erro na abertura do arquivo de escrita: " + excecao);
        }
    }

    public void fecharArquivo() {

        try {
            saida.close();
        } catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo de escrita: " + excecao);
        }
    }

    public void escrever(String textoEntrada) {

        try {
            saida.write(textoEntrada);
            saida.newLine();
        } catch (IOException excecao) {
            System.out.println("Erro de entrada/saída " + excecao);
        }
    }
}



//======================================================================================



public class Main {


    private static void criaJogo(Vector<Jogo> vetorJogos, String linha) {

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







//======================================================================================




    //===MAIN===
    public static void main(String[] args) {
        MyIO.setCharset("UTF-8"); //Muda o charset da aplicação para Português

        ArquivoTXTLeitura arquivo = new ArquivoTXTLeitura();
        arquivo.abrirArquivo("//tmp//partidas.txt");
        Vector<Jogo> vetorJogos = new Vector<Jogo>(); //cria vetor de Objeto de capacidade Dinâmica

        while (true) { //Lê a entrada enquanto != FIM
            String linha = arquivo.lerLinhaArquivo();
            if (linha == null) {
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



}

