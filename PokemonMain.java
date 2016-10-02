/* 
    Miniature Pokemon Game
    Author: Yuan Jing Vincent Yan (kaikulimu@yahoo.com.hk)
*/


import java.util.*;

public class PokemonMain {

   private static Random rand = new Random();
   
   private static Trainer[] TrainerSet;
   private static int id;
   private static int trainerSize;
   private static Scanner kb = new Scanner(System.in); 

   public static void main(String[] args) throws Exception{
      int option = 0;
      int end = 0;
      
      id = 1;
      trainerSize = 0;
      TrainerSet = new Trainer[1];
      Trainer newTrainer;
      int case1count, case2count, case3count, case5count, case5count2, case5count3, case5count4;
      String trainerWantsPoke;
      String case2PokeName;
      int case2PokeLvl;
      String case2PokeType;
      int battler1 = -1;
      int battler2 = -1;
      int battleID1, battleID2;
      Pokemon battlePoke1 = new Pokemon(null, 0, 0, 0, 0, 0, null, 0);
      Pokemon battlePoke2 = new Pokemon(null, 0, 0, 0, 0, 0, null, 0);
      
      
      System.out.println("Welcome to Pokemon Battle Set-up Program!");
   
      while (end == 0) {
      //Options menu
         System.out.println("Please select an option from the menu:");
         System.out.println("1. Add a Trainer");
         System.out.println("2. Add a Pokemon to a Trainer");
         System.out.println("3. Display all the Pokemon for a Trainer");
         System.out.println("4. Display the names and Pokemon for all the Trainers");
         System.out.println("5. Stage a battle between two Pokemon");
         System.out.println("6. Display the trainer who has the highest level Pokemon");
                  //display just one of the Trainers if it's a tie
         System.out.println("7. Exit the program");
         //System.out.println("trainerSize = " + trainerSize);
         option = kb.nextInt();
      
         switch (option) {
            case 1:
               do {
                  case1count = 0;
                  System.out.println("Please enter a new Trainer name:");
                  newTrainer = new Trainer(kb.next());
                  if (trainerSize > 0) {
                     for (int i=0; i<trainerSize; i++) {
                        if (newTrainer.getName().equals(TrainerSet[i].getName())) {
                           System.out.println("This Trainer already exist! Please enter another name!");
                           case1count++;
                        }
                     }  
                  }  
               } while (case1count != 0);
               addTrainer(newTrainer);
               System.out.println();
               break;
            case 2:  
               case2count = 0;
               System.out.println("Enter Trainer name to add a new Pokemon to:");
               trainerWantsPoke = kb.next();
               for (int i=0; i<trainerSize; i++) {
                  if (trainerWantsPoke.equals(TrainerSet[i].getName())) {
                     System.out.println("Enter new Pokemon's name, level and type(e.g. Charmander 4 FIRE)");
                     case2PokeName = kb.next().toLowerCase();
                     case2PokeLvl = kb.nextInt();
                     case2PokeType = kb.next();
                     TrainerSet[i].addPokemon(case2PokeName, case2PokeLvl, case2PokeType);
                     case2count++;
                  }
               } 
               
               if (case2count == 0)
                  System.out.println("Invalid Trainer!!");
               System.out.println();
               break;
            case 3:
               case3count = 0;
               System.out.println("Enter Trainer name to display all Pokemon:");
               trainerWantsPoke = kb.next();
               for (int i=0; i<trainerSize; i++) {
                  if (trainerWantsPoke.equals(TrainerSet[i].getName())) {
                     System.out.println(TrainerSet[i]);
                     TrainerSet[i].displayPokemon();
                     case3count++;
                  }
               } 
               
               if (case3count == 0)
                  System.out.println("Invalid Trainer!!");
               System.out.println();
               break;
            case 4:
               bubbleSortTrainers();
               for (int i=0; i<trainerSize; i++) {
                  System.out.println(TrainerSet[i]);
                  TrainerSet[i].displayPokemon();
                  System.out.println();
               }
               break;
            case 5:
               if (trainerSize == 0) {
                  System.out.println("There isn't any Trainer");
                  System.out.println();
                  break;
               }
               
               System.out.println("Prepare for battle!");
               case5count = 0;
               case5count2 = 0;
               case5count3 = 0;
               case5count4 = 0;
               System.out.println("Enter first Trainer's name and Pokemon ID:");
               trainerWantsPoke = kb.next();
               for (int i=0; i<trainerSize; i++) {
                  if (trainerWantsPoke.equals(TrainerSet[i].getName())) {
                     battler1 = i;
                     case5count++;
                  }
               } 
               
               battleID1 = kb.nextInt();
               if (case5count != 0) {   
                  for (int i=0; i<TrainerSet[battler1].getnumPoke(); i++) {
                     if (battleID1 == TrainerSet[battler1].getPokeID(i)){
                        battlePoke1 = TrainerSet[battler1].getPoke(i);
                        case5count2++;
                     }
                  } 
               }
            
               if (case5count == 0) {
                  System.out.println("Invalid Trainer!!");
                  System.out.println();
               } 
               else if (case5count2 == 0) {
                  System.out.println("Invalid Pokemon ID!");
                  System.out.println();
               }
               else {                  
                  case5count3 = 0;
                  System.out.println("Enter second Trainer's name and Pokemon ID:");
                  trainerWantsPoke = kb.next();
                  for (int i=0; i<trainerSize; i++) {
                     if (trainerWantsPoke.equals(TrainerSet[i].getName())) {
                        battler2 = i;
                        case5count3++;
                     }
                  } 
                  if (battler2 == battler1) {
                     System.out.println("A Trainer can't fight against him/herself.");
                     case5count3 = 0;  
                  }
                  
                  battleID2 = kb.nextInt();
                  if (case5count != 0) {   
                     for (int i=0; i<TrainerSet[battler2].getnumPoke(); i++) {
                        if (battleID2 == TrainerSet[battler2].getPokeID(i)){
                           battlePoke2 = TrainerSet[battler2].getPoke(i);
                           case5count4++;
                        }
                     } 
                  }
                  
                  if (case5count3 == 0) {
                     System.out.println("Invalid Trainer!!");
                     System.out.println();
                  }
                  else if (case5count4 == 0) {
                     System.out.println("Invalid Pokemon ID!");
                     System.out.println();
                  }
                  
                  else { //now both Pokemons are valid. Battle begins!
                     System.out.println("Battle begins!");
                     PokemonBattle(battlePoke1, battlePoke2, battler1, battler2);
                     System.out.println();
                  }
               }
               break;
         
            case 6:
               Trainer temp;
               if (trainerSize == 0) {
                  System.out.println("There isn't any Trainer");
                  System.out.println();
                  break;
               }
               for (int i = trainerSize-1; i > 0; i--) {
                  if (TrainerSet[i].highestLvlPoke().getLevel() > TrainerSet[i-1].highestLvlPoke().getLevel()){
                     temp = TrainerSet[i];
                     TrainerSet[i] = TrainerSet[i-1];
                     TrainerSet[i-1] = temp;
                  }
               }
               System.out.println(TrainerSet[0] + " has the highest level Pokemon");
               System.out.println();
               break;
            case 7:
               System.out.println("Goodbye!");
               end = 1;
               break;
            default: 
               System.out.println("Invalid Option! Please select again.");
         }
      
      }
   
   }   
   
