package babelnet_poc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelSense;
import it.uniroma1.lcl.babelnet.BabelSynset;
import it.uniroma1.lcl.babelnet.BabelSynsetIDRelation;
import it.uniroma1.lcl.babelnet.BabelSynsetRelation;
import it.uniroma1.lcl.babelnet.data.BabelPointer;
import it.uniroma1.lcl.jlt.util.Language;

public class BabelNetPoc {

	public static void main(String[] args) {
		
		BabelNet bn = BabelNet.getInstance();
		
		try {
			List<BabelSynset> synsets = bn.getSynsets("cachorro", Language.PT);
			
			List<String> hypernyms = new ArrayList<String>();
			for (BabelSynset s : synsets) {
		        BabelSynset by = bn.getSynset(s.getID());
		        List<BabelSynsetRelation> edges = by.getOutgoingEdges(BabelPointer.HYPERNYM);
		        for(BabelSynsetRelation edge : edges) {
		        	for(BabelSense sense : bn.getSynset(edge.getBabelSynsetIDTarget())) {
		        		String hyperonym = edge.getPointer() + " - " + sense.getFullLemma();
		        		if (!hypernyms.contains(hyperonym))
			        		hypernyms.add(hyperonym);		        			
		        	}
		        }
			}
			
			for (String s : hypernyms)
				System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
