import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Radar radar = new Radar();

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a velocidade de 5 carros:");
        for (int i = 0; i < 5; i++) {
            radar.velocidade = sc.nextDouble();
            if (radar.velocidade > 60.0) {
                radar.cont++;
            }
        }
        System.out.println("Total de veiculos acima de 60Km/h: " + radar.cont + "\nTotal Arrecadado com multas: " + radar.totalArrecadado());


        sc.close();
    }
}