package pattern.behavioral.interpreter;

public class Main {
	public static void main(String args[]) {
		Note n = new Note("sa");
		NotesInterpreter nn = new NotesInterpreter();
		nn.getNoteFromKeys(n);
	}
}
