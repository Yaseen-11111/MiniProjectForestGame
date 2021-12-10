/* ***************************************
  @author    Yaseen Rashid
  @date      5 October 2021
  @version   1

                The Forest
                __________
    A game where you explore a forest and
        help the animals along the way
   ****************************************/

import java.io.*;
import java.util.*;

public class Main {
    //PLAYER CODE
    //player info setup
    public static class Player implements Serializable {
        String name;
        int health;
        int speed;
        int strength;
        int inventory;
        int xp;
        Weapon currentWeapon;
        Surrounding previousLocation;
        Surrounding currentLocation;
        File gameDirectory;


        List<Food> foodInventory = new ArrayList<>() ;
        List<Weapon> weaponInventory = new ArrayList<>();
    }

    //create player records
    public static void createPlayerRecord(Player player, String name) {
        final Surrounding spawnLocation = getLocation("Crash Site");

        setPlayerInventory(player, 10);
        addWeaponToInventory(getWeaponArray()[0], player);
        setPlayerName(player, name);
        setPlayerHealth(player, 100);
        setPlayerSpeed(player, 10);
        setPlayerCurrentWeapon(player, getPlayerWeaponInventory(player).get(0));
        setPlayerStrength(player, 12 + getPlayerCurrentWeapon(player).strengthIncrease);
        setPlayerCurrentLocation(player, spawnLocation);
    }

    //setters
    public static void setPlayerName(Player player, String name) {
        player.name = name;
    }

    public static void setPlayerHealth(Player player, int health) {
        player.health = health;
    }

    public static void setPlayerSpeed(Player player, int speed) {
        player.speed = speed;
    }

    public static void setPlayerStrength(Player player, int strength) {
        player.strength = strength;
    }

    public static void setPlayerInventory(Player player, int inventory) {
        player.inventory = inventory;
    }

    public static void setPlayerCurrentLocation(Player player, Surrounding currentLocation) {
        player.currentLocation = currentLocation;
    }

    public static void setPlayerPreviousLocation(Player player, Surrounding previousLocation) {
        player.previousLocation = previousLocation;
    }

    public static void setPlayerXP(Player player, int xp) {
        player.xp = xp;
    }

    public static void setPlayerCurrentWeapon(Player player, Weapon weapon) {
        player.currentWeapon = weapon;
    }

    public static void setPlayerGameDirectory(Player player, File file) {
        player.gameDirectory = file;
    }

    //getters
    public static String getPlayerName(Player player) {
        return player.name;
    }

    public static int getPlayerHealth(Player player) {
        return player.health;
    }

    public static int getPlayerSpeed(Player player) { //needs imp
        return player.speed;
    }

    public static int getPlayerStrength(Player player) { //needs imp
        return player.strength;
    }

    public static int getPlayerInventory(Player player) { //needs imp
        return player.inventory;
    }

    public static Surrounding getPlayerCurrentLocation(Player player) {
        return player.currentLocation;
    }

    public static Surrounding getPlayerPreviousLocation(Player player) {
        return player.previousLocation;
    }

    public static int getPlayerXP(Player player) {
        return player.xp;
    }

    public static Weapon getPlayerCurrentWeapon(Player player) {
        return player.currentWeapon;
    }

    public static List<Food> getPlayerFoodInventory(Player player) {
        return player.foodInventory;
    }

    public static List<Weapon> getPlayerWeaponInventory(Player player) {
        return player.weaponInventory;
    }

    public static File getPlayerGameDirectory(Player player) {
        return player.gameDirectory;
    }

    public static void checkPlayerLevel(Player player) {
        int playerXP = getPlayerXP(player);
        if (playerXP >= 100) {
            playerLevelUp(player, 25);
        }
    }

    public static void playerLevelUp(Player player, int level) {
        setPlayerStrength(player, getPlayerStrength(player) + level);
        setPlayerHealth(player, getPlayerHealth(player) + level);
        setPlayerSpeed(player, getPlayerSpeed(player) + level);
        setPlayerInventory(player, getPlayerInventory(player) + 15);
        setPlayerXP(player, 0);
        print("\n" + getPlayerName(player) + " has leveled up! ");
    }

