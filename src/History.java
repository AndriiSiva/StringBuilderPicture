import java.util.ArrayList;
import java.util.List;

public class History {

    private final List<StringBuilderPicture> pictures = new ArrayList<>();

    public void save(StringBuilderPicture picture) {
        pictures.add(picture);
    }

    public StringBuilderPicture undo() {
        if (!pictures.isEmpty()) {
            return pictures.remove(pictures.size() - 1);
        }
        return null;
    }
}
