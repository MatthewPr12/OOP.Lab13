package visitor;

import java.util.Map;

public interface Visitor<T> {
    Map<String, String> onGroupStart(Task<T> task);

    Map<String, String> onGroupEnd(Task<T> task);
    Map<String, String> onSignature(Task<T> task);
}
