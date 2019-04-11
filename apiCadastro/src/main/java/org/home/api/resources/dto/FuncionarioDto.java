package org.home.api.resources.dto;

import org.home.api.model.Formulario;
import org.home.api.model.Funcionario;
import org.home.api.model.FuncionarioDependente;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class FuncionarioDto {


    @Inject
    private FuncionarioDependenteDto funcionarioDependenteDto;

    private Integer idFuncionario;
    private String nome;
    private String cpf;
    private String rg;
    private String telefone;
    private String email;
    private String endereco;
    private LocalDate dataCadastro;
    private LocalDate dataNascimento;
    private List<FuncionarioDependenteDto> funcionarioDependente;
    private Formulario formulario;

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public List<FuncionarioDependenteDto> getFuncionarioDependente() {
        return funcionarioDependente;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setFuncionarioDependente(List<FuncionarioDependenteDto> funcionarioDependente) {
        this.funcionarioDependente = funcionarioDependente;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public FuncionarioDto toRepresentation(final Funcionario funcionario) {
        FuncionarioDto funcionarioDto = new FuncionarioDto();
        funcionarioDto.setIdFuncionario(funcionario.getIdFuncionario());
        funcionarioDto.setCpf(funcionario.getCpf());
        funcionarioDto.setDataCadastro(funcionario.getDataCadastro());
        funcionarioDto.setDataNascimento(funcionario.getDataNascimento());
        funcionarioDto.setEmail(funcionario.getEmail());
        funcionarioDto.setEndereco(funcionario.getEndereco());
        funcionarioDto.setFormulario(funcionario.getFormulario());
        funcionarioDto.setRg(funcionario.getRg());
        funcionarioDto.setNome(funcionario.getNome());
        funcionarioDto.setTelefone(funcionario.getTelefone());
        funcionarioDto.setFuncionarioDependente(funcionarioDependenteDto.toRepresentation(funcionario.getFuncionarioDependente()));
        return funcionarioDto;
    }

    public Funcionario toEntity(final FuncionarioDto funcionarioDto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(funcionarioDto.getIdFuncionario());
        funcionario.setCpf(funcionarioDto.getCpf());
        funcionario.setDataCadastro(funcionarioDto.getDataCadastro());
        funcionario.setDataNascimento(funcionarioDto.getDataNascimento());
        funcionario.setEmail(funcionarioDto.getEmail());
        funcionario.setEndereco(funcionarioDto.getEndereco());
        funcionario.setFormulario(funcionarioDto.getFormulario());
        funcionario.setRg(funcionarioDto.getRg());
        funcionario.setNome(funcionarioDto.getNome());
        funcionario.setTelefone(funcionarioDto.getTelefone());
        funcionario.setFuncionarioDependente(funcionarioDependenteDto.toEntity(funcionarioDto.getFuncionarioDependente()));
        return funcionario;
    }
}
