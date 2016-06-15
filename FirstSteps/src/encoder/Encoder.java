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
	 * Encodes the whole set of sequences of amino acids
	 */
	public Instances encodeAll(Instances s) {
		// TODO stuff
		return null;
	}
	
	public Instance encodePeptide(Instance p) {
		// TODO stuff
		return null;
	}
	
	/**
	 * encodes a single amino acid
	 */
	public abstract int[] encodeSingle(AminoAcid aa);
}
