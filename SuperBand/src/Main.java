import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("******* Welcome to Malaysia SuperBand ********");
        System.out.println();

        System.out.print("Please enter the number of customers: ");
        int numCustomers = input.nextInt();
        
        double totalCharges = 0;
        int numPackageA = 0;
        int numPackageB = 0;
        String highestPayer = "";
        double highestCharge = 0;

        System.out.println();

        for (int i = 1; i <= numCustomers; i++) {
//            flush the buffer to prevent skipping of input
            input.nextLine();
            System.out.println("Customer " + i);
            System.out.print("Enter Name: ");
            String name = input.nextLine();
            System.out.print("Enter Package type (A - advance, B - Basic): ");
            char packageType = input.next().charAt(0);
            packageType = Character.toUpperCase(packageType);
            System.out.print("Enter Total Internet use (in MB): ");
            int internetUse = input.nextInt();
            double charge = calCharge(packageType, internetUse);
            totalCharges += charge;
            if (packageType == 'A') {
                numPackageA++;
            } else if (packageType == 'B') {
                numPackageB++;
            }
            if (charge > highestCharge) {
                highestCharge = charge;
                highestPayer = name;
            }
            System.out.printf("Total to be paid by %s: RM %d\n", name, (int)charge);
            System.out.println();
        }
        System.out.printf("Total Charges: RM %.2f\n", totalCharges);
        System.out.printf("Number of customers for package A: %d\n", numPackageA);
        System.out.printf("Number of customers for package B: %d\n", numPackageB);
        System.out.printf("The customer with the highest charge is %s\n", highestPayer);

        input.close();
    }

    public static double calCharge(char packageType, int internetUse) {
        double basicCharge = 48;
        double advancedCharge = 88;
        double extraChargeRateA = 0.05;
        double extraChargeRateB = 0.10;
        int quotaA = 10 * 1000;
        int quotaB = 6 * 1000;
        double extraChargeA = (internetUse - quotaA) * extraChargeRateA;
        double extraChargeB = (internetUse - quotaB) * extraChargeRateB;

        if (packageType == 'A') {
            if (internetUse >= quotaA) {
                return advancedCharge + extraChargeA;
            } else {
                return advancedCharge;
            }
        } else if (packageType == 'B') {
            if (internetUse >= quotaB) {
                return basicCharge + extraChargeB;
            } else {
                return basicCharge;
            }
        } else {
            return -1; // invalid package type
        }
    }
}