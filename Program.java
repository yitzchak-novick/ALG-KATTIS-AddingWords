import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		HashMap<String, Integer> wordToNum = new HashMap<String, Integer>();
		HashMap<Integer, String> numToWord = new HashMap<Integer, String>();
		
		while (kb.hasNextLine()) {
			String line = kb.nextLine();
			String[] parts = line.split(" ");
			
			if (parts[0].equals("clear")){
				wordToNum.clear();
				numToWord.clear();
			}
			
			if (parts[0].equals("def")) {
				int val = Integer.parseInt(parts[2]);
				numToWord.remove(wordToNum.get(parts[1]));
				wordToNum.put(parts[1], val);
				numToWord.put(val, parts[1]);
			}
			
			if (parts[0].equals("calc")){
				int result = 0;
				if (!wordToNum.containsKey(parts[1])) {
					System.out.println(line.substring(5) + " unknown");
					continue;
				}
				else {
					result = wordToNum.get(parts[1]);
				
					boolean unknown = false;
					for (int i = 2; i < parts.length - 1; i += 2) {
						if (!wordToNum.containsKey(parts[i+1])) {
							unknown = true;
							break;
						}
						else {
							boolean add = parts[i].equals("+");
							int nextVal = wordToNum.get(parts[i+1]);
							if (add) result += nextVal;
							else result -= nextVal;
						}
					}
					if (unknown || !numToWord.containsKey(result)) {
						System.out.println(line.substring(5) + " unknown");
					}
					else {
						System.out.println(line.substring(5) + " " + numToWord.get(result));
					}
				}
			}
		}
	}

}
