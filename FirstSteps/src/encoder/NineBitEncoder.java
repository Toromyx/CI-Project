package encoder;
/**
 * @author Friederike
 */

public class NineBitEncoder extends Encoder {


	/**
	 *Encodes each amino acid as a 9Bit array. Source: see paper
	 * @param Current amino acid that needs to be encoded
	 * @return Encoding as int[]
	 */
	@Override
	public int[] encodeSingle(AminoAcid aa) {

		int[] attValues = new int[9];

		switch (aa) {
		case A:
			int[] a = { 1, 0, 0, 0, 0, 1, 0, 0, 0 };
			attValues = a;
			break;
		case C:
			int[] c = { 0, 0, 0, 0, 0, 1, 0, 0, 1 };
			attValues = c;
			break;
		case D:
			int[] d = { 0, 0, 1, 0, 0, 1, 0, 0, 0 };
			attValues = d;
			break;
		case E:
			int[] encode = { 0, 0, 1, 0, 0, 0, 1, 0, 0 };
			attValues = encode;
			break;
		case F:
			int[] f = { 1, 0, 0, 1, 0, 0, 1, 0, 1 };
			attValues = f;
			break;
		case G:
			int[] g = { 0, 0, 0, 0, 0, 1, 0, 0, 0 };
			attValues = g;
			break;
		case H:
			int[] h = { 0, 1, 0, 1, 0, 0, 1, 1, 1 };
			attValues = h;
			break;
		case I:
			int[] i = { 1, 0, 0, 0, 1, 0, 0, 1, 1 };
			attValues = i;
			break;
		case K:
			int[] k = { 0, 1, 0, 0, 0, 0, 1, 1, 1 };
			attValues = k;
			break;
		case L:
			int[] l = { 1, 0, 0, 0, 1, 0, 0, 0, 1 };
			attValues = l;
			break;
		case M:
			int[] m = { 1, 0, 0, 0, 0, 0, 1, 0, 0 };
			attValues = m;
			break;
		case N:
			int[] n = { 0, 0, 0, 0, 0, 1, 0, 0, 0 };
			attValues = n;
			break;
		case P:
			int[] p = { 1, 0, 0, 0, 0, 0, 0, 1, 0 };
			attValues = p;
			break;
		case Q:
			int[] q = { 0, 0, 0, 0, 0, 0, 1, 0, 0 };
			attValues = q;
			break;
		case R:
			int[] r = { 0, 1, 0, 0, 0, 0, 1, 1, 0 };
			attValues = r;
			break;
		case S:
			int[] s = { 0, 0, 0, 0, 0, 1, 0, 1, 1 };
			attValues = s;
			break;
		case T:
			int[] t = { 0, 0, 0, 0, 0, 1, 0, 1, 0 };
			attValues = t;
			break;
		case V:
			int[] v = { 1, 0, 0, 0, 1, 1, 0, 1, 0 };
			attValues = v;
			break;
		case W:
			int[] w = { 1, 0, 0, 1, 0, 0, 1, 1, 0 };
			attValues = w;
			break;
		case Y:
			int[] y = { 0, 0, 0, 1, 0, 0, 1, 1, 1 };
			attValues = y;
			break;
		default:
			System.err.println("Amino acid was not found. Default values were used for encoding.");
			int[] x = { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
			attValues = x;

		}

		return attValues;
	}
}
