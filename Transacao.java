import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
    private LocalDateTime data;
    private String tipo;
    private double valor;

    public Transacao(LocalDateTime data, String tipo, double valor) {
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
    public LocalDateTime getData() {
        return data;
    }

    public String getDataFormatada(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return data.format(formato);
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    

}
