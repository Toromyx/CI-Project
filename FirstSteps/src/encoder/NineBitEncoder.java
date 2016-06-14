package src.encoder;
/**
 * @author Friederike
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVSaver;

public class NineBitEncoder extends Encoder {
	
	
	public Instances encodeAll(String p) {

		ArrayList<Attribute> x = new ArrayList();
		AminoAcid[] seq = AAInterface.stringToAA(p);
		x.add(new Attribute("first"));
		x.add(new Attribute("second"));
		x.add(new Attribute("third"));
		x.add(new Attribute("fourth"));
		x.add(new Attribute("fifth"));
		x.add(new Attribute("sixth"));
		x.add(new Attribute("seventh"));
		x.add(new Attribute("eigth"));
		x.add(new Attribute("ninth"));

		Instances dataset = new Instances("Encoding", x, 9);

		for (int i = 0; i < p.length(); i++) {
			dataset.add(encodeSingle(seq[i]));
		}

		/*
		 * CSVSaver saver = new CSVSaver(); try { saver.setFile(new
		 * File("encoding.txt")); saver.setInstances(dataset);
		 * saver.setFieldSeparator("\t"); saver.writeBatch(); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		return dataset;
	}
	
	
	/**
	 * Source: https://books.google.de/books?id=JbBsPibqtIsC&pg=PA27&lpg=PA27&dq=amino+acid+representation+in+ann&source=bl&ots=IedGSv-5bk&sig=jCBvzz8RBdBBdWNNImoGKwDR3uw&hl=en&sa=X&ved=0ahUKEwjh7cPj25XNAhUFnRoKHWW3APUQ6AEIHDAA#v=onepage&q&f=false
	 * @param Current amino acid that needs to be encoded
	 * @return Encoding as instance
	 */
	private Instance encodeSingle(AminoAcid aa) {

		int[] attValues = new int[9];
		Instance result = new DenseInstance(9);

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
			System.err.println("Amino acid was not found. Default value was used.");
			int[] x = { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
			attValues = x;

		}
		result.setValue(0, attValues[0]);
		result.setValue(1, attValues[1]);
		result.setValue(2, attValues[2]);
		result.setValue(3, attValues[3]);
		result.setValue(4, attValues[4]);
		result.setValue(5, attValues[5]);
		result.setValue(6, attValues[6]);
		result.setValue(7, attValues[7]);
		result.setValue(8, attValues[8]);

		return result;
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * NineBitEncoder test = new NineBitEncoder(); test.encodeAll("AAAAAAAAA");
	 * 
	 * AminoAcid a = AminoAcid.A; AminoAcid c = AminoAcid.C; AminoAcid d =
	 * AminoAcid.D; AminoAcid e = AminoAcid.E; AminoAcid f = AminoAcid.F;
	 * AminoAcid g = AminoAcid.G; AminoAcid h = AminoAcid.H; AminoAcid i =
	 * AminoAcid.I; AminoAcid k = AminoAcid.K; AminoAcid l = AminoAcid.L;
	 * AminoAcid m = AminoAcid.M; AminoAcid n = AminoAcid.N; AminoAcid p =
	 * AminoAcid.P; AminoAcid q = AminoAcid.Q; AminoAcid r = AminoAcid.R;
	 * AminoAcid s = AminoAcid.S; AminoAcid t = AminoAcid.T; AminoAcid v =
	 * AminoAcid.V; AminoAcid w = AminoAcid.W; AminoAcid y = AminoAcid.Y;
	 * AminoAcid x = AminoAcid.X;
	 * 
	 * 
	 * System.out.println(test.encodeSingle(a));
	 * System.out.println(test.encodeSingle(c));
	 * System.out.println(test.encodeSingle(d));
	 * System.out.println(test.encodeSingle(e));
	 * System.out.println(test.encodeSingle(f));
	 * System.out.println(test.encodeSingle(g));
	 * System.out.println(test.encodeSingle(h));
	 * System.out.println(test.encodeSingle(i));
	 * System.out.println(test.encodeSingle(k));
	 * System.out.println(test.encodeSingle(l));
	 * System.out.println(test.encodeSingle(m));
	 * System.out.println(test.encodeSingle(n));
	 * System.out.println(test.encodeSingle(p));
	 * System.out.println(test.encodeSingle(q));
	 * System.out.println(test.encodeSingle(r));
	 * System.out.println(test.encodeSingle(s));
	 * System.out.println(test.encodeSingle(t));
	 * System.out.println(test.encodeSingle(v));
	 * System.out.println(test.encodeSingle(w));
	 * System.out.println(test.encodeSingle(y));
	 * System.out.println(test.encodeSingle(x));
	 * System.out.println(test.encodeSingle(a));
	 * 
	 * 
	 * }
	 */
}