    //bubble sort
    public static void bubbleSort(List<Food> list, List<Weapon> list1) {
        if (list1 == null) {
            int n = list.size()-1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n - i; j++) {
                    if (getFoodName(list.get(j)).compareTo(getFoodName(list.get(j + 1))) > 0) { //compares the two string items in the list to determine which one is grater
                        // swapping pair
                        Food temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);
                    }
                }
            }
        } else {
            int n = list1.size()-1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n - i; j++) {
                    if (getWeaponName(list1.get(j)).compareTo(getWeaponName(list1.get(j + 1))) > 0) {
                        // swapping pair
                        Weapon temp = list1.get(j);
                        list1.set(j, list1.get(j + 1));
                        list1.set(j + 1, temp);
                    }
                }
            }
        }
    }

    public static void manageFoodInventory(Player player) {
        printFoodList(player);
        boolean error = true;
        while (error) {
            error = false;
            String in = inputString("\na) Remove item from inventory" +
                    "\nb) Sort inventory" +
                    "\nc) Leave inventory management" +
                    "\n Please enter input >>> ");
            if (in.length()==0) {
                error = true;
            } else {
                switch (in.toLowerCase()) {
                    case "a":
                        int i = Integer.parseInt(inputString("\nPlease enter the position of the item or '0' to cancel >>> "));
                        if (i != 0) {
                            removeFoodFromInventory(i, player);
                        }
                        break;
                    case "b":
                        bubbleSort(getPlayerFoodInventory(player), null);
                        break;
                    case "c":
                        break;

                }
            }
        }
    }

    public static void manageWeaponInventory(Player player) {
        printWeaponList(player);
        boolean error = true;
        int i;
        while (error) {
            error = false;
            String in = inputString("\na) Remove item from inventory" +
                    "\nb) Sort inventory" +
                    "\nc) Change primary weapon" +
                    "\nd) Leave inventory management" +
                    "\n Please enter input >>> ");
            if (in.length()==0) {
                error = true;
            } else {
                char chIn = in.toLowerCase().charAt(0);
                switch (chIn) {
                    case 'a':
                        i = Integer.parseInt(inputString("\nPlease enter the position of the item >>> "));
                        removeWeaponFromInventory(i-1, player);
                        break;
                    case 'b':
                        bubbleSort(null, getPlayerWeaponInventory(player));
                        break;
                    case 'c':
                        printWeaponList(player);
                        i = Integer.parseInt(inputString("\nPlease enter the positon of the item >>> "));
                        setPlayerCurrentWeapon(player,  getPlayerWeaponInventory(player).get(i-1));
                        setPlayerStrength(player, getPlayerStrength(player) + getStrengthIncrease(getPlayerCurrentWeapon(player)));
                        break;
                    case 'd':
                        break;
                }
            }
        }
    }

    public static int getFoodListSize(Player player) {
        return player.foodInventory.size();
    }

    public static int getWeaponListSize(Player player) {
        return player.weaponInventory.size();
    }

    public static String addFoodToInventory(Food item, Player player) {
        print(item.name);
        int inventory = getPlayerInventory(player);
        int size = getFoodInventorySize(player);
        int remaining = inventory - size;

        if (getFoodSpace(item) <= remaining) {
            getPlayerFoodInventory(player).add(item);
            return "\nItem added to the list\n";
        } else {
            return "\nInventory is full";
        }
    }

    public static String addWeaponToInventory(Weapon item, Player player) {
        int inventory = getPlayerInventory(player);
        int size = getWeaponInventorySize(player);
        int remaining = inventory - size;

        if (getInventorySpace(item) <= remaining) {
            getPlayerWeaponInventory(player).add(item);
            return "\n" + item.name + " added to the list";
        } else {
            return "\nError: Inventory is full";
        }
    }

    public static void removeFoodFromInventory(int i, Player player) {
        getPlayerFoodInventory(player).remove(i-1);
    }

    public static void removeWeaponFromInventory(int i, Player player) {
        getPlayerWeaponInventory(player).remove(i-1);
    }

    public static int getFoodInventorySize(Player player) {
        int size = 0;
        for (int i = 0; i < getFoodListSize(player); i++) {
            size += getFoodSpace(getPlayerFoodInventory(player).get(i));
        }
        return size;
    }

    public static int getWeaponInventorySize(Player player) {
        int size = 0;
        for (int i = 0; i < getWeaponListSize(player); i++) {
            size += getInventorySpace(getPlayerWeaponInventory(player).get(i));
        }
        return size;
    }

    public static void printFoodList(Player player) {
        int size = getPlayerFoodInventory(player).size();
        print("\n--Inventory--");
        for (int i = 0; i < size; i++) {
            print("\nName: "+(i+1)+") " + getFoodName(getPlayerFoodInventory(player).get(i)) + "\nSpace: " + getFoodSpace(getPlayerFoodInventory(player).get(i)));
        }
        print("\n");
    }

    public static void printWeaponList(Player player) {
        int size = getPlayerWeaponInventory(player).size();
        for (int i = 0; i < size; i++) {
            print("\nName: "+(i+1)+") " + getWeaponName(getPlayerWeaponInventory(player).get(i)) + "\nSpace: " + getInventorySpace(getPlayerWeaponInventory(player).get(i)));
        }
        print("\n");
    }

    //method to create player record
    public static Player playerCreator() {
        String name;
        weaponArray();

        boolean nameCreated = false;
        Player player = new Player();

        while (!nameCreated) {
            name = inputString("Hello there, what is your name? >>> ");
            if (name.length()>1) {
                createPlayerRecord(player, name);
                createGameDirectory(player);
                addVisited(getPlayerCurrentLocation(player), getSurroundingArray());
                print("\nHello there " + name + ", welcome to The forest, where you will face challenges and a chance to explore this vast land. What will you find?\n ");
                print("\nGame rules are straight forward, enjoy the game, explore the forest, defeat threats and make friends. \nEnter 'OPTIONS' to bring up the options menu, press 'N' to go north, 'E' to go east, 'S' to go south, 'W' to go west. Enjoy...  ");
                print("\n -------------------------------------------------------------------------------------------------------------------------");
                nameCreated = true;
            } else {
                print("Please enter a name with more than 1 character >>> ");
            }
        }
        return player;
    }

    //FOOD CODE
    //food info setup
    public static class Food implements Serializable {
        public static Food[] foodArray;
        String name;
        int foodValue;
        int foodSpace;
    }

    //array of in-game food
    public static void foodArray() {
        Food f1 = new Food();
        Food f2 = new Food();
        Food f3 = new Food();
        Food f4 = new Food();
        Food f5 = new Food();
        Food f6 = new Food();

        Food.foodArray = new Food[]{
                createFoodRecords(f1, "Apple", 5, 2),
                createFoodRecords(f2, "Protein Bar", 15, 5),
                createFoodRecords(f3, "Steak", 30, 10),
                createFoodRecords(f4, "Fish", 20, 5),
                createFoodRecords(f5, "Nuts", 5, 2),
                createFoodRecords(f6, "Banana", 8, 3)
        };
    }

    //create food records
    public static Food createFoodRecords(Food food, String name, int foodValue, int foodSpace) {
        setFoodName(food, name);
        setFoodValue(food, foodValue);
        setFoodSpace(food, foodSpace);
        return food;
    }

    //setters
    public static void setFoodName(Food food, String name) {
        food.name = name;
    }

    public static void setFoodValue(Food food, int foodValue) {
        food.foodValue = foodValue;
    }

    public static void setFoodSpace(Food food, int foodSpace) {
        food.foodSpace = foodSpace;
    }

    //getters
    public static String getFoodName(Food food) {
        return food.name;
    }

    public static int getFoodValue(Food food) {
        return food.foodValue;
    }

    public static int getFoodSpace(Food food) {
        return food.foodSpace;
    }

    public static Food[] getFoodArray() {
        return Food.foodArray;
    }

    //WEAPON CODE
    //weapon info setup
    public static class Weapon implements Serializable {
        public static Weapon[] weaponArray;
        String name;
        int strengthIncrease;
        int inventorySpace;
    }

    public static void weaponArray() {
        Weapon weapon0 = new Weapon();
        Weapon weapon1 = new Weapon();
        Weapon weapon2 = new Weapon();
        Weapon weapon3 = new Weapon();
        Weapon weapon4 = new Weapon();
        Weapon weapon5 = new Weapon();
        Weapon weapon6 = new Weapon();

        Weapon.weaponArray = new Weapon[]{
                createWeaponRecords(weapon0, "Fist", 0, 0),
                createWeaponRecords(weapon1, "Dark Sword", 20, 10),
                createWeaponRecords(weapon2, "Brass Axe", 15, 12),
                createWeaponRecords(weapon3, "Long Bow", 10, 8),
                createWeaponRecords(weapon4, "Cross Bow", 15, 7),
                createWeaponRecords(weapon5, "Lightning Rod", 30, 20),
                createWeaponRecords(weapon6, "Bat", 5, 5)
        };
    }

    public static Weapon[] getWeaponArray() {
        return Weapon.weaponArray;
    }

    public static Weapon createWeaponRecords(Weapon weapon, String name, int strengthIncrease, int inventorySpace) {
        setWeaponName(weapon, name);
        setStrengthIncrease(weapon, strengthIncrease);
        setInventorySpace(weapon, inventorySpace);
        return weapon;
    }

    //setters
    public static void setWeaponName(Weapon weapon, String name) {
        weapon.name = name;
    }

    public static void setStrengthIncrease(Weapon weapon, int strengthIncrease) {
        weapon.strengthIncrease = strengthIncrease;
    }

    public static void setInventorySpace(Weapon weapon, int inventorySpace) {
        weapon.inventorySpace = inventorySpace;
    }

    //getters
    public static String getWeaponName(Weapon weapon) {
        return weapon.name;
    }

    public static int getStrengthIncrease(Weapon weapon) {
        return weapon.strengthIncrease;
    }

    public static int getInventorySpace(Weapon weapon) {
        return  weapon.inventorySpace;
    }

    public static Weapon getWeapon(String name, Player player){
        int length = getWeaponListSize(player);
        for (int i = 0; i<length; i++) {

            if (getWeaponArray()[i] != null) {
                if ((getWeaponName(getWeaponArray()[i])).equals(name)) {
                    return getWeaponArray()[i];
                }
            }
        }
        return getWeaponArray()[0];
    }

    //CREATURE CODE
    //creature info setup
    public static class Creature {
        public static Creature[] creatureArray;
        String name;
        boolean agro;
        int damage;
        int health;
        int speed;
        int xp;
    }

    //array of in-game creatures
    public static void creatureArray() {
        Creature c1 = new Creature();
        Creature c2 = new Creature();
        Creature c3 = new Creature();
        Creature c4 = new Creature();
        Creature c5 = new Creature();
        Creature c6 = new Creature();
        Creature c7 = new Creature();

        Creature.creatureArray = new Creature[]{
                createCreatureRecords(c1, "Bear", true, 10, 100, 15, 10),
                createCreatureRecords(c2, "Dragon", true, 25, 100, 40, 25),
                createCreatureRecords(c3, "Troll", true, 10, 100, 5, 20),
                createCreatureRecords(c4, "Wolf", true, 15, 100, 35, 15),
                createCreatureRecords(c5, "Cow", false, 0, 100, 10, 5),
                createCreatureRecords(c6, "Cat", false, 5, 100, 20, 5),
                createCreatureRecords(c7, "Dog", false, 10, 100, 20, 5),
                createCreatureRecords(c7, "Sheep", false, 0, 100, 10, 5)
        };
    }

    //create creature records
    public static Creature createCreatureRecords(Creature creature, String creatureName, boolean agro, int damage, int health, int speed, int xp) {
        setCreatureName(creature, creatureName);
        setCreatureAgro(creature, agro);
        setCreatureDamage(creature, damage);
        setCreatureHealth(creature, health);
        setCreatureSpeed(creature, speed);
        setCreatureXP(creature, xp);
        return creature;
    }

    //setters
    public static void setCreatureName(Creature creature, String creatureName) {
        creature.name = creatureName;
    }

    public static void setCreatureAgro(Creature creature, boolean agro) {
        creature.agro = agro;
    }

    public static void setCreatureDamage(Creature creature, int damage) {
        creature.damage = damage;
    }

    public static void setCreatureHealth(Creature creature, int health) {
        creature.health = health;
    }

    public static void setCreatureSpeed(Creature creature, int speed) {
        creature.speed = speed;
    }

    public static void setCreatureXP(Creature creature, int xp) {
        creature.xp = xp;
    }

    //getters
    public static String getCreatureName(Creature creature) {
        return creature.name;
    }

    public static boolean getCreatureAgro(Creature creature) {
        return creature.agro;
    }

    public static int getCreatureDamage(Creature creature) {
        return creature.damage;
    }

    public static int getCreatureHealth(Creature creature) {
        return creature.health;
    }

    public static int getCreatureSpeed(Creature creature) {
        return creature.speed;
    }

    public static int getCreatureXP(Creature creature) {
        return creature.xp;
    }

    public static Creature[] getCreatureArray() {
        return Creature.creatureArray;
    }

    //boss creatures?? (maybe later)

    //SURROUNDING CODE
    //surrounding info setup
    public static class Surrounding implements Serializable {
        final static int size = 50;
        static final Surrounding[] visitedLocations = new Surrounding[size];
        static Surrounding[] map;

        String name;
        Surrounding west;
        Surrounding east;
        Surrounding north;
        Surrounding south;
        boolean visited;
        String preDes;
        String postDes;
    }

    //returns the location in the abstract data type, Surrounding, using the name provided
    public static Surrounding getLocation(String name){
        int length = Surrounding.map.length;
        for (int i = 0; i<length; i++) {

            if (Surrounding.map[i] != null) {
                if ((getSurroundingName(Surrounding.map[i])).equals(name)) {
                    return Surrounding.map[i];
                }
            }
        }
        return Surrounding.map[0];
    }

    //create surrounding records
    public static Surrounding createSurroundingRecords(Surrounding surrounding, String name, Surrounding west, Surrounding east, Surrounding north, Surrounding south, boolean visited, String preDes, String postDes) {
        setSurroundingName(surrounding, name);
        setSurroundingWest(surrounding, west);
        setSurroundingEast(surrounding, east);
        setSurroundingNorth(surrounding, north);
        setSurroundingSouth(surrounding, south);
        setSurroundingVisited(surrounding, visited);
        setSurroundingPreDes(surrounding, preDes);
        setSurroundingPostDes(surrounding, postDes);
        return surrounding;
    }

    //setters
    public static void setSurroundingName(Surrounding location, String name) {
        location.name = name;
    }

    public static void setSurroundingWest(Surrounding location, Surrounding west) {
        location.west = west;
    }

    public static void setSurroundingEast(Surrounding location, Surrounding east) {
        location.east = east;
    }

    public static void setSurroundingNorth(Surrounding location, Surrounding north) {
        location.north = north;
    }

    public static void setSurroundingSouth(Surrounding location, Surrounding south) {
        location.south = south;
    }

    public static void setSurroundingVisited(Surrounding location, boolean visited) {
        location.visited = visited;
    }

    public static void setSurroundingPreDes(Surrounding location, String preDes) {
        location.preDes = preDes;
    }

    public static void setSurroundingPostDes(Surrounding location, String postDes) {
        location.postDes = postDes;
    }

    //getters
    public static String getSurroundingName(Surrounding location) {
       return location.name;
    }

    public static Surrounding getSurroundingWest(Surrounding location) {
        return location.west;
    }

    public static Surrounding getSurroundingEast(Surrounding location) {
        return location.east;
    }

    public static Surrounding getSurroundingNorth(Surrounding location) {
        return location.north;
    }

    public static Surrounding getSurroundingSouth(Surrounding location) {
        return location.south;
    }

    public static boolean getSurroundingVisited(Surrounding location) {
        return location.visited;
    }

    public static String getSurroundingPreDes(Surrounding location) {
        return location.preDes;
    }

    public static String getSurroundingPostDes(Surrounding location) {
        return location.postDes;
    }

    public static Surrounding[] getSurroundingArray() {
        return Surrounding.visitedLocations;
    }

    //array of surrounding

    //method to create the game map
    public static void loadMap() {
        creatureArray();
        Surrounding deadEnd = new Surrounding();
        Surrounding crashSite = new Surrounding(); //start
        Surrounding desecratedCemetery = new Surrounding();
        Surrounding statues = new Surrounding();
        Surrounding wreckedCockpit = new Surrounding();
        Surrounding treeHouse = new Surrounding();
        Surrounding temple = new Surrounding();
        Surrounding levelUpZ1 = new Surrounding();
        Surrounding levelUpZ2 = new Surrounding();
        Surrounding lakeSide1 = new Surrounding();

        Surrounding.map = new Surrounding[]{
                createSurroundingRecords(deadEnd, "Dead End", null, null, null, null, false, "null", "null"),
                createSurroundingRecords(crashSite, "Crash Site", wreckedCockpit, deadEnd, desecratedCemetery, statues, false, "This is the crash site, where you began your journey ", "You have returned to the Crash Site...  "),
                createSurroundingRecords(desecratedCemetery, "Desecrated Cemetery", deadEnd, treeHouse, temple, crashSite, false, "Behold, the desecrated cemetery, mysteries await ", "You have entered the Desecrated Cemetery, be careful... "),
                createSurroundingRecords(statues, "Tholmans Statue Hold", levelUpZ1, levelUpZ2, crashSite, deadEnd, false, "Statues here must be important, what if they are being guarded? ", "Welcome to Tholmans Statues... "),
                createSurroundingRecords(wreckedCockpit, "Wrecked Cockpit", lakeSide1, crashSite, deadEnd, levelUpZ1, false, "This is the other end of the plane, what is left? ", "You have entered the Cockpit... ")
        };
    }

    //method to add data to the array
    public static void addVisited(Surrounding location, Surrounding[] visitedArray) {
        //iterates through array until a value in the array is null, where it will then change the value of the null value to the location
        for (int i = 0; i < 50; i++) {
            //prevents duplicated entries to the array
            if (visitedArray[i] == location) {
                return;
            }
            //adds data to the array if the selected position in the array is null, similar to a stack, using the first null value as a pointer
            if (visitedArray[i] == null) {
                visitedArray[i] = location;
                return;
            }
        }
        //debugging purposes, shouldn't be printed unless there's an error
        //array size should be the exact size that is possible in the game
        print("Error: Array is full");
    }

    //prints the array of visited locations in a list format
    public static void viewVisitedArray() {
        //length of array
        int len = getSurroundingArray().length;

        //iterates through the visitedLocations array to determine the number of locations in the array ignoring the null values
        for (int i = 0; i < len; i++){
            if (getSurroundingArray()[i] == null) {
                break;
            } else {
                print("\n" +(i+1) +") " + getSurroundingName(getSurroundingArray()[i]));
            }
        }
    }

    //random selector
    public static Creature randomCreature(Creature[] creatures) {
        Random random = new Random();
        int max = creatures.length;
        return creatures[random.nextInt((max)-1)];
    }

    public static Food randomFood(Food[] food) {
        Random random = new Random();
        int max = food.length;
        return food[random.nextInt((max)-1)];
    }

    public static Weapon randomWeapon(Weapon[] weapons) {
        Random random = new Random();
        int max = weapons.length;
        int randomVal = random.nextInt((max)-1);
        if (randomVal == 0) {
            return randomWeapon(weapons);
        } else {
            return weapons[random.nextInt((max) - 1)];
        }
    }

    //INPUT OUTPUT CODE
    //print messages without writing System.out.println() each time
    public static void print(String message){
        System.out.print(message);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //handle inputs
    public static String inputString(String message) {
        Scanner sc = new Scanner(System.in);
        print(message);
        return sc.nextLine().toLowerCase();
    }


    //method to handle yes or no questions
    public static boolean trueOrFalse(String ans) {
        boolean validIn = false;

        while (!validIn) {
            if (ans.equalsIgnoreCase("yes")||ans.equals("no")) {
                validIn = true;
            } else {
                print("Please enter a valid response (yes or no) >>>");
                inputString("");
            }
        }
        return ans.equals("yes");
    }

    //title page
    public static void titlePage() {
        print("    ███        ▄█    █▄       ▄████████         ▄████████  ▄██████▄     ▄████████    ▄████████    ▄████████     ███     \n" +
              "▀█████████▄   ███    ███     ███    ███        ███    ███ ███    ███   ███    ███   ███    ███   ███    ███ ▀█████████▄ \n" +
              "   ▀███▀▀██   ███    ███     ███    █▀         ███    █▀  ███    ███   ███    ███   ███    █▀    ███    █▀     ▀███▀▀██ \n" +
              "    ███   ▀  ▄███▄▄▄▄███▄▄  ▄███▄▄▄           ▄███▄▄▄     ███    ███  ▄███▄▄▄▄██▀  ▄███▄▄▄       ███            ███   ▀ \n" +
              "    ███     ▀▀███▀▀▀▀███▀  ▀▀███▀▀▀          ▀▀███▀▀▀     ███    ███ ▀▀███▀▀▀▀▀   ▀▀███▀▀▀     ▀███████████     ███     \n" +
              "    ███       ███    ███     ███    █▄         ███        ███    ███ ▀███████████   ███    █▄           ███     ███     \n" +
              "    ███       ███    ███     ███    ███        ███        ███    ███   ███    ███   ███    ███    ▄█    ███     ███     \n" +
              "   ▄████▀     ███    █▀      ██████████        ███         ▀██████▀    ███    ███   ██████████  ▄████████▀     ▄████▀   \n" +
              "                                                                       ███    ███                                       ");
    }

    //load game data if available to pass to the game setup method
    public static void loadGame() {
        titlePage();
        String ans;
        ans = inputString("\nDo you have a game saved? (yes or no) >>> ");
        if (trueOrFalse(ans)) {
            String file;
            file = checkGameDirectory();
            if (file.equals("-1")) {
                file = inputString("\nPlease enter the file directory >>> ");
            }
            Player player = readFile(file);
            print("\nWelcome back " + getPlayerName(player) + "\n");
            directionOptions(player);

        } else {
            startGame();
        }
    }

    public static void createGameDirectory(Player player) {
        boolean fileExist = false;

        File userDirectory = new File("C://");

        String[] pathNames = userDirectory.list();

        assert pathNames != null;
        for (String pathName : pathNames) {
            if (pathName.equals("theForest")) {
                fileExist = false;
                print("\nGame save data already exist... \n");
                break;
            } else {
                fileExist = true;
            }
        }
        if (fileExist) {
            File gameDirectory = new File(userDirectory + "//theForest");
            setPlayerGameDirectory(player, gameDirectory);
            if (gameDirectory.mkdir() && writeFile(player, gameDirectory, false)) {
                print("\nGame save data created successfully... \n");
            } else {
                print("\nError creating game save date... \n");
            }
        }
    }

    public static String checkGameDirectory() {
        boolean fileExist = false;
        File userDirectory = new File("C:\\");
        String[] pathNames = userDirectory.list();

        assert pathNames != null;
        for (String pathName : pathNames) {
            if (pathName.equals("theForest")) {
                fileExist = true;
                print("Save files found\nFILES");
                break;
            }
        }
        if (fileExist) {
            File gameDirectory = new File(userDirectory + "\\theForest");
            pathNames = gameDirectory.list();
            int len = Objects.requireNonNull(pathNames).length;
            String ans = "-1";
            for (int i = 0; i < len; i++) {
                print("\n" + (i+1) + ": " + pathNames[i]);
            }
            while (Integer.parseInt(ans) > len | Integer.parseInt(ans) <= 0) {
                ans = inputString("\n\nPlease enter a number correlated to the files >>> ");
            }
            return gameDirectory + "\\" + pathNames[(Integer.parseInt(ans)-1)];
        }
        return "-1";
    }

    //GAME CODE
    //game info setup
    public static void startGame() {
        loadMap();
        foodArray();
        weaponArray();
        gameDirection();
    }

    //varied scenarios faced at different locations
    public static void locationOptions(Surrounding location, Player player) {
        Creature creature = randomCreature(getCreatureArray());
        Random randomNumber = new Random();
        int creatureChance = randomNumber.nextInt(100);

        if (creatureChance >=30) {
            print("You are currently at the " + getSurroundingName(location) + " where there is a " + getCreatureName(creature) + " approaching you... "  );
            if (getCreatureAgro(creature)) {
                agroCreatureOptions(creature, player);
            } else {
                passiveCreatureOptions(creature, player);
            }
        }
    }

    //PASSIVE CODE
    //passive creature options
    public static void passiveCreatureOptions(Creature creature, Player player) {
        String in = inputString("\nThe " + getCreatureName(creature) + " is a passive creature." +
                "\nWould you like to: " +
                "\na) ATTACK 'a'" +
                "\nb) PET 'b'" +
                "\nc) LEAVE ALONE 'c'" +
                "\nPlease enter your input >>> ");
        switch (in.toLowerCase()) {
            case "a":
                fightCreature(creature, player);
                break;
            case "b":
                petCreature(player);
                break;
            case "c":
                directionOptions(player);
                break;
        }
    }

    public static void petCreature(Player player) {
        setPlayerHealth(player, getPlayerHealth(player) + 5);
        print("\nHealth: " + getPlayerHealth(player));

    }

    //AGRO CODE
    //options to attack or retreat from creature
    public static void agroCreatureOptions(Creature creature, Player player) {
        String in = inputString("\nWould you like to pass this way or turn back? The " + getCreatureName(creature) + " is not a passive creature." +
                "\nWould you like to: " +
                "\na) ATTACK 'a'" +
                "\nb) TURN BACK 'b'" +
                "\nc) RUN 'c'" +
                "\nPlease enter your input >>> ");

        boolean error = true;

        while (error) {
            error = false;

            switch (in) {
                case "a":
                    fightCreature(creature, player);
                    break;
                case "b":
                    retreat(player);
                    break;
                case "c":
                    run(creature, player);
                    break;
            }

        }
    }

    //method to retreat, sets players location to old location
    public static void retreat(Player player) {
        setPlayerCurrentLocation(player, getPlayerPreviousLocation(player));
        directionOptions(player);
    }

    //respawn method, which sets players location to the crash site and sets their health back to 100
    public static void respawn(Player player) {
        print("\nYou died... \n");
        setPlayerCurrentLocation(player, getLocation("Crash Site"));
        setPlayerHealth(player, 100);
        directionOptions(player);
    }

    public static void run(Creature creature, Player player) {
        if (getCreatureSpeed(creature) > getPlayerSpeed(player)) {
            print("\nYou're too slow! " + getCreatureName(creature) + " has caught up with you... ");
            fightCreature(creature, player);
        } else {
            print("\nYou out ran the " + getCreatureName(creature));
        }

    }

    //attack options for the player, carrying out the attack
    public static boolean playerAttack(Player player, Creature creature) {
        char attack;
        boolean error = true;
        while (error) {
            error = false;

            print("\n" + getPlayerName(player) + "'s health: " + getPlayerHealth(player) + "\n" + getCreatureName(creature) + "'s health: " + getCreatureHealth(creature) + "\n");


            String in = inputString("\nChoice your attack: " +
                    "\na)Slash 'a'" +
                    "\nb)Punch 'b'" +
                    "\nc)Kick 'c'" +
                    "\nd)Dodge 'd'" +
                    "\nPlease enter here >>> ");

            if (in.length()>0) {
                attack = in.charAt(0);
                //makes sure the string character entered is between the characters ('a' and 'b') and that the string is a length of 1
                if ((attack >= 97 && attack <=101)) {
                    int creatureHealth = getCreatureHealth(creature);
                    int playerStrength = getPlayerStrength(player);
                    switch (attack) {
                        case 'a':
                            setPlayerXP(player, (int) (getPlayerXP(player) + getCreatureXP(creature)*0.5));
                            setCreatureHealth(creature, creatureHealth - playerStrength);
                            return false;
                        case 'b':
                            setPlayerXP(player, (int) (getPlayerXP(player) + getCreatureXP(creature)*0.5));
                            setCreatureHealth(creature,   (int)(creatureHealth - playerStrength * 0.75));
                            return false;
                        case 'c':
                            setPlayerXP(player, (int) (getPlayerXP(player) + getCreatureXP(creature)*0.5));
                            setCreatureHealth(creature, (int) (creatureHealth - playerStrength * 0.8));
                            return false;
                        case 'd':
                            return true;
                    }
                } else {
                    error = true;
                }
            } else {
                error = true;
            }
            if (error) {
                print("\nError: Please enter a valid input... (a, b, c, d)");
            }
        }
        return false;
    }

    //the attack made by the creature
    public static void creatureAttack(Player player, Creature creature, boolean dodge) {
        if (!dodge) {
            setPlayerHealth(player, (getPlayerHealth(player) - getCreatureDamage(creature)));
        } else {
            print("\nNice dodge! ");
        }

    }

    //options available while fighting the creature
    public static void fightCreature(Creature creature, Player player) {
        while (getPlayerHealth(player) > 0 && getCreatureHealth(creature) > 0) {
            int playerHealth = getPlayerHealth(player);

            creatureAttack(player, creature, playerAttack(player, creature));

            if (playerHealth <=10) {
                if (trueOrFalse(inputString("\n" + getPlayerName(player) + ", your health is very low, would you like to try and run? (yes or no) >>> "))) {
                    retreat(player);
                }
            }
        }
        if (getPlayerHealth(player) == 0 && getCreatureHealth(creature) > 0) {
            respawn(player);
        } else {
            print(getCreatureName(creature) + " is dead... ");
            setCreatureHealth(creature, 100);
            setPlayerXP(player, (getPlayerXP(player) + getCreatureXP(creature)));
            if (!getCreatureAgro(creature) && getPlayerHealth(player) <=90) {
                setPlayerHealth(player, getPlayerHealth(player)+10);
            } else {
                setPlayerHealth(player, 100);
            }
            locationItems(player);
        }
    }

    public static void locationItems(Player player) {
        Random random1 = new Random();
        Random random2 = new Random();
        Food food;
        Weapon weapon;
        int foodChance = random1.nextInt(100);
        int weaponChance = random2.nextInt(100);
        if (foodChance > 20) {
            food = randomFood(getFoodArray());
            print("\n\nYou have found: " +
                    "\nName: " + getFoodName(food) +
                    "\nValue: " + getFoodValue(food) +
                    "\nSpace: " + getFoodSpace(food) +
                    "\nSpace available: " + (getPlayerInventory(player) - getFoodListSize(player)));
            itemHandler("food", player, food, null);

        }
        if (weaponChance > 15) {
            weapon = randomWeapon(getWeaponArray());
            while (weapon.name.equals("fist")) {
                weapon = randomWeapon(getWeaponArray());
            }
            print("\n\nYou have found: " +
                    "\nName: " + getWeaponName(weapon) +
                    "\nStrength Increase: " + getStrengthIncrease(weapon) +
                    "\nSpace: " + getInventorySpace(weapon) +
                    "\nSpace available: " + (getPlayerInventory(player) - getWeaponListSize(player)));
            itemHandler("weapon", player, null, weapon);
        }
    }

    public static void itemHandler(String type, Player player, Food food, Weapon weapon) {
        boolean error = true;
        String result;
        while (error) {
            error = false;
            String in = inputString(
                    "\n\nWould you like to collect the item? " +
                            "\na) Collect" +
                            "\nb) Manage inventory" +
                            "\nc) Leave item" +
                    "\nPlease enter here >>> ");
            if (in.length() < 1) {
                error = true;
            } else {
                switch (in.toLowerCase()) {
                    case "a":
                        switch (type) {
                            case "food":
                                result = addFoodToInventory(food, player);
                                print(result);

                                error = result.contains("Error");
                                break;
                            case "weapon":
                                result = addWeaponToInventory(weapon, player);
                                print(result);
                                error = result.contains("Error");
                                break;
                        }
                        break;
                    case "b":
                        switch (type) {
                            case "food":
                                manageFoodInventory(player);
                                break;
                            case "weapon":
                                manageWeaponInventory(player);
                                break;
                        }
                        break;
                    case "c":
                        break;
                }
            }
        }


    }

    //handles the main inputs from the user, then calling the correct methods for the inputs
    public static void inputHandler(Player player) {
        boolean validIn = false;
        boolean newLocation = false;
        setPlayerPreviousLocation(player, getPlayerCurrentLocation(player));

        while (!validIn) {
            String in = inputString("\nPlease enter N, E, S or W or if your lost, enter 'MAP' or 'OPTIONS' >>> ");
            if (in.equalsIgnoreCase("N")||in.equalsIgnoreCase("E")||in.equalsIgnoreCase("S")||in.equalsIgnoreCase("W")||in.equalsIgnoreCase("OPTIONS")||in.equalsIgnoreCase("MAP")) {
                switch (in.toUpperCase()) {
                    case "N":
                        setPlayerCurrentLocation(player, getSurroundingNorth(getPlayerCurrentLocation(player)));
                        newLocation = true;
                        break;
                    case "E":
                        setPlayerCurrentLocation(player, getSurroundingEast(getPlayerCurrentLocation(player)));
                        newLocation = true;
                        break;
                    case "S":
                        setPlayerCurrentLocation(player, getSurroundingSouth(getPlayerCurrentLocation(player)));
                        newLocation = true;
                        break;
                    case "W":
                        setPlayerCurrentLocation(player, getSurroundingWest(getPlayerCurrentLocation(player)));
                        newLocation = true;
                        break;
                    case "OPTIONS":
                        optionsMenu(player);
                        break;
                    case "MAP":
                        mapOptions(player);
                        break;
                }
                if (getSurroundingName(getPlayerCurrentLocation(player)).equals("Dead End")) {
                    print("\nThat is a Dead End, lets look again... \n");
                    setPlayerCurrentLocation(player, getPlayerPreviousLocation(player));
                } else if (newLocation){
                    addVisited(getPlayerCurrentLocation(player), getSurroundingArray());
                    setSurroundingVisited(getPlayerCurrentLocation(player), true);
                    print("\n"+getSurroundingPreDes(getPlayerCurrentLocation(player)));
                    print("\n"+getSurroundingPostDes(getPlayerCurrentLocation(player)));
                    validIn = true;
                }
            }
        }
        locationOptions(getPlayerCurrentLocation(player), player);
        directionOptions(player);
    }

    public static void mapOptions(Player player) {
        boolean run = true;
        print("                                \n" +
                ",--.   ,--.   ,---.   ,------.  \n" +
                "|   `.'   |  /  O  \\  |  .--. ' \n" +
                "|  |'.'|  | |  .-.  | |  '--' | \n" +
                "|  |   |  | |  | |  | |  | --'  \n" +
                "`--'   `--' `--' `--' `--'      \n" +
                "                                ");//soft font

        while (run) {
            String in = inputString("\n -------------------------------------------------------------------------------------------------------------------------\n " +
                    "\nPlease enter: \na) View Map 'a' \nb) View Visited locations 'b' \nc) Close MAP 'c' \nPlease enter your input >>> ");
            if (in.equalsIgnoreCase("a")||in.equalsIgnoreCase("b")||in.equalsIgnoreCase("c")) {
                switch (in.toLowerCase()) {
                    case "a":
                        print("\nYou are currently at the " + getSurroundingName(getPlayerCurrentLocation(player)));
                        break;
                    case "b":
                        viewVisitedArray();
                        break;
                    case "c":
                        run = false;
                }
            }
        }
        directionOptions(player);
    }

    public static void optionsMenu(Player player) {
        boolean run = true;
        print("                                                                  \n" +
                " ,-----.  ,------.  ,--------. ,--.  ,-----.  ,--.  ,--.  ,---.   \n" +
                "'  .-.  ' |  .--. ' '--.  .--' |  | '  .-.  ' |  ,'.|  | '   .-'  \n" +
                "|  | |  | |  '--' |    |  |    |  | |  | |  | |  |' '  | `.  `-.  \n" +
                "'  '-'  ' |  | --'     |  |    |  | '  '-'  ' |  | `   | .-'    | \n" +
                " `-----'  `--'         `--'    `--'  `-----'  `--'  `--' `-----'  \n" +
                "                                                                  ");//soft font

        while (run) {
            String in = inputString("\n -------------------------------------------------------------------------------------------------------------------------\n" +
                    "\nPlease enter: \na): Save Game 'a' \nb) End Game 'b' \nc) View stats menu 'c' \nd) Manage weapons 'd' \ne) Close Options Menu 'e' \nPlease enter your input >>> ");
            switch (in.toLowerCase()) {
                case "a":
                    saveGame(player);
                    break;
                case "b":
                    stopGame();
                    break;
                case "c":
                    print(statsMenu(player));
                    break;
                case "d":
                    manageWeaponInventory(player);
                    break;
                case "e":
                    run = false;
                    break;
            }
        }
        directionOptions(player);
    }

    public static String statsMenu(Player player) {
        return "  ____  _        _       \n" +
                " / ___|| |_ __ _| |_ ___ \n" +
                " \\___ \\| __/ _` | __/ __|\n" +
                "  ___) | || (_| | |_\\__ \\\n" +
                " |____/ \\__\\__,_|\\__|___/\n" +
                "                         "+
                "\n\nName: " + getPlayerName(player) +
                "\nHealth: " + getPlayerHealth(player) +
                "\nStrength: " + getPlayerStrength(player) +
                "\nnSpeed: " + getPlayerSpeed(player) +
                "\nInventory Space: " + getPlayerInventory(player) +
                "\nCurrent Weapon: " + getWeaponName(getPlayerCurrentWeapon(player));
    }

    public static void stopGame() {
        print("Ending game... ");
        System.exit(130);
    }
    public static void saveGame(Player player){
        String ans = inputString("\na)Existing saved game file" +
                "\nb)New saved game file");

        switch (ans) {
            case "a":
                existingGameSave(player);
                break;
            case "b":
                newGameSave(player);
                break;
        }
    }

    public static void existingGameSave(Player player) {
        print("\nSaving game... ");
        if (writeFile(player, getPlayerGameDirectory(player), false)) {
            print("\nGame saved successfully... \n");
        } else {
            print("\nError saving game... ");
        }
    }

    public static void newGameSave(Player player) {
        print("\nSaving game... ");
        if (writeFile(player, getPlayerGameDirectory(player), true)) {
            print("\nGame saved successfully... \n");
        } else {
            print("\nError saving game... ");
        }    }

    //reads file data
    private static Player readFile(String fileName) {
        print(fileName);

        try (FileInputStream fis = new FileInputStream(fileName)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            Player loadPlayer = (Player) ois.readObject();
            ois.close();
            return loadPlayer;
        } catch (ClassNotFoundException | IOException ex) {
            print(ex.getMessage());
            stopGame();
            return new Player();
        }
    }

    //write file data
    private static boolean writeFile(Player player, File filePath, Boolean newFile) {
        int count = 0;
        if (newFile) {
            File userDirectory = new File("C://forestGame");
            count = Objects.requireNonNull(userDirectory.list()).length;
        }
        try {
            FileOutputStream fos = new FileOutputStream(filePath + "//savedGame.txt" + count, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(player);
            oos.close();
            return true;
        } catch (IOException ex) {
            print(ex.getLocalizedMessage());
            return false;
        }
    }

    //method to display the input options available for the user
    public static void directionOptions(Player player) {
        checkPlayerLevel(player);
        String north = getSurroundingName(getSurroundingNorth(getPlayerCurrentLocation(player)));
        String east = getSurroundingName(getSurroundingEast(getPlayerCurrentLocation(player)));
        String south = getSurroundingName(getSurroundingSouth(getPlayerCurrentLocation(player)));
        String west = getSurroundingName(getSurroundingWest(getPlayerCurrentLocation(player)));


        //display the map direction in a visibly appealing format to represent north, east, south and west
        print("\n -------------------------------------------------------------------------------------------------------------------------\n");

        print("                        "+north+"\n" +
                "                             [north]\n" +
                "                               \n" +
                "                               \n" +
                "                               \n" +
                "                               \n" +
                "                               \n" +
                ""+west+" [west]                          [east] "+east+"\n" +
                "                               \n" +
                "                               \n" +
                "                               \n" +
                "                               \n" +
                "                               \n" +
                "                               \n" +
                "                             [south]\n" +
                "                        "+south+"");
        print("\n -------------------------------------------------------------------------------------------------------------------------\n");
        inputHandler(player);
    }

    //sets the game direction up, so that the user can navigate the map correctly
    public static void gameDirection() {
        Player player = playerCreator();
        print("\nYour plane has crashed in this forest, where you must venture the forest to survive this disaster\n");
        directionOptions(player);
    }

    public static void main(String[] args) {
        loadGame();

    }
}