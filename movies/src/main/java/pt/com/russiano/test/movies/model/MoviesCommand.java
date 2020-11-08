package pt.com.russiano.test.movies.model;

public class MoviesCommand {
// ------------------------------ FIELDS ------------------------------

    private String nome;
    private String nomeFilme;
    private String realizador;
    private String protagonista;
    private String anoLancamento;

    public MoviesCommand() {
    }
// --------------------- GETTER / SETTER METHODS ---------------------

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public String getRealizador() {
        return realizador;
    }

    public String getProtagonista() {
        return protagonista;
    }

    public String getAnoLancamento() {
        return anoLancamento;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public void setRealizador(String realizador) {
        this.realizador = realizador;
    }

    public void setProtagonista(String protagonista) {
        this.protagonista = protagonista;
    }

    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

}
//2.       A lista deve conter “Nome do filme”, “Realizador”, “Protagonistas”, “Ano de Lançamento”.