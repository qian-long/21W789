package edu.mit.rerun.client;

public class ClientException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public ClientException(String error) {
        super(error);
    }

}
