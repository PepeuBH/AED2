import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n, num, i, j, fat;

        System.out.print("Digite a quantidade de numeros que serao lidos:");

        n = sc.nextInt();

        for (i = 1; i <= n; i++) {
            System.out.println("\n Digite o " + i + " numero");
            num = sc.nextInt();
            fat = 1;
            for (j = 1; j <= num; j++) {
                fat = fat * j;
                System.out.println("O Fatorial de " + num + " = " + fat);
            }
        }

        sc.close();
    }
}