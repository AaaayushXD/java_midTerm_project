import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Store store = new Store();
            store.features();
        } catch (IOException e) {
            System.out.println("Something went wrong. Please try again" );
        }
    }
}

