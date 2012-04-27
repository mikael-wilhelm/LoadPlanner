package exceptions;


public class LoadNotFoundException extends Exception{
    public LoadNotFoundException(){
        super("Load id not found");
    }
}
