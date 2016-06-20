package encoder;

import java.awt.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import encoder.AAInterface.AminoAcid;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Parent class encoder, includes all necessary methods
 * @author Thomas
 *
 */
public abstract class Encoder implements AAInterface{

	public final int codeLength;
	
	
	
	public Encoder() {
		this.codeLength = encodeSingle(AminoAcid.A).length;
	}

	/**
	 * includes class labels (target values)
	 * @param s
	 * @return
	 */
	public Instances encodeAll(Instances s) {
		
		ArrayList<Attribute> attrList = new ArrayList<Attribute>();

		int peptideLength = s.get(0).attribute(0).value(0).length();

		for (int i=1; i<=peptideLength; i++) {
			for (int j=1; j<=codeLength; j++) {
				attrList.add(new Attribute("AA"+i+"attr"+j)); // numeric
			}
		}
		attrList.add(new Attribute("targetValue"));

		Instances outInst = new Instances("Encoded Dataset", attrList, s.size());
		
		for (Iterator<Instance> instIt = s.iterator(); instIt.hasNext();) {
			Instance currInstance = instIt.next();
			DenseInstance instToAdd = encodePeptide(currInstance.stringValue(0)); // encoding takes place here
			double currTarget = currInstance.value(1);
			instToAdd.setValue(peptideLength*codeLength, currTarget);
			outInst.add(instToAdd);
		}
		
		outInst.setClassIndex(outInst.numAttributes()-1);

		return outInst;
	}

	/**
	 * encodes a single peptide from a String into a DenseInstance, which can be added to an Instances object
	 * @param p the peptide to encode
	 * @return the encoded peptide as a DenseInstance with numeric values
	 */
	public DenseInstance encodePeptide(String p) {

		double[] attrValues = new double[(p.length()*codeLength)+1]; // +1 for class label
		
		for (int aa=0; aa<p.length(); aa++) {
			int[] encodedAa = encodeSingle(AAInterface.charToAA(p.charAt(aa)));
			for (int i=0; i<encodedAa.length; i++) {
				attrValues[aa*codeLength+i] = (double)encodedAa[i];
			}
			
		}

		DenseInstance densInst =  new DenseInstance(1, attrValues);

		return densInst;
	}

	/**
	 * encodes a single amino acid
	 */
	public abstract int[] encodeSingle(AminoAcid aa);

//	public static void main(String[] args) {
//		NineBitEncoder nbe = new NineBitEncoder();
//
//		DenseInstance attrList = nbe.encodePeptide("SQEAEFTGY");
//
//		System.out.println(attrList);
//	}
}
