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
            theOutput = "Welcome to our surname server. Here are the available commands : SAVE, UPDATE, DISPLAY," +
                    " DELETE, STOP. " + "Please type your request.";
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
            System.out.println();
            return "Unknown Command. Try again.";
        }
        int nbParam = request.getListParameters().size();
        switch(request.getCommand()){
            case SAVE: {
                if (nbParam != 2)
                    return "Wrong number of arguments. Try again.";

                return save(request.getValue("NAME"), request.getValue("SURNAME"));
            }

            case UPDATE: {
                if (nbParam == 2 || nbParam == 3)
                    return update(request.getValue("NAME"), request.getValue("SURNAME"),
                            request.getValue("NEWSURNAME"));

                return "Wrong number of arguments. Try again.";
            }

            case DISPLAY: {
                if (nbParam == 0 || nbParam == 1)
                    return display(request.getValue("NAME"));

                return "Wrong number of arguments. Try again.";
            }

            case DELETE: {
                if (nbParam == 1 || nbParam == 2)
                    return delete(request.getValue("NAME"), request.getValue("SURNAME"));

                return "Wrong number of arguments. Try again.";
            }

            case STOP: {
                return "STOP OK";
            }
            default: return "STOP OK";
        }
    }
}
