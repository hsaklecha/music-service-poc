package Search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class TrieSearch {
    TrieNode root;
    ArrayList<MusicData> data = new ArrayList<>();
    List<String> resultList = new ArrayList<>();
    
    public TrieSearch(){
        root = new TrieNode();
//        fillData();
        readFile();
    }
    
    public void fillData(){
//    	data.add(new MusicData("har", 1));
//    	data.add(new MusicData("harshit chote bhaiya", 2));
//    	data.add(new MusicData("sojjwal bade bhaiya", 3));
//    	data.add(new MusicData("mayank bhaiya", 4));
//    	data.add(new MusicData("harish saiyan", 5));
//    	data.add(new MusicData("raj babbar", 6));
//    	data.add(new MusicData("raja babu", 7));
//    	data.add(new MusicData("hariya saiyya", 8));
//    	data.add(new MusicData("うどんゲルゲ feat.初音ミク china bhaiya", 9));
//    	data.add(new MusicData("うどんab feat.初音ミク", 10));
    }
    
    public void readFile() {
    	BufferedReader br = null;

		try {

			String line;

			URL url = new URL("https://raw.githubusercontent.com/hbalakrishnan/code/master/musical_group.tsv");
			br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
			br.readLine(); //ignore first line
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split("/m/");
				data.add(new MusicData(tokens[0].trim(), tokens[1]));
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
    }
    
    // Adds a word into the data structure.
    public void addWord(MusicData data) {
        TrieNode p= root;
        String word = data.name;
        String[] wordArray = word.split(" ");
        
        
//        for(int i=0; i<word.length(); i++){
//            char c=word.charAt(i);
//            int index = c-'a';
//            if(p.arr[index]==null){
//                TrieNode temp = new TrieNode();
//                p.arr[index]=temp;
//                p=temp;
//            }else{
//                p=p.arr[index];
//            }
//        }
        for(int y=0; y< wordArray.length; y++){
        Map<Character, TrieNode> children = root.arr;
 
        for(int i=0; i<wordArray[y].length(); i++){
            char c = wordArray[y].charAt(i);
 
            TrieNode t;
            if(children.containsKey(c)){
                    t = children.get(c);
            }else{
                t = new TrieNode(c);
                children.put(c, t);
            }
 
            children = t.arr;
 
            //set leaf node
            if(i==wordArray[y].length()-1)
                t.id.add(data.id);
         }
        }
    }
    
    public void addWords(){
      for(MusicData d : data){
    	  addWord(d);
      }
    }
    
    
    // Returns if the word is in the trie.
    public boolean WordSearch(String word) {
        TrieNode p = searchNode(word);
        if(p==null){
            return false;
        }else{
            if(p.id != null)
                return true;
        }
 
        return false;
    }
 
    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public List<String> startsWith(String prefix) {
        TrieNode p = searchNode(prefix);
        Stack<TrieNode> s = new Stack<TrieNode>();
       s.add(p);
       
        while(s.isEmpty()== false){
          TrieNode x = s.pop();
       if(x!=null){
          if(!x.id.isEmpty()) 
        	  resultList.addAll(x.id);
         
          for(Character c : x.arr.keySet()){
        	  s.add(x.arr.get(c));
          }
       }
          
        }
        return resultList;
    }
 
    public TrieNode searchNode(String s){
        TrieNode p = root;
        for(int i=0; i<s.length(); i++){
            char c= s.charAt(i);
 
            if(p.arr.containsKey(c)){
                p = p.arr.get(c);
            }else{
                return null;
            }
        }
 
        if(p==root)
            return null;
 
        return p;
    }
	
//    public boolean WordSearch(TrieNode p, String word, int start){
//    	if (p.isLeaf && start == word.length())
//			return true;
// 
//		if (start >= word.length())
//			return false;
//    	
//		char c = word.charAt(start);
//		if (c == '.') {
//			boolean tResult = false;
//			for (int j = 0; j < 26; j++) {
//				if (p.arr[j] != null) {
//					if (WordSearch(p.arr[j], word, start + 1)) {
//						tResult = true;
//						break;
//					}
//				}
//			}
// 
//			if (tResult)
//				return true;
//		} else {
//			int index = c - 'a';
// 
//			if (p.arr[index] != null) {
//				return WordSearch(p.arr[index], word, start + 1);
//			} else {
//				return false;
//			}
//		}
// 
//		return false;
//    }

    
  public static void main(String[] args) {
	  TrieSearch searchObject = new TrieSearch();
	  searchObject.addWords();
	//  searchObject.search("har");
	  for(String i : searchObject.startsWith("任賢")){
	  System.out.println(i);
	  }
  }
	
}
