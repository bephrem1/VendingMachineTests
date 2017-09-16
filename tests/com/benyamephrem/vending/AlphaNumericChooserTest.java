package com.benyamephrem.vending;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

//FIRST testing mnemonic
//Fast: Tests should be fast
//Isolated: Should not rely on other code
//Repeatable: Should get same results everytime
//Self-Verifying: Tests must be fixable and trustable
//Timely: Make sure you tests throughout development

public class AlphaNumericChooserTest {

    private AlphaNumericChooser chooser;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        chooser = new AlphaNumericChooser(26,10);
    }

    @Test
    public void validInputReturnProperLocation() throws Exception {
        AlphaNumericChooser.Location loc = chooser.locationFromInput("B4");

        assertEquals("proper row", 1, loc.getRow());
        assertEquals("proper column", 3, loc.getColumn());

    }

    //This tests the exception by putting the expected exception in the @Test
    //annotation and when it is thrown then the test passes
    @Test(expected = InvalidLocationException.class)
    public void choosingWrongInputIsNotAllowed() throws Exception {
        chooser.locationFromInput("WRONG");

    }

    @Test(expected = InvalidLocationException.class)
    public void choosingLargerThanMaxIsNotAllowed() throws Exception {
        chooser.locationFromInput("B12");
    }

    @Test
    public void constructingLargerThanAlphabetNotAllowed() throws Exception {
        //We expect an IllegalArgumentException and we expect the message below
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Maximum rows supported is 26");

        new AlphaNumericChooser(27, 10);
    }
}