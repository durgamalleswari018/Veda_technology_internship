import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    // ANSI COLORS
    static final String RESET = "\u001B[0m";
    static final String PURPLE_BG = "\u001B[45m";
    static final String BLUE = "\u001B[34m";
    static final String BRIGHT_BLUE = "\u001B[94m";
    static final String CYAN = "\u001B[96m";
    static final String WHITE = "\u001B[97m";
    static final String YELLOW = "\u001B[93m";
    static final String GREEN = "\u001B[92m";
    static final String RED = "\u001B[91m";
    static final String MAGENTA = "\u001B[95m";
    static final String CLEAR = "\u001B[2J\u001B[H";

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {

        boolean playAgain = true;

        while (playAgain) {

            System.out.print(CLEAR + PURPLE_BG);

            showTitle();
            loadingAnimation();

            int difficulty = chooseDifficulty();

            int maxNumber;
            int maxAttempts;
            int scoreMultiplier;

            if (difficulty == 1) {
                maxNumber = 50;
                maxAttempts = 10;
                scoreMultiplier = 1;
            } else if (difficulty == 2) {
                maxNumber = 100;
                maxAttempts = 8;
                scoreMultiplier = 2;
            } else {
                maxNumber = 500;
                maxAttempts = 7;
                scoreMultiplier = 5;
            }

            int targetNumber = random.nextInt(maxNumber) + 1;
            int attempts = 0;
            int score = 0;
            boolean won = false;

            System.out.println();
            blueBox("GAME STARTED");
            System.out.println(WHITE + "🎯 Guess a number between 1 and "
                    + maxNumber + RESET);
            System.out.println(CYAN + "❤️ Attempts available: "
                    + maxAttempts + RESET);
            System.out.println();

            while (attempts < maxAttempts) {

                System.out.print(YELLOW + "❓ Enter your guess: " + RESET);

                if (!scanner.hasNextInt()) {
                    System.out.println(RED + "⚠️ Please enter a valid number!" + RESET);
                    scanner.next();
                    continue;
                }

                int guess = scanner.nextInt();

                if (guess < 1 || guess > maxNumber) {
                    System.out.println(
                            RED + "⚠️ Enter a number between 1 and "
                            + maxNumber + "!" + RESET);
                    continue;
                }

                attempts++;

                System.out.print(CYAN + "🔍 Checking" + RESET);

                for (int i = 0; i < 3; i++) {
                    Thread.sleep(250);
                    System.out.print(".");
                }

                System.out.println();

                if (guess == targetNumber) {

                    won = true;

                    score = (maxAttempts - attempts + 1)
                            * 100
                            * scoreMultiplier;

                    System.out.println();
                    winAnimation();

                    blueBox("🎉 YOU WON! 🎉");

                    System.out.println(GREEN
                            + "🏆 Correct number: "
                            + targetNumber + RESET);

                    System.out.println(CYAN
                            + "🎯 Attempts used: "
                            + attempts + RESET);

                    System.out.println(YELLOW
                            + "⭐ SCORE: "
                            + score + RESET);

                    if (attempts <= 3) {
                        System.out.println(
                                MAGENTA + "🔥 AMAZING! You're a MASTER GUESSER!"
                                + RESET);
                    } else if (attempts <= 5) {
                        System.out.println(
                                GREEN + "⭐ Excellent performance!"
                                + RESET);
                    } else {
                        System.out.println(
                                CYAN + "👏 Great job! You found it!"
                                + RESET);
                    }

                    break;
                }

                if (guess < targetNumber) {
                    System.out.println(
                            BLUE + "⬆️ TOO LOW!"
                            + RESET);
                    System.out.println(
                            CYAN + "💡 Hint: Try a HIGHER number."
                            + RESET);
                } else {
                    System.out.println(
                            RED + "⬇️ TOO HIGH!"
                            + RESET);
                    System.out.println(
                            CYAN + "💡 Hint: Try a LOWER number."
                            + RESET);
                }

                // EXTRA HINT
                if (attempts == 3 && !won) {
                    if (targetNumber % 2 == 0) {
                        System.out.println(
                                YELLOW + "🔮 EXTRA HINT: The number is EVEN."
                                + RESET);
                    } else {
                        System.out.println(
                                YELLOW + "🔮 EXTRA HINT: The number is ODD."
                                + RESET);
                    }
                }

                int remaining = maxAttempts - attempts;

                System.out.println(
                        BRIGHT_BLUE + "❤️ Remaining attempts: "
                        + remaining + RESET);

                showProgressBar(attempts, maxAttempts);
                System.out.println();
            }

            if (!won) {

                System.out.println();
                blueBox("💥 GAME OVER 💥");

                System.out.println(
                        RED + "😢 You used all your attempts."
                        + RESET);

                System.out.println(
                        YELLOW + "🔐 The secret number was: "
                        + targetNumber + RESET);

                System.out.println(
                        CYAN + "🔄 Better luck next time!"
                        + RESET);
            }

            System.out.println();

            System.out.print(
                    MAGENTA + "❓ Do you want to play again? (Y/N): "
                    + RESET);

            String answer = scanner.next();

            if (!answer.equalsIgnoreCase("Y")) {
                playAgain = false;
            }
        }

        System.out.print(CLEAR);

        blueBox("👋 THANK YOU FOR PLAYING!");

        System.out.println(
                PURPLE_BG + WHITE
                + "      🎮 GAME SESSION ENDED 🎮      "
                + RESET);

        System.out.println();
        scanner.close();
    }

    // TITLE
    static void showTitle() {

        System.out.println(
                BRIGHT_BLUE
                + "╔══════════════════════════════════════════╗"
                + RESET);

        System.out.println(
                BRIGHT_BLUE
                + "║"
                + WHITE
                + "       🎯 NUMBER GUESSING GAME 🎯       "
                + BRIGHT_BLUE
                + "║"
                + RESET);

        System.out.println(
                BRIGHT_BLUE
                + "╚══════════════════════════════════════════╝"
                + RESET);

        System.out.println();
        System.out.println(
                CYAN + "        🧠 TEST YOUR GUESSING SKILLS!"
                + RESET);
    }

    // LOADING ANIMATION
    static void loadingAnimation() throws InterruptedException {

        System.out.print(
                YELLOW + "\n🔮 Initializing game"
                + RESET);

        for (int i = 0; i < 5; i++) {
            Thread.sleep(250);
            System.out.print(
                    BRIGHT_BLUE + " ◆" + RESET);
        }

        System.out.println("\n");
    }

    // DIFFICULTY
    static int chooseDifficulty() {

        System.out.println(
                BRIGHT_BLUE
                + "╔══════════════════════════════════════╗"
                + RESET);

        System.out.println(
                BRIGHT_BLUE
                + "║"
                + WHITE
                + "          ⚡ DIFFICULTY ⚡           "
                + BRIGHT_BLUE
                + "║"
                + RESET);

        System.out.println(
                BRIGHT_BLUE
                + "╠══════════════════════════════════════╣"
                + RESET);

        System.out.println(
                BRIGHT_BLUE + "║ " + GREEN
                + "1️⃣ EASY       1 - 50              "
                + BRIGHT_BLUE + "║" + RESET);

        System.out.println(
                BRIGHT_BLUE + "║ " + YELLOW
                + "2️⃣ MEDIUM     1 - 100             "
                + BRIGHT_BLUE + "║" + RESET);

        System.out.println(
                BRIGHT_BLUE + "║ " + RED
                + "3️⃣ HARD       1 - 500             "
                + BRIGHT_BLUE + "║" + RESET);

        System.out.println(
                BRIGHT_BLUE
                + "╚══════════════════════════════════════╝"
                + RESET);

        int choice;

        while (true) {

            System.out.print(
                    MAGENTA + "\n❓ Select difficulty: "
                    + RESET);

            if (scanner.hasNextInt()) {

                choice = scanner.nextInt();

                if (choice >= 1 && choice <= 3) {
                    return choice;
                }
            } else {
                scanner.next();
            }

            System.out.println(
                    RED + "⚠️ Choose 1, 2, or 3."
                    + RESET);
        }
    }

    // BLUE BOX
    static void blueBox(String message) {

        System.out.println(
                BRIGHT_BLUE
                + "\n╔══════════════════════════════════════╗"
                + RESET);

        System.out.println(
                BRIGHT_BLUE
                + "║ "
                + WHITE
                + message
                + BRIGHT_BLUE
                + " ║"
                + RESET);

        System.out.println(
                BRIGHT_BLUE
                + "╚══════════════════════════════════════╝"
                + RESET);
    }

    // PROGRESS BAR
    static void showProgressBar(int attempts, int maxAttempts) {

        int progress = (attempts * 20) / maxAttempts;

        System.out.print(
                BLUE + "📊 Progress: ["
                + RESET);

        for (int i = 0; i < 20; i++) {

            if (i < progress) {
                System.out.print(
                        BRIGHT_BLUE + "█" + RESET);
            } else {
                System.out.print(
                        WHITE + "░" + RESET);
            }
        }

        System.out.println(
                BLUE + "]"
                + RESET);
    }

    // WIN ANIMATION
    static void winAnimation() throws InterruptedException {

        System.out.print(
                GREEN + "\n🎉"
                + RESET);

        for (int i = 0; i < 5; i++) {
            Thread.sleep(150);
            System.out.print(
                    YELLOW + " ✨"
                    + RESET);
        }

        System.out.println("\n");
    }
}