package encoder;

import weka.core.Instances;

/**
 * Parent class encoder, includes all necessary methods
 * @author Thomas
 *
 */
public abstract class Encoder {
	
	/**
	 * Encodes
	 */
	public abstract Instances encode(String p);
}
