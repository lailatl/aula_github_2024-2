import java.util.ArrayList;
public class Conta {
    private int numero;
    private int agencia;
    private String tipo;
    private double saldo = 0.0;
    private ArrayList<Transacao> transacoes = new ArrayList<>();

    public Conta(int num, int agencia, String tipo) {
       this.numero = num;
       this.agencia = agencia;
       this.tipo = tipo;
    }
    // Getters e Setters para cada atributo
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}
