package com.robin.plan.exception;

import com.robin.plan.constantes.Message;

public class UebNotFoundException extends RuntimeException{

    public UebNotFoundException() {
        super(Message.UebNotFound);
    }
}
