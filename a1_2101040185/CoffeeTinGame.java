package a1_2101040185;

import utils.TextIO;

import java.util.Arrays;
import java.util.Random;

/**
 * @overview A program that performs the coffee tin game on a 
 *    tin of beans and display result on the standard output.
 *
 */
public class CoffeeTinGame {
    /** constant value for the green bean */
    private static final char GREEN = 'G';
    /** constant value for the blue bean */
    private static final char BLUE = 'B';
    /** constant for removed beans */
    private static final char REMOVED = '-';
    /** the null character */
    private static final char NULL = '\u0000';
    /** the char 30-elements array containing BLUE, GREEN and REMOVED */
    private static char [] BeansBag =
            {'B','G','-','B','G','-','B','G','-','B','G','-','B','G','-',   // 5  -3
            'B','G','-','B','G','-','B','G','-','B','G','-','B','G','-',};  // 5  -3

    /**
     * the main procedure
     * @effects
     *    initialise a coffee tin
     *    {@link TextIO#putf(String, Object...)}: print the tin content
     *    {@link @tinGame(char[])}: perform the coffee tin game on tin
     *    {@link TextIO#putf(String, Object...)}: print the tin content again
     *    if last bean is correct
     *      {@link TextIO#putf(String, Object...)}: print its colour
     *    else
     *      {@link TextIO#putf(String, Object...)}: print an error message
     */
//    public static void main(String[] args) {
//          char [] tin = { BLUE, GREEN, GREEN, GREEN, BLUE, BLUE, GREEN, GREEN, BLUE};
//          TextIO.putf("Original Tin : "+ new String(tin));
//          //
//        int green = 0;
//        for (char bean : tin){
//            if (bean == GREEN )
//                ++green;
//        }
//        char expectedLastBean = (green % 2 == 0) ? 'B' : 'G';
//          //
//        char theLastBean = tinGame(tin);
//        TextIO.putf("\nAfter playing Tin : "+ new String(tin));
//        //
//
//        if(expectedLastBean != theLastBean)
//            TextIO.putf("\nError!");
//        else
//            TextIO.putf("\n The last bean is : "+ String.valueOf(theLastBean));
//    }
    public static void main(String[] args) {
        // initialise some beans
        char[][] tins = {
                {BLUE, BLUE, BLUE, GREEN, GREEN},
                {BLUE, BLUE, BLUE, GREEN, GREEN, GREEN},
                {GREEN},
                {BLUE,GREEN, GREEN, GREEN},
                {BLUE, GREEN}
        };

        for (int i = 0; i < tins.length; i++) {
            // System.out.println(tins.length);
            char[] tin = tins[i];

            // expected last bean
            // p0 = green parity /\
            // (p0=1 -> last=GREEN) /\ (p0=0 -> last=BLUE)
            // count number of greens
            int greens = 0;
            for (char bean : tin) {
                if (bean == GREEN)
                    greens++;  // dem hat xanh co trong tin
            }
            // expected last bean      hat cuoi cung la mau gi se duoc du doan dua vao so hat green trong tin
            final char last = (greens % 2 == 1) ? GREEN : BLUE;

            // print the content of tin before the game
            System.out.printf("%nTIN (%d Gs): %s %n", greens, Arrays.toString(tin));

            // perform the game
            // get actual last bean
            char lastBean = tinGame(tin);
            // lastBean = last \/ lastBean != last

            // print the content of tin and last bean
            System.out.printf("tin after: %s %n", Arrays.toString(tin));

            // check if last bean as expected and print
            if (lastBean == last) {
                System.out.printf("last bean: %c%n", lastBean);
            } else {
                System.out.printf("Oops, wrong last bean: %c (expected: %c)%n", lastBean, last);
            }
        }
    }

    /**
     * Performs the coffee tin game to determine the colour of the last bean
     *
     * @requires tin is not null /\ tin.length > 0
     * @modifies tin
     * @effects
     *   take out two beans from tin
     *   if same colour
     *     throw both away, put one blue bean back
     *   else
     *     put green bean back
     *   let p0 = initial number of green beans
     *   if p0 = 1
     *     result = `G'
     *   else
     *     result = `B'
     */
    public static char tinGame(char[] tin) {

        while (hasAtLeast2Beans ( tin )){
            char [] twoBeans  = takeTwo( tin ) ;
            //System.out.println("lay ra: "+ Arrays.toString(twoBeans));
//            check 2 beans then throw and put back one
            updateTin( tin, twoBeans);
        }
        // if tin has only one bean or null
        return anyBean(tin);
    }

