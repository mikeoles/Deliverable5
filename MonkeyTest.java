
/**
 * CS1632 Deliverable 4
 *
 * @author Nick Taglianetti
 * @author Michael Oles
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class MonkeyTest {
    // Test that generateId returns the correct ID

    @Test
    public void testReturnsCorrectId() {
        Monkey m1 = new Monkey();
        int id = m1.generateId(3);
        assertEquals(id, 223495);
    }

    // Every time generateId is ran with the same input it should return the same ID
    @Test
    public void testCheckSameIdReturned() {
        Monkey m1 = new Monkey();
        int id = m1.generateId(23434);
        int secondId = m1.generateId(23434);
        assertEquals(id, secondId);
    }

    // Test that generateId returns the correct ID with a negative input
    @Test
    public void testNegativeParameterIdTest() {
        Monkey m1 = new Monkey();
        int id = m1.generateId(-1);
        assertEquals(id, 223491);
    }

    // Test that generateId returns the correct ID with a very large input
    @Test
    public void testLargeParameterIdTest() {
        Monkey m1 = new Monkey();
        int id = m1.generateId(2000000);
        assertEquals(id, 2223492);
    }
	
	//Test that the edge case of 2 returns that it is a prime number
	@Test
	public void testTwoIsPrime(){
		Monkey testMonkey = new Monkey();
		boolean prime = testMonkey.isPrime(2);
		assertTrue(prime);
	}
	
	//Test that an odd number that is not prime returns false when isPrime() is called
	@Test
	public void testNotPrime(){
		Monkey testMonkey = new Monkey();
		boolean prime = testMonkey.isPrime(12587);
		assertFalse(prime);
	}	
	
	//Test that a large known prime number returns true when isPrime() is called
	@Test
	public void testLargePrime(){
		Monkey testMonkey = new Monkey();
		boolean prime = testMonkey.isPrime(1002583);
		assertTrue(prime);
	}	
	
	//Test that a negative number is not a prime number
	@Test
	public void testNegativePrime(){
		Monkey testMonkey = new Monkey();
		boolean prime = testMonkey.isPrime(-100);
		assertFalse(prime);
	}	
	
	//Test that the monkey passes the banana to a monkey with a lower number
	@Test
	public void testNextPrimeMonkeyDecreases(){
		Monkey testMonkey = new Monkey();
		testMonkey.setMonkeyNum(1009);
		int nextMonkeyNum = testMonkey.nextPrimeMonkey();
		assertTrue(nextMonkeyNum < 1009);
	}		
	
	//Test that when a non-prime monkey is passed in it still finds the lowest prime
	@Test
	public void testNextPrimeMonkeyComposite(){
		Monkey testMonkey = new Monkey();
		testMonkey.setMonkeyNum(126);
		int nextMonkeyNum = testMonkey.nextPrimeMonkey();
		assertEquals(nextMonkeyNum,113);
	}	
	
	//Even though 1 is not a prime number 
	//when nextPrimeMonkey() is called on monkey number 2
	//The banana should be passed to monkey number 1
	@Test
	public void testNextPrimeMonkeyTwo(){
		Monkey testMonkey = new Monkey();
		testMonkey.setMonkeyNum(2);
		int nextMonkeyNum = testMonkey.nextPrimeMonkey();
		assertEquals(nextMonkeyNum,1);
	}	

	//Test that when a negative number is thrown in an exception occurs
	@Test
	public void testNextPrimeMonkeyNegative(){
		try{
			Monkey testMonkey = new Monkey();
			testMonkey.setMonkeyNum(-1);
			int nextMonkeyNum = testMonkey.nextPrimeMonkey();
			fail();
		} catch (IllegalArgumentException iae) {
			// expected behavior
		}
	}		
}
