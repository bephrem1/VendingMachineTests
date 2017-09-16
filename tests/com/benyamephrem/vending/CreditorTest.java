package com.benyamephrem.vending;

///AAA's of testing code in JUnit
//Arrange: Arrange your objects so they are ready to be tested
//Act: Cause the behaviour that you are testing to happen
//Assert: Verify that the expected behaviour occurred
//You separate each line by double spaces

//"Correct" way to find edge cases
//Conform: What happens when input doesn't conform to expected format
//Ordering: Is order of values returned in the expected order
//Range: What if input is too big or too small?
//Reference: Does this unit reference code from another unit? Is the other unit tested?
//Existence: What happens when something is null?
//Cardinality: What happens with 1 value, what happens with multiple values?
//Time: Understand what order things must run and when each should happen

import org.junit.Before;
import org.junit.Test;
//Having the "static up here allows you to just type the method below without the class name
//before the method as with usual static calls.
import static org.junit.Assert.*;

public class CreditorTest {

    private Creditor creditor;

    //@Before annotation is ran before the tests are gathered up and ran in any
    //random order. (@After "tearDown methods" destroy any files/things made from
    //tests and are ran after all tests)
    @Before
    public void setUp() throws Exception {
        creditor = new Creditor();
    }

    //If no error happen in the method then it is assumed to pass.
    //The fail() method forces the code to fail.

    @Test
    public void addingFundsIncrementsAvaliableFunds() throws Exception {
        //Notice how the "creditor" object did not require an import
        //because the tests folder is in the same package (com.teamtreehouse.vending)

        creditor.addFunds(25);  //Act
        creditor.addFunds(25);  //Act

        assertEquals(50, creditor.getAvailableFunds()); //Assert assertEquals(Expected Value, Actual Value)
                                                                 //Always look for the right assert methods to get helpful
                                                                 //errors

    }

    @Test
    public void refundingReturnsAllAvaliableFunds() throws Exception {
        creditor.addFunds(10);  //Arrange

        int refund = creditor.refund(); //Act

        assertEquals(10, refund); //Assert
    }

    @Test
    public void refundingResetsAvaliableFundsToZero() throws Exception {
        creditor.addFunds(10);  //Arrange

        creditor.refund(); //Act

        assertEquals(0, creditor.getAvailableFunds()); //Assert
    }

    @Test(expected = NotEnoughFundsException.class)
    public void deductingMoreThanCurrentFundsNotAllowed() throws Exception {
        creditor.addFunds(100); //Arrange

        creditor.deduct(110); //Act
    }
}