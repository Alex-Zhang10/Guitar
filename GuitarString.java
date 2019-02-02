// Borui (Alex) Zhang
// 1/18/2018
// CSE 143 AL 
// TA: Kyle Pierce
// Homework 2

// Class GuitarString simulates a vibrating guitar string
// with a given frequency. It acts as a ring buffer.

import java.util.*; // for Queue

public class GuitarString {
   private Queue<Double> ringBuffer;
   private int capacity;
   
   public static final double ENERGY_DECAY_FACTOR = 0.996;
   
   // pre: frequency > 0 && capacity >= 2 
   //      (throws IllegalArgumentException if not) 
   // post: constructs a resting GuitarString of with a capacity
   //       which creates a ring buffer of desired capacity
   // parameter: double frequency is the frequency of a note
   public GuitarString(double frequency) {
      if (frequency <= 0 || calCapacity(frequency) < 2) {
         throw new IllegalArgumentException();
      } 
      ringBuffer = new LinkedList<Double>();
      capacity = calCapacity(frequency);
      for (int i = 0; i < capacity; i++) {
         ringBuffer.add(0.0);
      }
   }
   
   // pre: init.length >= 2 
   //      (throws IllegalArgumentException if not) 
   // post: constructs a GuitarString with capacity equals init's length
   //       and initializes the contents of ring buffer
   //       as the values in array double[] init
   // parameter: double[] init stores values for ring buffer 
   public GuitarString(double[] init) {
      if (init.length < 2) {
         throw new IllegalArgumentException();      
      }
      ringBuffer = new LinkedList<Double>();
      capacity = init.length;
      for (int i = 0; i < init.length; i++) {
         ringBuffer.add(init[i]);
      }
   }
   
   // replaces all the elements in the ring buffer with "capacity" 
   // number of random values in the range of -0.5 <= value < 0.5
   public void pluck() {
      while (!ringBuffer.isEmpty()) {
         ringBuffer.remove();
      }
      for (int i = 0; i < capacity; i++) {
         double num = Math.random() - 0.5;
         ringBuffer.add(num);
      }
   }
   
   // removes the first value in ringBuffer
   // add the average of the first two values in ringBuffer times
   // the ENERGY_DECAY_FACTOR to the end of the list
   public void tic() {
      double num1 = ringBuffer.remove();
      ringBuffer.add((num1 + sample()) / 2 * ENERGY_DECAY_FACTOR);
   }
   
   // returns the first value (type double) in the ring buffer
   public double sample() {
      return ringBuffer.peek();
   }
   
   // returns an integer capacity (rounded)
   private int calCapacity(double frequency) {
      return (int) Math.round(StdAudio.SAMPLE_RATE / frequency);
   }
}