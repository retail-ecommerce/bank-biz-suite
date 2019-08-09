
package com.doublechain.bank;


public class BankRuntimeException extends RuntimeException {
    static final long serialVersionUID = -1;

    public BankRuntimeException() {
        super();
    }


    public BankRuntimeException(String message) {
        super(message);
    }


    public BankRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }


    public BankRuntimeException(Throwable cause) {
        super(cause);
    }


   
}









