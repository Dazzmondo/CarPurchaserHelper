/*
Project: Car advice and recommendation tool
*/

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        new Driver();
    }
    Driver(){
        Scanner input = new Scanner(System.in); //Use of Scanner to enable inputs through keyboard
        //Created a CarPurchaserHelper Object called carPurchaserHelper
        CarPurchaserHelper carPurchaserHelper = new CarPurchaserHelper();

        //Calling welcome message from CarPuchaserHelper class
        System.out.println(carPurchaserHelper.printWelcomeMessage());

        //Loop Control Variable
        //Needed to set a number that wasn't 0
        //7 chosen because wasn't menu option or 6 which someone might press by mistake. Realised later this logic was redundant.
        int choice = 7;

        // using while loop for menu
        while (choice != 0) {
            //Menu that comes after welcome message
            System.out.print("=== Car Finance Calculator ===\n" +
                    "1. Calculate HP\n" +
                    "2. Calculate PCP\n" +
                    "3. Calculate Weekly Fuel Cost\n" +
                    "4. Calculate Monthly Cost\n" +
                    "5. Recommend Car Type\n" +
                    "0. Exit\n" +
                    "Choose an option:\n");

            //Taking user/keyoard input using Scanner
            choice = input.nextInt();  //update Loop Control Variable, will not terminate while not equal to 0

            //Using if keyword with boolean condition (choice = 1/HP selection)
            if (choice == 1){
                //Collect relevant input from user with Scanner for HP Calculation
                System.out.print("Enter car price: ");
                double hpPrice = input.nextDouble();
                System.out.print("Enter deposit: ");
                double hpDeposit = input.nextDouble();
                System.out.print("Enter annual interest rate (%): ");
                double hpInterest = input.nextDouble();
                System.out.print("Enter term in years: ");
                int hpTerm = input.nextInt();

                double hpMonthly = carPurchaserHelper.calculateHP(hpPrice, hpDeposit, hpInterest, hpTerm); //call calculateHP
                //ADDED FUNCTIONALITY: added 2 decimal format to replicate €0.00 (Source: https://stackoverflow.com/questions/2538787/how-to-print-a-float-with-2-decimal-places-in-java)
                System.out.println("Monthly HP payment: €" + String.format("%.2f", hpMonthly));

                //**ADDED FUNCTIONALITY
                // I originally left the code like this, however it would immediately loop back to the menu,
                // which meant the user had to scroll up past the menu in the terminal to check their answer.
                //I checked for ways to address this. One I found was to create a timed pause,
                //but this required the use of catch which was a constraint. (source: https://medium.com/@AlexanderObregon/how-to-pause-a-java-program-for-a-few-seconds-safely-and-correctly-7b49c71576f9)
                //Therefore, I instead decided to enter a new string asking the user to press enter to return to menu.
                System.out.println("Please press enter to return to menu");
                input.nextLine();
                //I originally just used this once, but it had no effect. Upon further investigation,
                //it was apparently because when a user enters input.nextLine after input.nextInt
                //it actually leaves a "\n" on the following line. (Source: https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo)
                //Entering just one input.nextLine() reads "\n" immediately and instantly returns to the menu as before.
                //To address this, I entered input.nextLine() a 2nd time which paused and gave the user time to press enter.
                //I recreated this for each of the options.
                input.nextLine();
            }

            //If user chooses PCP/choice == 2
            else if (choice == 2){
                //Collect relevant input from user with Scanner for PCP Calculation
                System.out.print("Enter car price: ");
                double pcpPrice = input.nextDouble();
                System.out.print("Enter deposit: ");
                double pcpDeposit = input.nextDouble();
                System.out.print("Enter annual interest rate (%): ");
                double pcpInterest = input.nextDouble();
                System.out.print("Enter term in years: ");
                int pcpTerm = input.nextInt();
                System.out.print("Enter Guaranteed Minimum Future Value %: ");
                double gmfvPerCent = input.nextDouble();

                double pcpMonthly = carPurchaserHelper.pcpResultMonth(pcpPrice, pcpDeposit, pcpInterest, pcpTerm, gmfvPerCent); //call pcpResultMonth
                System.out.println("Monthly PCP payment: €" + String.format("%.2f",pcpMonthly)); //convert to 2 decimals

                //Pause to let user see answer before returning to menu
                System.out.println("Please press enter to return to menu");
                input.nextLine(); //buffer
                input.nextLine(); //waits for user to press enter
            }

            //If user chooses Fuel Cost/choice == 3
            else if (choice == 3) {
                //Collect relevant input from user with Scanner for Fuel Cost
                System.out.print("Enter km driven per week: ");
                double km = input.nextDouble();
                System.out.print("Enter cost of fuel per litre: €");
                double costFuel = input.nextDouble();

                double estFuelCostWeekly = carPurchaserHelper.fuelCostEstimator(km, costFuel);  //call fuelCostEstimator
                System.out.println("Your weekly fuel cost: €" + String.format("%.2f",estFuelCostWeekly)); //convert to 2 decimals

                //Pause to let user see answer before returning to menu
                System.out.println("Please press enter to return to menu");
                input.nextLine(); //buffer
                input.nextLine(); //waits for user to press enter
            }

            //If user chooses Monthly cost/choice == 4
            else if (choice == 4) {
                //Collect relevant input from user with Scanner for Monthly cost
                System.out.print("Enter price of car: ");
                double priceCar = input.nextDouble();
                System.out.print("Enter number of months to complete the payment: ");
                int monthNum = input.nextInt();

                double monthlyCost = carPurchaserHelper.calculateMonthlyCost(priceCar, monthNum); //call calculateMonthlyCost
                System.out.println("Monthly cost: €" + String.format("%.2f",monthlyCost)); //convert to 2 decimals

                //Pause to let user see answer before returning to menu
                System.out.println("Please press enter to return to menu");
                input.nextLine(); //buffer
                input.nextLine(); //waits for user to press enter
            }

            //If user chooses Recommend Car Type/choice == 5
            else if (choice == 5) {
                System.out.println("Enter the lifestyle that most suits you: family, adventurer, luxury or speed demon");
                input.nextLine(); //same issue as earlier, buffer to deal with "\n"
                String lifestyle = input.nextLine();

                String recommend = carPurchaserHelper.recommendCarType(lifestyle); //call recommendCarType
                System.out.println (recommend);

                //Pause to let user see answer before returning to menu
                System.out.println("Please press enter to return to menu");
                //Originally had another buffer input.nextLine() but because the last input wasn't input.nextInt(),
                // it was no longer an issue. Now it goes directly to press enter message.
                input.nextLine(); //Waits for user to press enter

                //Minor issue when somebody doesn't enter one of the proposed words.
                //Instead of asking you to re-enter, skips to press enter message.
                //Thought auto-correcting to what the user likely wanted based on letters typed would improve functionality but opted against it.
            }

            //After relevant input and calculation, program will loop back to menu

            }
        //If user selects Exit/choice == 0, will proceed to goodbye message
        if (choice == 0){
            //calls printGoodbyeMessage method from CarPurchaserHelper class
            carPurchaserHelper.printGoodbyeMessage();
        }
      }
    }