   /**Sort the array of Trainers, TrainerSet, in ascending order
   */
   public static void bubbleSortTrainers() {
      Trainer temp;
      for (int end = trainerSize-1; end > 0; end--)
         for (int i=0; i < end; i++)
            if (TrainerSet[i].greaterThan(TrainerSet[i+1])) {  // swap
               temp = TrainerSet[i];
               TrainerSet[i] = TrainerSet[i+1];
               TrainerSet[i+1] = temp;
            }
   }

   /**Add a Trainer to the array of Trainers
      @param t The Trainer to be added
   */
   public static void addTrainer(Trainer t) {
      if (trainerSize == TrainerSet.length) {
         resize();
      }
      TrainerSet[id-1] = t;
      trainerSize++;
      System.out.println("Added a new Trainer called: " + TrainerSet[id-1]);
      id++;
   }  
   
   /**Double the size of the array of Trainers
   */
   public static void resize() {
      Trainer[] newTrainerSet = new Trainer[TrainerSet.length*2];
      for (int i=0; i<TrainerSet.length; i++) {
         newTrainerSet[i] = TrainerSet[i];
      }
      TrainerSet = newTrainerSet;
   }
   
   /**Stage a battle between two Pokemons
      @param a Pokemon of the first trainer
      @param b Pokemon of the second trainer
      @param trainA ID of the first trainer with respect to the array of Trainers
      @param trainB ID of the second trainer with respect to the array of Trainers
   */         
   public static void PokemonBattle(Pokemon a, Pokemon b, int trainA, int trainB) throws Exception
   {
   
      while (a.isAlive() && b.isAlive())
      {
         float critical = rand.nextInt() % 4 == 0 ? 2.0f : 1.0f;
         float random = RandomVariable();
         float typeModifier = getTypeModifier(a.getType(), b);
         float STAB = getSTAB(a.getType(), a);
         
            //Charmander, use Ember!
         a.attack(b, a.getBase(), critical, typeModifier, STAB, random);
         
            //If b has fainted break out of the loop
         if (!b.isAlive()) {
            TrainerSet[trainB].deletePoke(b);
            break;
         }
         
         random = RandomVariable();
         typeModifier = getTypeModifier(b.getType(), a);
         STAB = getSTAB(b.getType(), b);
         
            //Bulbasaur, use Vine Whip!    
         b.attack(a, b.getBase(), critical, typeModifier, STAB, random);
         
         if (!a.isAlive()) {
            TrainerSet[trainA].deletePoke(a);
            break;  
         }
      }
      
        //Test getHealth() and isAlive() now that a Pokemon has fainted
      
      if (a.getHealth() != 0 && b.getHealth() != 0)
         System.out.println("getHealth() is incorrect!");
      
      if (a.isAlive() && b.isAlive())
         System.out.println("isAlive() is incorrect!");
   }
   
