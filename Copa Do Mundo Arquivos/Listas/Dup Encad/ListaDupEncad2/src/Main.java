
import java.io.*;


public class Main{
    public static final int MAX_LENGTH = 1000;

    public static JogoCopa find(JogoCopa[] vetor, String string) {
        String data = string.split(";")[0];
        String selecao1 = string.split(";")[1];

        int dia = Integer.parseInt(data.split("/")[0]);
        int mes = Integer.parseInt(data.split("/")[1]);
        int ano = Integer.parseInt(data.split("/")[2]);

        for (JogoCopa jogo : vetor) {
            if ((jogo.getDia() == dia) && (jogo.getMes() == mes) && (jogo.getAno() == ano)
                    && jogo.getSelecao1().equals(selecao1)) {
                return jogo;
            }
        }

        return null;
    }

    // Main
    // ===================================================================================

    public static void main(String[] args) {
        MyIO.setCharset("UTF-8");

        JogoCopa[] copa;
        ListaDuplamenteEncadeada listaCopa;
        String entrada, filename = "partidas.txt";
        ArquivoLeitura arquivo = new ArquivoLeitura(filename);

        copa = arquivo.carregar(MAX_LENGTH);
        listaCopa = new ListaDuplamenteEncadeada();

        // First part
        entrada = MyIO.readLine();

        while(!entrada.equals("FIM")) {
            try {
                listaCopa.inserirFim(find(copa, entrada));
            } catch (Exception e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

            entrada = MyIO.readLine();
        }

        // Second part
        int quant = MyIO.readInt();

        for(int i = 0; i < quant; i++) {
            entrada = MyIO.readLine();
            String jogoBusca, comando = entrada.substring(0, 2);
            int posicao;

            try {
                switch (comando) {
                    case "II":
                        jogoBusca = entrada.substring(3);
                        listaCopa.inserirInicio(find(copa, jogoBusca));

                        break;

                    case "I*":
                        String posicaoNum = entrada.split(" ")[1];
                        entrada = entrada.replace(posicaoNum + " ", "");
                        posicao = Integer.parseInt(posicaoNum);
                        jogoBusca = entrada.substring(3);

                        listaCopa.inserir(find(copa, jogoBusca), posicao);

                        break;

                    case "IF":
                        jogoBusca = entrada.substring(3);
                        listaCopa.inserirFim(find(copa, jogoBusca));

                        break;

                    case "RI":
                        System.out.print("(R) ");
                        listaCopa.removerInicio().imprimir();;

                        break;

                    case "R*":
                        posicao = Integer.parseInt(entrada.substring(3));
                        System.out.print("(R) ");
                        listaCopa.remover(posicao).imprimir();;

                        break;

                    case "RF":
                        System.out.print("(R) ");
                        listaCopa.removerFim().imprimir();;

                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Third part
        try {
            listaCopa.mostrar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Celula {

    private JogoCopa item;
    private Celula anterior;
    private Celula proximo;

    public Celula(JogoCopa novo) {

        item = novo;
        anterior = null;
        proximo = null;
    }

    public Celula() {

        item = new JogoCopa();
        anterior = null;
        proximo = null;
    }

    public JogoCopa getItem() {
        return item;
    }
    public void setItem(JogoCopa item) {
        this.item = item;
    }

    public Celula getProximo() {
        return proximo;
    }
    public void setProximo(Celula proximo) {
        this.proximo = proximo;
    }

    public Celula getAnterior() {
        return anterior;
    }
    public void setAnterior(Celula anterior) {
        this.anterior = anterior;
    }
}

class ListaDuplamenteEncadeada {

    private Celula primeiro;
    private Celula ultimo;
    private int tamanho;

    public ListaDuplamenteEncadeada() {
        Celula sentinela;

        sentinela = new Celula();

        primeiro = sentinela;
        ultimo = sentinela;

        tamanho = 0;
    }

    public boolean listaVazia() {
        if (primeiro == ultimo)
            return true;
        else
            return false;
    }

    public void inserirInicio(JogoCopa novo) throws Exception {
        Celula novaCelula;
        novaCelula = new Celula(novo);

        novaCelula.setProximo(primeiro.getProximo());
        primeiro.getProximo().setAnterior(novaCelula);
        novaCelula.setAnterior(primeiro);

        primeiro.setProximo(novaCelula);

        tamanho++;
    }

    public void inserir(JogoCopa novo, int posicao) throws Exception{
        Celula novaCelula, anterior, aux;
        int count;

        if((posicao >= 0) && (posicao <= tamanho)) {
            novaCelula = new Celula(novo);

            aux = primeiro.getProximo();
            count = 0;

            while(count < posicao) {
                aux = aux.getProximo();
                count++;
            }

            anterior = aux.getAnterior();
            anterior.setProximo(novaCelula);
            aux.setAnterior(novaCelula);

            novaCelula.setProximo(aux);
            novaCelula.setAnterior(anterior);

            tamanho++;
        } else
            throw new Exception("Nao foi possivel inserir o novo elemento: posicao invalida");

    }

    public void inserirFim(JogoCopa novo) {
        Celula novaCelula;

        novaCelula = new Celula(novo);

        ultimo.setProximo(novaCelula);
        novaCelula.setAnterior(ultimo);

        ultimo = novaCelula;

        tamanho++;

    }

    public JogoCopa removerInicio() throws Exception {
        Celula retirado;

        retirado = primeiro.getProximo();
        primeiro.setProximo(retirado.getProximo());
        retirado.getProximo().setAnterior(primeiro);

        tamanho--;

        return retirado.getItem();
    }

    public JogoCopa remover(int posicao) throws Exception{
        Celula retirado, anterior;
        int count;

        if(!listaVazia()) {
            if((posicao >= 0) && (posicao <= tamanho)) {
                retirado = primeiro.getProximo();
                count = 0;

                while(count < posicao) {
                    retirado = retirado.getProximo();
                    count++;
                }

                anterior = retirado.getAnterior();
                anterior.setProximo(retirado.getProximo());
                retirado.getProximo().setAnterior(anterior);

                tamanho--;

                return retirado.getItem();
            } else
                throw new Exception("Nao foi possivel inserir o novo elemento: posicao invalida");
        } else
            throw new Exception("Nao foi possivel inserir o novo elemento: a lista ja esta vazia");
    }

    public JogoCopa removerFim() throws Exception {
        Celula removida, penultima;

        if (! listaVazia()) {

            removida = ultimo;

            penultima = ultimo.getAnterior();
            penultima.setProximo(null);
            removida.setAnterior(null);

            ultimo = penultima;

            tamanho--;

            return (removida.getItem());
        } else
            throw new Exception("Não foi possível remover o último item da lista: a lista está vazia!");
    }

    public void mostrar() throws Exception {
        Celula aux;

        if (! listaVazia()) {
            aux = primeiro.getProximo();
            int i = 0;

            while (aux != null) {
                System.out.print("[" + i++ + "]");
                aux.getItem().imprimir();
                aux = aux.getProximo();
            }
        } else
            throw new Exception("Não foi possível imprimir o conteúdo da lista: a lista está vazia!");
    }
}

class JogoCopa {
    private int ano, dia, mes, placarSelecao1, placarSelecao2;
    private String etapa, selecao1, selecao2, local;

    public JogoCopa() { }

    public JogoCopa(int ano, String etapa, int dia, int mes, String selecao1, int placarSelecao1, int placarSelecao2, String selecao2, String local) {
        this.setAno(ano);
        this.setEtapa(etapa);
        this.setDia(dia);
        this.setMes(mes);
        this.setSelecao1(selecao1);
        this.setPlacarSelecao1(placarSelecao1);
        this.setPlacarSelecao2(placarSelecao2);
        this.setSelecao2(selecao2);
        this.setLocal(local);
    }

    //#region Getters

    public int getAno() {
        return this.ano;
    }

    public String getEtapa() {
        return this.etapa;
    }

    public int getDia() {
        return this.dia;
    }

    public int getMes() {
        return this.mes;
    }

    public String getSelecao1() {
        return this.selecao1;
    }

    public int getPlacarSelecao1() {
        return placarSelecao1;
    }

    public int getPlacarSelecao2() {
        return placarSelecao2;
    }

    public String getSelecao2() {
        return selecao2;
    }

    public String getLocal() {
        return local;
    }
    //#endregion

    //#region Setters

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setSelecao1(String selecao1) {
        this.selecao1 = selecao1;
    }

    public void setSelecao2(String selecao2) {
        this.selecao2 = selecao2;
    }

    public void setPlacarSelecao1(int placarSelecao1) {
        this.placarSelecao1 = placarSelecao1;
    }

    public void setPlacarSelecao2(int placarSelecao2) {
        if(placarSelecao2 > 0) {
            this.placarSelecao2 = placarSelecao2;
        }
    }

    public void setLocal(String local) {
        this.local = local;
    }
    //#endregion

    // clonar classe
    public JogoCopa clonar() {
        return new JogoCopa(this.ano, this.etapa, this.dia, this.mes, this.selecao1, this.placarSelecao1, this.placarSelecao2, this.selecao2, this.local);
    }

    public void imprimir() {
        System.out.println("[COPA " + this.getAno() + "] " + "[" + this.getEtapa() + "] " + "[" + this.getDia() + "/" + this.getMes() + "] " + "["
                + this.getSelecao1() + " (" + this.getPlacarSelecao1() + ")" + " x " + "(" + this.getPlacarSelecao2() + ") "
                + this.getSelecao2() + "]" + " [" + this.getLocal() + "]");

    }

    public boolean eMenor(JogoCopa jogo) {
        if(this.getAno() < jogo.getAno()) {
            return true;
        } else if(this.getAno() == jogo.getAno()){
            if(this.getMes() < jogo.getMes()) {
                return true;
            } else if(this.getMes() == jogo.getMes()) {
                if(this.getDia() < jogo.getDia()) {
                    return true;
                } else if(this.getDia() == jogo.getDia()) {
                    if(this.getSelecao1().compareTo(jogo.getSelecao1()) < 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}

class ArquivoLeitura {
    private BufferedReader entrada;
    private int quantidadeLinhas;

    public ArquivoLeitura(String nomeArquivo) {
        try {
            entrada = new BufferedReader(new FileReader(nomeArquivo));
        } catch (FileNotFoundException excecao) {
            System.out.println("Arquivo nao encontrado");
        }
    }

    public void fecharArquivo() {
        try {
            entrada.close();
        } catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo de leitura: " + excecao);
        }
    }

    @SuppressWarnings("finally")
    public String ler() {
        String textoEntrada = null;

        try {
            textoEntrada = entrada.readLine();
        } catch (EOFException excecao) {
            textoEntrada = null;
        } catch (IOException excecao) {
            System.out.println("Erro de leitura: " + excecao);
            textoEntrada = null;
        } finally {
            return textoEntrada;
        }
    }

    public JogoCopa[] carregar(int length) {
        JogoCopa[] copa = new JogoCopa[length];
        String string = null;
        int i = 0;

        string = this.ler();

        while (string != null) {

            int ano = Integer.parseInt(string.split("#")[0]);
            String etapa = string.split("#")[1];
            int dia = Integer.parseInt(string.split("#")[2]);
            int mes = Integer.parseInt(string.split("#")[3]);
            String selecao1 = string.split("#")[4];
            int placarSelecao1 = Integer.parseInt(string.split("#")[5]);
            int placarSelecao2 = Integer.parseInt(string.split("#")[6]);
            String selecao2 = string.split("#")[7];
            String local = string.split("#")[8];

            copa[i++] = new JogoCopa(ano, etapa, dia, mes, selecao1, placarSelecao1, placarSelecao2, selecao2, local);
            string = this.ler();
        }

        this.setQuantidadeLinhas(i);

        this.fecharArquivo();

        return this.copiarVetor(copa);
    }

    public JogoCopa[] copiarVetor(JogoCopa[] vetorOriginal) {
        JogoCopa[] vetorCopia = new JogoCopa[this.getQuantidadeLinhas()];
        int i = 0;

        while (i != this.getQuantidadeLinhas()) {
            vetorCopia[i] = vetorOriginal[i++];
        }

        return vetorCopia;
    }

    public void setQuantidadeLinhas(int quantidade) {
        this.quantidadeLinhas = quantidade;
    }

    public int getQuantidadeLinhas() {
        return this.quantidadeLinhas;
    }
}
