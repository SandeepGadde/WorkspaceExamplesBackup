package pattern.behavioral.interpreter;

public class NotesProducer {
	private Frequency freq;

	public NotesProducer() {
		
	}

	/**
	 * This method produces the sound wave of the frequency it gets.
	 */
	public void playSound(Frequency freq) {
		this.freq = freq;
		System.out.println("Playing sound at frequency :"+freq.freq);
	}
}
