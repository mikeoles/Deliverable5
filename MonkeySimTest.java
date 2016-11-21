
/**
 * CS1632 Deliverable 4
 *
 * @author Nick Taglianetti
 * @author Michael Oles
 */
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import org.mockito.*;

public class MonkeySimTest {
    //Test that the first Monkey in monkeyList has a monkeyNum of 1

    @Test()
    public void testFirstMonkeyNum1() throws NoIdException {
        List<Monkey> monkeyList = new LinkedList<Monkey>();
        Monkey m1 = new Monkey();
        Monkey m2 = new Monkey();
        monkeyList.add(m1);
        monkeyList.add(m2);
        assertEquals(MonkeySim.getFirstMonkey(monkeyList).getMonkeyNum(), 1);
    }

    // Test that the first monkey in monkeyList is null if no Monkeys were
    // added to monkeyList
    @Test()
    public void testFirstMonkeyNullForEmptyList() {
        List<Monkey> monkeyList = new LinkedList<Monkey>();
        assertNull(MonkeySim.getFirstMonkey(monkeyList));
    }

    // Test that getFirstMonkey will not return a Monkey other than
    // the first one that was added to monkeyList
    @Test()
    public void testReturnOnlyFirstMonkey() {
        List<Monkey> monkeyList = new LinkedList<Monkey>();
        Monkey m1 = new Monkey();
        monkeyList.add(m1);
        Monkey m2 = new Monkey();
        monkeyList.add(m2);
        Monkey m3 = new Monkey();
        monkeyList.add(m3);
        assertNotEquals(MonkeySim.getFirstMonkey(monkeyList), m2);
        assertNotEquals(MonkeySim.getFirstMonkey(monkeyList), m3);
    }

    // Tests that the a Monkey must be added to monkeyList in order to
    // be returned as the first Monkey in the list
    @Test()
    public void testFirstMonkeyAdded() {
        List<Monkey> monkeyList = new LinkedList<Monkey>();
        Monkey m1 = new Monkey();
        assertNotEquals(MonkeySim.getFirstMonkey(monkeyList), m1);
    }

    //Test that stringifyResults returns a string with
    //the correct number of the monkey that threw and caught a banana
    @Test()
    public void testMonkeyNums() {
        Monkey m1 = Mockito.mock(Monkey.class);
        Mockito.when(m1.getMonkeyNum()).thenReturn(4);
        Monkey m2 = Mockito.mock(Monkey.class);
        Mockito.when(m2.getMonkeyNum()).thenReturn(1000);
        String str = MonkeySim.stringifyResults(8, m1, m2);
        assertTrue(str.contains("Threw banana from Monkey (#4") 
		&& str.contains("to Monkey (#1000"));
    }

    //Check that the correct ID's are printed out by stringifyResults
    @Test()
    public void testMonkeyIds() throws NoIdException {
        Monkey m1 = Mockito.mock(Monkey.class);
        Mockito.when(m1.getId()).thenReturn(4);
        Monkey m2 = Mockito.mock(Monkey.class);
        Mockito.when(m2.getId()).thenReturn(23456);
        String s1 = MonkeySim.stringifyResults(8, m1, m2);
        assertTrue(s1.contains("ID 4") && s1.contains("ID 23456"));
    }

    //Test the stringifyResults returns a string with
    //the correct round based on the first parameter
    @Test()
    public void testCorrectRound() {
        Monkey m1 = Mockito.mock(Monkey.class);
        Mockito.when(m1.getMonkeyNum()).thenReturn(2);
        Monkey m2 = Mockito.mock(Monkey.class);
        Mockito.when(m2.getMonkeyNum()).thenReturn(3);
        String str = MonkeySim.stringifyResults(27, m1, m2);
        assertTrue(str.contains("//Round 27"));
    }
	
	//Test that when run simulation is called with the prime monkey setting
	//that the monkeys nextPrimeMonkey() method is being called
	@Test()
	public void testRunSimlationPrimeMethod(){
		MonkeyWatcher mw = new MonkeyWatcher();
		MonkeyList ml = Mockito.mock(LinkedList.class);
		Monkey testMonkey = Mockito.mock(Monkey.class);
		Mockito.when(ml.get(Mockito.anyInt())).thenReturn(testMonkey);
		MonkeySim.runSimulation(ml,mw,true);
		Mockito.verify(testMonkey, Mockito.atLeast(1)).nextPrimeMonkey();
	}
	
	//Test that when run simulation is called without the prime monkey setting
	//that the monkeys nextPrimeMonkey() method is not being called
	@Test()
	public void testRunSimlationNotPrimeMethod(){
		MonkeyWatcher mw = new MonkeyWatcher();
		MonkeyList ml = Mockito.mock(LinkedList.class);
		Monkey testMonkey = Mockito.mock(Monkey.class);
		Mockito.when(ml.get(Mockito.anyInt())).thenReturn(testMonkey);
		MonkeySim.runSimulation(ml,mw,false);
		Mockito.verify(testMonkey, Mockito.times(0)).nextPrimeMonkey();
	}	
}
