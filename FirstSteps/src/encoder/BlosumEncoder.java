package encoder;

import weka.core.Instances;

public class BlosumEncoder extends Encoder implements BlosumInterface{

	protected static enum BlosumNum{b30, b35, b40, b45, b50, b55, b60, b62, b65, b70, b75, b80, b85, b90, b100}
	protected static int[] blosumNums = {30, 35, 40, 45, 50, 55, 60, 62, 65, 70, 75, 80, 85, 90, 100};
	protected static boolean[] blosumExists = new boolean[101];

	static {
		for (int i=0; i<blosumNums.length; i++){
			blosumExists[i] = true;
		}
	}

	protected static BlosumNum defaultBlosum = BlosumNum.b50;
	
	//non-static stuff follows
	private BlosumNum blosumNum;

	public BlosumEncoder() {
		this.blosumNum = defaultBlosum;
	}

	public BlosumEncoder(int bNum) {
		this.blosumNum = findBlosumNum(bNum);
	}

	public BlosumNum getBlosumNum() {
		return blosumNum;
	}

	public void setBlosumNum(BlosumNum blosumNum) {
		this.blosumNum = blosumNum;
	}

	public void setBlosumNum(int bNum) {
		this.blosumNum = findBlosumNum(bNum);
	}

	/**
	 * from any number, returns the correct Blosum Matrix Number. If there isn't one, chooses the nearest number, prefers lower number.
	 * @param n
	 * @return
	 */
	protected static BlosumNum findBlosumNum(int n) {
		if(blosumExists[n]) {
			return intToBlosumNum(n);
		} else {
			for (int range=1; range < blosumExists.length; range++) {
				if(n-range>0 && blosumExists[n-range]) {
					return intToBlosumNum(n-range);
				} else if(n+range<blosumExists.length && blosumExists[n+range]) {
					return intToBlosumNum(n+range);
				}
			}
			System.err.println("No fitting Blosum matrix found. Choosing default value 50.");
			return defaultBlosum;
		}
	}

	/**
	 * from a fitting number, returns the correct Blosum Matrix Number
	 * @param n
	 * @return
	 */
	protected static BlosumNum intToBlosumNum(int n) {
		switch(n){
		case(30):
			return BlosumNum.b30;
		case(35):
			return BlosumNum.b35;
		case(40):
			return BlosumNum.b40;
		case(45):
			return BlosumNum.b45;
		case(50):
			return BlosumNum.b50;
		case(55):
			return BlosumNum.b55;
		case(60):
			return BlosumNum.b60;
		case(62):
			return BlosumNum.b62;
		case(65):
			return BlosumNum.b65;
		case(70):
			return BlosumNum.b70;
		case(75):
			return BlosumNum.b75;
		case(80):
			return BlosumNum.b80;
		case(85):
			return BlosumNum.b85;
		case(90):
			return BlosumNum.b90;
		case(100):
			return BlosumNum.b100;
		default:
			System.err.println("No fitting Blosum matrix found. Choosing default value 50.");
			return defaultBlosum;
		}
	}

	@Override
	public Instances encodeSingle(AminoAcid aa) {
		// TODO Auto-generated method stub
		return null;
	}
}
