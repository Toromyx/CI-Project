package encoder;
/**
 * @author Friederike
 */
import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class SixCharEncoder extends Encoder {



	public int[] encodeSingle(AminoAcid aa) {

		int[] attValues = new int[6];

		switch (aa) {
		case A:
			int[] a = {5, 3, 5, 0, 0, 10};
			attValues = a;
			break;
		case C:
			int[] c = {4, 5, 5, 0, 0, 10};
			attValues = c;
			break;
		case D:
			int[] d = {10, 5, 0, 0, 1, 10};
			attValues = d;
			break;
		case E:
			int[] e = {10, 6, 0, 0, 1, 0};
			attValues = e;
			break;
		case F:
			int[] f = {1, 8, 5, 10, 0, 10};
			attValues = f;
			break;
		case G:
			int[] g = {5, 2, 5, 0, 0, 0};
			attValues = g;
			break;
		case H:
			int[] h = {5, 6, 5, 0, 1, 0};
			attValues = h;
			break;
		case I:
			int[] i = {3, 7, 5, 0, 0, 10};
			attValues = i;
			break;
		case K:
			int[] k = {10, 7, 10, 0, 2, 10};
			attValues = k;
			break;
		case L:
			int[] l = {3, 7, 5, 0, 0, 0};
			attValues = l;
			break;
		case M:
			int[] m = {3, 7, 5, 0, 0, 5};
			attValues = m;
			break;
		case N:
			int[] n = {6, 5, 5, 0, 2, 0};
			attValues = n;
			break;
		case P:
			int[] p = {5, 5, 5, 0, 0, 0};
			attValues = p;
			break;
		case Q:
			int[] q = {6, 6, 5, 0, 2, 10};
			attValues = q;
			break;
		case R:
			int[] r = {10, 8, 10, 0, 4, 6};
			attValues = r;
			break;
		case S:
			int[] s = {6, 4, 5, 0, 1, 0};
			attValues = s;
			break;
		case T:
			int[] t = {5, 5, 5, 0, 1, 10};
			attValues = t;
			break;
		case V:
			int[] v = {3, 6, 5, 0, 0, 7};
			attValues = v;
			break;
		case W:
			int[] w = {0, 10, 5, 10, 1, 0};
			attValues = w;
			break;
		case Y:
			int[] y = {2, 8, 5, 10, 1, 8};
			attValues = y;
			break;
		default:
			System.err.println("Amino acid was not found. Default values were used for encoding.");
			int[] x = {5, 5, 5, 5, 5, 5};
			attValues = x;

		}
		
		return attValues;
	}
}
