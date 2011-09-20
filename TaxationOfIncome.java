/* Xiao G. Wu
 * CS111A - Assignment 4
 * 09/14/2011
 */

import java.text.DecimalFormat;

/** This class calculates income tax based on random income range */

public class TaxationOfIncome
{
    /** Main method */

    public static void main(String[] args)
    {
        // Named constants
        final double MIN_INCOME = 2000; // Minimum Income
        final double MAX_INCOME = 200000; // Maximum Income

        // Variables
        double income = 0.0;
        double tax = 0.0;
        double effTaxRate = 0.0;

        // Get random income
        income = getIncome(MIN_INCOME, MAX_INCOME);

        // Calculate income tax 
        tax = calculateTax(income); 

        // Calcuate effective tax rate
        effTaxRate = calculateEffectiveTaxRate(income, tax);

        // Display values

        // Create DecimalFormat objects
        DecimalFormat incomeFormatter = new DecimalFormat("$#,##0.00");
        DecimalFormat taxFormatter = new DecimalFormat("$#,##0");
        //DecimalFormat taxFormatter2 = new DecimalFormat("$#,##0.00");
        DecimalFormat effTaxRateFormatter = new DecimalFormat("#0%");
        //DecimalFormat effTaxRateFormatter2 = new DecimalFormat("0.0000");

        // Print values to standard out
        System.out.println("Income: " + incomeFormatter.format(income));
        System.out.println("Tax owed: " + taxFormatter.format(tax));
        //System.out.println("Tax: " + taxFormatter2.format(tax));
        System.out.println("Effective Tax Rate: " + effTaxRateFormatter.format(effTaxRate));
        //System.out.println("Effective Tax Rate: " + effTaxRateFormatter2.format(effTaxRate));

    }

    /** This method returns the income based on min max range args */
    public static double getIncome(double minIncome, double maxIncome)
    {
        // Use Math random number generator to set income between min max range
        return minIncome + (Math.random() * ((maxIncome - minIncome) + 1));
    }

    /** This method calculates the income tax based on income */
    public static double calculateTax(double income)
    {
        // Named constants
        final double AID_CUT_OFF = 10000.0; // Aid cut off
        final double SPECIAL_ASSESSMENT_CUT_OFF = 150000.0; // Special assessment cut off
        final double STANDARD_DEDUCTION = 15000.0; // Standard deduction
        final double B_RATE = 0.22; // B Bracket tax rate
        final double C_RATE = 0.36; // C Bracket tax rate
        final double SPECIAL_ASSESSMENT_FEE = 5000.0; // Special assessment fee

        // Income Ranges
        double A_RANGE_START = 0.0;
        double A_RANGE_CUT_OFF = 15000.0;
        double B_RANGE_START = A_RANGE_CUT_OFF;
        double B_RANGE_CUT_OFF = 100000.0;
        double C_RANGE_START = B_RANGE_CUT_OFF;
        double C_RANGE_CUT_OFF = income;

        // Tax brackets
        double B_BRACKET_START = B_RANGE_START - STANDARD_DEDUCTION;
        double B_BRACKET_CUT_OFF = B_RANGE_CUT_OFF - STANDARD_DEDUCTION;
        double C_BRACKET_START = C_RANGE_START - STANDARD_DEDUCTION;
        double C_BRACKET_CUT_OFF = C_RANGE_CUT_OFF - STANDARD_DEDUCTION;


        // Variables
        double tax = 0.0;
        double taxableIncome = 0.0;
        boolean eligibleForAid = false;
        boolean specialAssessment = false;

        // Check for aid
        if (income < AID_CUT_OFF)
            eligibleForAid = true;

        // Check for special assessment
        if (income > SPECIAL_ASSESSMENT_CUT_OFF)
            specialAssessment = true;

        // If eligible for aid no taxes due
        if (eligibleForAid)
            return tax;
        
        // Calculate taxableIncome by subtracting standard deduction
        taxableIncome = income - STANDARD_DEDUCTION;

        // Calculate tax
        
        // A Tax bracket
        // A Tax bracket pays no taxes
        
        // B Tax bracket
        // B Tax bracket pays 22%
        if (taxableIncome >= B_BRACKET_START && taxableIncome < B_BRACKET_CUT_OFF)
        {
           tax = taxableIncome * B_RATE; 
           return tax;
        }

        // C Tax bracket
        // C Tax bracket pays 22% on B Range income and 36% on C Range income 
        else if (taxableIncome >= C_BRACKET_START && taxableIncome <= taxableIncome)
        {
            // Calculate tax for first B Bracket money
            tax = B_BRACKET_CUT_OFF * B_RATE;
            // Minus B Bracket money from remaining taxable income
            taxableIncome -= B_BRACKET_CUT_OFF;
            // Calculate tax for second C Bracket money and add to B Bracket tax
            tax = tax + (taxableIncome * C_RATE);
            // Add special assessment fee if necessary
            if (specialAssessment)
                tax += SPECIAL_ASSESSMENT_FEE;
            return tax;
        }

        return tax;  // This should never be reached
    }

    /** This method calculates the effective tax rate based on tax owed and income */

    public static double calculateEffectiveTaxRate(double income, double taxOwed)
    {
        return taxOwed / income;
    }
}
