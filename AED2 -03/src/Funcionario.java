public class Funcionario {
    public double salario;
    public String nome;



    public double novoSalario(){
        if(salario <= 1200){
            return salario += (salario * 10) / 100;
        }else return salario += (salario * 5) / 100;
    }

}
