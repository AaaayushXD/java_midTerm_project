import java.io.*;

public class LogManager extends Output{
    public double getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(double bankBalance) {
        this.bankBalance = bankBalance;
    }

    private double bankBalance ;
    public void updateLog(String name, String action, String date) {
        try{
            File file = new File("log.txt");
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(name + " is " + action + " on " +date + "\n");
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while updating the log file");
            e.printStackTrace();
        }
    }

    public void updateBalance(double updatedAmount, String action, String date) {
        try {
            File file = new File("transaction.txt");
            File tmpFile = new File("tmp.txt");
            FileWriter fileWriter = new FileWriter(tmpFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bankBalance = loadBalance();

            if(action.equalsIgnoreCase("added")) {
                bankBalance += updatedAmount;
                outputText("New amount = "+bankBalance);
            } else if(action.equalsIgnoreCase("deducted")) {
                bankBalance -= updatedAmount;
                outputText("New Amount = " +bankBalance);
            }
            bufferedWriter.write("Total Balance = Rs " + bankBalance);
            bufferedWriter.newLine();
            bufferedWriter.write("Rs " +updatedAmount+ " is " + action + " on " + date);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
            file.delete();
            tmpFile.renameTo(file);

        } catch (IOException e) {
            System.out.println("Something went wrong while updating balance.");
            e.printStackTrace();
        }
    }

    public double loadBalance() throws IOException {
        File orginalFile = new File("transaction.txt");
        if(!orginalFile.exists()) {
            createTransactionFile();
        }
        FileReader fileReader = new FileReader(orginalFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        double bankBalance = 1000.0;
        while((line = bufferedReader.readLine()) !=null) {
            if(line.startsWith("Total")) {
                String[] parts = line.split("Rs ");
                bankBalance = Double.parseDouble(parts[1]);
                break;
            }
        }
        bufferedReader.close();
        fileReader.close();
        return bankBalance;
    }

    public void createTransactionFile() {
        try {
            File file = new File("transaction.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Total Balance = Rs "+ 1000.0);
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
