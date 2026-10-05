/*
Project: Car advice and recommendation tool
*/

public class CarPurchaserHelper {

    //Method for calculating HP
    public double calculateHP(double carPrice, double deposit, double annualInterest, int termYears) {
        double amountToFinance = carPrice - deposit; //deposit subtracted from car price
        double monthlyInterestRate = (annualInterest / 100) / 12; //Converts annual interest percentage into per month number
        int totalMonths = termYears * 12; //number of total months based on term years

        if (monthlyInterestRate == 0) {  //if no interest rate (wouldn't that be a nice world)
            return amountToFinance / totalMonths; //amount to finance divided by number of months for monthly cost
        }
        else {
            // Calculate (1 + r)^n using a loop
            double factor = 1; //multiply variable initialised to 1
            for (int i = 0; i < totalMonths; i++) {  //0 is set as initial Loop Control Variable i, i increases by 1 until it is no longer less than total months, terminating loop
                factor *= (1 + monthlyInterestRate);  //adds monthly interest
            }

            double monthlyPayment = amountToFinance * monthlyInterestRate * factor / (factor - 1);
            return monthlyPayment;  //returns the monthly payment factoring in interest rate
        }
    }


    //Method for calculating PCP
    public double pcpResultMonth(double carPrice, double deposit, double annualInterest, int termYears, double gmfvPercent) {
        double gmfv = carPrice * (gmfvPercent / 100);  //converts GMFV percentage into a full number to be represented in €, stored as gmfv
        double amountToFinance = carPrice - deposit - gmfv;  //amount to finance is the car price minus the deposit and gmfv

        double monthlyInterestRate = (annualInterest / 100) / 12;  //Converts annual interest percentage into per month number
        int totalMonths = termYears * 12;  //number of total months based on term years, 12 months equals 1 year

        double monthlyPayment; //monthlyPayment double declared

        if (monthlyInterestRate == 0) {  //if no interest
            monthlyPayment = amountToFinance / totalMonths; //monthly payment is amount to finance divided by number of months
        }
        else {
            // Calculate (1 + r)^n using a loop
            double factor = 1;  //multiply variable initialised to 1
            for (int i = 0; i < totalMonths; i++) {  //0 is set as initial Loop Control Variable i, i increases by 1 until it is no longer less than total months, terminating loop
                factor *= (1 + monthlyInterestRate);  //adds monthly interest
            }

            monthlyPayment = amountToFinance * monthlyInterestRate * factor / (factor - 1); //monthly payment factoring in interest rate
        }

        return  monthlyPayment; //returns monthlyPayment
    }

    //Method for Welcome Message to call in Driver class with Scanner. No parameters, returns a String
    public String printWelcomeMessage() {
        return "Welcome to the Car Helper Program!\n" +
                "Let's find the perfect car for you";
    }

    //Method for Goodbye Message to call in Driver class with Scanner. No parameters, returns a String
    public String printGoodbyeMessage(){
        return "Thank you for using our Car Helper program. We hope you enjoyed the experience. Goodbye!";
    }


    //Method to calculate monthly cost
    public double calculateMonthlyCost(double carPrice, int numMonths){
        double monthlyPayment = carPrice/numMonths;
        return monthlyPayment;
    }


    //Method to calculate weekly fuel cost
    public double fuelCostEstimator (double kmPerWeek, double fuelCost){
        double litresPerWeek = ((kmPerWeek / 100) * 6);
        return litresPerWeek * fuelCost;
    }


    //Method to recommend car based on user's lifestyle/input. 1 String parameter, returns a String.
    public String recommendCarType(String lifestyle) {
        //ADDED FUNCTIONALITY: Used ignore case to limit typing errors
        if (lifestyle.equalsIgnoreCase("family")) {
            return "Based on your requirement, we recommend a Ford Focus"; //Number 1 choice according to the Top Gear website. Good enough for me.
        }
        else if (lifestyle.equalsIgnoreCase("adventurer")) {
            return "Based on your requirement, we recommend a Subaru Outback"; //It was either Subaru or Toyota
        }
        else if (lifestyle.equalsIgnoreCase("luxury")) {
            return "Based on your requirement, we recommend a BMW i7"; //The i5 upgraded
        }
        else if (lifestyle.equalsIgnoreCase("speed demon")){
            return "Based on your requirement, we recommend a Koenigsegg Jesko Absolut"; //It reaches 310mph (499km/h)! It also looks like a Batmobile
        }
        else {
            return "Please enter one of the words provided"; //to address any incorrect/unacceptable inputs. Will loop back to menu.
        }
    }
}