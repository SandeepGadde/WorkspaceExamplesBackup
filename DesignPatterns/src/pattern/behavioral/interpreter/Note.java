package pattern.behavioral.interpreter;

public class Note {
	String key;
	public Note(String key) {
		this.key = key;
	}
	public String getFreq() {
		if(key.equals("sa"))
			return "256 Hz";
		else if(key.equals("re"))
			return "288 Hz";
		else if(key.equals("ga"))
			return "320 Hz";
		else
			return "";
	}
}
