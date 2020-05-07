public class Caesar {
    public static void main(String[] args) {
        String guess = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String result = caesar(guess, 3);
        String unenc = caesar(result, -3);
        print(result);
        print(unenc);
        print("");
        result = caesar2(guess, 3);
        unenc = caesar2(result, -3);
        print(result);
        print(unenc);
    }
    public static void print(Object s) {
        System.out.println(s);
    }
    /**
     * caesar - without strings or shifting arrays
     * - it's all math
     * - note that it also decrypts if you use negative keys
     */
    public static String caesar(final String _s, final int _key) { // note: final params can't be changed
        String result = "";
        char asciiA = "A".charAt(0); // the ascii value for 'A'
        String s = _s.trim().toUpperCase().replaceAll("[^A-Z]", ""); //normalize
        //print(s);
        for(int i = 0; i<s.length(); i++) {
            int c = ((int)s.charAt(i))-asciiA; // get the character's ordinal value (0-25)
            c = (c + _key + 26) % 26; // shift it using modulus to 'wrap around' (+26 = fix neg. values on key < 0)
            result += ((char) (asciiA + c)); // append the new character
        }
        return result;
    }

    /**
     * caesar - with a shifted string...
     * @param _s
     * @param _key
     * @return
     */
    public static String caesar2(final String _s, final int _key) { // note: final params can't be changed
        char asciiA = "A".charAt(0); // ascii 'A'
        int key = Math.abs(_key);
        String a = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // base string
        String b = a.substring(key)+a.substring(0,Math.abs(key)); //shifted string
        if (_key < 0) { // unencrypting string
            b = a.substring((a.length()-key))+a.substring(0,a.length()-key); //shifted string
        }
        String result = "";
        String s = _s.trim().toUpperCase().replaceAll("[^A-Z]", ""); //normalize
        //print(s);
        for(int i = 0; i<s.length(); i++) {
            int c = ((int)s.charAt(i))-asciiA; // the character's index value
            int x = a.charAt(c)-asciiA; // get the index into the 'b' string
            result += b.substring(x,x+1); // add on the character at x from 'b'
        }
        return result;
    }
}
