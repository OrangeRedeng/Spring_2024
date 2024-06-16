public class Main {
    public static void main(String[] args) {
        String bracket = "(((())()()))))";

        int bracketCount = 0;
        for(int i=0; i<bracket.length();i++){
            if(bracket.charAt(i)=='('){
                bracketCount++;
            } else if (bracket.charAt(i) == ')') {
                bracketCount--;
            }
        }
        if (bracketCount == 0){
            System.out.println("The bracket balance rule is fulfilled");
        }
        else {
            System.out.println("Unbalanced string");
        }
    }
}
