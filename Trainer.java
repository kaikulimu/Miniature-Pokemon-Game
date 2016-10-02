import java.util.*;

public class Trainer {
   
   private String name;
   private Pokemon[] myPokemons;
   private int numPoke; 
   private int pokeID;
   
   /**Constructs a Trainer.
      @param name The name of the Trainer
   */
   public Trainer(String name) {
      this.name = name;
      myPokemons = new Pokemon[1];
      numPoke = 0;
      pokeID = 1;
   }
   
   /**Add a new Pokemon to the Trainer
      @param nameX New Pokemon's name
      @param lvlX New Pokemon's level
      @param typeX New Pokemon's type
   */
   public void addPokemon(String nameX, int lvlX, String typeX){
      int counter = 0;
      Type typeXX = Type.FIRE;
      if (numPoke == myPokemons.length) {
         resize();
      }
      
      char nameInit = Character.toUpperCase(nameX.charAt(0));
      nameX = nameInit + nameX.substring(1);
      
      if (! (nameX.equals("Pikachu") || nameX.equals("Charmander") || nameX.equals("Squirtle") 
                  || nameX.equals("Bulbasaur")))
         System.out.print("Invalid Pokemon! ");            
      else
         counter += 1;
         
      if (lvlX < 1 || lvlX > 99)
         System.out.print("Invalid Level! ");
                  
      else
         counter += 1;  
         
      //Types: FIRE, WATER, GRASS, ELECTRIC
      counter ++;
      if (typeX.equalsIgnoreCase("FIRE"))
         typeXX = Type.FIRE;
      else if (typeX.equalsIgnoreCase("WATER"))
         typeXX = Type.WATER;
      else if (typeX.equalsIgnoreCase("GRASS"))
         typeXX = Type.GRASS;
      else if (typeX.equalsIgnoreCase("ELECTRIC"))
         typeXX = Type.ELECTRIC;
      else {
         System.out.print("Invalid type! ");
         counter--;
      }
      
      if (counter == 3) {
         myPokemons[numPoke] = new Pokemon(nameX, lvlX, typeXX, pokeID);
         System.out.println("Added new Pokemon: " + nameX);
         numPoke++;
         pokeID++;
      }
      else
         System.out.println();
   }
   
   /**Display all Pokemons for a Trainer, sorted by health from highest to lowest
   */
   public void displayPokemon() {
      bubbleSortPoke();
      for (int i=0; i<numPoke; i++) {
         System.out.println("Level " + myPokemons[i].getLevel() + " " + myPokemons[i].getName() + " ID#" + 
            myPokemons[i].getID() + " Health: " + myPokemons[i].getHealth());
      }
   }
   
   /**Find the highest level Pokemon of a Trainer
      @return The highest level Pokemon
   */
   public Pokemon highestLvlPoke() {
      Pokemon temp;
      if (numPoke == 0)
         return new Pokemon(null, 0, 0, 0, 0, 0, null, 0);
      for (int end = numPoke-1; end > 0; end--) {
         if (myPokemons[end].getLevel() > myPokemons[end-1].getLevel()) {  // swap
               temp = myPokemons[end];
               myPokemons[end] = myPokemons[end-1];
               myPokemons[end-1] = temp;
            }
      }
      return myPokemons[0];
   }
   
   /** Sort array of Pokemons, in descending order of health
   */
   public void bubbleSortPoke() {
      Pokemon temp;
      for (int end = numPoke; end > 0; end--)
         for (int i=0; i < end-1; i++)
            if (myPokemons[i].getHealth() < myPokemons[i+1].getHealth()) {  // swap
               temp = myPokemons[i];
               myPokemons[i] = myPokemons[i+1];
               myPokemons[i+1] = temp;
            }
   }
   
   /**Test whether a Trainer's name is alphabetically greater than another Trainer's name, ignoring case
      @param t2 The other Trainer
      @return True if the first Trainer's name is alphabetically greater, false otherwise
   */
   public boolean greaterThan(Trainer t2) {
      String name1 = this.name.toLowerCase();
      String name2 = t2.name.toLowerCase();
      while (name1.length() != 0 && name2.length() != 0){
         if (name1.charAt(0) > name2.charAt(0))
            return true;
         else if (name1.charAt(0) < name2.charAt(0))
            return false;
         else if (name1.charAt(0) == name2.charAt(0)) {
            name1 = name1.substring(1);
            name2 = name1.substring(1);
         }
      }
      if (name1.length() == 0)
         return false;
      return true;
   }
   
   /**Double the size of the array of Pokemons
   */
   public void resize() {
      Pokemon[] newPokemons = new Pokemon[myPokemons.length*2];
      for (int i=0; i<myPokemons.length; i++) {
         newPokemons[i] = myPokemons[i];
      }
      myPokemons = newPokemons;
   }
   
   /**Delete a Pokemon from the Trainer
      @param xx The Pokemon to be deleted
   */
   public void deletePoke(Pokemon xx) {
      int where = -1;
      for (int i=0; i < numPoke && where < 0; i++)
         if (myPokemons[i].equals(xx))
            where = i;
   
      for ( ; where < numPoke-1; where++)
         myPokemons[where] = myPokemons[where+1];
      numPoke--;
      myPokemons[numPoke] = null;
   }
   
   /**Gets a Pokemon from the Trainer
      @param j the index of the Pokemon in the array of Pokemons
      @return The desired Pokemon
   */
   public Pokemon getPoke(int j) {
      return myPokemons[j];
   }
   
   /**Gets the id of a Pokemon from the Trainer
      @param j the index of the Pokemon in the array of Pokemons
      @return The Pokemon's id
   */
   public int getPokeID(int j) {
      return myPokemons[j].getID();
   }
   
   /**Gets the name of the Trainer
      @return The name
   */ 
   public String getName() {
      return name;
   }
   
   /**Gets the number of Pokemons possessed by the Trainer
      @return The number of Pokemons
   */
   public int getnumPoke() {
      return numPoke;
   }
   
   /****Returns the string representation of a Pokemon which includes name and level only.
      @return The string of a Pokemon
   */
   public String toString() {
      return name;
   }
}