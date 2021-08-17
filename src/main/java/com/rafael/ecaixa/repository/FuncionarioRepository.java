package com.rafael.ecaixa.repository;

import com.rafael.ecaixa.model.Funcionario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends MongoRepository<Funcionario, String> {

    public Funcionario findByNome(String nome);

    public Funcionario findByCpf(String cpf);

    public Funcionario deleteByCpf(String cpf);
}
