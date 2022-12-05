import java.io.*;

class Main {

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
        AVL arvore;
        String entrada, filename = "/tmp/partidas.txt";
        ArquivoTextoLeitura arquivo = new ArquivoTextoLeitura(filename);

        copa = arquivo.carregar(MAX_LENGTH);
        arvore = new AVL();

        // First part
        entrada = MyIO.readLine();

        while(!entrada.equals("FIM")) {
            try {
                arvore.inserir(find(copa, entrada));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            entrada = MyIO.readLine();
        }

        // Second part
        entrada = MyIO.readLine();

        while(!entrada.equals("FIM")) {
            try {
                arvore.pesquisar(find(copa, entrada));
            } catch (Exception e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

            entrada = MyIO.readLine();
        }
    }
}

// Classe AVL
// ===================================================================================

class AVL {
    private No raiz;

    public AVL() {
        raiz = null;
    }

    // Pesquisar
    // ===================================================================================

    public JogoCopa pesquisar(JogoCopa chave) {
        return pesquisar(this.raiz, chave);
    }

    private JogoCopa pesquisar(No raizSubarvore, JogoCopa chave) {
        JogoCopa jogoRaiz;
        No esquerda, direita;

        if (raizSubarvore == null) {
            System.out.println("NAO");
            return null;

        } else {

            jogoRaiz = raizSubarvore.getItem();
            esquerda = raizSubarvore.getEsquerda();
            direita = raizSubarvore.getDireita();

            System.out.print("[" + jogoRaiz + "] - ");

            if (chave.equals(jogoRaiz)) {
                System.out.println("SIM");
                return jogoRaiz;

            } else if (!chave.eMenor(jogoRaiz)) {
                return pesquisar(direita, chave);

            } else
                return pesquisar(esquerda, chave);
        }
    }

    // Inserir
    // ===================================================================================

    public void inserir(JogoCopa novo) throws Exception {
        this.raiz = inserir(this.raiz, novo);
    }

    private No inserir(No raizSubarvore, JogoCopa novo) throws Exception{
        JogoCopa jogoRaiz;
        No esquerda, direita;

        if (raizSubarvore == null) {
            raizSubarvore = new No(novo);

        } else {
            jogoRaiz = raizSubarvore.getItem();
            esquerda = raizSubarvore.getEsquerda();
            direita = raizSubarvore.getDireita();

            if (novo.equals(jogoRaiz)) {
                throw new Exception("Não foi possível inserir o item na árvore: chave já inseriada anteriormente!");

            } else if (novo.eMenor(jogoRaiz)) {
                raizSubarvore.setEsquerda(inserir(esquerda, novo));

            } else
                raizSubarvore.setDireita(inserir(direita, novo));
        }

        return balancear(raizSubarvore);
    }

    // Balancear
    // ===================================================================================

    private No balancear(No raizSubarvore) {

        int fatorBalanceamento, fatorBalanceamentoFilho;
        No esquerda, direita;

        esquerda = raizSubarvore.getEsquerda();
        direita = raizSubarvore.getDireita();

        fatorBalanceamento = raizSubarvore.getFatorBalanceamento();

        if (fatorBalanceamento == 2) {
            fatorBalanceamentoFilho = esquerda.getFatorBalanceamento();

            if (fatorBalanceamentoFilho == -1) {
                raizSubarvore.setEsquerda(rotacionarEsquerda(esquerda));
            }

            raizSubarvore = rotacionarDireita(raizSubarvore);
        } else if (fatorBalanceamento == -2) {
            fatorBalanceamentoFilho = direita.getFatorBalanceamento();

            if (fatorBalanceamentoFilho == 1) {
                raizSubarvore.setDireita(rotacionarDireita(direita));
            }

            raizSubarvore = rotacionarEsquerda(raizSubarvore);
        } else
            raizSubarvore.setAltura();

        return raizSubarvore;
    }

    // Rotacionar
    // ===================================================================================

    private No rotacionarDireita(No p) {

        No u = p.getEsquerda();
        No filhoEsquerdaDireita = u.getDireita();

        u.setDireita(p);
        p.setEsquerda(filhoEsquerdaDireita);

        p.setAltura();
        u.setAltura();

        return u;
    }

    private No rotacionarEsquerda(No p) {

        No z = p.getDireita();
        No filhoDireitaEsquerda = z.getEsquerda();

        z.setEsquerda(p);
        p.setDireita(filhoDireitaEsquerda);

        p.setAltura();
        z.setAltura();

        return z;
    }

