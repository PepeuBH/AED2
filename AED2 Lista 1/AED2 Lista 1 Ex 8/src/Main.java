import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        int[] vetor = new int[6];


        System.out.println("Preenche vetor:");
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = sc.nextInt();
        }

        int auxImpar = 0;
        int impar = 0;
        int auxPar = 0;
        int par = 0;


        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] % 2 == 0) {
                System.out.println(vetor[i] + " eh PAR!");
                par++;
                auxPar += vetor[i];
            } else {
                System.out.println(vetor[i] + " eh IMPAR!");
                impar++;
                auxImpar += vetor[i];
            }
        }

        System.out.println("A soma dos numeros pares digitados eh: " + auxPar);
        System.out.println("A quantidade de numeros impares digitados eh: " + impar);


        sc.close();
    }
}