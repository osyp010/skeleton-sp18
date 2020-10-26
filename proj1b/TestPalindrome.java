import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator obo = new OffByOne();
    static CharacterComparator ob5 = new OffByN(5);

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } // Uncomment this class once you've created your Palindrome class.

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("abba"));
        assertTrue(palindrome.isPalindrome("aabaa"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("catt"));
        assertFalse(palindrome.isPalindrome("Dad"));
    }

    @Test
    public void testIsPalindromeReloaded() {
        assertTrue(palindrome.isPalindrome("", obo));
        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("flabke", obo));
        assertFalse(palindrome.isPalindrome("flaake", obo));
        assertFalse(palindrome.isPalindrome("aa", obo));

        assertTrue(palindrome.isPalindrome("", ob5));
        assertTrue(palindrome.isPalindrome("a", ob5));
        assertTrue(palindrome.isPalindrome("af", ob5));
        assertTrue(palindrome.isPalindrome("abdgf", ob5));
        assertFalse(palindrome.isPalindrome("abddgf", ob5));
        assertFalse(palindrome.isPalindrome("aa", ob5));
    }
}
