package org.fraserirvine.vendingmachine.dto;

import java.math.BigDecimal;

public class Change {

    private int twoPound;
    private int onePound;
    private int fiftyPence;
    private int twentyPence;
    private int tenPence;
    private int fivePence;
    private int twoPence;
    private int onePenny;


    public Change(BigDecimal amount) {
        //set all values to be zero initially
        this.twoPound = 0;
        this.onePound = 0;
        this.fiftyPence = 0;
        this.twentyPence = 0;
        this.tenPence = 0;
        this.fivePence = 0;
        this.twoPence = 0;
        this.onePenny = 0;

        BigDecimal workingDecimal = amount;

        while (true) {
            if (bigDecimalComparison(workingDecimal,2.0) >= 0) {
                this.twoPound++;
                workingDecimal = workingDecimal.subtract(BigDecimal.valueOf(2.0));
            } else if (bigDecimalComparison(workingDecimal, 1.0) >= 0) {
                this.onePound++;
                workingDecimal = workingDecimal.subtract(BigDecimal.valueOf(1.0));
            } else if (bigDecimalComparison(workingDecimal,0.50) >= 0){
                this.fiftyPence++;
                workingDecimal = workingDecimal.subtract(BigDecimal.valueOf(0.5));
            } else if (bigDecimalComparison(workingDecimal, 0.20) >= 0) {
                this.twentyPence++;
                workingDecimal = workingDecimal.subtract(BigDecimal.valueOf(0.20));
            } else if (bigDecimalComparison(workingDecimal, 0.10) >= 0) {
                this.tenPence++;
                workingDecimal = workingDecimal.subtract(BigDecimal.valueOf(0.10));
            } else if (bigDecimalComparison(workingDecimal, 0.05) >= 0) {
                this.fivePence++;
                workingDecimal = workingDecimal.subtract(BigDecimal.valueOf(0.05));
            } else if (bigDecimalComparison(workingDecimal, 0.02) >= 0) {
                this.twoPence++;
                workingDecimal = workingDecimal.subtract(BigDecimal.valueOf(0.02));
            } else if (bigDecimalComparison(workingDecimal, 0.01) >= 0) {
                this.onePenny++;
                workingDecimal = workingDecimal.subtract(BigDecimal.valueOf(0.01));
            } else {
                break;
            }
        }
    }

    private int bigDecimalComparison(BigDecimal i, double denomination) {
        return i.compareTo(BigDecimal.valueOf(denomination));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Change: | ");
        if (twoPound != 0) {
            sb.append("£2: ").append(twoPound).append(" | ");
        }
        if (onePound != 0) {
            sb.append("£1: ").append(onePound).append(" | ");
        }
        if (fiftyPence != 0) {
            sb.append("50p: ").append(fiftyPence).append(" | ");
        }
        if (twentyPence != 0) {
            sb.append("20p: ").append(twentyPence).append(" | ");
        }
        if (tenPence != 0) {
            sb.append("10p: ").append(tenPence).append(" | ");
        }
        if (fivePence != 0) {
            sb.append("5p: ").append(fivePence).append(" | ");
        }
        if (twoPence != 0) {
            sb.append("2p: ").append(twoPence).append(" | ");
        }
        if (onePenny != 0) {
            sb.append("1p: ").append(onePenny).append(" | ");
        }
        return sb.toString();
    }

    //getter methods for testing purposes


    public int getTwoPound() {
        return twoPound;
    }

    public int getOnePound() {
        return onePound;
    }

    public int getFiftyPence() {
        return fiftyPence;
    }

    public int getTwentyPence() {
        return twentyPence;
    }

    public int getTenPence() {
        return tenPence;
    }

    public int getFivePence() {
        return fivePence;
    }

    public int getTwoPence() {
        return twoPence;
    }

    public int getOnePenny() {
        return onePenny;
    }
}
