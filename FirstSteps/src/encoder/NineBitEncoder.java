package encoder;

import weka.core.Instances;

public class NineBitEncoder extends Encoder{

	private static int AAToBit(AminoAcid a){
		
		switch(a){
			case A:
				return 100001000;
			case C:
				return 000001001;
			case D:
				return 001001000;
			case E:
				return 001000100;
			case F:
				return 100100101;
			case G:
				return 000001000;
			case H:
				return 010100111;
			case I:
				return 100010011;
			case K :
				return 010000111;
			case L :
				return 100010001;
			case M :
				return 100000100;
			case N:
				return 000001000;
			case P:
				return 100000010;
			case Q:
				return 000000100;
			case R:
				return 010000110;
			case S:
				return 000001011;
			case T:
				return 000001010;
			case V:
				return 100011010;
			case W:
				return 100100110;
			case Y:
				return 000100111;
			default:
				System.err.println("Sequence could not be encoded correctly.");
				return 111111111; //Amino acid X
		}
	}
	
	public static void main(String[]args){
		AminoAcid x = AminoAcid.A;
		System.out.println(AAToBit(x));
		
	}
	
	@Override
	public int[] encodeSingle(AminoAcid aa) {
		// TODO Auto-generated method stub
		return null;
	}

}
