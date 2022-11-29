import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Parimpar parimpar = new Parimpar();

        System.out.println("Digite dois numeros:");
        parimpar.num1 = sc.nextInt();
        parimpar.num2 = sc.nextInt();

        if(parimpar.soma() % 2 == 0){
            System.out.println("Soma entre os numeros eh PAR!");
        }else
            System.out.println("Soma entre os numeros eh IMPAR!");





        sc.close();
    }
}