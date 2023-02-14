package utilities;

import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Ahmed.H.Biby
 * Purpose: This class includes random generators methods that can be used in the other functional classes via delegation.
 */
public class RandomGenerator {

    private static String[] firstNames = {"Arya", "Gordon", "Ashlynn", "Easton", "Averi", "Nathaniel",  "Paulina", "Gary",
            "Braelynn", "Aarav", "Leyla", "Dion",  "Amara", "Dalton", "Calliope", "Rhett", "Morgan", "Moses", "Claire",
            "Leonard", "Bellamy", "Kingston", "Bailee", "Cade",  "Rylan",  "Eliezer", "Elaina", "Gerald", "Amayah",
            "Remy", "Zara", "Easton", "Sawyer",  "Jamal", "Gloriay", "Kyng", "Angie", "Riley", "Macie", "Ismael",
            "Charlee",  "Logan",  "Meredith", "Ridge",  "Navy",  "Jericho",  "Haven",  "Emory", "Kaliyah", "Ari",
            "Frances", "Eric", "Anastasia",  "Jovanni", "Elaine", "Andrew", "Aislinn", "Silas", "Oakley",  "Salvatore",
            "Octavia", "Leroy", "Royalty", "Joel", "Alaia",  "Dilan", "Anaya", "Bentlee",  "Alice", "Cash", "Arielle",
            "Mohammad", "Bristol", "Bentley", "Jaycee", "Louis", "Kiana", "Blaine",  "Ansley",  "Darius", "Keyla",
            "Samson", "Demi",  "Harry",  "Adalynn", "Johnathan", "Olivia", "Gerardo", "Aurora", "Adam",  "Tiffany",
            "Emery", "Miley", "Lewis",  "Sylvia", "Fernando",  "Emmalynn", "Tristan", "Ariyah", "Maxwell"};

    private static String[] lastNames = {"Vo", "Acevedo", "Reed", "Lester", "Cole", "Herman", "Calhoun", "Newton", "Vincent",
            "Meyers", "Stuart", "Fernandez", "Tate", "Ho", "Porter", "Ryan", "Person", "Cruz", "Wilkinson",
            "O’Donnell", "Alexander", "Giles", "Cabrera", "Hurley",  "Rowland", "Maldonado", "Burch", "Xiong", "Vaughn",
            "Carroll", "Reed", "Estrada", "Benton",  "Rangel", "Duffy", "Chase", "O’brien", "McCormick", "Duran",
            "Reid",  "Thomas",  "Bryan",  "Skinner", "Yu",  "Mayo",  "Cortez",  "Bradshaw",  "Hess", "Chang", "Blair",
            "Kelley", "Simpson", "O’Connell",  "Moody", "Phillips", "Spence", "Jimenez", "Fuller", "Galindo",
            "Frazier", "Hayden", "Salinas", "Hart", "Graham", "Conrad",  "Fletcher", "Case", "Ramos", "Jensen", "Norris",
            "Foley", "Waters",  "Fernandez", "Stanton", "Singh", "Rivers", "Gould", "Faulkner",  "Summers", "Quintero",
            "Petersen", "Leonard", "Ahmed", "Jordan",  "Cardenas", "Smith", "Pineda", "Scott", "Myers", "Durham",
            "Barry", "Eaton", "Parsons", "Casey", "Fleming", "Dickson",  "Meyer", "Zimmerman", "Kennedy", "Pollard"};

    private static String[] performanceCarName = {"Dodge Viper", "Chevrolet Corvette", "Lamborghini Countach", "Porsche 911",
            "Ferrari Daytona", "Nissan Z-Car", "Toyota Supra Mark IV", "Acura NSX", "Mazda MX-5 Miata",
            "MG MGB", "Subaru BRZ", "Toyota GR86", "Chevrolet Camaro", "Chevrolet Camaro ZL1", "Ford Mustang",
            "Ford Mustang Shelby GT500", "Porsche 718 Cayman", "Porsche 718 Boxster", "Ferrari F8", "Maserati MC20",
            "McLaren 720S" };

    private static String[] pickupCarNames = {"Ford F-150 Raptor", "Dodge Ram 1500 TRX",  "GMC Sierra Supercharged",
            "Jeep Gladiator",  "Ford Velociraptor 6×6", "Toyota Tacoma TRD Pro", "Ford F-150 Lightning",
            "Nikola Badger", "Rivian R1T", "Tesla Cybertruck", "GMC Hummer EV", "Toyota Tundra",
            "Chevrolet Colorado ZR2", "Chevrolet Silverado 1500", "Nissan Titan XD", "Toyota Hilux",
            "Mahindra Bolero Camper", "Isuzu D-Max", "Ford Maverick", "Hyundai Santa Cruz", "Honda Ridgeline",
            "Nissan Frontier", "GMC Canyon", "Ford Ranger"};
    private static String[] normalCarNames = {"Subaru Outback", "Toyota Corolla", "Honda Odyssey", "Lucid Air", "Honda Accord",
            "Nissan Sunny", "Nissan Leaf", "Honda Civic", "Hyundai Elantra", "Tesla Model 3",
            "Mercedes-Benz C-class", "Mercedes-Benz S-class", "Mercedes-Benz G-class", "Tesla Model S",
            "Hyundai Sonata", "Mercedes-Benz AMG-class", "Kia K5", "BMW i4", "Audi A3", "Volvo XC90",
            "Volvo XC70", "Mazda3", "Dodge Charger", "Chevrolet Bolt", "Chevrolet Spark", "Lexus RC 300",
            "Lexus RX 350", "Renault Logan", "Mitsubishi Eclipse"};


