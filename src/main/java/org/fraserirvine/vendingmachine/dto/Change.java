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

        BigDecimal thisBD = amount;

        while (true) {
            if (bdCompare(thisBD,2.0) >= 0) {
                this.twoPound++;
                thisBD = thisBD.subtract(BigDecimal.valueOf(2.0));
            } else if (bdCompare(thisBD, 1.0) >= 0) {
                this.onePound++;
                thisBD = thisBD.subtract(BigDecimal.valueOf(1.0));
            } else if (bdCompare(thisBD,0.50) >= 0){
                this.fiftyPence++;
                thisBD = thisBD.subtract(BigDecimal.valueOf(0.5));
            } else if (bdCompare(thisBD, 0.20) >= 0) {
                this.twentyPence++;
                thisBD = thisBD.subtract(BigDecimal.valueOf(0.20));
            } else if (bdCompare(thisBD, 0.10) >= 0) {
                this.tenPence++;
                thisBD = thisBD.subtract(BigDecimal.valueOf(0.10));
            } else if (bdCompare(thisBD, 0.05) >= 0) {
                this.fivePence++;
                thisBD = thisBD.subtract(BigDecimal.valueOf(0.05));
            } else if (bdCompare(thisBD, 0.02) >= 0) {
                this.twoPence++;
                thisBD = thisBD.subtract(BigDecimal.valueOf(0.02));
            } else if (bdCompare(thisBD, 0.01) >= 0) {
                this.onePenny++;
                thisBD = thisBD.subtract(BigDecimal.valueOf(0.01));
            } else {
                break;
            }
        }
    }

    private int bdCompare(BigDecimal i, double denomination) {
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

    public boolean hasNoChange() {
        return twoPound == 0 &&
                onePound == 0 &&
                fiftyPence == 0 &&
                twentyPence == 0 &&
                tenPence == 0 &&
                fivePence == 0 &&
                twoPence == 0 &&
                onePenny == 0;
    }
}
