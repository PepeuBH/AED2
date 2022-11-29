public class Calculadora {
    public double num1, num2, num3;
    public double  peso1, peso2, peso3;


    //caso 1
    public double media(){
        return (num1 + num2 + num3) / 3;
    }


    //caso 2
    public double mediaPond(){
        return ((num1 * peso1) + (num2 * peso2) + (num3 * peso3)) / (peso1 + peso2 + peso3);
    }



    //caso 3

}