    private static String[] carYears = {"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020",
            "2021", "2022", "2023"};

    private static ArrayList<String> usedNames = new ArrayList<>();
    private static ArrayList<String> usedNormalCarNames = new ArrayList<>();
    private static ArrayList<String> usedPerformanceCarNames = new ArrayList<>();
    private static ArrayList<String> usedPickupCarNames = new ArrayList<>();

    /**
     * 1) Method that generates random person names using a combination of a random name from names1 array, as first name,
     * and a random name from names2 array, as last name.
     *
     * 2) can generate up to 9702 names
     *
     * 3) may encounter infinite while loop issue
     * @return random person name
     */
    public static String nameGenerator(){
        Random random = new Random();
        int rand_int1 = random.nextInt(firstNames.length);
        int rand_int2 = random.nextInt(lastNames.length);
        String name;
        name = firstNames[rand_int1] + " " + lastNames[rand_int2];
        while(usedNames.contains(name)) {
            int rand_int3 = random.nextInt(firstNames.length);
            int rand_int4 = random.nextInt(lastNames.length);
            name = firstNames[rand_int3] + " " + lastNames[rand_int4];
        }
        usedNames.add(name);
        //System.out.println(name);
        //System.out.println(usedNames);
        return name;
    }
    /**
     * 1) Method that generates random normal car names using a combination of a random car name from normalCarName array
     * and a random year from carYear array.
     *
     * 2) can generate up to (need to be tested) names
     *
     * 3) may encounter infinite while loop issue
     *
     * @return random normal car name
     */
    public static String normalCarNameGenerator(){
        Random random = new Random();
        int rand_int1 = random.nextInt(normalCarNames.length);
        int rand_int2 = random.nextInt(carYears.length);
        String name;
        name = normalCarNames[rand_int1] + " " + carYears[rand_int2];
        while(usedNormalCarNames.contains(name)) {
            int rand_int3 = random.nextInt(normalCarNames.length);
            int rand_int4 = random.nextInt(carYears.length);
            name = normalCarNames[rand_int3] +" " + carYears[rand_int4];
        }
        usedNormalCarNames.add(name);
        //System.out.println(name);
        //System.out.println(usedNormalCarNames);
        return name;
    }

    /**
     * 1) Method that generates random performance car names using a combination of a random pickup name from performanceCarName array
     * and a random year from carYear array.
     *
     * 2) can generate up to 294 names
     *
     * 3) may encounter infinite while loop issue
     *
     * @return random performance car name
     */

    public static String performanceCarNameGenerator(){
        Random random = new Random();
        int rand_int1 = random.nextInt(performanceCarName.length);
        int rand_int2 = random.nextInt(carYears.length);
        String name = new String();
        name = performanceCarName[rand_int1] +" " + carYears[rand_int2];
        while(usedPerformanceCarNames.contains(name)) {
            int rand_int3 = random.nextInt(performanceCarName.length);
            int rand_int4 = random.nextInt(carYears.length);
            name = performanceCarName[rand_int3] +" " + carYears[rand_int4];
        }
        usedPerformanceCarNames.add(name);
        //System.out.println(name);
        //System.out.println(usedPerformanceCarNames);
        return name;
    }
    /**
     * 1) Method that generates random pickup car names using a combination of a random pickup name from pickupCarName array
     * and a random year from carYear array.
     *
     * 2) can generate up to 336 names
     *
     * 3) may encounter infinite while loop issue
     *
     * @return random pickup car name
     */

    public static String pickupCarNameGenerator(){
        Random random = new Random();
        int rand_int1 = random.nextInt(pickupCarNames.length);
        int rand_int2 = random.nextInt(carYears.length);
        String name = new String();
        name = pickupCarNames[rand_int1] +" " + carYears[rand_int2];
        while(usedPickupCarNames.contains(name) == true) {
            int rand_int3 = random.nextInt(pickupCarNames.length);
            int rand_int4 = random.nextInt(carYears.length);
            name = pickupCarNames[rand_int3] +" " + carYears[rand_int4];
        }
        usedPickupCarNames.add(name);
        //System.out.println(name);
        //System.out.println(usedPickupCarNames);
        return name;
    }

    /**
     * Method that generates a int random number within a range.
     *
     * @param min: lowerbound of the range (inclusive)
     *
     * @param max: upperbound of the range (inclusive)
     *
     * @return random int number
     */
    public static int randomIntGenerator(int min, int max){
        Random random = new Random();
        int randomNum = random.nextInt(max - min + 1) + min;
        //System.out.println(randomNum);
        return randomNum;
    }

    /**
     * Method that generates an outcome(true/false) for given a probability parameter.
     *
     * @param probability a double that ranges between 0 and 1
     *
     * @return boolean random outcome
     */
    public static boolean probabilisticOutcomeGenerator(double probability){
        Random random = new Random();
        boolean outcome;
        double randomNum = random.nextInt(101) * 100;
        if (probability*100 >= randomNum){
            outcome = true;
        }
        else {
            outcome = false;
        }
        System.out.println(randomNum);
        System.out.println(outcome);
        return outcome;
    }

    /**
     * Method to generate random Cleanliness by probability
     *
     * @param probs arrays of probability for each Cleanliness type, size of 3
     * @return a random Cleanliness
     */
    public static Vehicle.Cleanliness getRandomCleanliness(double[] probs) {
        Random random = new Random();
        double randomNum = random.nextInt(101) * 100;
        if (randomNum <= 5) {
            return Vehicle.Cleanliness.SPARKING;
        } else if (randomNum <= 40) {
            return Vehicle.Cleanliness.CLEAN;
        } else {
            return Vehicle.Cleanliness.DIRTY;
        }
    }
}