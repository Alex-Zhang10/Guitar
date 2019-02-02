// Borui (Alex) Zhang
// 1/18/2018
// CSE 143 AL 
// TA: Kyle Pierce
// Homework 2

// Class Guitar37 simulates a guitar with 37 strings
// which allows users to play notes

public class Guitar37 implements Guitar {
   private GuitarString[] list; // list of guitar strings
   private int times; // total times of tic
   
   public static final int TOTAL_STRINGS = 37; // number of strings
   
   public static final int A_POSITION = 24; // standard index of concert A
   
   public static final String KEYBOARD =
      "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout   
   
   // constructs a Guitar with "TOTAL_STRING" number of strings
   // each string has a different pitch
   public Guitar37() {
      times = 0;
      list = new GuitarString[TOTAL_STRINGS];
      for (int i = 0; i < list.length; i++) {
         list[i] = new GuitarString(440 * Math.pow(2, (double)(i - 24) / 12));
      }
   }
   
   // allows user to play pitch by passing an integer value
   // does nothing if the pitch is out of range
   public void playNote(int pitch) {
      int index = pitch + A_POSITION;
      if (index < list.length && index >= 0) {
         list[index].pluck();
      }
   }
   
   // returns true if key is contained in String KEYBOARD
   // false otherwise
   public boolean hasString(char key) {
      return KEYBOARD.indexOf(key) >= 0;
   }
   
   // pre: key is contained in String KEYBOARD 
   //      (throws IllegalArgumentException if not) 
   // post: allows user to play the pitch by passing one of
   //       the specified characters
   public void pluck(char key) {
      if (!hasString(key)) {
         throw new IllegalArgumentException();
      }
      int index = KEYBOARD.indexOf(key);
      list[index].pluck();
   }
   
   // returns the sum (double) of all samples from all strings in the guitar
   public double sample() {
      double sum = list[0].sample();
      for (int i = 1; i < list.length; i++) {
         sum += list[i].sample();
      }
      return sum;
   }
   
   // tics guitar strings one time and adds 1 to total tic times 
   public void tic() {
      for (int i = 0; i < list.length; i++) {
         list[i].tic();
      }
      times++;
   }
   
   // returns (an integer) the times tic has been called
   public int time() {
      return times;
   }
}