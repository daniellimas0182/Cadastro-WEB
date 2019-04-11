package org.home.api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "funcionarios_dependente")
@SequenceGenerator(name = "FUNCIONARIO_DEPENDENTE_SEQ", sequenceName = "FUNCIONARIO_DEPENDENTE_SEQ", allocationSize = 1)
public class FuncionarioDependente {

    @Id
    @Column(name = "id_funcionario_dependente")
    @GeneratedValue(generator = "FUNCIONARIO_DEPENDENTE_SEQ", strategy = GenerationType.SEQUENCE)
    private Integer idFuncionarioDependente;

    @Column(length = 80, nullable = false)
    private String nome;

    @Column(length = 80, nullable = false)
    private String email;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;


    @ManyToOne
    @JoinColumn(name = "idFuncionario")
    private Funcionario funcionario;

    public Integer getIdFuncionarioDependente() {
        return idFuncionarioDependente;
    }

    public void setIdFuncionarioDependente(Integer idFuncionarioDependente) {
        this.idFuncionarioDependente = idFuncionarioDependente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
