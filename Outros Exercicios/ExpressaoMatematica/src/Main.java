public class Main {
    public static void main(String[] args) {
        String entrada;
        Pilha pilha = new Pilha();
        Char topoPilha, novoCaractere;

        entrada = MyIO.readLine();

        while(!entrada.equals("FIM")) {
            entrada = entrada.replace(" ", "");
            char[] caracteres = entrada.toCharArray();

            for(char caractere : caracteres) {
                if(caractere >= 48) {
                    System.out.print(caractere + " ");
                } else {
                    novoCaractere = new Char(caractere);

                    try {
                        switch(caractere) {
                            case '(':

                                pilha.empilhar(novoCaractere);
                                break;

                            case ')':
                                topoPilha = pilha.consultarTopo();

                                if(topoPilha != null) {
                                    while(topoPilha != null && topoPilha.getCaractere() != '(') {

                                        System.out.print(pilha.desempilhar().getCaractere() + " ");
                                        topoPilha = pilha.consultarTopo();
                                    }

                                    pilha.desempilhar();
                                }
                                break;

                            default:
                                topoPilha = pilha.consultarTopo();

                                while(topoPilha != null && topoPilha.getPrioridade() >= novoCaractere.getPrioridade()) {

                                    System.out.print(pilha.desempilhar().getCaractere() + " ");
                                    topoPilha = pilha.consultarTopo();
                                }


                                pilha.empilhar(novoCaractere);
                                break;
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                System.out.println(pilha.esvasiarPilha());
            } catch(Exception e) {
                e.printStackTrace();
            }

            entrada = MyIO.readLine();
        }
    }
}

class Pilha {
    private Celula fundo;
    private Celula topo;

    public Pilha() {
        Celula sentinela;

        sentinela = new Celula();
        this.fundo = sentinela;
        this.topo = sentinela;
    }

    public boolean pilhaVazia() {
        if (this.topo == this.fundo) {
            return true;
        }

        return false;
    }

    public void empilhar(Char novo) {
        Celula novaCelula;

        novaCelula = new Celula(novo);
        novaCelula.setProximo(this.topo);
        this.topo = novaCelula;
    }

    public Char desempilhar() throws Exception {
        Celula desempilhado;

        if (! this.pilhaVazia()) {
            desempilhado = this.topo;
            this.topo = this.topo.getProximo();
            desempilhado.setProximo(null);

            return desempilhado.getItem();
        } else
            throw new Exception("Não foi possível desempilhar: a pilha está vazia!");
    }

    public Char consultarTopo() {
        if (! this.pilhaVazia()) {
            return this.topo.getItem();
        }

        return null;
    }

    public String esvasiarPilha() throws Exception{
        String saida = "";
        int tam;

        while(! this.pilhaVazia()) {
            saida += this.desempilhar().getCaractere() + " ";
        }

        tam = saida.length();

        if (tam > 0) {
            saida = saida.substring(0, tam - 1);
        }

        return saida;
    }
}

class Celula {
    private Char item;
    private Celula proximo;

    public Celula(Char novo) {
        item = novo;
        proximo = null;
    }

    public Celula() {
        item = null;
        proximo = null;
    }

    public Char getItem() {
        return item;
    }

    public Celula getProximo() {
        return proximo;
    }


    public void setItem(Char item) {
        this.item = item;
    }

    public void setProximo(Celula proximo) {
        this.proximo = proximo;
    }
}

class Char {
    private static final int PRIORIDADE_UM = 1,
            PRIORIDADE_DOIS = 2,
            PRIORIDADE_TRES = 3;

    private char caractere;
    private int prioridade;

    public Char(char caractere) {
        this.caractere = caractere;
        this.setPrioridade(caractere);
    }

    private void setPrioridade(char caractere) {
        if(caractere == '+' || caractere == '-' ) {

            this.prioridade = PRIORIDADE_DOIS;
        } else if(caractere == '*' || caractere == '/' ) {

            this.prioridade = PRIORIDADE_TRES;
        } else if(caractere == '(') {

            this.prioridade = PRIORIDADE_UM;
        }
    }

    public int getPrioridade() {
        return this.prioridade;
    }

    public char getCaractere() {
        return this.caractere;
    }
}

