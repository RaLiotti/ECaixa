package com.rafael.ecaixa.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Document(collection = "funcionario")
public class Funcionario {

    @Id
    private String id;

    private String nome;

    @Email
    private String email;

    @CPF(message = "cpf invalido")
    private String cpf;

}
