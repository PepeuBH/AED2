import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Aluno aluno = new Aluno();

        Scanner sc = new Scanner(System.in);

        System.out.println("Qual o nome do aluno?");
        aluno.nome = sc.nextLine();

        System.out.println("Digite as tres notas deste aluno:");
        aluno.nota1 = sc.nextDouble();
        aluno.nota2 = sc.nextDouble();
        aluno.nota3 = sc.nextDouble();

        if(aluno.media() < 4.0){
            System.out.println("Aluno reprovado!");
        }else if(4.0 <= aluno.media() && aluno.media() < 6.0){
            System.out.println("Aluno em Exame Especial!");
        }else
            System.out.println("Aluno Aprovado!");


        sc.close();
    }
}