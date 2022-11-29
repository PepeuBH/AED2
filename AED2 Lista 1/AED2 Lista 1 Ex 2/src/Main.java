import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Qual funcao voce gostaria de usar?\n1 - Somar dois numeros\n2 - Raiz Quadrada\n3 - Sair");

        int op = 0;

        Calc calculadora = new Calc();
        Scanner sc = new Scanner(System.in);
        while (op != 3){
            System.out.println("Qual funcao voce gostaria de usar?\n1 - Somar dois numeros\n2 - Raiz Quadrada\n3 - Sair");
            op = sc.nextInt();
            switch (op){
                case 1:
                    System.out.println("Digite dois numero para somar: ");
                    calculadora.num1 = sc.nextDouble();
                    calculadora.num2 = sc.nextDouble();
                    System.out.println(calculadora.num1 + " + " + calculadora.num2 + " = " + calculadora.soma());
                    break;
                case 2:
                    System.out.println("Digite um numero para calcular sua raiz quadrada: ");
                    calculadora.num1 = sc.nextDouble();
                    System.out.println("Raiz Quadrada de " + calculadora.num1 + " = " + calculadora.raiz());
                    break;
                case 3:
                    System.out.println("Saindo do programa...");
                    break;
            }
        }




        sc.close();
    }
}