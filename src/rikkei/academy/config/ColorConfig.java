package rikkei.academy.config;

/**
 * <p><b><u}>Description detail</u></b> </p>
 * <p>Format color for Console Log.</p>
 * <p>Syntax: (Color Type + "Content" + Reset Type).</p>
 * <p>Exp: System.out.printf(YELLOW + "STRING" + RESET).</p><br/>
 * <p>Escape_character["code" m "Output text"</p>
 * <p><b>Note:</b> DOS doesn't support ANSI colors. You can use Linux terminal or Netbeans IDE to run this program.
 * And Escape character in Java is ASCII code 33</p>
 * <p>exp: \033[30m "Content" \033[0m</p>
 * <p>Detail of Escape character, please refer this
 * <a href="https://en.wikipedia.org/wiki/ANSI_escape_code">Escape Code Link</a></p>
 */
public final class ColorConfig {
    // Reset
    /**
     * Reset color to default
     */
    public static final String RESET = "\033[0m";  // Text Reset

    // Text style
    /**
     * Bold text style
     */
    public static final String BOLD = "\033[1m";  // Bold
    /**
     * Italic text style
     */
    public static final String ITALIC = "\033[3m";  // Bold
    /**
     * Underline text style
     */
    public static final String UNDERLINE = "\033[4m";  // Underline

    // Regular Colors
    /**
     * Color with font style Regular
     */
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    // Bold
    /**
     * Color with font style Bold
     */
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Italics
    /**
     * Color with font style Italic
     */
    public static final String BLACK_ITALIC = "\033[3;30m";  // BLACK
    public static final String RED_ITALIC = "\033[3;31m";    // RED
    public static final String GREEN_ITALIC = "\033[3;32m";  // GREEN
    public static final String YELLOW_ITALIC = "\033[3;33m"; // YELLOW
    public static final String BLUE_ITALIC = "\033[3;34m";   // BLUE
    public static final String PURPLE_ITALIC = "\033[3;35m"; // PURPLE
    public static final String CYAN_ITALIC = "\033[3;36m";   // CYAN
    public static final String WHITE_ITALIC = "\033[3;37m";  // WHITE
    // Underline
    /**
     * Color with font style Underlined
     */
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // Background
    /**
     * Background color
     */
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    // High Intensity
    /**
     * Color with font style High Intensity
     */
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    /**
     * Color with font style Bold High Intensity
     */
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    /**
     * Background with font style Bold High Intensity
     */
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
}