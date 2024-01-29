package u6pp;
import java.util.Arrays;
import java.util.Scanner;

public class Blackjack {

    private Deck decky;
    private Card[] playerHand; // this probably makes the players hand, dunno.
    private Card[] dealerHand; // uh, yeah, idk, i think (woah) it makes the dealers hand. Dunno im just guessing even though I made this. Im losing my sanity.
    private String playerName; // uh, i think this one saves the name of player. or the soul. hm. i may change this to playerSoul. :p


    public Blackjack() {
        decky = new Deck(); // creates a new decko
        playerHand = new Card[2]; // how big the initial hand is (can expand)
        dealerHand = new Card[2];
    }

    public void play() { // this is pain
        String result = "";

        action(); // deals the card

        Scanner sc = new Scanner(System.in); // creates scanner
        System.out.print("Welcome to Blackjack! What is your name? ");
        playerName = sc.nextLine(); // gets user input of name

        System.out.println("Hello " + playerName + " I am Gambletron 5000! Let's play some cards.");
        System.out.println("Your hand: " + Arrays.toString(playerHand)); // prints hand (dealer and yours)
        System.out.println("Dealer's hand: " + Arrays.toString(dealerHand));

        playerTurn(); // starts players turn
        dealerTurn(); // starts dealers turn

        result = determineResult(playerHand, dealerHand);

        System.out.println(result);
        // extra note: could loop it + have a if statement to check if player wants to continue, but can't cuz the checking thingy :/
        sc.close();
    }

    private void action(){
        playerHand[0] = decky.deal(); // deals el cards
        playerHand[1] = decky.deal();
        dealerHand[0] = decky.deal();
        dealerHand[1] = decky.deal();
    }

    private void playerTurn(){
        Scanner sc = new Scanner(System.in);

        while (!isBust(playerHand)) {
            System.out.println("Do you want to hit or stand?");
            String choice = sc.nextLine().toLowerCase();

                if (choice.equals("hit")) {
                playerHand = Arrays.copyOf(playerHand, playerHand.length + 1); // Ngl idk what I did here, tab9 kinda just did this for lmao.
                playerHand[playerHand.length - 1] = decky.deal();
                System.out.println("User's hand: " + Arrays.toString(playerHand));
            } else if (choice.equals("stand")) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter Hit or Stand.");
            }
        }
    }


    private void dealerTurn(){
        while (dealerKeepHitting(dealerHand)) {
            dealerHand = Arrays.copyOf(dealerHand, dealerHand.length + 1); // Gets the exact copy of the same hand, and increases it by 1 so that it keeps hitting on the next line without out of bounds error
            dealerHand[dealerHand.length - 1] = decky.deal(); // gets card.
            System.out.println("Dealer's hand: " + Arrays.toString(dealerHand));
        }
    }


    public static int calcPoints(Card[] hand) {
       int totalPts = 0; // calcsPoints for this iteration

       for (Card card : hand) { // stonks
           String value = card.getValue();
           if (value.equals("Ace")) {
               totalPts += 11;
           } else if (value.equals("King") || value.equals("Queen") || value.equals("Jack")){
                totalPts += 10;

           } else if (value.equals("2")) {
                totalPts += 2;
           } else if (value.equals("3")){
            totalPts += 3;
           } else if (value.equals("4")){
            totalPts += 4;
           } else if (value.equals("5")) {
            totalPts += 5;
           } else if (value.equals("6")) {
            totalPts += 6;
           } else if (value.equals("7")) {
            totalPts += 7;
           } else if (value.equals("8")) {
            totalPts += 8;
           } else if (value.equals("9")) {
            totalPts += 9;
           } else if (value.equals("10")) {
            totalPts += 10;
           } else{
            totalPts += Integer.parseInt(value);
           }
       }
       return totalPts;
    }

    public static String determineResult(Card[] userHand, Card[] dealerHand) {
        int userPoints = calcPoints(userHand); // calcs point to see who wins
        int dealerPoints = calcPoints(dealerHand);

        if (isBust(userHand)) { // checks who wins
            return "User Bust";
        } else if (isBust(dealerHand)) {
            return "Dealer Bust";
        } else if (isBlackjack(userHand) && isBlackjack(dealerHand)) {
            return "User Pushes";
        } else if (isBlackjack(userHand)) {
            return "User Wins";
        } else if (isBlackjack(dealerHand)) {
            return "User Loses";
        } else if (userPoints > dealerPoints) {
            return "User Wins";
        } else if (userPoints < dealerPoints) {
            return "User Loses";
        } else {
            return "User Pushes";
        }
    }

    public static boolean isBust(Card[] hand) {
        if (calcPoints(hand) > 21){
            return true;
        }
        return false;
    }

    public static boolean isBlackjack(Card[] hand) {
        return hand.length == 2 && calcPoints(hand) == 21;
    }

    public static boolean dealerKeepHitting(Card[] hand) {
        return calcPoints(hand) < 17;
    }

}
