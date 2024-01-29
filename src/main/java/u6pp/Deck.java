package u6pp;


public class Deck {

    private Card[] deck = new Card[52]; // creates deck with no cards
    private int currentCardIndex = 0; // Gets the CURRENT card index (universal) (seperate from other card index)

    public Deck() {
        initializeDeck(); // idk I just have this here cuz it looks nicer ;)
    }

    private void initializeDeck() { // Private, so im fineee :D
        int cardIndex = 0; // gets the index of the cards (important for getting the next cards in Card)

        // Card has a constructor that allows you to create a card with a specific val (or rank) and suit. I hated reading it. >:(
        for (String suit : Card.SUITS) { // for every Card.Suits, we're making a new suit
            for (String stonk : Card.VALUES) { // for every Card.Values, we're making a new value
                deck[cardIndex++] = new Card(suit, stonk); // increase the cardindex every 
            }
        }
    }

    public int numLeft() {
        return deck.length - currentCardIndex;
    }

    public Card deal() {
        if (currentCardIndex < deck.length) {
            return deck[currentCardIndex++]; // increases 1 after returning the deck[currentCardIndex].
        } else{
            return null; // idk what to put here.
        }
    }

    public void shuffle() {
        for (int i = deck.length - 1; i > 0; i--) { // Shuffle the deck from the top (51) and shuffle it all the way down.
            int j = (int) (Math.random() * (i + 1));
            Card temp = deck[i]; // swappers
            deck[i] = deck[j];
            deck[j] = temp;
        }
        currentCardIndex = 0; // 0 once done, so we can start allllllllll over
    }

    public Card[] getDeck() {
        return deck; // wild
    }


    public static void printArray(Card[] array) {
        for (Card card : array) { // self explanatory
            System.out.println(card);
        }
    }
}
