public class Main {
    public static void main(String[] args) throws Exception {
        CustomStringBuilder custom = new CustomStringBuilder("Hello"); //Example
        custom.append(" World");
        System.out.println(custom);

        custom.append("!");
        System.out.println(custom);

        custom.undo(); // Cancel adding "!"
        System.out.println(custom);

        custom.undo(); // Cancel adding " World"
        System.out.println(custom);

        custom.insert(5, " Java");
        System.out.println(custom);

        custom.undo();
        System.out.println(custom);

    }

}