    /**
     * @effects
     *  if tin has at least two beans
     *    return true
     *  else
     *    return false
     */
    private static boolean hasAtLeast2Beans(char[] tin) {
        int times = 0;  //count the available beans
        for (char c : tin){
            if ( c  != REMOVED )
                ++times;
        }
        if (times >= 2)
            return true;
        return false;  // return instantly less than 2, mostly 1
    }

    /**
     * @requires tin has at least 2 beans left
     * @modifies tin
     * @effects
     *  remove any two beans from tin and return them
     */
    private static char[] takeTwo(char[] tin) {
        return new char[]{ takeOne( tin ) , takeOne( tin ) };
    }

    /**
     * @requires tin has at least one bean
     * @modifies tin
     * @effects
     *     {@link: #randInt(int) } : choose a random bean from tin
     *      repeat choosing randomly until get an actual bean
     *   remove that bean chosen randomly from tin and return it
     */
    public static char takeOne(char[] tin) {

//          choose 2 differ indices => this can be done by assigning the selected bean to '_'
             int index = randInt(tin.length);
             while ( tin [index] == REMOVED ){
                index = randInt(tin.length);
             }
             char bean = tin [index]; //get bean at random index
             tin [index] = REMOVED;  // the set index (where I pick up bean ) to removed value

             return bean;

    }

    /**
     * Update tin according to the game moves.
     *
     * @requires <tt>tin != null /\
     *  twoBeans != null /\ twoBeans.length=2 /\
     * twoBeans[0], twoBeans[1] in { BLUE, GREEN } </tt>
     * @modifies <tt>tin</tt>
     * @effects <tt>let b1 = twoBeans[0], b2 = twoBeans[1]
     *    if b1 = b2
     *      throw both away
     *     {@link #putIn} : put a blue bean back
     *    else
     *      throw away the blue bean
     *     {@link #putIn} : put the green one back from twoBeans
     *  </tt>
     */
    public static void updateTin(char[] tin, char[] twoBeans) {
        char bean1 = twoBeans[0];
        char bean2 = twoBeans[1];
        //if (tin.length == 0 || twoBeans.length == 0 || twoBeans.length < 2) return;  ?? return what
        if (bean1 == bean2){
            putIn (tin, getBean(BeansBag, BLUE));
//            for (int i = 0; i < tin.length; i++){
//                if (tin[i] == REMOVED){
//
//                   tin[i] = getBean(BeansBag, BLUE); //using putIn
//                    break;
//                }
//            }
        }
        else {
             putIn(tin, getBean(twoBeans, GREEN));
//            for (int i = 0; i < tin.length; i++){
//                if (tin[i] == REMOVED){
//                    tin[i] = getBean(twoBeans, GREEN);
//                    break;
//                }
//            }
        }
//        throw twoBeans
        twoBeans [0] = REMOVED;
        twoBeans [1] = REMOVED;
        System.out.println("update" + Arrays.toString(tin));
    }

    /**
     * @requires tin has vacant positions for new beans
     * @modifies tin
     * @effects
     *   place bean into any vacant position in tin
     */
    private static void putIn(char[] tin, char bean) {
              for (int i = 0; i < tin.length; i++){
                  if (tin [i] == REMOVED){
                      tin [i] = bean;
                      break;
                  }
              }
    }

    /**
     * @effects
     *  if there are beans in tin
     *    return any such bean
     *  else
     *    return '\u0000' (null character)
     */
    private static char anyBean(char[] tin) {
        for (char bean : tin){
            if (bean != REMOVED)  //find one bean left
//                bean = REMOVED;   // take this out lastly
                return bean;
        }
        // none bean left
        return NULL;
    }

    /**   ????
     *  choose randomly a bean from tin ranging from 0 to n - 1
     * @requires chose a bean (not blank) (but the input is just an integer, not include tin)
     *  @effects return this random index
     */
    public static int randInt (int n){  // n maybe the possibilities that contains beans
        Random rd = new Random();
        int index = rd.nextInt(n);  // make sure that choosing 2 different beans from 2 specific index
        return index;               // and it's not REMOVED
    }

    /**
     *
     *  @requires array is not null /\ array contains at least a bean matching the bean type 
     *
     *  @effects
     *     repeat choosing randomly a bean until it matches the bean type
     *  return expected bean from Bag and removed it
     *
     */
    public static char getBean (char [] BeansBag, char typeBean){
         int index = randInt(BeansBag.length);
         while (BeansBag [index] != typeBean){
              index = randInt(BeansBag.length);
         }
         if (BeansBag [index] == typeBean)
          {
              BeansBag [index] = REMOVED;
              return typeBean;
          }
        return NULL; // none proper bean left
    }
}
