public class PasswordEntry {

    private String service;
    private String login;
    private String password;
    private String URL;

    PasswordEntry(String service, String login,String password, String URL){
        this.service = service;
        this.login = login;
        this.password = password;
        this.URL = URL;

    }
    PasswordEntry(){

    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setService(String service){
        this.service = service;
    }
    public void setLogin(String login){
        this.login=login;
    }
    public void setURL(String URL){
        this.URL = URL;
    }

    public String toFormattedString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(
                "Service: " + service
                + "\nLogin: "  + login
                + "\nPassword: " + password
                + "\nURL: "
                + URL + "\n"
        );
        return  stringBuilder.toString();

    }
    public String getService(){
        return service;
    }
    public  String getPassword(){
        return password;
    }
    public String getLogin(){
        return login;
    }
    public String getURL(){
        return URL;
    }


}
