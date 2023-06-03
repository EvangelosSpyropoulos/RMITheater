package th;

import java.io.Serializable;

public abstract class RMIMessage {
    // Subclasses should define this internal method to declare
    // how the object is printed
    protected abstract String showMessage();
    
    // Calling code prints the object in the same way as built-in types
    @Override
    public String toString() {
        return showMessage();
    }
}