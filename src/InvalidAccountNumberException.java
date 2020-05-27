public class InvalidAccountNumberException extends IllegalArgumentException{

    public InvalidAccountNumberException(){
        super();
    }

    public InvalidAccountNumberException(String message){
        super(message);
    }


}
