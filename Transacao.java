import java.util.Date;

public class Transacao {
    private Date data;
    private String tipo;
    private double valor;

    public Transacao(Date data, String tipo, double valor) {
        this.data = data;
        this.tipo = tipo;
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    

}
