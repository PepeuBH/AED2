
import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.Collections;

// --------------------------------------------

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

    Jogo(int ano, String etapa, int dia, int mes, String selecao1, int placarSelecao1, int placarSelecao2, String selecao2, String local) {
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

    private static void toString(Jogo j) {
        System.out.println("[Copa " + j.getAno() + "] [" + j.getEtapa() + "] [" + j.getDia() + "/" + j.getMes() + "] [" + j.getSelecao1() + " (" + j.getPlacarSelecao1() + ") x (" + j.getPlacarSelecao2() + ") " + j.getSelecao2() + "] [" + j.getLocal() + "]");
    }


    private static void bubbleSort(Vector<Jogo> vetorJogosOrdenar) {

        int n = vetorJogosOrdenar.size();
        for (int i = (n - 1); i > 0; i--) {
            for (int j = 0; j < i; j++) {

                if (vetorJogosOrdenar.get(j).dia < vetorJogosOrdenar.get(j + 1).dia) {
                    Jogo temp = vetorJogosOrdenar.get(j);
                    vetorJogosOrdenar.set(j, vetorJogosOrdenar.get(j + 1));
                    vetorJogosOrdenar.set(j + 1, temp);

                } else if (vetorJogosOrdenar.get(j).dia == vetorJogosOrdenar.get(j + 1).dia) {
                    if (vetorJogosOrdenar.get(j).mes < vetorJogosOrdenar.get(j + 1).mes) {
                        Jogo temp = vetorJogosOrdenar.get(j);
                        vetorJogosOrdenar.set(j, vetorJogosOrdenar.get(j + 1));
                        vetorJogosOrdenar.set(j + 1, temp);

                    } else if (vetorJogosOrdenar.get(j).mes == vetorJogosOrdenar.get(j + 1).mes) {
                        if (vetorJogosOrdenar.get(j).ano < vetorJogosOrdenar.get(j + 1).ano) {
                            Jogo temp = vetorJogosOrdenar.get(j);
                            vetorJogosOrdenar.set(j, vetorJogosOrdenar.get(j + 1));
                            vetorJogosOrdenar.set(j + 1, temp);

                        } else if (vetorJogosOrdenar.get(j).ano == vetorJogosOrdenar.get(j + 1).ano) {
                            if (vetorJogosOrdenar.get(j).selecao1.compareTo(vetorJogosOrdenar.get(j + 1).selecao1) != 0)  {
                                Jogo temp = vetorJogosOrdenar.get(j);
                                vetorJogosOrdenar.set(j, vetorJogosOrdenar.get(j + 1));
                                vetorJogosOrdenar.set(j + 1, temp);
                            }
                        }
                    }
                }
            }

        }


       ;

    }



    private static void splitJogoInsereVetor(Vector<Jogo> vetorJogos, String busca, Vector<Jogo> vetorJogosOrdenar) {
        int dia, mes, ano;

        String[] textoSeparado = busca.split(";");
        String pais = textoSeparado[1];
        textoSeparado = textoSeparado[0].split("/");

        dia = Integer.valueOf(textoSeparado[0]);
        mes = Integer.valueOf(textoSeparado[1]);
        ano = Integer.valueOf(textoSeparado[2]);

        buscaJogoVetor(vetorJogos, pais, dia, mes, ano, vetorJogosOrdenar);
    }


    private static void buscaJogoVetor(Vector<Jogo> vetorJogosCompleto, String pais, int dia, int mes, int ano, Vector<Jogo> vetorJogosOrdenar) {
        for (int i = 0; i < vetorJogosCompleto.size(); i++)
            if (dia == vetorJogosCompleto.get(i).getDia() && mes == vetorJogosCompleto.get(i).getMes() && ano == vetorJogosCompleto.get(i).getAno() && pais.equals(vetorJogosCompleto.get(i).getSelecao1())) {
                vetorJogosOrdenar.set(i, vetorJogosCompleto.get(i));
                //toString(vetorJogosOrdenar.get(i));
            }

    }


    private static void criaJogo(Vector<Jogo> vetorJogos, String a) {
        String[] textoSeparado = a.split("#");// quebra o vetor nas posições marcadas péeplo #
        Jogo j = new Jogo(Integer.valueOf(textoSeparado[0]), // data
                textoSeparado[1], // fase
                Integer.valueOf(textoSeparado[2]), // dia
                Integer.valueOf(textoSeparado[3]), // mes
                textoSeparado[4], // time 1
                Integer.valueOf(textoSeparado[5]), // palacar 1
                Integer.valueOf(textoSeparado[6]), // placar 2
                textoSeparado[7], // time 2
                textoSeparado[8]); // local
        vetorJogos.add(j);

    }


    public static void main(String[] args) {
        MyIO.setCharset("UTF-8");
        ArquivoTextoLeitura texto = new ArquivoTextoLeitura("partidas.txt");// caminho de abertura do arquivo no VERDE = /tmp/partidas.txt


        Vector<Jogo> vetorJogos = new Vector<Jogo>();

        /* - ----- Leitura dos dados do arquivo -------------------*/
        while (true) {
            String linha = texto.ler();
            if (linha == null) {
                texto.fecharArquivo();
                break;
            }
            criaJogo(vetorJogos, linha);
        }
        texto.fecharArquivo();
        /*------------------------------------------------------------*/


        /* ------------- Vetor com jogos a serem ordenados --------------------- */
        Vector<Jogo> vetorJogosOrdenar = new Vector<Jogo>();


        int numBuscas = MyIO.readInt(); //  numero de buscas feitas

        // faz a leitura dos jogos a serem buscados, o valor numBuscas
        for (int i = 0; i < numBuscas; i++) {
            String linha = MyIO.readLine();// entrada de dados pelo verde
            splitJogoInsereVetor(vetorJogos, linha, vetorJogosOrdenar);
        }


        bubbleSort(vetorJogosOrdenar);        // Implementar este metodo ---------------------------------


        for (int i = 0; i < vetorJogosOrdenar.size(); i++)
            toString(vetorJogosOrdenar.get(i));            // Implementar este metodo ---------------------------------

    }






// --------------------- Classe ler arquivo ------------------------------------

    static class ArquivoTextoLeitura {

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
            String linha = null;
            try {
                linha = entrada.readLine();
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
}
