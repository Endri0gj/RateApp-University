package rate.exception;

public class RateAppException extends java.lang.Exception {
    public static <Key,Value> String noSuchElement(Key key,Value value){
        return "No user found with "+key+": "+value;
    }
    public static <Key,Value> String alreadyRegistered(Key key,Value value){
        return "User with "+key+": "+value+" is already registered!";
    }

}
