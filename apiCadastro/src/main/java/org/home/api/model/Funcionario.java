package org.home.api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "funcionarios")

@SequenceGenerator(name = "FUNCIONARIO_SEQ", sequenceName = "FUNCIONARIO_SEQ", allocationSize = 1)
public class Funcionario {

    @Id
    @Column(name = "id_funcionario")
    @GeneratedValue(generator = "FUNCIONARIO_SEQ", strategy = GenerationType.SEQUENCE)
    private Integer idFuncionario;

    @Column(length = 80, nullable = false)
    private String nome;

    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(length = 11, nullable = false)
    private String rg;

    @Column(length = 12, nullable = false)
    private String telefone;

    @Column(length = 80, nullable = false)
    private String email;

    @Column(length = 80, nullable = false)
    private String endereco;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "dt_cadastro")
    private LocalDate dataCadastro;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;


    @OneToMany(mappedBy = "funcionario", fetch = FetchType.EAGER)
    private List<FuncionarioDependente> funcionarioDependente;

    @ManyToOne
    @JoinColumn(name = "idFormulario")
    private Formulario formulario;



    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    public List<FuncionarioDependente> getFuncionarioDependente() {
        return funcionarioDependente;
    }

    public void setFuncionarioDependente(List<FuncionarioDependente> funcionarioDependente) {
        this.funcionarioDependente = funcionarioDependente;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public void addFuncionarioDependente(FuncionarioDependente dependente){
        dependente.setFuncionario(this);
        this.funcionarioDependente.add(dependente);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "formulario=" + formulario +
                '}';
    }
}
