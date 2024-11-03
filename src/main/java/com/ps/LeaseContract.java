package com.ps;

public class LeaseContract extends Contract{
    private static final double EXPECTED_ENDING_VALUE_PERCENTAGE = 0.50;
    private static final double LEASE_FEE_PERCENTAGE = 0.07;
    private static final double ANNUAL_INTEREST_RATE = 0.04;
    private static final int LEASE_TERM = 36;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }
    //make own getters for e.e.v.p and l.f
    public double getExpectedEndingValue() {
        return getVehicleSold().getPrice() * EXPECTED_ENDING_VALUE_PERCENTAGE;
    }
    public double getLeaseFee() {
        return getVehicleSold().getPrice() * LEASE_FEE_PERCENTAGE;
    }

    @Override
    public double getTotalPrice() {
        double monthlyPayment = getMonthlyPayment();
        double totalLeaseCost = (monthlyPayment * LEASE_TERM) + getLeaseFee();
        return totalLeaseCost;
    }

    @Override
    public double getMonthlyPayment() {
        double originalPrice = getVehicleSold().getPrice();
        double expectedEndingValue = getExpectedEndingValue();

        //since it's a lease and not a complete purchase of the car
        double amountBeingFinanced = originalPrice - expectedEndingValue;

        //annual to monthly
        double monthlyInterestRate = ANNUAL_INTEREST_RATE/12;

        return (amountBeingFinanced * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -LEASE_TERM));

    }
}
