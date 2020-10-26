import java.util.Calendar;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> chars = new ArrayDeque();
        for (int i = 0; i < word.length(); i += 1) {
            chars.addLast(word.charAt(i));
        }
        return chars;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> chars = wordToDeque(word);
        boolean isPal = recHelper(chars, cc);
        return isPal;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> chars = wordToDeque(word);
        boolean isPal = recHelper(chars);
        return isPal;
    }

    private boolean recHelper(Deque<Character> chars, CharacterComparator cc) {
        if (chars.size() == 0 || chars.size() == 1) {
            return true;
        }
        if (cc.equalChars(chars.removeFirst(), chars.removeLast())) {
            return recHelper(chars, cc);
        }
        return false;
    }

    private boolean recHelper(Deque<Character> chars) {
        if (chars.size() == 0 || chars.size() == 1) {
            return true;
        }
        if (chars.removeFirst() == chars.removeLast()) {
            return recHelper(chars);
        }
        return false;
    }
}
