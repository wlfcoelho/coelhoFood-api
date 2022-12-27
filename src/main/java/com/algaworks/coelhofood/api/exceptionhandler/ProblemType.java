package com.algaworks.coelhofood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontratada");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://coelhofood.com.br" + path;
        this.title = title;
    }
}