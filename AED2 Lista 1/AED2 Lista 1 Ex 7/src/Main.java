import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Calculadora calc = new Calculadora();

        int op = 0;

        while (op != 4) {
            System.out.println("""
                    - Menu de opcoes -
                    1. Media Aritmetica
                    2. Media Ponderada
                    3. Media Aritmetica e Ponderada
                    4. Sair
                    Digite a opcao desejada:""");
             op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Digite 3 valores para mostrar a media entre eles:");
                    calc.num1 = sc.nextInt();
                    calc.num2 = sc.nextInt();
                    calc.num3 = sc.nextInt();
                    System.out.println("Media: " + calc.media());
                    break;

                case 2:

                    System.out.println("Digite 3 valores e o peso de cada um, para mostrar a media ponderada entre eles:");
                    calc.num1 = sc.nextDouble();
                    System.out.println("Peso:");
                    calc.peso1 = sc.nextDouble();

                    System.out.println("Valor:");
                    calc.num2 = sc.nextDouble();
                    System.out.println("Peso:");
                    calc.peso2 = sc.nextDouble();

                    System.out.println("Valor:");
                    calc.num3 = sc.nextDouble();
                    System.out.println("Peso:");
                    calc.peso3 = sc.nextDouble();

                    System.out.println("Media ponderada: " + calc.mediaPond());
                    break;

                case 3:
                    System.out.println("Digite 3 valores e o peso de cada um, para mostrar a media ponderada entre eles:");
                    calc.num1 = sc.nextDouble();
                    System.out.println("Peso:");
                    calc.peso1 = sc.nextDouble();

                    System.out.println("Valor:");
                    calc.num2 = sc.nextDouble();
                    System.out.println("Peso:");
                    calc.peso2 = sc.nextDouble();

                    System.out.println("Valor:");
                    calc.num3 = sc.nextDouble();
                    System.out.println("Peso:");
                    calc.peso3 = sc.nextDouble();

                    System.out.println("Media: " + calc.media() + "Media ponderada: " + calc.mediaPond());
                    break;

                case 4:
                    System.out.println("Saindo do programa...");
                    break;
            }

        }

        sc.close();
    }
}