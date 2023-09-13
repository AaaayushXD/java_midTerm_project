import java.io.IOException;
import java.util.Scanner;

public class Store extends StoreManager{

    public void title() {
        System.out.println("----------------------------------");
        System.out.println("Logged in as Aayush Lamichhane - LC00017001850");
        System.out.println("This is store's stock management software");
        System.out.println("----------------------------------");
    }
    public void menuPage() {
        System.out.println("------------------------------");
        System.out.println("1. Add new items to your stock");
        System.out.println("2. Update any item from the stock.");
        System.out.println("3. Buy any item to add to your stock.");
        System.out.println("4. Sell any item from your stock");
        System.out.println("5. View all your items.");
        System.out.println("6. Remove any item from stock.");
        System.out.println("7. Delete file");
        System.out.println("8. Exit");
        System.out.println("------------------------------");
    }

    public void features()  throws IOException{
        int choice = 0;
        while(choice != 8) {
            title();
            menuPage();
            System.out.println("Please select any option from given list (1-8): ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    title();
                    addItems();
                    break;
                case 2:
                    title();
                    updateItem();
                    break;
                case 3:
                    title();
                    buyItem();
                    break;
                case 4:
                    title();
                    sellItem();
                    break;
                case 5:
                    title();
                    viewItem();
                    break;
                case 6:
                    title();
                    removeItem();
                    break;
                case 7:
                    title();
                    deleteFile();
                    break;
                case 8:
                    title();
                    exit();
                    break;
                default:
                    System.out.println("Invalid input. Please select from given option only. (1-8). Thank you!!");
            }
        }
    }
}
