import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Funcionario funcionario = new Funcionario();

        Scanner sc = new Scanner(System.in);

        System.out.println("Nome do Funcionario:");
        funcionario.nome = sc.nextLine();
        System.out.println("Qual o salario atual deste funcionario?");
        funcionario.salario = sc.nextDouble();


        if(funcionario.salario <= 1200){
            funcionario.salario += (funcionario.salario / 100) * 10;
        }else
            funcionario.salario += (funcionario.salario / 100) * 5;
        System.out.println("Funcionario: " + funcionario.nome + "\nNovo Salario: " + funcionario.salario);

        sc.close();
    }
}