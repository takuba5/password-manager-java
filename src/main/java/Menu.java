import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public String service;
    public String login;
    public String password;
    public String url;

    public  List<PasswordEntry> informations = new ArrayList<>();
    public Scanner scanner = new Scanner(System.in);
    Menu(){



}
    void addToList(){

        System.out.println("Type service: ");
         service = scanner.nextLine();
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

public void MENU(){
        JsonStorage storage = new JsonStorage();
        informations = storage.read();
        System.out.println("=====Password Manager=====\n");
        while (true){
        System.out.println("What do you want do to? 1-Add entry, 2-View entries,3-Search entry, 4-Delete entry, 5-Save and exit ");
        String choise = scanner.nextLine();
        if(choise.equals("1")){
            addToList();
        } else if (choise.equals("2")) {

            for(PasswordEntry entry: informations){
                System.out.println(entry.toFormattedString());
            }

        }else if(choise.equals("3")){
            searchEntry();

        }else if (choise.equals("5")) {
            storage.save(informations);

        break;

        }else {
        System.out.println("Wrong choise ");
        }
        }


        }
}
