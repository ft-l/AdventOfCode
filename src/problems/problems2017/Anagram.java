package problems.problems2017;

public class Anagram {

    public static char[] charArray;

    public Anagram(String word) {
        charArray = word.toCharArray();
        doAnagram(charArray.length);
    }

    public void changeOrder(int newsize) {
        int j;
        int pointAt = charArray.length - newsize;
        char temp = charArray[pointAt];

        for (j = pointAt + 1; j < charArray.length; j++) {
            charArray[j - 1] = charArray[j];
        }

        charArray[j - 1] = temp;

    }

    public void doAnagram(int newsize) {
        if (newsize == 1) {
            return;
        }
        for (int i = 0; i < newsize; i++) {
            doAnagram(newsize - 1);
            if (newsize == 2) {
                display();
            }
            changeOrder(newsize);
        }
    }

    public void display() {
        for (int i = 0; i < charArray.length; i++) {
            System.out.print(charArray[i]);
        }
        System.out.println();
    }
}

