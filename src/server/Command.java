package server;

/**
 * @author Lucas MARTINEZ
 * @version 03/11/15
 */
public enum Command {

    WELCOME("WELCOME"), SAVE("SAVE"), UPDATE("UPDATE"), DISPLAY("DISPLAY"), DELETE("DELETE"), STOP("STOP");
    String name;

    Command (String str){
        this.name = str;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public static Command toCommand(String str) {
        for (Command c : values())
            if (str.toUpperCase().equals(c.toString()))
                return c;
        return null; //temporaire (exception)
    }
}
