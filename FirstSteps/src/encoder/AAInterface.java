package src.encoder;

public interface AAInterface {

	static enum AminoAcid{A,C,D,E,F,G,H,I,K,L,M,N,P,Q,R,S,T,V,W,Y,X}

	static AminoAcid charToAA(char c) {
		switch(c) {
		case 'a':
		case 'A':
			return AminoAcid.A;
		case 'c':
		case 'C':
			return AminoAcid.C;
		case 'd':
		case 'D':
			return AminoAcid.D;
		case 'e':
		case 'E':
			return AminoAcid.E;
		case 'f':
		case 'F':
			return AminoAcid.F;
		case 'g':
		case 'G':
			return AminoAcid.G;
		case 'h':
		case 'H':
			return AminoAcid.H;
		case 'i':
		case 'I':
			return AminoAcid.I;
		case 'k':
		case 'K':
			return AminoAcid.K;
		case 'l':
		case 'L':
			return AminoAcid.L;
		case 'm':
		case 'M':
			return AminoAcid.M;
		case 'n':
		case 'N':
			return AminoAcid.N;
		case 'p':
		case 'P':
			return AminoAcid.P;
		case 'q':
		case 'Q':
			return AminoAcid.Q;
		case 'r':
		case 'R':
			return AminoAcid.R;
		case 's':
		case 'S':
			return AminoAcid.S;
		case 't':
		case 'T':
			return AminoAcid.T;
		case 'v':
		case 'V':
			return AminoAcid.V;
		case 'w':
		case 'W':
			return AminoAcid.W;
		case 'y':
		case 'Y':
			return AminoAcid.Y;
		default:
			System.err.println("Found unknown amino acid.");
			return AminoAcid.X;
		}

	}

	public static AminoAcid[] stringToAA(String s) {
		AminoAcid[] aaArray = new AminoAcid[s.length()];

		for(int aa=0; aa<aaArray.length; aa++) {
			aaArray[aa] = charToAA(s.charAt(aa));
		}

		return aaArray;
	}

	public static char aaToChar(AminoAcid aa) {
		switch(aa) {
		case A:
			return 'A';
		case C:
			return 'C';
		case D:
			return 'C';
		case E:
			return 'E';
		case F:
			return 'F';
		case G:
			return 'G';
		case H:
			return 'H';
		case I:
			return 'I';
		case K:
			return 'K';
		case L:
			return 'L';
		case M:
			return 'M';
		case N:
			return 'N';
		case P:
			return 'P';
		case Q:
			return 'Q';
		case R:
			return 'R';
		case S:
			return 'S';
		case T:
			return 'T';
		case V:
			return 'V';
		case W:
			return 'W';
		case Y:
			return 'Y';
		default:
			System.err.println("Found unknown amino acid.");
			return 'X';
		}
	}

}
