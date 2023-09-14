import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager extends LogManager{

    List<Items> itemsList = new ArrayList<>();

    //to validate the date entered by the user
    public String todayDate() {
        return LocalDate.now().toString();
    }

    /// to add new item to the array list and file
    public void addToListAndFile(String name, double quantity, double price, String date) {
        Items item = new Items(name, quantity, price, date);
        itemsList.add(item);
        saveToFile();
    }

    public void saveToFile() {
        try {
            File file = new File("store.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            int index = itemsList.size() - 1;
            String fileLine = "Product name: " + itemsList.get(index).getName()
                    + ", Quantity (in stock):  " + itemsList.get(index).getQuantity()
                    + ", Price (in NRs): " + itemsList.get(index).getPrice()
                    + ", Date (yyyy-mm-dd): " + itemsList.get(index).getDate();
            bufferedWriter.write(fileLine);
            bufferedWriter.newLine();

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while saving data to file");
            e.printStackTrace();
        }
    }
    public void removeFromFile(String name) {
        try{
            File file = new File("store.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Items item: itemsList) {
                if(item.getName().equalsIgnoreCase(name)) {
                    String output = "Item successfully deleted: " +item.getName();
                    outputText(output);
                }
                else {
                    String outputLine = "Product name: " + item.getName()
                            + ", Quantity (in stock):  " + item.getQuantity()
                            + ", Price (in NRs): " + item.getPrice()
                            + ", Date (dd-mm-yyyy): " + item.getDate();
                    bufferedWriter.write(outputLine);
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Couldn't remove item from the file.");
            e.printStackTrace();
        }
    }
    public void userInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of product: ");
        String name = scanner.nextLine();
        System.out.println("Enter quantity of product: ");
        double quantity = scanner.nextDouble();
        System.out.println("Enter price (in NRs): ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        String date = todayDate();

        addToListAndFile(name, quantity, price, date);
        updateLog(name, "added", date);
    }
    public void loadFromFile() {
        try {
            File file = new File("store.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            itemsList.removeAll(itemsList);
            while((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 4) {
                    String name = parts[0].split(": ")[1];
                    double quantity = Double.parseDouble(parts[1].split(": ")[1]);
                    double price = Double.parseDouble(parts[2].split(": ")[1]);
                    String date = parts[3].split(": ")[1];
                    Items item = new Items(name, quantity, price, date);
                    itemsList.add(item);
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e)  {
            System.out.println("Couldn't read from file. Something went wrong.");
            e.printStackTrace();
        }
    }


}
