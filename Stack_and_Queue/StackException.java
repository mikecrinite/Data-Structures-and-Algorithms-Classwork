package postfixinfix2;

@SuppressWarnings("serial")
public class StackException extends Exception {
	public StackException() { super(); }
  public StackException(String message) { super(message); }
  public StackException(String message, Throwable cause) { super(message, cause); }
  public StackException(Throwable cause) { super(cause); }
}
