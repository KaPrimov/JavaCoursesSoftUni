import org.ahmadsoft.ropes.Rope;
import org.ahmadsoft.ropes.impl.FlatCharSequenceRope;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TextEditorImpl implements TextEditor {

    private Map<String, Boolean> loggedUsers = new HashMap<>();
    private Map<String, Rope> usersStrings = new HashMap<>();

    @Override
    public void login(String username) {
        this.loggedUsers.put(username, true);
        this.usersStrings.putIfAbsent(username, new FlatCharSequenceRope(""));
    }

    @Override
    public void logout(String username) {
        this.loggedUsers.replace(username, false);
    }

    @Override
    public void prepend(String username, String string) {
        if(this.usersStrings.containsKey(username)) {
            this.usersStrings.get(username).insert(0, string);
        }
    }

    @Override
    public void insert(String username, int index, String string) {
        if(this.usersStrings.containsKey(username)) {
            this.usersStrings.get(username).insert(index, string);
        }
    }

    @Override
    public void substring(String username, int startIndex, int length) {
        if(this.usersStrings.containsKey(username)) {
            this.usersStrings.replace(username,
                    new FlatCharSequenceRope(this.usersStrings.get(username).
                            subSequence(startIndex, startIndex + length)));
        }
    }

    @Override
    public void delete(String username, int startIndex, int length) {
        if(this.usersStrings.containsKey(username)) {
            this.usersStrings.replace(username,
                    new FlatCharSequenceRope(this.usersStrings.get(username).
                            delete(startIndex, startIndex + length)));
        }
    }

    @Override
    public void clear(String username) {
        if(this.usersStrings.containsKey(username)) {
            this.usersStrings.get(username).delete(0, this.usersStrings.get(username).length() - 1);
        }
    }

    @Override
    public int length(String username) {
        if(this.usersStrings.containsKey(username)) {
           return this.usersStrings.get(username).length();
        }
        return 0;
    }

    @Override
    public String print(String username) {
        if(this.usersStrings.containsKey(username)) {
            Iterator<Character> iterator = this.usersStrings.get(username).iterator();
            StringBuilder sb = new StringBuilder();
            while (iterator.hasNext()) {
                sb.append(iterator.next());
            }
            return sb.toString();
        }
        return null;
    }

    @Override
    public void undo(String username) {
        throw new NotImplementedException();
    }

    @Override
    public Iterable<String> users(String prefix) {
        return null;
    }
}
