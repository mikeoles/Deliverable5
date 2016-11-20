public class Monkey {

    private static int monkeyNum = 0;

    private int thisMonkeyNum = 0;

    private int id = -1;

    private Banana banana = null;

    /**
     * Get this monkey's number.
     * @return int monkey number
     */

    public int getMonkeyNum() {
	return thisMonkeyNum;
    }

    /**
     * Getter for id.
     * @return id of monkey
     */

    public int getId() throws NoIdException {
	if (id < 0) {
	    throw new NoIdException();
	} else {
	    return id;
	}
    }

    /**
     * Return which monkey should get banana next.
     * @return int which monkey should get banana.
     */

    public int nextMonkey() {
	if (thisMonkeyNum % 2 == 0) {
	    return thisMonkeyNum / 2;
	} else {
	    return (thisMonkeyNum * 3) + 1;
	}
    }

    /**
     * Checks to see if this monkey has a banana.
     * @return true if has banana, false otherwise
     */

    public boolean hasBanana() {
	return banana != null;
    }

    /**
     * Receive a banana from another monkey.
     * @param b1 - Banana given to this monkey
     */

    public void throwBananaTo(Banana b1) {
	banana = b1;
    }

    /**
     * Throw a banana from this monkey.
     * @return Banana - the banana this monkey held
     */

    public Banana throwBananaFrom() {
	Banana toReturn = banana;
	banana = null;
	return toReturn;
    }

    /**
     * Generate a unique ID for this monkey.
     * Note that monkey ID generation must
     * always return the correct value for
     * a given n (i.e., the id for the first
     * monkey should always be the same).
     * @param num Monkey number
     * @return int ID for this monkey
     */

    public int generateId(int num) {
      return num + 223492;
    }

    /**
     * Monkey constructor.
     */

    public Monkey() {
	thisMonkeyNum = monkeyNum;
	monkeyNum++;
	id = generateId(thisMonkeyNum);
    }

}
