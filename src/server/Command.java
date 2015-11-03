package server;

/**
 * @author Lucas MARTINEZ
 * @version 03/11/15
 */
public enum Command {

    SAVE("SAVE"), UPDATE("UPDATE"), DISPLAY("DISPLAY"), DELETE("DELETE"), STOP("STOP");
    String name;

    Command (String str){
        this.name = str;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
