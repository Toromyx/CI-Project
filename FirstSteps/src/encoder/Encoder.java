package encoder;

import java.util.ArrayList;
import java.util.Enumeration;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Parent class encoder, includes all necessary methods
 * @author Thomas
 *
 */
public abstract class Encoder implements AAInterface{
	
	/**
	 * Encodes the whole set of sequences of amino acids
	 */
	public Instances encodeAll(Instances s) {
		// TODO stuff
		return null;
	}
	
	public abstract ArrayList<Attribute> encodePeptide(String p); /* {
		
		int[][] encodedPeptide = new int[p.length()][];
		
		for (int i=0; i<p.length(); i++) {
			char c = p.charAt(i);
			AminoAcid aa = AAInterface.charToAA(c);
			int[] aaCode = encodeSingle(aa);
			encodedPeptide[i] = aaCode;
		}
		
		ArrayList<Attribute> attributeList = new ArrayList<>();
		
		for (int i=0; i<encodedPeptide.length; i++) {
			for (int j=0; j<encodedPeptide[i].length; j++) {
				attributeList.add(new Attribute("aminoAcidAttr"));
			}
		}
        
		return null;
	}*/
	
	/**
	 * encodes a single amino acid
	 */
	public abstract int[] encodeSingle(AminoAcid aa);
	
	public static void main(String[] args) {
		
	}
}
