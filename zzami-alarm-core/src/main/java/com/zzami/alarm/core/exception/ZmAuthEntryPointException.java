package com.zzami.alarm.core.exception;

public class ZmAuthEntryPointException extends RuntimeException {
 
    private static final long serialVersionUID = 1L;
    
    public ZmAuthEntryPointException() {
        super();
    }
    
    public ZmAuthEntryPointException(String msg) {
        super(msg);
    }
    
    public ZmAuthEntryPointException(String msg, Throwable t) {
        super(msg, t);
    }
    

}
