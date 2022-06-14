import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.*;

public class Letters {
//    private int x;
    private char letter;
    private boolean clicked = false;
    private ImageView letterView = new ImageView(new Image("file:img/letters.jpeg", 425, 355, false, false));
    private static List<Character> vowels = new ArrayList<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    private static HashMap<Character, Integer> allLetters = new HashMap<Character, Integer>();
    private static int count;
    private static String word = "";


//    private static List<Integer> free_spaces = new ArrayList<>();

    private boolean noVowelsFound() {
        for (Character key : allLetters.keySet()) {
            if (vowels.contains(key)) {
                return false;
            }
        }
        return true;
    }

    public Letters() {
        char c;
        Random r = new Random();
        if (this.count == 5 && noVowelsFound()) {
            c = vowels.get(r.nextInt(4));
        }
        else {
            do {
                c = (char) (r.nextInt(26) + 'a');
            } while (allLetters.getOrDefault(c, 2) < 2);
        }
        this.letter = c;
        int char_num = Character.getNumericValue(c) - Character.getNumericValue('a');
//        System.out.println(char_num);
//        System.out.println("divided by 5: " + (char_num / 5));
//        System.out.println(2 + ((char_num % 6) * 68));
//        System.out.println(2 + ((char_num / 5) * 68));
        letterView.setViewport(new Rectangle2D(2 + ((char_num % 6) * 71), 2 + ((char_num / 6) * 71),68,68));
        System.out.println(c);
        if (allLetters.containsKey(c)) {
            allLetters.put(c, allLetters.get(c) + 1);
        } else {
            allLetters.put(c, 1);
        }
        this.count++;

        letterView.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
//            System.out.print("clicked a letter");
            if (!clicked){
                letterView.setX(166 + word.length() * 78);
                letterView.setY(150);
                this.clicked = true;

                this.word += this.letter;
            }
        });
    }

    public void removeLetter(char c) {
        if (allLetters.get(c) == 1) {
            allLetters.remove(c);
        } else {
            allLetters.put(c, allLetters.get(c) - 1);
        }
        count--;
    }

    public int getCount() {
        return count;
    }

    public ImageView getLetterView() {
        return letterView;
    }

    public char getLetter() {
        return letter;
    }

    public void resetWord() {
        this.word = "";
    }

    public String getWord() {
        return word;
    }

    public void setClicked() {
        this.clicked = false;
    }
}
