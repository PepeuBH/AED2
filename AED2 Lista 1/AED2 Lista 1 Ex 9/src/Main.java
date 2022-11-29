import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Stack pilha = new Stack();

        Stack pilhaInvertida = new Stack();

        System.out.println("Digite 15 letras: ");
        for (int i = 0; i < 15; i++) {
            String palavra = sc.next();
            pilha.push(palavra);
        }

        for (int i = 0; i < 15; i++) {
            pilhaInvertida.push(pilha.pop());
        }

        for(Object p: pilhaInvertida){
            System.out.println(p);
        }

        sc.close();
    }


}