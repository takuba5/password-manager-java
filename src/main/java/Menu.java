import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public  List<PasswordEntry> informations = new ArrayList<>();
    public Scanner scanner = new Scanner(System.in);
    Menu(){



}
    void addToList(){

        System.out.println("Type service: ");
        String service = scanner.nextLine();
        System.out.println("Type login: ");
        String login = scanner.nextLine();
        System.out.println("Type password: ");
        String password = scanner.nextLine();
        System.out.println("Type URL: ");
        String url = scanner.nextLine();
        informations.add(new PasswordEntry(service,login,password,url));

    }

public void MENU(){
        JsonStorage storage = new JsonStorage();
        informations = storage.read();
        System.out.println("=====Password Manager=====\n");
        while (true){
        System.out.println("What do you want do to? 1-Add entry, 2-View entry, 3-Save and exit ");
        String choise = scanner.nextLine();
        if(choise.equals("1")){
            addToList();
        } else if (choise.equals("2")) {

            for(PasswordEntry entry: informations){
                System.out.println(entry.toFormattedString());
            }

        } else if (choise.equals("3")) {
            storage.save(informations);

        break;

        }else {
        System.out.println("Wrong choise ");
        }
        }


        }
}
