import Controll.UserRepository;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.User;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Welcom to User Management System");
            System.out.println("=================================");
            System.out.println("1. Create User");
            System.out.println("2. Search User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Display All User");
            System.out.println("6. Exit");
            System.out.println("=================================");
            System.out.print("Please select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 1:
                    userRepository.createUser();
                    break;
                case 2:
                    userRepository.searchUser();
                    break;
                case 3:
                    userRepository.updateUser();
                    break;
                case 4:
                    userRepository.deleteUser();
                    break;
                case 5:
                    userRepository.displayUsers();
                    break;
                case 6:
                    return;
            }
        }
    }
}