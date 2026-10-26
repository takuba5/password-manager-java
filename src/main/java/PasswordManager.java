import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PasswordManager {


    public  List<PasswordEntry> informations = new ArrayList<>();
    public Scanner scanner = new Scanner(System.in);
    PasswordManager(){



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
        System.out.println("Type password: (If you want to generate it, press \"g\") else press anythink else ");

        String input = scanner.nextLine();
        if(input.equalsIgnoreCase("g")){
            String randomPassword = new PasswordGenerator().generateRandomPassword();
            System.out.println("Generated password"  );
            password = randomPassword;
        }else {
            password = input;
        }
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

        while (true){
            System.out.println("Are you sure to delete this entry? (y/n)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                informations.remove(foundEntry);
                System.out.println("Entry removed succesfully");
                break;


            } else if (choice.equalsIgnoreCase("n")) {
                System.out.println("Deletion canneled");
                break;

            } else {
                System.out.println("Wrong choice");

            }
        }

    }
    void editEntry(){
        System.out.println("What service you want to edit?");
        String searchItem = scanner.nextLine();

        PasswordEntry foundEntry = null;
        if (searchItem.isBlank()) {
            System.out.println("Service name cannot be empty.");
            return;
        }
        for (PasswordEntry entry: informations){
            if(entry.getService().equalsIgnoreCase(searchItem)){
                foundEntry = entry;
                break;
            }
        }
        if(foundEntry==null){
            System.out.println("Service not found");
            return;
        }

        String service = foundEntry.getService();
        String login = foundEntry.getLogin();
        String password = foundEntry.getPassword();
        String url = foundEntry.getURL();
        System.out.println("Change service: (If you wont change it press enter) ");
        String newService = scanner.nextLine();
        if(!newService.isBlank()){
            service = newService;
        }

        System.out.println("Change login: (If you wont change it press enter) ");
        String newLogin = scanner.nextLine();
        if(!newLogin.isBlank()){
            login = newLogin;
        }

        System.out.println("Change password: (If you wont change it press enter) ");
        String newPassword = scanner.nextLine();
        if(!newPassword.isBlank()){
            password = newPassword;
        }

        System.out.println("Change url: (If you wont change it press enter) ");
        String newUrl = scanner.nextLine();
        if(!newUrl.isBlank()){
            url = newUrl;
        }
        while (true){
            System.out.println("Are you sure want to edit this entry? (y/n)");

            String choice = scanner.nextLine();
            if(choice.equalsIgnoreCase("y")){
                foundEntry.setService(service);
                foundEntry.setLogin(login);
                foundEntry.setPassword(password);
                foundEntry.setURL(url);
                System.out.println("Editing completed successfully");
                break;

            } else if (choice.equalsIgnoreCase("n")) {
                System.out.println("Canneled operation");
                break;
            }else{
                System.out.println("Wrong choice");
            }
        }


    }

    public void menu(){
        JsonStorage storage = new JsonStorage();
        informations = storage.read();
        System.out.println("=====Password Manager=====\n");
        while (true){
            System.out.println("What do you want do to? 1-Add entry, 2-View entries, 3-Search entry, 4-Delete entry, 5-Edit entry, 6-Save and exit ");
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
                editEntry();


            }else if(choice.equals("6")){
                storage.save(informations);
                break;
            }else {
                System.out.println("Wrong choise ");
            }
        }


    }
}
