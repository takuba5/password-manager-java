import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {


    public  List<PasswordEntry> informations = new ArrayList<>();
    public Scanner scanner = new Scanner(System.in);
    Menu(){



}
    void addToList(){
        String service;
        String login;
        String password;
        String url;

        System.out.println("Type service: ");
         service = scanner.nextLine();
         if(service==null || service.isBlank() ){
             System.out.println("Service name cannot be empty");
             return;
         }
        System.out.println("Type login: ");
         login = scanner.nextLine();
        System.out.println("Type password: ");
         password = scanner.nextLine();
        System.out.println("Type URL: ");
         url = scanner.nextLine();
        informations.add(new PasswordEntry(service,login,password,url));

    }

    void searchEntry(){
        System.out.println("What do you want to search: ");
        String searchItem = scanner.nextLine();
        searchItem = searchItem.toLowerCase();


        boolean found = false;
        for(PasswordEntry entry:informations){
            if(entry.getService().toLowerCase().contains(searchItem)){
                System.out.println(entry.toFormattedString());
                found = true;
            }
        }
        if(!found){
            System.out.println("There is no service about this name");
        }
    }

    void deleteEntry() {
        String serviceName;
        System.out.println("What service do you want to delete?");
        serviceName = scanner.nextLine();

        PasswordEntry foundEntry = null;
        for (PasswordEntry entry : informations) {
            if (entry.getService().equalsIgnoreCase(serviceName)) {
                foundEntry = entry;
                break;
            }
        }
        if (foundEntry == null) {
            System.out.println("Service not found.");
            return;
        }
        System.out.println("Found: " +foundEntry.toFormattedString());
        System.out.println("Are you soure to delete this entry? (y/n)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            informations.remove(foundEntry);


        } else if (choice.equalsIgnoreCase("n")) {
            System.out.println("Deletion canneled");
            return;

        } else {
            System.out.println("Wrong choice");
        }
    }

public void menu(){
        JsonStorage storage = new JsonStorage();
        informations = storage.read();
        System.out.println("=====Password Manager=====\n");
        while (true){
        System.out.println("What do you want do to? 1-Add entry, 2-View entries, 3-Search entry, 4-Delete entry, 5-Save and exit ");
        String choice = scanner.nextLine();
        if(choice.equals("1")){
            addToList();
        } else if (choice.equals("2")) {

            for(PasswordEntry entry: informations){
                System.out.println(entry.toFormattedString());
            }

        }else if(choice.equals("3")){
            searchEntry();

        }else if(choice.equals("4")){
            deleteEntry();

        }else if (choice.equals("5")) {
            storage.save(informations);

        break;

        }else {
        System.out.println("Wrong choise ");
        }
        }


        }
}
