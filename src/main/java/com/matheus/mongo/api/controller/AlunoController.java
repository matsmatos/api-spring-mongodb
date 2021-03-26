package com.matheus.mongo.api.controller;

import com.matheus.mongo.api.constant.Constant;
import com.matheus.mongo.api.model.Aluno;
import com.matheus.mongo.api.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Secured({Constant.ROLE_ADMIN})
    @GetMapping("/")
    public List<Aluno> obterAlunos() {
        return this.alunoService.getAllAlunos();
    }

    @PostMapping
    public void criarAluno(@RequestBody Aluno aluno) {
        this.alunoService.createAluno(aluno);
    }

    @GetMapping("/{id}")
    public Aluno getAlunoById(@PathVariable("id") String id) {
        return this.alunoService.getAlunoById(id);
    }

    @PutMapping("/editar")
    public Aluno updateAluno(@RequestBody Aluno aluno) {
        return this.alunoService.updateAluno(aluno);
    }

    @DeleteMapping("/{id}")
    public void deleteAluno(@PathVariable("id") String id){
        this.alunoService.deleteAluno(id);

    }

}
