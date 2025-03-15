import java.util.Arrays;

class StringBuilderPicture {

    private final char[] value;
    private final int count;

    public StringBuilderPicture(char[] value, int count) {
        this.value = Arrays.copyOf(value, value.length);
        this.count = count;
    }

    public char[] getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }
}