package databaseAccess;

/**
 * Created by IntelliJ IDEA.
 * User: mikael.lof
 * Date: 2012-04-13
 * Time: 10:01
 * To change this template use File | Settings | File Templates.
 */
public class LoadNotFoundException extends Exception{
    public LoadNotFoundException(){
        super("Load id not found");
    }
}
