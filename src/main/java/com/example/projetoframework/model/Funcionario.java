public class Funcionario() {
    private Long idFuncionario;
    private String nome;
    private String sobrenome;
    private String codigoFuncionario;
    private int idade;
    private Genero genero;
    private double Salario;
    private Setor setor;

    public Funcionario(String nome, String sobrenome, String codigoFuncionario, int idade, Genero genero, Setor setor) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.codigoFuncionario = codigoFuncionario;
        this.idade = idade;
        this.genero = genero;
        this.setor = setor;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(String codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public double getSalario() {
        return Salario;
    }

    public void setSalario(double salario) {
        Salario = salario;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}