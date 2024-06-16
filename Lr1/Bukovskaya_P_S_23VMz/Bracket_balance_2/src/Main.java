public class Main {
    public static void main(String[] args) {
        String bracket = "{[(())]}";

        while (bracket.contains("()") || bracket.contains("[]") || bracket.contains("{}")) {
            bracket=bracket.replaceAll("\\(\\)", "");
            bracket=bracket.replaceAll("\\[]", "");
            bracket=bracket.replaceAll("\\{}", "");
        }

        if (bracket.isEmpty()){
            System.out.println("The bracket balance rule is fulfilled");
        }
        else System.out.println("Unbalanced string");
    }
}
