import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       System.out.println();
       Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        int maior = higher(a, b, c);
        imprimeMaior(maior);
        sc.close();
    }

    private static void imprimeMaior(int maior) {
        System.out.println("Maior Valor: " + maior);
    }

    private static int higher(int a, int b, int c) {
        int aux;
        if(a > b && a > c){
            aux = a;
        }else if(b > c){
            aux = b;
        }else
            aux = c;
        return aux;
    }
}