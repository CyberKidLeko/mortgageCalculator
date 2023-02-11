import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String fileName = "customerData.txt";

        System.out.print("Enter the number of customers: ");
        int numCustomers = input.nextInt();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < numCustomers; i++) {
                System.out.print("Enter customer name: ");
                String name = input.next();
                System.out.print("Enter customer age: ");
                int age = input.nextInt();
                System.out.print("Enter customer occupation: ");
                String occupation = input.next();
                bw.write("Customer list\n");

                bw.write(name + "\t" + age + "\t" + occupation);
                bw.newLine();

            }
            System.out.println("File written");
        } catch (IOException e) {
            System.out.println("Error writing to file " + fileName);
        }

        System.out.print("What would you like to retrieve (1 - names, 2 - ages, 3 - occupations)? ");
        int retrieveOption = input.nextInt();

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] customerData = line.split(" ");
                if (retrieveOption == 1) {
                    sb.append(customerData[0]).append(System.lineSeparator());
                } else if (retrieveOption == 2) {
                    sb.append(customerData[1]).append(System.lineSeparator());
                } else if (retrieveOption == 3) {
                    sb.append(customerData[2]).append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file " + fileName);
        }

        System.out.println("Retrieved data:");
        System.out.println(sb.toString());

        System.out.print("Would you like to store the retrieved data in a new text file (y/n)? ");
        String storeOption = input.next();
        if (storeOption.equals("y")) {
            System.out.print("Enter the file name: ");
            String newFileName = input.next();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(newFileName))) {
                bw.write(sb.toString());
                System.out.println("File written");
            } catch (IOException e) {
                System.out.println("Error writing to file " + newFileName);
                System.out.println("Done");
            }
        }
    }
}
