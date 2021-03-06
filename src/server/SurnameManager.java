package server;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Lucas MARTINEZ
 * @version 05/11/15
 */
public class SurnameManager {
    private ConcurrentHashMap<String, LinkedList<String>> surnames;
    private LinkedList<String> listSurnames = new LinkedList<>();

    public SurnameManager(){
        surnames = new ConcurrentHashMap<>();
        firstSurnames();
        for (LinkedList<String> list : surnames.values()){
            listSurnames.addAll(list);
        }
    }

    /**
     * Permet de remplir avec quelques surnoms à chaque création
     */
    private void firstSurnames(){
        surnames.put("Lucas", new LinkedList<>(Arrays.asList("Lulu", "Marti")));
        surnames.put("Ugo", new LinkedList<>(Arrays.asList("ElKoukou")));
    }

    /**
     * Ajoute un <name, surname> à la hashmap des surnoms
     * @param name
     * @param surname
     * @return
     */
    public String save(String name, String surname){
        String str;
        if (surnames.containsKey(name)){
            str = "SAVE FAILED " + name + " ALREADY EXISTS";
        } else if (listSurnames.contains(surname)) {
            str = "SAVE FAILED " + surname + " ALREADY EXISTS";
        } else {
            surnames.putIfAbsent(name, new LinkedList<>(Arrays.asList(surname)));
            listSurnames.add(surname);
            str = "SAVE OK " + name + "=[" + surname + "]";
        }
        System.out.println(surnames); //temp
        return str;
    }

    /**
     * Met à jour la liste des surnoms en supprimant surname, appelle ensuite update(name, newsurname)
     * @param name
     * @param surname
     * @param newsurname
     * @return la string envoyée par le serveur
     */
    public String update(String name, String surname, String newsurname){
        if (!surnames.containsKey(name)){
            return "UPDATE FAILED " + name + " DOESN'T EXIST";
        } else if (!listSurnames.contains(surname) && newsurname != null) {
            return "UPDATE FAILED " + surname + " DOESN'T EXIST";
        } else {
            LinkedList<String> values = surnames.get(name); //on récup les surnoms de name

            if (values.contains(surname) && !listSurnames.contains(newsurname)){
                values.remove(surname);
                listSurnames.remove(surname);
                surnames.put(name, values);
            }

            if (newsurname == null){
                newsurname = surname;
            }

            if (listSurnames.contains(newsurname)) {
                return "UPDATE FAILED " + newsurname + " ALREADY EXISTS";
            }
            return update(name, newsurname);
        }
    }

    /**
     * Ajoute surname en surnom de name dans la hashmap des surnoms
     * @param name
     * @param surname
     * @return
     */
    public String update(String name, String surname){
        LinkedList<String> values = surnames.get(name); //on récup les surnoms de name
        values.add(surname); //on ajoute le nouveau à la liste
        surnames.put(name, values); //on met les surnoms à jour
        listSurnames.add(surname);
        System.out.println(surnames); //temp
        return "UPDATE OK " + name + "=" + values;
    }

    /**
     * Affiche le couple <nom, surnoms> s'il y a un paramètre correct, affiche la liste entière
     * s'il n'y a pas de paramètre, affiche un fail si le paramètre est erroné
     * @param name
     * @return la string envoyée par le serveur
     */
    public String display(String name){
        String str = "";
        if (name == null){
            for(String n : surnames.keySet()){
                str += n + "=" + surnames.get(n) + " ";
            }
        } else if(!surnames.containsKey(name)){
            return "DISPLAY FAILED " + name + " DOESN'T EXIST";
        } else {
            str += name + "=" + surnames.get(name);
        }

        return "DISPLAY OK " + str;
    }

    public String delete(String name, String surname){
        if (surnames.containsKey(name)) {
            if (surname == null) {
                LinkedList<String> list = surnames.get(name);
                surnames.remove(name);
                listSurnames.removeAll(list);
                System.out.println(surnames); //temp
                return "DELETE OK " + name;
            }

            LinkedList<String> values = surnames.get(name);
            if (values.contains(surname)){
                values.remove(surname);
                listSurnames.remove(surname);
                surnames.put(name, values);
                System.out.println(surnames); //temp
                return "DELETE OK " + surname;
            }
            return "DELETE FAILED " + surname + " DOESN'T EXIST";
        }
        return "DELETE FAILED " + name + " DOESN'T EXIST";
    }

    @Override
    public String toString(){
        return surnames.toString();
    }
}
