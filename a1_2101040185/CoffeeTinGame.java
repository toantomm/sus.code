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
            {'B','G',' ','B','G',' ','B','G',' ','B','G',' ','B','G',' ',   // 5  -3
            'B','G',' ','B','G',' ','B','G',' ','B','G',' ','B','G',' ',};  // 5  -3

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
    public static void main(String[] args) {
          char [] tin = { BLUE, GREEN, GREEN, GREEN, BLUE, BLUE, GREEN, GREEN, BLUE};
          TextIO.putf("Original Tin : "+ new String(tin));
          //
        int green = 0;
        for (char bean : tin){
            if (bean == GREEN )
                ++green;
        }
        char expectedLastBean = (green % 2 == 0) ? 'B' : 'G';
          //
        char theLastBean = tinGame(tin);
        TextIO.putf("\nAfter playing Tin : "+ new String(tin));
        //

        if(expectedLastBean != theLastBean)
            TextIO.putf("\nError!");
        else
            TextIO.putf("\n The last bean is : "+ String.valueOf(theLastBean));
    }

    /**
     * Performs the coffee tin game to determine the colour of the last bean
     *
     * @requires tin is not null /\ tin.length > 0   tin can be 1
     * @modifies tin
     * @effects
     *   take out two beans from tin
     *   if same colour
     *     throw both away, put one blue bean back
     *   else
     *     put green bean back
     *   let p0 = initial number of green beans    so hat xanh ban dau
     *   if p0 = 1
     *     result = `G'
     *   else
     *     result = `B'
     */
    public static char tinGame(char[] tin) {

        while (hasAtLeast2Beans ( tin )){
          //  !tin.equals(null) && tin.length > 0
            char twoBeans [] = { takeOne( tin ) , takeOne( tin ) };  // or this can be replaced with takeTwo funtion
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
    public static boolean hasAtLeast2Beans(char[] tin) {
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
     * @requires tin has at least one bean
     * @modifies tin
     * @effects
     *   remove any bean from tin and return it
     */
    public static char takeOne(char[] tin) {

//          choose 2 differ indices => this can be done by assigning the selected bean to '_'
           int index = randInt(tin.length);
             while ( tin [index] == REMOVED ){ // repeat changing randomly until get bean
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
     *      put a blue bean back
     *    else
     *      throw away the blue bean
     *      put the green one back
     *  </tt>
     */
     /**
     * @requires tin has vacant positions for new beans
     * @modifies tin
     * @effects
     *   place bean into any vacant position in tin
     */
    public static void updateTin(char[] tin, char[] twoBeans) {
//        if (beans[0] == beans[1])
////                throw both and put B from Bags to tin back
////                  put B back in the previous B index int tin
//
//                else
////                 throw B and put G back in tin
        if (tin.length == 0 || twoBeans.length == 0 || twoBeans.length < 2) return; // ?? return what
        
        if (twoBeans[0] == twoBeans[1]){        
            for (int i = 0; i < tin.length; i++){
                if (tin[i] == REMOVED){// find a blank place to put B
                    tin[i] = getBean(BeansBag, BLUE);// put B back in tin from bag
                    break;
                }
            }
        }
        else {
//                for (char bean : tin){
//                    if ( bean == REMOVED ){
//                        bean = getBean( twoBeans, GREEN); //find a Green bean in twoBeans (this case: G-B)
//                        break;
//                    }
//                }
            for (int i = 0; i < tin.length; i++){
                if (tin[i] == REMOVED){
                    tin[i] = getBean(twoBeans, GREEN);
                    break;
                }
            }
        }
//        throw twoBeans
        twoBeans [0] = REMOVED;
        twoBeans [1] = REMOVED;
    }

    /**
     * @requires tin has vacant positions for new beans
     * @modifies tin
     * @effects
     *   place bean into any vacant position in tin
     */
   

    /**
     * @effects
     *  if there are beans in tin
     *    return any such bean
     *  else
     *    return '\u0000' (null character)
     */
    public static char anyBean(char[] tin) {
        for (char bean : tin){
            if (bean != REMOVED)  //find one bean left
//                bean = REMOVED;   // take this out lastly
                return bean;
        }
        // none bean left
        return NULL;
    }

    /**
     *  choose randomly a bean from tin ranging from 0 to n - 1
     *
     *  @effects
     *   make sure that only choosing a bean (not blank)
     *    return this random index
     */
    public static int randInt (int n){  // n maybe the possibilities that contains beans
        Random rd = new Random();
        int index = rd.nextInt(n);  // make sure that choosing 2 different beans from 2 specific index
        return index;               // and it's not REMOVED
    }
    /**
     *  the rest beans (one bean) after contains2Beans is false
     * @modifies: tin
     *
     */

    /**
     *
     *  return expected bean from Bag and removed it
     *
     */
    public static char getBean (char [] BeansBag, char typeBean){
        for (char c : BeansBag) {
            if (c == typeBean){
                c = REMOVED;
                return typeBean;
            }
        }
        return NULL; // none proper bean left
    }
}
