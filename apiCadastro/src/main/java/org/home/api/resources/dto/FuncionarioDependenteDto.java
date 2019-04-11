package org.home.api.resources.dto;

import org.home.api.model.FuncionarioDependente;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FuncionarioDependenteDto {

    private Integer idFuncionarioDependente;
    private String nome;
    private String email;
    private LocalDate dataNascimento;

    public Integer getIdFuncionarioDependente() {
        return idFuncionarioDependente;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setIdFuncionarioDependente(Integer idFuncionarioDependente) {
        this.idFuncionarioDependente = idFuncionarioDependente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public FuncionarioDependenteDto toRepresentation(final FuncionarioDependente funcionarioDependente) {
        FuncionarioDependenteDto funcionarioDependenteDto = new FuncionarioDependenteDto();
        funcionarioDependenteDto.setIdFuncionarioDependente(funcionarioDependente.getIdFuncionarioDependente());
        funcionarioDependenteDto.setNome(funcionarioDependente.getNome());
        funcionarioDependenteDto.setDataNascimento(funcionarioDependente.getDataNascimento());
        funcionarioDependenteDto.setEmail(funcionarioDependente.getEmail());
        return funcionarioDependenteDto;
    }

    public List<FuncionarioDependenteDto> toRepresentation(final List<FuncionarioDependente> funcionarioDependente) {
        return funcionarioDependente.stream().map(func -> toRepresentation(func)).collect(Collectors.toList());
    }

    public FuncionarioDependente toEntity(final FuncionarioDependenteDto funcionarioDependenteDto) {
        FuncionarioDependente funcionarioDependente = new FuncionarioDependente();
        funcionarioDependente.setIdFuncionarioDependente(funcionarioDependenteDto.getIdFuncionarioDependente());
        funcionarioDependente.setNome(funcionarioDependenteDto.getNome());
        funcionarioDependente.setDataNascimento(funcionarioDependenteDto.getDataNascimento());
        funcionarioDependente.setEmail(funcionarioDependenteDto.getEmail());

        return funcionarioDependente;
    }

    public List<FuncionarioDependente> toEntity(final List<FuncionarioDependenteDto> funcionarioDependenteDto) {
        return funcionarioDependenteDto.stream().map(func -> toEntity(func)).collect(Collectors.toList());
    }
}