    // Remover
    // ===================================================================================

    public void remover(JogoCopa chaveRemover) throws Exception {
        this.raiz = remover(this.raiz, chaveRemover);
    }

    private No remover(No raizSubarvore, JogoCopa chaveRemover) throws Exception {
        JogoCopa jogoRaiz;
        No esquerda, direita;

        if (raizSubarvore == null) {

            throw new Exception("Não foi possível remover o item da árvore: chave não encontrada!");
        } else {

            jogoRaiz = raizSubarvore.getItem();
            esquerda = raizSubarvore.getEsquerda();
            direita = raizSubarvore.getDireita();

            if (chaveRemover.equals(jogoRaiz)) {

                if (esquerda == null) {
                    raizSubarvore = direita;
                } else if (direita == null) {
                    raizSubarvore = esquerda;
                } else
                    raizSubarvore.setEsquerda(antecessor(raizSubarvore, esquerda));

            } else if (!chaveRemover.eMenor(jogoRaiz)) {
                raizSubarvore.setDireita(remover(direita, chaveRemover));
            } else
                raizSubarvore.setEsquerda(remover(esquerda, chaveRemover));
        }

        return balancear(raizSubarvore);
    }

    // Antecessor
    // ===================================================================================

    private No antecessor(No noRetirar, No raizSubarvore) {

        if (raizSubarvore.getDireita() != null)
            raizSubarvore.setDireita(antecessor(noRetirar, raizSubarvore.getDireita()));
        else {
            noRetirar.setItem(raizSubarvore.getItem());
            raizSubarvore = raizSubarvore.getEsquerda();
        }

        return balancear(raizSubarvore);
    }

    // Caminhamento em Ordem
    // ===================================================================================

    public void caminhamentoEmOrdem() {
        caminhamentoEmOrdem(this.raiz);
    }

    private void caminhamentoEmOrdem(No raizSubarvore) {
        if (raizSubarvore != null) {
            caminhamentoEmOrdem(raizSubarvore.getEsquerda());
            raizSubarvore.getItem().imprimir();
            caminhamentoEmOrdem(raizSubarvore.getDireita());
        }
    }
}

// Classe No
// ===================================================================================

class No {

    private JogoCopa item;
    private int altura;
    private No esquerda;
    private No direita;

    public No() {

        item = new JogoCopa();
        altura = 0;
        esquerda = null;
        direita = null;
    }

    public No(JogoCopa registro) {

        item = registro;
        altura = 0;
        esquerda = null;
        direita = null;
    }

    public JogoCopa getItem() {
        return item;
    }
    public void setItem(JogoCopa item) {
        this.item = item;
    }

    public No getEsquerda() {
        return esquerda;
    }
    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }
    public void setDireita(No direita) {
        this.direita = direita;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura() {

        int alturaEsq, alturaDir;

        if (this.direita == null) {
            alturaDir = -1;

        } else
            alturaDir = this.direita.getAltura();


        if (this.esquerda == null) {
            alturaEsq = -1;

        } else
            alturaEsq = this.esquerda.getAltura();


        if (alturaEsq > alturaDir) {
            this.altura = alturaEsq + 1;

        } else
            this.altura = alturaDir + 1;
    }

    public int getFatorBalanceamento() {

        int alturaEsq, alturaDir;

        if (this.direita == null) {
            alturaDir = -1;

        } else
            alturaDir = this.direita.getAltura();


        if (this.esquerda == null) {
            alturaEsq = -1;

        } else
            alturaEsq = this.esquerda.getAltura();

        return (alturaEsq - alturaDir);
    }
}

// Classe Jogo
// ===================================================================================

class JogoCopa implements Cloneable {
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
        try {
            return (JogoCopa) this.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Couldn't clone JogoCopa");
            return this;
        }
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

    @Override
    public String toString() {
        return this.getDia() + "/" + this.getMes() + "/" + this.getAno() + ";" + this.getSelecao1();
    }
}

// Classe ArquivoTextoLeitura 
// ===================================================================================

class ArquivoTextoLeitura {
    private BufferedReader entrada;

    public ArquivoTextoLeitura(String nomeArquivo) {
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

        this.fecharArquivo();

        return this.copiarVetor(copa, i);
    }

    public JogoCopa[] copiarVetor(JogoCopa[] vetorOriginal, int length) {
        JogoCopa[] vetorCopia = new JogoCopa[length];
        int i = 0;

        while (i != length) {
            vetorCopia[i] = vetorOriginal[i++];
        }

        return vetorCopia;
    }
}

