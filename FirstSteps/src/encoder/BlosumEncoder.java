package encoder;

public class BlosumEncoder extends Encoder {

	private static enum BlosumNum{b30, b35, b40, b45, b50, b55, b60, b62, b65, b70, b75, b80, b85, b90, b100}
	private static int[] BlosumNums = {30, 35, 40, 45, 50, 55, 60, 62, 65, 70, 75, 80, 85, 90, 100};
	
	private BlosumNum blosumNum = BlosumNum.b50;
	
	public BlosumEncoder() {
		// TODO add code for more class variables
	}
	
	public BlosumEncoder(int bNum) {
		// TODO cases for the Blosum number
	}

	public BlosumNum getBlosumNum() {
		return blosumNum;
	}

	public void setBlosumNum(BlosumNum blosumNum) {
		this.blosumNum = blosumNum;
	}

	public void setBlosumNum(int bNum) {
		// TODO cases
	}

	@Override
	public void encode(String p) {
		// TODO Auto-generated method stub
	}

	/**
	 * from a number, returns the correct Blosum Matrix Number. If there isn't one, chooses the nearest number.
	 * @param n
	 * @return
	 */
	private BlosumNum findBlosumNum(int n) {
		
		return null;
	}
}
