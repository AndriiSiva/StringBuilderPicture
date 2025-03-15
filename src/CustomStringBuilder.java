import java.util.Arrays;

class CustomStringBuilder {
    private char[] value;
    private int count;
    private final History history = new History();

    public CustomStringBuilder() {
        this.value = new char[16];
        this.count = 0;
    }

    public CustomStringBuilder(String str) {
        this.value = new char[str.length() + 16];
        this.count = str.length();
        str.getChars(0, str.length(), value, 0);
    }


    public CustomStringBuilder append(String str) {
        if (str == null) {
            str = "null";
        }
        int len = str.length();
        ensureCapacity(count + len);
        str.getChars(0, len, value, count);
        count += len;
        history.save(createSnapshot()); // Сохраняем снимок
        return this;
    }


    public CustomStringBuilder delete(int start, int end) {
        if (start < 0 || end > count || start > end) {
            throw new StringIndexOutOfBoundsException();
        }
        System.arraycopy(value, end, value, start, count - end);
        count -= (end - start);
        history.save(createSnapshot()); // Сохраняем снимок
        return this;
    }


    public CustomStringBuilder insert(int offset, String str) {
        if (offset < 0 || offset > count) {
            throw new StringIndexOutOfBoundsException();
        }
        if (str == null) {
            str = "null";
        }
        int len = str.length();
        ensureCapacity(count + len);
        System.arraycopy(value, offset, value, offset + len, count - offset);
        str.getChars(0, len, value, offset);
        count += len;
        history.save(createSnapshot()); // Сохраняем снимок
        return this;
    }


    public CustomStringBuilder undo() {
        StringBuilderPicture memento = history.undo();
        if (memento != null) {
            restoreSnapshot(memento);
        }
        return this;
    }


    private StringBuilderPicture createSnapshot() {
        return new StringBuilderPicture(value, count);
    }


    private void restoreSnapshot(StringBuilderPicture memento) {
        this.value = Arrays.copyOf(memento.getValue(), memento.getValue().length);
        this.count = memento.getCount();
    }


    private void ensureCapacity(int minimumCapacity) {
        if (minimumCapacity - value.length > 0) {
            int newCapacity = value.length * 2 + 2;
            if (newCapacity - minimumCapacity < 0) {
                newCapacity = minimumCapacity;
            }
            value = Arrays.copyOf(value, newCapacity);
        }
    }

    @Override
    public String toString() {
        return new String(value, 0, count);
    }
}
