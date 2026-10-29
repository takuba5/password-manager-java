import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PasswordAnalyzer{
    private static final String bigLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String smallLetters = "abcdefghijklmnopqrstuvwxyz";
    private static final String numbers = "1234567890";
    private static final String specialChars = "~!@#$%^&*()_+{}[]|:;'<>,.?/`";



    public void  checkPasswordHealth(List<PasswordEntry> informations, Scanner scanner){
        if(informations.isEmpty()){
            System.out.println("There are no saved services");
            return;
        }

        int i = 1;
        System.out.println("Select service: ");
        for(PasswordEntry entry: informations){
            System.out.println(i +": "+ entry.getService());
            i++;
        }

        int choice;
        while (true){
            try {
                System.out.print(">");
                choice = scanner.nextInt();
                scanner.nextLine();
            }catch (InputMismatchException e){
                System.out.println("Type number ");
                scanner.nextLine();
                continue;
            }
            if(choice>informations.size() || choice<1){
                System.out.println("There is no service like this number");
                continue;
            }
            break;
        }
        String password = informations.get(choice - 1).getPassword();
        StringBuilder infoAboutPassword = new StringBuilder();

        int points = 0;
        if(password.length()>=12){
            points++;
            infoAboutPassword.append("Length:                     " + password.length() + "     ✔\n" );
        }else {
            infoAboutPassword.append("Length:                     " + password.length() + "     ❌\n" );
        }
        boolean bLetters = false;
        boolean sLetters = false;
        boolean isNumbers = false;
        boolean isSpecialCharacters = false;

        int intbLetters = 0;
        int intsLetters = 0;
        int intisNumbers = 0;
        int intisSpecialCharacters = 0;


        for(int j=0;j< password.length();j++){
            char character = password.charAt(j);
            if(Character.isUpperCase(character)){
                bLetters = true;
                intbLetters++;
            }
            if (Character.isLowerCase(character)) {
                sLetters = true;
                intsLetters++;
            }
            if (Character.isDigit(character)) {
                isNumbers = true;
                intisNumbers++;
            }
            if (!Character.isLetterOrDigit(character)) {
                isSpecialCharacters = true;
                intisSpecialCharacters++;
            }
        }
        if(sLetters){
            points++;
            infoAboutPassword.append("Lowercase letters:         " + intsLetters + "     ✔\n");
        }else {
            infoAboutPassword.append("Lowercase letters:         " + intsLetters + "     ❌\n");
        }
        if(bLetters){
            points++;
            infoAboutPassword.append("Uppercase letters:         " + intbLetters + "     ✔\n");
        }else {
            infoAboutPassword.append("Uppercase letters:         " + intbLetters + "     ❌\n");
        }
        if(isNumbers){
            points++;
            infoAboutPassword.append("Numbers:                   " + intisNumbers + "     ✔\n");
        }else {
            infoAboutPassword.append("Numbers:                   " + intisNumbers + "     ❌\n");
        }
        if(isSpecialCharacters){
            points++;
            infoAboutPassword.append("Special characters:        " + intisSpecialCharacters + "     ✔\n");
        }else {
            infoAboutPassword.append("Special characters:        " + intisSpecialCharacters + "     ❌\n");
        }
        System.out.println(infoAboutPassword);
        System.out.println("Score: " + points + "/5");


        boolean reused = false;

        for (PasswordEntry entry : informations) {
            if (password.equals(entry.getPassword()) && !entry.equals(informations.get(choice - 1))) {
                if (!reused) {
                    System.out.println("This password is also used for:");
                    reused = true;
                }
                System.out.println("- " + entry.getService());
            }
        }

        if (!reused) {
            System.out.println("This password is not reused.");
        }


    }

}