package com.ps;

public class SalesContract extends Contract {
    private static final double SALES_TAX_AMOUNT = 0.05;
    private static final double RECORDING_FEE = 100.00;
    private boolean isFinanced;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicleSold);
        this.isFinanced = isFinanced;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    @Override
    public double getTotalPrice() {
        double originalPrice = getVehicleSold().getPrice();
        double salesTax = originalPrice * SALES_TAX_AMOUNT;
        double processingFee;

        if (originalPrice < 10000) {
            processingFee = 295.00;
        } else {
            processingFee = 495.00;
        }
        double totalPrice = originalPrice + salesTax + RECORDING_FEE + processingFee;
        return totalPrice;
    }

    @Override
    public double getMonthlyPayment() {

        if (!isFinanced) {
            return 0.0;
        }
        double totalPrice = getTotalPrice();
        double annualInterestRate;
        int loanTerm;

        if (totalPrice >= 10000) {
            annualInterestRate = 0.045;
            loanTerm = 48;

        } else {
            annualInterestRate = 0.0525;
            loanTerm = 24;
        }

        double monthlyInterestRate = annualInterestRate / 12;
        return (totalPrice * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, - loanTerm));

    }

}
