package encoder;

import weka.core.Instance;
import weka.core.Instances;

/**
 * Parent class encoder, includes all necessary methods
 * @author Thomas
 *
 */
public abstract class Encoder {
	
	static enum AminoAcid{A,C,D,E,F,G,H,I,K,L,M,N,P,Q,R,S,T,V,W,Y,X}
	
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
	public abstract Instances encodeSingle(AminoAcid aa);
}
