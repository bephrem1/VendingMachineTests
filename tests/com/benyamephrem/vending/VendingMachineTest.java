package com.benyamephrem.vending;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendingMachineTest {

    private VendingMachine machine;

    //This is a test double class to imitate the actual Notifier interface
    //(which does nothing) but it implements Notifier so it has an "is-a"
    //relationship
    public class NotifierStub implements Notifier{
        @Override
        public void onSale(Item item) {
            return;
        }
    }

    @Before
    public void setUp() throws Exception {
        Notifier notifier = new NotifierStub();
        machine = new VendingMachine(notifier, 10, 10, 10);
        machine.restock("A1", "Twinkies", 10, 30, 75);

    }

    @Test
    public void vendingWhenStockedReturnsItem() throws Exception {
        machine.addMoney(75);

        Item item = machine.vend("A1");

        assertEquals("Twinkies", item.getName());
    }

    @Test
    public void runningSalesTotalIsIncrementedOnVend() throws Exception {
        machine.addMoney(75);

        machine.vend("A1");

        assertEquals(75, machine.getRunningSalesTotal());
    }
}