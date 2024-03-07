package model;

public class Carro {
    private int codigo;
    private String marca;
    private String modelo;
    private String cor;
    private char condicao;

    public Carro() {
        this.codigo = -1;
        this.marca = "";
        this.modelo = "";
        this.cor = "";
        this.condicao = '*';
    }

    public Carro(int codigo, String marca, String modelo, String cor, char condicao) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.condicao = condicao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public char getCondicao() {
        return condicao;
    }

    public void setCondicao(char condicao) {
        this.condicao = condicao;
    }

    @Override
    public String toString() {
        return "Carro [codigo=" + codigo + ", marca=" + marca + ", modelo=" + modelo + ","
        		+ "cor=" + cor + ", condicao=" + condicao + "]";
    }   
}
