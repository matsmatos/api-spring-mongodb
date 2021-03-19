package com.matheus.mongo.api.service;

import com.matheus.mongo.api.model.Aluno;
import com.matheus.mongo.api.model.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    public void createAluno(Aluno aluno) {

        this.validaAluno(aluno);

        Optional<Aluno> novoAluno = Optional.of(this.alunoRepository.save(aluno));
        novoAluno.orElseThrow(() -> new IllegalArgumentException("Erro ao salvar"));

    }

    private void validaAluno(Aluno aluno) {
        if (Objects.isNull(aluno.getEscolaridade())) {
            throw new IllegalArgumentException("Falta escolaridade do aluno");
        }
        if (Objects.isNull(aluno.getIdade())) {
            throw new IllegalArgumentException("Falta idade do aluno");
        }
        if (Objects.isNull(aluno.getNome())) {
            throw new IllegalArgumentException("Falta nome do aluno");
        }
    }

    public Aluno getAlunoById(String id) {
        Optional<Aluno> aluno = this.alunoRepository.findById(id);
        aluno.orElseThrow(() -> new IllegalArgumentException("Objeto inválido"));
        return aluno.get();
    }

    public Aluno updateAluno(Aluno aluno) {
        if (Objects.isNull(aluno.getId())) {
            throw new IllegalArgumentException("Falta identificador do aluno");
        }
        Optional<Aluno> alunoAtualizado = Optional.of(this.alunoRepository.save(aluno));
        alunoAtualizado.orElseThrow(() -> new IllegalArgumentException("Erro ao atualizar aluno"));
        return alunoAtualizado.get();
    }

    public void deleteAluno(String id) {
        Optional<Aluno> aluno = this.alunoRepository.findById(id);
        aluno.orElseThrow(() -> new IllegalArgumentException("Aluno inexistente"));

        this.alunoRepository.delete(aluno.get());
    }
}
