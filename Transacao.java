import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
    private LocalDateTime data;
    private String tipo;
    private double valor;
    private String descricao = "";
    private double saldoAntesTransacao;
    private double saldoPosTransacao;
    private Cliente clienteRemetente = null;
    private Cliente clienteDestinatario = null;

    public Transacao(LocalDateTime data, String tipo, double valor, double saldoAntesTransacao, double saldoPosTransacao) {
        this.data = data;
        this.tipo = tipo;
        this.valor = valor;
        this.saldoAntesTransacao = saldoAntesTransacao;
        this.saldoPosTransacao = saldoPosTransacao;
    }

    public double getSaldoAntesTransacao(){
        return this.saldoAntesTransacao;
    }

    public double getSaldoPosTransacao(){
        return this.saldoPosTransacao;
    }

    public void setTransacaoCliente(Cliente remetente, Cliente destinatario){
        this.clienteRemetente = remetente;
        this.clienteDestinatario = destinatario;
    }

    public Cliente getClienteDestinatario(){
        return this.clienteDestinatario;
    }

    public Cliente getClienteRemetente(){
        return this.clienteRemetente;
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
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
