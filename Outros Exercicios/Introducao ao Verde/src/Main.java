import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String linha = null;

        linha = sc.nextLine();

        while (!linha.equals("FIM")) {
            System.out.println(isUpper(linha));
            linha = sc.nextLine();
        }

        sc.close();
    }

    public static int isUpper(String linha) {
        int cont = 0;
        for (int i = 0; i < linha.length(); i++) {
            char letra = linha.charAt(i);
            if (letra >= 'A' && letra <= 'Z') {
                cont++;
            }
        }

        return cont;
    }
}