public class DublicateAccountNumberException extends Exception{

    public DublicateAccountNumberException(){
        super();
    }

    public DublicateAccountNumberException(String message){
        super(message);
    }

    public DublicateAccountNumberException(String message,Throwable cause){
        super(message,cause);
    }
}

