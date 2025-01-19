package Controll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import model.User;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class UserRepository {
    private static final String TELEGRAM_API_URL = "https://api.telegram.org/bot7812970096:AAFplgy_gt9I8T6gsPQIey933mR1hPd59YM/sendMessage";
    private static final String CHAT_ID = "865964563";
    private List<User> users = new ArrayList<>();
    public void createUser(){

        Random random = new Random();
        int id = random.nextInt(10000);

        String uuid = UUID.randomUUID().toString();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Your name: ");
        String name = scanner.nextLine();

        System.out.println("Your email: ");
        String email = scanner.nextLine();
        User user = new User(id, UUID.fromString(uuid), name, email, false);
        users.add(user);
        System.out.println("Successful Created");
        sendTelegram(user);
    }
    public void sendTelegram(User user) {
      // User user = new User();
        String message = "New User Created: " + user.getName();
        try {
            URL url = new URL(TELEGRAM_API_URL + "?chat_id=" + CHAT_ID + "&text=" + message);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.connect();
            int responseCode = connection.getResponseCode();
            System.out.println("Telegram response code: " + responseCode);
            connection.disconnect();
            System.out.println("Notification sent to Telegram bot!");
        } catch (Exception e) {
            System.out.println("Error sending message to Telegram: " + e.getMessage());
        }
    }
    public void searchUser(){
        System.out.println("Entered UUID: ");
        String uuid = new Scanner(System.in).nextLine();
        for(User user : users){
            if(user.getUuid().toString().equals(uuid)){
                System.out.println(user);
            }
        }
    }
    public void deleteUser(){
        System.out.println("Entered UUID: ");
        String uuid = new Scanner(System.in).nextLine();
        for(User user : users){
            if(user.getUuid().toString().equals(uuid)){
                users.remove(user);
                System.out.println("Successful Deleted");
            }
        }
    }
    public void updateUser(){
        System.out.println("Entered UUID: ");
        String uuid = new Scanner(System.in).nextLine();
        for(User user : users){
            if(user.getUuid().toString().equals(uuid)){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Your new name: ");
                String name = scanner.nextLine();
                System.out.println("Your new email: ");
                String email = scanner.nextLine();
                users.remove(user);
                users.add(new User(user.getId(), user.getUuid(), name, email, user.isDeleted()));
                System.out.println("Successful Updated");
            }
        }
    }
    public void displayUsers(){
        System.out.println("=================All User ================");
        Table table = new Table(5 , BorderStyle.UNICODE_BOX_DOUBLE_BORDER , ShownBorders.ALL);
        String coll [] = { "ID" , "UUID" , "NAME" , "EMAIL" , "Active"};
        for(int i =0; i<coll.length; i++){
            table.addCell(coll[i] , new CellStyle(CellStyle.HorizontalAlign.center));
            table.setColumnWidth( i , 20 , 50 );
        }
        for (User user : users) {
            if (!user.isDeleted()) {
                table.addCell(String.valueOf(user.getId()));
                table.addCell(String.valueOf(user.getUuid()));
                table.addCell(user.getName());
                table.addCell(user.getEmail());
                table.addCell(user.isDeleted() ? "No" : "Yes");
            }
        }
        System.out.println(table.render());

    }
}
