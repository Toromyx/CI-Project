package encoder;

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
	 * Encodes the whole sequence of amino acids
	 */
	public Instances encodeAll(String p) {
		// TODO stuff
		return null;
	}
	
	/**
	 * encodes a single amino acid
	 */
	//public abstract int[] encodeSingle(AminoAcid aa);
}
