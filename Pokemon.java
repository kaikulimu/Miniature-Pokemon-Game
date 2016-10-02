import java.util.*;

public class Pokemon {
   
   private static Random rand = new Random();
   //[private] instance data members
   private String name; 
   private int level, health, attack, defense, base, id;
   private Type type;
   
   final private int DEFAULT = 50; 
   final private double other = 1.0;
   
   
   //[public] constructors
   
   /**Constructs a Pokemon.
      @param name The name of the Pokemon
      @param level The level
      @param health The starting health
      @param attack The attack stat
      @param defense The defense stat
      @param type The Pokemon's type
      @param ID The Pokemon's id
   */
   public Pokemon (String name, int level, int health, int attack, int defense, int base, Type type, int ID) {
      this.name = name;
      this.level = level;
      this.health = health;
      this.attack = attack;
      this.defense = defense;
      this.base = base;
      this.type = type;
      id = ID;
   }
   
   /**Constructs a Pokemon given the name, level, type, and id only.
      @param name The name of the Pokemon
      @param level The level
      @param type The Pokemon's type
      @param ID The Pokemon's id
   */
   public Pokemon (String name, int level, Type type, int ID) {
      this.name = name;
      this.level = level;
      health = 2*level + randInt(1, 10);
      attack = randInt(1,499);
      defense = randInt(1,499);
      base = randInt(10,250);
      this.type = type;
      id = ID;
   }
     
   
   /**Causes a Pokemon to attack another Pokemon.
      @param target The defending Pokemon
      @param base The base damage of the attack
      @param critical The critical damage modifier: 1.0 for non-critical, 2.0 for critical
      @param typeModifier The type damage modifier: {0.25, 0.5, 1.0, 2.0, 4.0}
      @param STAB The same-type attack bonus: either 1.0 or 1.5
      @param random A random value between [0.85, 1.0]
      @return The damage done, as a rounded-down integer.
   */
   public int attack(Pokemon target, int base, float critical, float typeModifier, float STAB, float random) {
      double modifier = STAB * typeModifier * critical * other * random;
      int damage = (int) Math.floor(((2.0*level + 10.0)/250.0 * attack/target.getDefense() * base + 2.0) * modifier);
      target.hurt(damage);
      
      //e.g. Charmander attacked Bulbasaur for 11 damage.
      System.out.println(this.name + " attacked " + target.name + " for " + damage + " damage.");
      if (critical == 2.0)
         System.out.println("Critical hit!");
      if (target.health ==0)
         System.out.println(target.name + " fainted!");
      return damage;
   }
   
   /**Test whether two Pokemons are referring to the same Pokemon.
      @param other The other Pokemon for comparison
      @return True if the two Pokemons are the same, false otherwise
   */
   public boolean equals(Pokemon other) {
      //this. vs other.
      return this.name.equalsIgnoreCase(other.name) && this.level == other.level && this.id == other.id;
   }
   
   /**Gets the attack stat of the Pokemon.
      @return The attack stat
   */
   public int getAttack() {
      return attack;
   }
   
   /**Gets the defense stat of the Pokemon.
      @return The defense stat
   */
   public int getDefense() {
      return defense;
   }
   
   /**Gets the current health of the Pokemon.
      @return The current health points
   */
   public int getHealth() {
      return health;
   }
   
   /**Gets the base damage of the Pokemon.
      @return The base damage
   */
   public int getBase() {
      return base;
   }
   
   /**Gets the level of the Pokemon.
      @return The current level
   */
   public int getLevel() {    
      return level;
   }
   
   /**Gets the id of the Pokemon.
      @return The id
   */
   public int getID() {    
      return id;
   }
   
   /**Gets the name of the Pokemon.
      @return The name
   */
   public String getName() { 
      return name;
   }
         
   /**Gets the type of the Pokemon.
      @return The type
   */
   public Type getType() {
      return type;
   }
   
   /**Decreases a Pokemon's health points.
      @param damage The damage amount, should never be < 0
   */
   public void hurt(int damage) {
      health -= damage;
      if (health < 0)
         health = 0;
   }
   
   /**Determines whether the Pokemon is still alive.
      @return True if the Pokemon is alive, false otherwise
   */
   public boolean isAlive() {
      if (health == 0) 
         return false;
      else   
         return true;
   }
   
   /**Returns the string representation of a Pokemon which includes name and level only.
      @return The string of a Pokemon
   */
   public String toString() {
      return name + " " + level + " " + type + " " + health + " " + attack + " " + defense + " " + id;
   }
   
   /** Randomly generates an integer from a lower bound to a higher bound.
      @param low the lower bound of the integer, inclusive
      @param high the higher bound of the integer, inclusive
      @return an integer within the bound
   */
   public static int randInt(int low, int high){
      int rnd = 0;
      rnd = rand.nextInt(high - low + 1) + low;
      return rnd;
   }

}
