package server;

/**
 * @author Lucas MARTINEZ
 * @version 03/11/15
 */
public class Protocol {
    private static final int WAITING = 0;
    private static final int WELCOME = 1;
    private SurnameManager surnameManager;

    private int state = WAITING;

    public Protocol(){
        surnameManager = new SurnameManager();
    }

    /**
     * Permet de traiter l'input du client, délègue la gestion de la requête à handle()
     * @param theInput
     * @return la string envoyée par le serveur
     */
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
     * Sauvegarde le surnom surname pour le nom name
     * @param name
     * @param surname
     * @return la string envoyée par le serveur
     */
    private String save(String name, String surname){
        return surnameManager.save(name, surname);
    }

    /**
     * Met à jour le surnom surname en newsurname pour le nom name
     * @param name
     * @param surname
     * @param newsurname
     * @return la string envoyée par le serveur
     */
    private String update(String name, String surname, String newsurname){
        return surnameManager.update(name, surname, newsurname);
    }

    private String display(String name){
        return surnameManager.display(name);
    }

    private String delete(String name, String surname){
        return surnameManager.delete(name, surname);
    }

    /**
     * Traite les requêtes reçues
     * @param request
     * @return
     */
    private String handle(Request request){
        if (request.getCommand() == null){
            return "Unknown Command. Try again.";
        }
        switch(request.getCommand()){
            case SAVE: {
                return save(request.getValue("NAME"), request.getValue("SURNAME"));
            }
            case UPDATE: {
                return update(request.getValue("NAME"), request.getValue("SURNAME"),
                        request.getValue("NEWSURNAME"));
            }
            case DISPLAY: {
                return display(request.getValue("NAME"));

            }
            case DELETE: {
                return delete(request.getValue("NAME"), request.getValue("SURNAME"));
            }
            case STOP: {
                return "STOP OK";
            }
            default: return "STOP OK";
        }
    }
}
