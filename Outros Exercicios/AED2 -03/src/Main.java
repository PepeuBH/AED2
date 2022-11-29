import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Funcionario funcionario = new Funcionario();

        Scanner sc = new Scanner(System.in);
        System.out.println("Qual o nome do funcionario?");
        funcionario.nome = sc.nextLine();

        System.out.println("Salario atual deste funcionario: ");
        funcionario.salario = sc.nextDouble();

        System.out.println("Funcionario: " + funcionario.nome + " Salario atual: " + funcionario.salario + " Novo Salario: " + funcionario.novoSalario());


        sc.close();
    }
}