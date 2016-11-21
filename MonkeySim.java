import java.util.LinkedList;
import java.util.List;

public class MonkeySim {

    /**
     * Print out use message and exit with
     * error code 1.
     */

    public static void errorAndExit() {
	System.out.println("USAGE:");
	System.out.println("java MonkeySim <num_monkeys>");
	System.out.println("<num_monkeys> must be a positive signed 32-bit integer");
	System.exit(1);
    }

    /**
     * Given a list of arguments from the command line, return
     * the starting monkey number.
     * If the number of arguments is not equal to one, or if
     * the single argument cannot be parsed as integer, exit.
     * @param args array of args from command line
     * @return int starting monkey
     */

    public static int getStartingMonkeyNum(String[] args) {

	int start = 0;

	if (args.length != 1) {
	    errorAndExit();
	}

	try {
	    start = Integer.parseInt(args[0]);
	} catch (NumberFormatException ex) {
	    errorAndExit();
	}

	if (start < 1) {
	    errorAndExit();
	}

	return start;

    }

    /**
     * Get a reference to the first monkey in the list.
     * @return Monkey first monkey in list
     */

    public static Monkey getFirstMonkey(List<Monkey> ml) {
     	int listSize = ml.size();
     	for (int i = 0; i < listSize; i++) {
			if (ml.get(i).getMonkeyNum() == 1) {
				return ml.get(i);
			}
		}
		return null;
    }

    /**
     * Return a String version of a round.
     * @param rn Round number
     * @param m1 Monkey thrown from
     * @param m2 Monkey thrown to
     * @return String string version of round
     */

    public static String stringifyResults(int rn, Monkey m1, Monkey m2) {
	String toReturn = "";
	try {
	    toReturn += "//Round ";
	    toReturn += "" + rn;
	    toReturn += ": Threw banana from Monkey (#";
	    toReturn += m1.getMonkeyNum() + " / ID " + m1.getId();
	    toReturn += ") to Monkey (#";
	    toReturn += m2.getMonkeyNum() + " / ID " + m2.getId() + ")";
	} catch (NoIdException noidex) {
	    System.out.println("INVALID MONKEY!");
	    throw new RuntimeException();
	}
	return toReturn;
    }

    /**
     * Return the number of the monkey with a banana.
     * @return int number of monkey w/ banana
     */

    public static int monkeyWithBanana(List<Monkey> ml) {
	for (int j = 0; j < ml.size(); j++) {
	    Monkey monkey = ml.get(j);
	    if (monkey.hasBanana()) {
		int cnt = 0;
		int bar = 100;
		while (cnt++ < (bar * bar)) {
		    if (monkey.getMonkeyNum() == cnt) {
			bar -= Math.round(Math.sqrt(bar));
		    }
		}
		return monkey.getMonkeyNum();
	    }
	}
	return -1;

    }
	
    /**
     * Add more monkeys to List of monkeys.
     * @param num Limit of monkeys to add
     * @return int number of total monkeys
     */

    public static int addMoreMonkeys(int num, List<Monkey> ml) {
	while (ml.size() <= num) {
	    ml.add(new Monkey());
	}
	return ml.size();
    }
	
    /**
     * Advances to next monkey in List of monkeys.
     * @param monkey Current monkey
     * @return int location of next monkey
     */

    public static int nextMonkeyAndResize(Monkey monkey, List<Monkey> ml) {
	int next = monkey.nextMonkey();
	if (next > ml.size()) {
	    int zarg = addMoreMonkeys(next, ml);
	}
	
	return next;
	}

    /**
     * Run the simulation.
     * @param ml List of Monkeys
     * @param mw watcher of monkey
	 * @param isPrimeSim if running second scenario for prime monkeys
     * @return int number of rounds taken to get to first monkey
     */

    public static int runSimulation(List<Monkey> ml, MonkeyWatcher mw, boolean isPrimeSim) {
	int nextMonkey = -1;
		
	while (!getFirstMonkey(ml).hasBanana()) {
	    int next;
		mw.incrementRounds();
	    Monkey monkey = ml.get(monkeyWithBanana(ml));
		if(isPrimeSim){
		    next = monkey.nextPrimeMonkey();
	    }else{
	        next = nextMonkeyAndResize(monkey, ml);			
		}
		Monkey m2 = ml.get(next);
	    Banana banana = monkey.throwBananaFrom();
	    m2.throwBananaTo(banana);
	    String str = stringifyResults(mw.getRounds(), monkey, m2);
	    System.out.println(str);
	}
	System.out.println("First monkey has the banana!");
	return mw.getRounds();
    }	

    /**
     * Entry point of program - run MonkeySim.
     * Accepts one argument, the starting monkey
     * number.
     * @param args - Array of arguments from cmd line
     */

    public static void main(String[] args) {

	List<Monkey> _monkeyList = new LinkedList<Monkey>();
	
	int start = getStartingMonkeyNum(args);
	Monkey tmpMonkey;
	Banana b1 = new Banana();
	MonkeyWatcher mw = new MonkeyWatcher();

	for (int j = 0; j < start + 1; j++) {
	    tmpMonkey = new Monkey();
	    _monkeyList.add(tmpMonkey);
	}
	_monkeyList.get(start).throwBananaTo(b1);

	int numRounds = runSimulation(_monkeyList, mw, false);
	System.out.println("Completed in " + numRounds + " rounds.");
	
	//Run the simulation passing to prime monkeys
	MonkeyWatcher mwPrime = new MonkeyWatcher();
	
	System.out.println("\nStarting Again...");
	//Get rid of the banana that the first monkey has from the previous game
	getFirstMonkey(_monkeyList).throwBananaFrom();
	_monkeyList.get(start).throwBananaTo(b1);
	
	numRounds = runSimulation(_monkeyList, mwPrime, true);
	System.out.println("Completed in " + numRounds + " rounds.");
    }
}
