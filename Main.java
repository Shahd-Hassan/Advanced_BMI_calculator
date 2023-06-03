import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("///////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("/////////////////---> WELCOME TO YOUR >> BMI CALCULATOR << <------/////////////////////////");
        System.out.println("###########################################################################################");
        System.out.println();

        int height = 5;
        int width = (2 * height) - 1;

            int i, j, half = (height / 2);
            for (i = 0; i < height; i++)
            {
                System.out.printf("*");
                for (j = 0; j < width; j++)
                {
                    if ((i == 0 || i == height - 1 || i == half)
                            && j < (width - 2))
                        System.out.printf("*");
                    else if (j == (width - 2)
                            && !(i == 0 || i == height - 1
                            || i == half))
                        System.out.printf("*");
                    else
                        System.out.printf(" ");
                }
                System.out.printf("\n");
            }
            System.out.println();
        int h, k, counter = 0;
        for (h = 0; h < height; h++)
        {
            System.out.printf("*");
            for (k = 0; k <= height; k++)
            {
                if (k == height)
                    System.out.printf("*");
                else if (k == counter
                        || k == height - counter - 1)
                    System.out.printf("*");
                else
                    System.out.printf(" ");
            }
            if (counter == height / 2)
            {
                counter = -99999;
            }
            else
                counter++;
            System.out.printf("\n");
        }
        System.out.println();
        int s, l;
        for (s= 0; s < height; s++)
        {
            for (l = 0; l < height; l++)
            {
                if (s == 0 || s == height - 1)
                    System.out.printf("*");
                else if (l == height / 2)
                    System.out.printf("*");
                else
                    System.out.printf(" ");
            }
            System.out.printf("\n");
        }
        System.out.println();
        System.out.println();
        System.out.println("BMI:-\n" +
                "               Body mass index. A measure that relates body weight to height.\n      BMI is sometimes used to measure total body fat and whether a person is a healthy weight.\n Excess body fat is linked to an increased risk of some diseases including heart disease and some cancers.");
        System.out.println();
        System.out.println("            LET'S GET STARTED ! .... ");
        System.out.println(">>You Will Have To Enter Your Data To Accurately Calculate Your Body Mass Index ::: ");
        System.out.println();
        System.out.println("####WARNING: IF INVALID INPUT IS ENTERED, THE PROGRAM WILL STOP!###");
        System.out.println();
        System.out.println("Please Note ! you will enter First:");
        System.out.println("The Number Of Persons You want to calculate Their BMI.");
        System.out.println();
        System.out.println("And Then For Each Person Enter his/her Data Which are:");
        System.out.println("   A.Name.\n   B.Age(in years)\n   C.Weight(in kilograms)\n   D.Height(in centimeters)\n   E.Gender");

        Scanner input = new Scanner(System.in);
        System.out.print("Enter The Number of persons:");
        int Np = input.nextInt();
        double femalecounter=0.0;//female number
        double malecounter=0.0;//male number
        double newBMRfemale=0.0;//female
        double newBMRmale=0.0;//male
        double highestBMI=0.0;
        String Hname=null;
        double Hage=0.0;
        double Hweight=0.0;
        double Hheight=0.0;
        String Hgender=null;
        double HBMR=0.0;
        double maleAverage=0.0;
        double femaleAverage=0.0;


            try {

                for (int x = 0; x < Np; x++) {
                    System.out.print("Enter The Name:");
                    String Name = input.next();
                    System.out.print("Enter Age:");
                    int Age = input.nextInt();
                    if(Age<0){
                        throw new IllegalArgumentException("INVALID INPUT");
                    }
                    System.out.print("Enter Weight:");
                    double Weight = input.nextInt();
                    if(Weight<0){
                        throw new IllegalArgumentException("INVALID INPUT");
                    }
                    System.out.print("Enter Height:");
                    double Height = input.nextInt();
                    if(Height<0){
                        throw new IllegalArgumentException("INVALID INPUT");
                    }
                    System.out.print("Enter Gender:");
                    String Gender = input.next();
                    double BMR = 0.0;//BMR
                    double BMI = 0.0;//BMI
                    String Status = null;//STATUS
                    boolean calcavg = false;



                    Height = Height / 100;
                    BMI = Weight / Math.pow(Height, 2);

                    if (BMI < 18.5) {
                        Status = "UnderWeight // Health in danger ## need to be in optimalWeight!";
                    }
                    if (BMI >= 18.5 && BMI <= 25) {
                        Status = "OptimalWeight // Health is Normal!";
                        calcavg = true;

                    }
                    if (BMI > 25) {
                        Status = "OverWeight // Health in danger ## need to be in optimalWeight!";
                    }


                    if (Gender.equalsIgnoreCase("male")) {//Male
                        BMR = 66 + 13.7 * Weight + 5 * Height - 6.8 * Age;
                        if (BMI >= 18.5 && BMI <= 25) {
                            Status = "OptimalWeight // Health is Normal!";
                            calcavg = true;
                            malecounter += 1;
                            newBMRmale += BMR;
                        }


                    } else if (Gender.equalsIgnoreCase("female")) {//Female
                        BMR = 655 + 9.6 * Weight + 1.8 * Height - 4.7 * Age;
                        if (BMI >= 18.5 && BMI <= 25) {
                            Status = "OptimalWeight // Health is Normal!";
                            calcavg = true;
                            femalecounter += 1;
                            newBMRfemale += BMR;


                        }

                    } else {
                        System.out.println("Invalid gender input!");
                        System.exit(1); // Exit the program if the gender is invalid
                    }

                    while (calcavg) {
                        maleAverage = calculateAverage(newBMRmale, malecounter);
                        femaleAverage = calculateAverage(newBMRfemale, femalecounter);
                        calcavg = false;
                    }

                    try {
                        FileWriter writer = new FileWriter("C://Users//shahd's Laptop//Desktop//Assignment 2 _BMI_Rand//src/Data.txt", true);
                        writer.write("\n" + "The Data Of The Person is: " + "\n" + "Name:" + Name + "\n" + "Age:" + Age + "\n" + "Weight:" + Weight + "\n" + "Height:" + Height + "\n" + "Gender:" + Gender + "\n" + "\n");
                        writer.write(">>Your BMI is:" + BMI + "\n" + "\n");
                        writer.write(">>Your BMR(The Basal Metabolic Rate, amount of required calories for a body to perform daily functions) is:" + BMR + "\n" + "\n");
                        writer.write(">>Your Status--> " + Status + "\n");
                        writer.write("----------------------------------------------------");
                        if (BMI > highestBMI) {
                            highestBMI = BMI;
                            Hname = Name;
                            Hage = Age;
                            Hweight = Weight;
                            Hheight = Height;
                            Hgender = Gender;
                            HBMR = BMR;
                        }

                        writer.close();
                        System.out.println("Data Successfully Added to file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred while appending double to file: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
            catch (IllegalArgumentException e) {//error in entering negative numbers
                System.out.println(e.getMessage());
            }



            try {
                     FileOutputStream report = new FileOutputStream("C://Users//shahd's Laptop//Desktop//Assignment 2 _BMI_Rand//src/Report.dat",true);
                     DataOutputStream outputFile = new DataOutputStream(report);
                     outputFile.writeUTF("\n"+"The Highest BMI is : "+highestBMI);
                     outputFile.writeUTF("\n"+"The Data of the person with The Highest BMI is : ");
                     outputFile.writeUTF("\n"+"Name : "+Hname);
                     outputFile.writeUTF("\n"+"Age : "+Hage);
                     outputFile.writeUTF("\n"+"Weight : "+Hweight);
                     outputFile.writeUTF("\n"+"Height : "+Hheight);
                     outputFile.writeUTF("\n"+"Gender : "+Hgender);
                     outputFile.writeUTF("\n"+"BMR : "+HBMR);
                     outputFile.writeUTF("\n"+"Average Male BMR: " + maleAverage);
                     outputFile.writeUTF("\n"+"Average FeMale BMR: " + femaleAverage);
                     outputFile.close();
                     report.close();



               } catch (IOException | NumberFormatException e) {
                 e.printStackTrace();
                                   }

            //The details of the person with the highest BMI
        System.out.println("///////////////////////////////////////////////////////////////////////////////////////////");
            System.out.println("Person with highest BMI details : ");
            System.out.println();
            System.out.println("Name : "+Hname);
            System.out.println("Age : "+Hage);
            System.out.println("Weight : "+Hweight);
            System.out.println("Height : "+Hheight);
            System.out.println("Gender : "+Hgender);
            System.out.println("BMI(Highest) :"+highestBMI);
            System.out.println("BMR : "+HBMR);
            System.out.println("YOU HAVE TO TAKE CARE OF YOUR HEALTH! MAINTAIN OPTIMAL WEIGHT FOR A BETTER LIFE!!!");
            //The Average BMR per gender for optimal weights
        System.out.println();
            System.out.println("Average Male BMR(OPTIMALWEIGHTED): " + maleAverage);
            System.out.println("Average Female BMR(OPTIMALWEIGHTED): " + femaleAverage);

    }
    public static double calculateAverage(double in,double count1) {
        double averageBMR = in / count1;
        return averageBMR;
    }



}