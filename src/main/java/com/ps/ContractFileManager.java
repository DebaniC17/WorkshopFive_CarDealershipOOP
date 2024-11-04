package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class ContractFileManager {
    public static void saveContract(Contract contract) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("contract.csv", true))) {
            if (contract instanceof SalesContract) {
                SalesContract sale = (SalesContract) contract;

                String salesContractToAdd = String.format(
                        "SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%s|%.2f|%s|%.2f",
                        sale.getDate(),
                        sale.getCustomerName(),
                        sale.getCustomerEmail(),
                        sale.getVehicleSold().getVin(),
                        sale.getVehicleSold().getYear(),
                        sale.getVehicleSold().getMake(),
                        sale.getVehicleSold().getModel(),
                        sale.getVehicleSold().getVehicleType(),
                        sale.getVehicleSold().getColor(),
                        sale.getVehicleSold().getOdometer(),
                        sale.getVehicleSold().getPrice(),
                        //sale tax amount
                        sale.getVehicleSold().getPrice() * 0.05,
                        //recording fee
                        100.00,
                        // processing fee...
                        "processing fee",
                        sale.getTotalPrice(),
                        sale.isFinanced(),
                        sale.getMonthlyPayment()

                );
                bufferedWriter.write(salesContractToAdd);
            } else if (contract instanceof LeaseContract) {
                LeaseContract lease = (LeaseContract) contract;

                String leaseContractToAdd = String.format(
                        "LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f",

                        lease.getDate(),
                        lease.getCustomerName(),
                        lease.getCustomerEmail(),
                        lease.getVehicleSold().getVin(),
                        lease.getVehicleSold().getYear(),
                        lease.getVehicleSold().getMake(),
                        lease.getVehicleSold().getModel(),
                        lease.getVehicleSold().getVehicleType(),
                        lease.getVehicleSold().getColor(),
                        lease.getVehicleSold().getOdometer(),
                        lease.getVehicleSold().getPrice(),
                        lease.getExpectedEndingValue(),
                        lease.getLeaseFee(),
                        lease.getTotalPrice(),
                        lease.getMonthlyPayment()
                );
                bufferedWriter.write(leaseContractToAdd);
            }
            System.out.println("Contract added");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
