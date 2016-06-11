package Search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class TrieNode {
	  Map<Character, TrieNode> arr = new TreeMap<>();
	   List<String> id = new ArrayList<>();
	   Character unicode;
	   
	   public TrieNode(){}
	   
	    public TrieNode(Character c){
	    	unicode = c;
	        arr = new TreeMap<Character, TrieNode>(
	                new Comparator<Character>() {
					
						@Override
						public int compare(Character o1, Character o2) {
							
							return o1 - o2;
						}
	                });
	    }
}
