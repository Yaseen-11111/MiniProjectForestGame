/* ***************************************
  @author    Yaseen Rashid
  @date      5 October 2021
  @version   1

                The Forest
                __________
    A game where you explore a forest and
        help the animals along the way
   ****************************************/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    //PLAYER CODE
    //player info setup
    public static class Player {
        String name;
        int health;
        int speed;
        int strength;
        int inventory;
        Surrounding previousLocation;
        Surrounding currentLocation;
    }

    //create player records
    public static void createPlayerRecord(Player player, String name) {
        setPlayerName(player, name);
        setPlayerHealth(player, 100);
        setPlayerSpeed(player, 10);
        setPlayerStrength(player, 12);
        setPlayerInventory(player, 10);
        setPlayerCurrentLocation(player, getLocation("Crash Site"));
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


    //method to create player record
    public static Player playerCreator() {
        String name;

        boolean nameCreated = false;
        Player player = new Player();

        while (!nameCreated) {
            name = inputString("Hello there, what is your name? >>> ");
            if (name.length()>1) {
                createPlayerRecord(player, name);
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
    public static class Food {
        public static Food[] foodArray;
        String foodName;
        int foodValue;
        int inventorySpace;
    }

    //array of in-game food
    public static Food[] foodArray() {
        Food f1 = new Food();
        Food f2 = new Food();
        Food f3 = new Food();

        return Food.foodArray = new Food[]{
                createFoodRecords(f1, "Apple", 5, 2),
                createFoodRecords(f2, "Protein Bar", 15, 5),
                createFoodRecords(f3, "Complete Nutri Bar", 30, 10)};
    }

    //create food records
    public static Food createFoodRecords(Food food, String foodName, int foodValue, int foodSpace) {
        food.foodName = foodName;
        food.foodValue = foodValue;
        food.inventorySpace = foodSpace;
        return food;
    }

    //WEAPON CODE
    //weapon info setup
    public static class Weapon {
        public static Weapon[] weaponArray;
        String weaponName;
        double strengthIncrease;
        int inventorySpace;
    }

    //CREATURE CODE
    //creature info setup
    public static class Creature {
        public static Creature[] creatureArray;
        String creatureName;
        boolean agro;
        int damage;
        int health;
        int speed;
        int xp;
        boolean food;
    }

    //array of in-game creatures
    public static Creature[] creatureArray() {
        Creature c1 = new Creature();
        Creature c2 = new Creature();
        Creature c3 = new Creature();
        Creature c4 = new Creature();
        Creature c5 = new Creature();
        Creature c6 = new Creature();
        Creature c7 = new Creature();

        return Creature.creatureArray = new Creature[]{
                createCreatureRecords(c1, "Bear", true, 10, 100, 15, 5, false),
                createCreatureRecords(c2, "Dragon", true, 25, 100, 40, 20, true),
                createCreatureRecords(c3, "Troll", true, 10, 100, 5, 5, false),
                createCreatureRecords(c4, "Wolf", true, 15, 100, 35, 15, false),
                createCreatureRecords(c5, "Cow", false, 0, 100, 10, 0, true),
                createCreatureRecords(c6, "Cat", false, 5, 100, 20, 0, false),
                createCreatureRecords(c7, "Dog", false, 10, 100, 20, 0, false),
                createCreatureRecords(c7, "Sheep", false, 0, 100, 10, 0, true)
        };
    }

    //create creature records
    public static Creature createCreatureRecords(Creature creature, String creatureName, boolean agro, int damage, int health, int speed, int xp, boolean food) {
        setCreatureName(creature, creatureName);
        setCreatureAgro(creature, agro);
        setCreatureDamage(creature, damage);
        setCreatureHealth(creature, health);
        setCreatureSpeed(creature, speed);
        setCreatureXP(creature, xp);
        setCreatureFood(creature, food);
        return creature;
    }

    //setters
    public static void setCreatureName(Creature creature, String creatureName) {
        creature.creatureName = creatureName;
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

    public static void setCreatureFood(Creature creature, boolean food) {
        creature.food = food;
    }

    //getters
    public static String getCreatureName(Creature creature) {
        return creature.creatureName;
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

    public static boolean getCreatureFood(Creature creature) {
        return creature.food;
    }

    public static Creature[] getCreatureArray() {
        return Creature.creatureArray;
    }

    public static void creatureInteraction() {

    }

    //boss creatures?? (maybe later)

    //SURROUNDING CODE
    //surrounding info setup
    public static class Surrounding {
        static final Surrounding[] visitedLocations = new Surrounding[50];
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
        print("ERROR: Array is full");
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

    //reads file data
    private static void readFile(String fileName) {

        try (FileInputStream fis = new FileInputStream(fileName)) {
            int content;
            // reads a byte at a time, if it reached end of the file, returns -1
            while ((content = fis.read()) != -1) {
                System.out.print((char) content);
            }
        } catch (IOException ex) {
            print(ex.getMessage());
        }
    }

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
            if (ans.equals("yes")||ans.equals("no")) {
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
        String ans = inputString("\nDo you have a game saved? (yes or no) >>> ");
        if (trueOrFalse(ans)) {
            String file = inputString("\nPlease enter the file directory >>> ");
            readFile(file);
        } else {
            startGame();
        }
    }

    //GAME CODE
    //game info setup
    public static void startGame() {
        loadMap();
        gameDirection();
    }

    //varied scenarios faced at different locations
    public static void locationOptions(Surrounding location, Player player) {
        Creature creature = randomCreature(getCreatureArray());
        Random randomNumber = new Random();
        int creatureChance = randomNumber.nextInt(100);

        if (creatureChance >=50) {
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
                "\na) ATTACK" +
                "\nb) PET" +
                "\nc) LEAVE ALONE" +
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
                "\na) ATTACK" +
                "\nb) TURN BACK " +
                "\nc) RUN" +
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
                    "\na)Slash" +
                    "\nb)Punch" +
                    "\nc)Kick" +
                    "\nd)Dodge" +
                    "\nPlease enter here >>> ");
            print(in);

            if (in.length()>0) {
                attack = in.charAt(0);
                //makes sure the string character entered is between the characters ('a' and 'b') and that the string is a length of 1
                if ((attack >= 97 && attack <=101)) {
                    int creatureHealth = getCreatureHealth(creature);
                    int playerStrength = getPlayerStrength(player);
                    switch (attack) {
                        case 'a':
                            setCreatureHealth(creature, creatureHealth - playerStrength);
                            return false;
                        case 'b':
                            setCreatureHealth(creature,   (int)(creatureHealth - playerStrength * 0.75));
                            return false;
                        case 'c':
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
                print("\nPlease enter a valid input... (a, b, c, d)");
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
        }
    }

    //handles the main inputs from the user, then calling the correct methods for the inputs
    public static String inputHandler(Player player) {
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
        return getSurroundingName(getPlayerCurrentLocation(player));
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
                    "\nPlease enter: \na: View Map 'a' \nb: View Visited locations 'b' \nc: Close MAP 'c' \nPlease enter your input >>> ");
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
                    "\nPlease enter: \na: Save Game 'a' \nb: End Game 'b' \nc: Close Options Menu 'c' \nPlease enter your input >>> ");
            if (in.equalsIgnoreCase("a")||in.equalsIgnoreCase("b")||in.equalsIgnoreCase("c")) {
                switch (in.toLowerCase()) {
                    case "a":
                        saveGame(player);
                        break;
                    case "b":
                        stopGame();
                        break;
                    case "c":
                        run = false;
                }
            }
        }
        directionOptions(player);
    }

    public static void stopGame() {
        print("Ending game... ");
        System.exit(130);
    }
    public static void saveGame(Player player){
        print("Saving game... ");
    }

    //method to display the input options available for the user
    public static void directionOptions(Player player) {
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
