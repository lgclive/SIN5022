package gerador;

public class Restricao {
    private ExpAritmetica operandoEsquerdo;
    private ExpAritmetica operandoDireito;
    private String operador;

    public ExpAritmetica getOperandoEsquerdo() {
        return operandoEsquerdo;
    }

    public ExpAritmetica getOperandoDireito() {
        return operandoDireito;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperandoEsquerdo(ExpAritmetica operandoEsquerdo) {
        this.operandoEsquerdo = operandoEsquerdo;
    }

    public void setOperandoDireito(ExpAritmetica operandoDireito) {
        this.operandoDireito = operandoDireito;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }
}
