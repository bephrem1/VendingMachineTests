package com.benyamephrem.vending;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class BinTest {

    private Bin bin;

    //The @Rule annotation allows you to specify exceptions within the method that
    //you expect to be thrown
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        bin = new Bin(10);
    }

    @Test
    public void restockingWithDifferentItemsIsNotAllowed() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Cannot restock apple with orange");

        bin.restock("apple", 2, 1, 3);
        bin.restock("orange", 2, 1, 3);
    }

    @Test
    public void whenEmptyPriceIsZero() throws Exception {
        assertEquals(0, bin.getItemPrice());
    }

    @Test
    public void whenEmptyNameIsNull() throws Exception {
        assertNull(bin.getItemName());
    }

    @Test
    public void overstockingNotAllowed() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        //Allows to ensure the the dynamic message provided is correct too
        thrown.expectMessage("There are only 10 spots left");

        bin.restock("apple", 11, 1, 3);
    }
}