package server;

/**
 * @author Lucas MARTINEZ
 * @version 03/11/15
 */
public class Protocol {
    private static final int WAITING = 0;
    private static final int WELCOME = 1;

    private int state = WAITING;


    public String processInput(String theInput) {
        Request request = new Request(theInput);
        String theOutput = null;

        if (state == WAITING) {
            theOutput = "Welcome to this awesome surname server. Please type your request.";
            state = WELCOME;
        } else if (state == WELCOME) {
            theOutput = handle(request);
        }
        return theOutput;
    }

    /**
     * Sauvegarde le surnom au nom donné
     * @param name
     * @param surname
     * @return la string envoyée par le serveur
     */
    private String save(String name, String surname){
        String output = null;
        //todo vraie sauvegarde des noms (avec try/catch et nos exceptions)
        System.out.println(name + surname); //temporaire
        output = "SAVE OK";  //temporaire
        return output;

    }

    /**
     * Traite les requêtes reçues
     * @param request
     * @return
     */
    private String handle(Request request){
        switch(request.getCommand()){
            case SAVE: {
                return save(request.getValue("NAME"), request.getValue("SURNAME"));
            }
            case UPDATE: {
                //todo

            }
            case DISPLAY: {
                //todo

            }
            case DELETE: {
                //todo

            }
            case STOP: {
                return "Bye.";
            }
            default: return "Bye.";
        }
    }
}
