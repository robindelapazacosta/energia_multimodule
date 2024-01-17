package com.robin.consumo_energia.domain.exception;


import com.robin.consumo_energia.domain.constantes.Message;

public class ExisteLecturaPosteriorException extends Exception {

    public ExisteLecturaPosteriorException() {
        super(Message.ExisteLecturaPosterior);
    }

    public ExisteLecturaPosteriorException(String message) {
        super(message);
    }
}