   /**Generate a random real number from 0.85 to 1.00 with exactly two digits of precision
      after the decimal point, inclusive at both ends
      @return The random number
   */
   public static float RandomVariable()
   {
      return rand.nextFloat() * 0.15f + 0.85f;
   }
   
   /**Generate the type damage modifier when a Pokemon attacks another Pokemon
      @param attackType The type of the attacking Pokemon
      @param target The defending Pokemon
      @return The type damage modifier
   */
   public static float getTypeModifier(Type attackType, Pokemon target) throws Exception
   {
      if (attackType == Type.FIRE)
      { 
         if (target.getType() == Type.GRASS)
            return 2.0f;
         else if (target.getType() == Type.FIRE)
            return 0.5f;
         else if (target.getType() == Type.WATER)
            return 0.5f;
         else if (target.getType() == Type.ELECTRIC)
            return 1.0f;
         else
            throw new Exception();
      }
      else if (attackType == Type.GRASS)
      { 
         if (target.getType() == Type.GRASS)
            return 0.5f;
         else if (target.getType() == Type.FIRE)
            return 0.5f;
         else if (target.getType() == Type.WATER)
            return 2.0f;
         else if (target.getType() == Type.ELECTRIC)
            return 1.0f;
         else
            throw new Exception();
      }
      else if (attackType == Type.WATER)
      { 
         if (target.getType() == Type.GRASS)
            return 0.5f;
         else if (target.getType() == Type.FIRE)
            return 2.0f;
         else if (target.getType() == Type.WATER)
            return 0.5f;
         else if (target.getType() == Type.ELECTRIC)
            return 1.0f;
         else
            throw new Exception();
      }
      else if (attackType == Type.ELECTRIC)
      { 
         if (target.getType() == Type.GRASS)
            return 0.5f;
         else if (target.getType() == Type.FIRE)
            return 1.0f;
         else if (target.getType() == Type.WATER)
            return 2.0f;
         else if (target.getType() == Type.ELECTRIC)
            return 0.5f;
         else
            throw new Exception();
      }
      else
         throw new Exception();
   }
   
   /**Generate the same-type attack bonus when a Pokemon attacks another Pokemon
      @param attackType The type of the attacking Pokemon
      @param source The attacking Pokemon
      @return The same-type attack bonus
   */
   public static float getSTAB(Type attackType, Pokemon source)
   {
      if (attackType == source.getType())
         return 1.5f;
      else
         return 1.0f;
   }

}


/* Reflections:
Although this assignment was very tedious and time-consuming, it was not hard once I figured out how to sort 
arrays and validate an input with each component of an array efficiently. The main take-away from this assignment
is that game developers in real life are doing a really good job because it's so hard to create a game, providing
that bug-testing has to be thorough as well. Therefore, in addition to learning how to do comparisons with regard
to objects within an array, I also learned that in complicated programs bug-testing can be harder than the actual
code itself and thus a large amount of time must be devoted to fix all the bugs. In order to fix bugs efficiently,
I realized that javadocs are very important - otherwise I wouldn't even know what my codes were supposed to be 
doing. Furthermore, I improved my ability to manipulate methods between different object classes. This is 
definitely the longest assignment ever!! It took me more than 10 hours to complete! I took lots of time to debug
because there were many sublte little bugs that were not obvious at all. Yet, the best part of the assignment is
that now that I have finished my Pokemon Battle Program, I can showcase it to my friends and play Pokemon with 
them! They are going to be so amazed by what I am capable of, or, more precisely, what Java is capable of. If I 
were the teacher, I would instruct the students to generate more realistic attack and defense stats, instead of
random numbers from 1 to 499, because Pokemons are dying too fast and sometimes a low-level Pokemon can defeat a
high-level Pokemon if it has lucky stats.
*/