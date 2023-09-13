import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public abstract class StoreManager extends FileManager {


    public abstract void title();

    public abstract void menuPage();


    public void setBankBalance(double bankBalance) {
        this.bankBalance = bankBalance;
    }

    private double bankBalance = getBankBalance();


    /// Features
    public void addItems() {
        featureInfo("Add Items", "It is used to add new items to our stock");
        userInput();
        outputText("Item added.");
    }

    public void removeItem() {
        featureInfo("Remove item", "It is used to remove any existing item from the stock");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of product you want to remove: ");
        String name = scanner.nextLine();
        String date = todayDate();

        //removing from list
        loadFromFile();
        boolean itemRemoved = itemsList.removeIf(item -> item.getName().equalsIgnoreCase(name));
        if (itemRemoved) {
            String output = "Item deleted successfully: " + name;
            outputText(output);
            updateLog(name, "removed", date);
        } else {
            String output = "Item not found";
            outputText(output);
        }

        //removing from file
        removeFromFile(name);

    }

    public void updateItem() {
        featureInfo("Update item", "It is used to update price and quantity of any existing item from the stock.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of item to update: ");
        String name = scanner.nextLine();
        String date = todayDate();
        loadFromFile();
        for (int i = 0; i < itemsList.size(); i++) {
            Items item = itemsList.get(i);
            if (item.getName().equalsIgnoreCase(name)) {
                double oldQuantity = item.getQuantity();
                double oldPrice = item.getPrice();
                itemsList.remove(item);
                removeFromFile(item.getName());
                System.out.println("1. Quantity \n2. Price ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Enter updated quantity: ");
                        double quantity = scanner.nextDouble();
                        scanner.nextLine();
                        addToListAndFile(name, quantity, oldPrice, date);
                        String output = name + "'s quantity is updated from " + oldQuantity + " to " + quantity + " on " + date;
                        outputText(output);
                        break;
                    case 2:
                        System.out.println("Enter new Price: ");
                        double price = scanner.nextDouble();
                        scanner.nextLine();
                        addToListAndFile(name, oldQuantity, price, date);
                        String outputPrice = name + "'s price is updated from Rs" + oldPrice + " to Rs" + price + " on " + date;
                        outputText(outputPrice);
                        break;
                    default:
                        String outputInvalid = "Invalid choice. Choose 1 or 2.";
                        outputText(outputInvalid);
                }
                break;
            }
        }
        updateLog(name, "updated", date);
    }

    public void deleteFile() {
        featureInfo("Delete file", "It is used to delete file which holds store's stock information.");
        String fileName = "store.txt";
        File file = new File(fileName);
        if (!file.exists()) {
            outputText("File doesn't exist.");
        }
        String date = todayDate();
        outputText("Successfully deleted the file :" + fileName);
        updateLog(fileName, "deleted", date);
        file.delete();
    }
    public void sellItem() {
        featureInfo("Sell item", "It is used to hold information of any product being sold.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name of selling product: ");
        String name = scanner.nextLine();
        String date = todayDate();
        loadFromFile();

        for (int i = 0; i < itemsList.size(); i++) {
            Items item = itemsList.get(i);
            if (item.getName().equalsIgnoreCase(name)) {
                System.out.println("How many would you like: ");
                double quantity = scanner.nextDouble();
                scanner.nextLine();
                if (quantity <= item.getQuantity()) {
                    double cost = quantity * item.getPrice();
                    outputText("Great. It would cost you Rs " + cost);
                    double quantityLeft = item.getQuantity() - quantity;
                    itemsList.remove(item);
                    removeFromFile(name);
                    addToListAndFile(name, quantityLeft, item.getPrice(), date);
                    updateLog(quantity + " " + name, " sold", date);
                    updateBalance(cost, "added", date);
                } else if (item.getQuantity() == 0) {
                    outputText("Sorry, we are out of stock right now.");
                } else {
                    outputText("Sorry! Unfortunately we do not have enough in stock.");
                }
                break;
            }

        }
    }
    public void buyItem() throws IOException{
        featureInfo("Restock item" , "It is used to hold information about restocked items to store");
        bankBalance = loadBalance();
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to buy again? ");
        String name = scanner.nextLine();
        String date = todayDate();
        loadFromFile();
        for(int i = 0; i<itemsList.size(); i++) {
            Items item = itemsList.get(i);
            if(item.getName().equalsIgnoreCase(name)) {
                System.out.println("How many more do you want? ");
                double quantity = scanner.nextDouble();
                scanner.nextLine();
                double totalCost = quantity * item.getPrice();
                if(bankBalance >= totalCost) {
                    double newQuantity = item.getQuantity() + quantity;
                    outputText(quantity + " " + name + " is added to your stock.");
                    System.out.println("Total " +name+" in your stock is " + newQuantity);
                    itemsList.remove(item);
                    removeFromFile(name);
                    addToListAndFile(name,newQuantity, item.getPrice(), date);
                    updateLog(quantity + " " +  name, " bought", date);
                    updateBalance(totalCost, "deducted", date);
                } else {
                    outputText("Insufficient balance. Please try again.");
                }
                break;
            }
        }
    }
    public void viewItem() {
        featureInfo("View items", "It is used to view all items from stock");
        loadFromFile();
        System.out.println("-------------Result---------------");
        System.out.println("----------------------------------");
        for(Items item: itemsList) {
            System.out.println("Product name: " + item.getName()
                    + ", Quantity (in stock):  " + item.getQuantity()
                    + ", Price (in NRs): " + item.getPrice()
                    + ", Date (yyyy-mm-dd): " + item.getDate());
        }
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
    }
    public void exit() {
        featureInfo("Exit", "It is used to exit from the software");
        outputText("See you soon ! Bye");
    }


}
