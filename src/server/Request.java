package server;

import java.util.LinkedList;

/**
 * @author Lucas MARTINEZ
 * @version 04/11/15
 */
public class Request {
    private Command command;
    private LinkedList<String[]> listParameters;

    public Request(String str){

        String tokens[] = str.split(" ");
        try {
            command = Command.toCommand(tokens[0]); //transforme le 1er mot de la requête en command
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (command != Command.WELCOME) {
            listParameters = new LinkedList<>();
            for (int i = 1; i < tokens.length; ++i) {
                String parameters[] = tokens[i].split("=");
                String nameParam = parameters[0];
                String value = "";

                try {
                    value = parameters[1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }

                String[] couple = {nameParam.toUpperCase(), value}; //crée un couple ("PARAM", "valeur")
                listParameters.add(couple); //ajoute ce couple à la liste des params
            }

            try {
                checkRequest();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Permet de vérifier le format de la requête en fonction de la commande
     */
    private void checkRequest() {

        switch(command){
            case SAVE: {
                System.out.println("save test OK"); //tmp
                //todo
                break;
            }
            case UPDATE: {
                System.out.println("update test OK"); //tmp
                //todo
                break;
            }
            case DISPLAY: {
                //todo
                break;
            }
            case DELETE: {
                //todo
                break;
            }
            case STOP: {
                //todo
                break;
            }
            default: break;
        }

    }

    public Command getCommand() {
        return command;
    }

    /**
     * Renvoie la valeur associée au paramètre param
     * @param param
     * @return
     */
    public String getValue(String param) {
        for (int i = 0; i < listParameters.size(); ++i){
            if(listParameters.get(i)[0].equals(param)){
                return listParameters.get(i)[1];
            }
        }
        return null;
    }
}
