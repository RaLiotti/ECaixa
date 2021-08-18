package com.rafael.ecaixa.controller;

import com.rafael.ecaixa.model.Funcionario;
import com.rafael.ecaixa.repository.FuncionarioRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {


    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario funcionario) throws Exception{

        Funcionario funcionarioAdicionado = this.funcionarioRepository.findByCpf(funcionario.getCpf().replace(".", "").replace("-", ""));

        if(ObjectUtils.isEmpty(funcionarioAdicionado)){

            funcionario.setCpf(funcionario.getCpf().replace(".", "").replace("-", ""));

            return ResponseEntity.ok(this.funcionarioRepository.save(funcionario));
        }else{
            return ResponseEntity.badRequest().body(funcionarioAdicionado);
        }

    }

    @GetMapping("funcionarios")
    public ResponseEntity<List<Funcionario>> buscarTodosFuncionarios(){

        List<Funcionario> funcionarios = this.funcionarioRepository.findAll();

        if(!ObjectUtils.isEmpty(funcionarios)){
            return ResponseEntity.ok(funcionarios);
        }else{
            return ResponseEntity.noContent().build();
        }
    }


    @GetMapping("/{cpfFuncionario}")
    public ResponseEntity<Funcionario> buscarFuncionario(@PathVariable String cpfFuncionario){

        Funcionario funcionario = this.funcionarioRepository.findByCpf(cpfFuncionario.replaceAll(".", "").replaceAll("-", ""));

        if(!ObjectUtils.isEmpty(funcionario)){
            return ResponseEntity.ok(funcionario);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Funcionario> deletarFuncionario(@RequestParam String cpf){

        Funcionario funcionario = this.funcionarioRepository.findByCpf(cpf.replaceAll(".", "").replaceAll("-", ""));

        if(!ObjectUtils.isEmpty(funcionario)){
            return ResponseEntity.ok(this.funcionarioRepository.deleteByCpf(cpf));
        }else{
            return ResponseEntity.noContent().build();
        }

    }


}
