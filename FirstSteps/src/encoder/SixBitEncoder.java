package src.encoder;

import java.io.File;
import java.io.IOException;
/**
 * @author Friederike
 */
import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVSaver;

public class SixBitEncoder extends Encoder {

	public Instances encodeAll(String p) {

		ArrayList<Attribute> attr = new ArrayList();
		AminoAcid[] seq = AAInterface.stringToAA(p);
		attr.add(new Attribute("first"));
		attr.add(new Attribute("second"));
		attr.add(new Attribute("third"));
		attr.add(new Attribute("fourth"));
		attr.add(new Attribute("fifth"));
		attr.add(new Attribute("sixth"));

		Instances dataset = new Instances("Encoding", attr, 6);

		for (int i = 0; i < p.length(); i++) {
			dataset.add(encodeSingle(seq[i]));
		}

		
		 CSVSaver saver = new CSVSaver(); try { saver.setFile(new
		 File("encoding.txt")); saver.setInstances(dataset);
		 saver.setFieldSeparator("\t"); saver.writeBatch(); } catch
		(IOException e) { // TODO Auto-generated catch block
		 e.printStackTrace(); }
		

		return dataset;
	}

	public Instance encodeSingle(AminoAcid aa) {

		char[] attValues = new char[6];
		Instance result = new DenseInstance(6);

		switch (aa) {
		case A:
			char[] a = { '5', '3', '5', '0', '0', 'a' };
			attValues = a;
			break;
		case C:
			char[] c = { '4', '5', '5', '0', '0', 'a' };
			attValues = c;
			break;
		case D:
			char[] d = { 'a', '5', '0', '0', '1', 'a' };
			attValues = d;
			break;
		case E:
			char[] encode = { 'a', '6', '0', '0', '1', '0' };
			attValues = encode;
			break;
		case F:
			char[] f = { '1', '8', '5', 'a', '0', 'a' };
			attValues = f;
			break;
		case G:
			char[] g = { '5', '2', '5', '0', '0', '0' };
			attValues = g;
			break;
		case H:
			char[] h = { '5', '6', '5', '0', '1', '0' };
			attValues = h;
			break;
		case I:
			char[] i = { '3', '7', '5', '0', '0', 'a' };
			attValues = i;
			break;
		case K:
			char[] k = { 'a', '7', 'a', '0', '2', 'a' };
			attValues = k;
			break;
		case L:
			char[] l = { '3', '7', '5', '0', '0', '0' };
			attValues = l;
			break;
		case M:
			char[] m = { '3', '7', '5', '0', '0', '5' };
			attValues = m;
			break;
		case N:
			char[] n = { '6', '5', '5', '0', '2', '0' };
			attValues = n;
			break;
		case P:
			char[] p = { '5', '5', '5', '0', '0', '0' };
			attValues = p;
			break;
		case Q:
			char[] q = { '6', '6', '5', '0', '2', 'a' };
			attValues = q;
			break;
		case R:
			char[] r = { 'a', '8', 'a', '0', '4', '6' };
			attValues = r;
			break;
		case S:
			char[] s = { '6', '4', '5', '0', '1', '0' };
			attValues = s;
			break;
		case T:
			char[] t = { '5', '5', '5', '0', '1', 'a' };
			attValues = t;
			break;
		case V:
			char[] v = { '3', '6', '5', '0', '0', '7' };
			attValues = v;
			break;
		case W:
			char[] w = { '0', 'a', '5', 'a', '1', '0' };
			attValues = w;
			break;
		case Y:
			char[] y = { '2', '8', '5', 'a', '1', '8' };
			attValues = y;
			break;
		default:
			System.err.println("Amino acid was not found. Default value was used.");
			char[] x = { '5', '5', '5', '5', '5', '5' };
			attValues = x;

		}
		result.setValue(0, attValues[0]);
		result.setValue(1, attValues[1]);
		result.setValue(2, attValues[2]);
		result.setValue(3, attValues[3]);
		result.setValue(4, attValues[4]);
		result.setValue(5, attValues[5]);
		System.out.println("result " + result);
		return result;
	}

	public static void main(String[] args) {

		SixBitEncoder test = new SixBitEncoder();
		test.encodeAll("ATYMAA");
		/*
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
		 */
	}
}
