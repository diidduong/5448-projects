package utilities;

import customer.Buyer;
import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Duy Duong, Ahmed.H.Biby
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

    private static String[] monsterTruckNames = {"Air Force Afterburner", "Avenger", "Bad News Travels Fast", "Batman",
            "Backwards Bob", "Bear Foot (1979)", "Bear Foot (F-150)", "Bear Foot (2xtreme)", "Bear Foot (Silverado)", "Bear Foot USA",
            "Bigfoot", "Black Stallion", "Blacksmith", "Blue Thunder", "Bounty Hunter", "Brutus", "Bulldozer", "Captain's Curse",
            "Cyborg", "El Toro Loco", "Grave Digger", "Grinder", "Gunslinger", "Jurassic Attack", "King Krunch", "Lucas Oil Crusader",
            "Madusa", "Maximum Destruction (Max-D)", "Mohawk Warrior", "Monster Mutt", "Monster Mutt", "Monster Mutt Dalmatian",
            "Predator", "Shell Camino",  "Raminator", "Snake Bite", "Stone Crusher", "Sudden Impact", "Swamp Thing", "The Destroyer",
            "The Felon", "USA-1", "War Wizard", "WCW Nitro Machine", "Zombie"};

    private static String[] motorcycleNames = {"Aprilia", "Benelli", "Beta", "Bimota", "BMW", "Brammo", "Buell", "Cagiva",
            "Can-Am", "CCW", "Ducati", "EBR", "Harley-Davidson", "Honda", "Husaberg", "Husqvarna", "Hyosung", "Indian", "Kawasaki",
            "KTM", "Kymco", "Laverda", "LiveWire", "Moto Guzzi", "MV Agusta", "Norton", "Phantom", "Piaggio", "Polaris Slingshot",
            "Ridley", "Roehr", "Royal Enfield", "Suzuki", "Triumph", "Ural", "Vespa", "Victory", "Yamaha", "Zero"};

    private static String[] electricCarNames = {"Lucid Air", "Nissan Leaf", "Tesla Model 3", "Tesla Model S", "Chevrolet Spark",
            "Fiat 500e", "Kia Niro EV", "Hyundai Ioniq 5", "Kia EV6", "Ford Mustang Mach-E", "Mercedes-Benz EQS-Class","Tesla Model Y",
            "Genesis GV60", "Volvo XC40 Recharge", "Audi E-Tron Sportback", "BMW iX", "Lucid Gravity", "MINI Cooper SE", "Volkswagen ID4",
            "Hyundai Kona Electric", "Toyota bZ4X", "Nissan Ariya", "Subaru Solterra", "Karma GSe-6", "Polestar"};

    private static String[] carYears = {"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020",
            "2021", "2022", "2023"};

    private static String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
            "12", "13", "14", "15"};

    private static ArrayList<String> usedNames = new ArrayList<>();
    private static ArrayList<String> usedNormalCarNames = new ArrayList<>();
    private static ArrayList<String> usedPerformanceCarNames = new ArrayList<>();
    private static ArrayList<String> usedPickupCarNames = new ArrayList<>();

    private static ArrayList<String> usedMotorcycleNames = new ArrayList<>();
    private static ArrayList<String> usedElectricCarNames = new ArrayList<>();
    private static ArrayList<String> usedMonsterTruckNames = new ArrayList<>();


    /**
     * Generate random person name
     *
     * @return person name in string
     */
    public static String personNameGenerator() {
        return nameGenerator(firstNames, lastNames, usedNames);
    }

    /**
     * Generate random vehicle name by type
     *
     * @param type vehicle type
     * @return vehicle name in string
     */
    public static String vehicleNameGenerator(Vehicle.VehicleType type) {
        switch (type) {
            case PERFORMANCE_CAR:
                return nameGenerator(performanceCarName, carYears, usedPerformanceCarNames);
            case CAR:
                return nameGenerator(normalCarNames, carYears, usedNormalCarNames);
            case PICKUP:
                return nameGenerator(pickupCarNames, carYears, usedPickupCarNames);
            case ELECTRIC_CAR:
                return nameGenerator(electricCarNames, carYears, usedElectricCarNames);
            case MONSTER_TRUCK:
                return nameGenerator(monsterTruckNames, numbers, usedMotorcycleNames);
            case MOTORCYCLE:
                return nameGenerator(motorcycleNames, carYears, usedMotorcycleNames);
            default:
                return "No Name";
        }
    }

    /**
     * 1) Method that generates random name with two part, first and second. This can be used to
     * generate name for person or vehicle. Names are not duplicated. Can generate up to
     * first * second possible names
     *
     * 2) can generate up to (need to be tested) names
     *
     * 3) if run out of name, return null
     *
     * @return random name in string, null if run out of name
     *
     * @param firstNameList first part
     * @param secondNameList second part
     * @param usedNameList list to store used names
     */
    public static String nameGenerator(String[] firstNameList, String[] secondNameList, ArrayList<String> usedNameList){
        //TODO: update UML
        Random random = new Random();
        String name = null;
        int possibleNumNames = firstNameList.length * secondNameList.length;

        while(usedNameList.size() < possibleNumNames) {
            int rand_int1 = random.nextInt(firstNameList.length);
            int rand_int2 = random.nextInt(secondNameList.length);
            String newName = firstNameList[rand_int1] +" " + secondNameList[rand_int2];
            if (!usedNameList.contains(newName)) {
                name = newName;
                break;
            }
        }

        // Add created name to used name
        if (name != null) {
            usedNameList.add(name);
        }
        return name;
    }

    /**
     * Method that generates an int random number within a range.
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
        return randomNum;
    }

    /**
     * Method that generates a double random number within a range.
     *
     * @param min: lowerbound of the range (inclusive)
     *
     * @param max: upperbound of the range (inclusive)
     *
     * @return random int number
     */
    public static double randomIntGenerator(double min, double max){
        Random random = new Random();
        double randomNum = random.nextDouble() * (max - min) + min;
        //System.out.println(randomNum);
        return randomNum;
    }

    /**
     *  Method that generates random int from normal distribution with mean and std given a minimum value
     *
     * @return random int
     */
    public static int randomIntFromNormalDistributionWithMeanAndStdWithMinimum(int mean, int std, int min){
        Random random = new Random();
        double randomInt = random.nextGaussian()*std+mean;
        while ((int)randomInt < min){
            randomInt = random.nextGaussian()*std+mean;
        }
//        System.out.println((int)randomInt); //for testing
        return (int) randomInt;
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
        double randomNum = random.nextInt(101);
        if (probability*100 >= randomNum){
            outcome = true;
        }
        else {
            outcome = false;
        }
        return outcome;
    }

    /**
     * Method to generate random Cleanliness by probability
     * for activities
     * @param probs arrays of probability for each Cleanliness type, size of 3
     * @return a random Cleanliness
     */
    public static Vehicle.Cleanliness getRandomCleanliness(double[] probs) {
        Random random = new Random();
        double randomNum = random.nextDouble();
        if (randomNum <= probs[0]) {
            return Vehicle.Cleanliness.SPARKLING;
        } else if (randomNum <= probs[0] + probs[1]) {
            return Vehicle.Cleanliness.CLEAN;
        } else {
            return Vehicle.Cleanliness.DIRTY;
        }
    }

    /**
     * Method to generate random Cleanliness
     * for constructors
     * @return random cleanliness for the constructor of the vehicle concrete classes
     */

    public static Vehicle.Cleanliness RandomCleanlinessGenerator() {
        Random random = new Random();
        double randomNum = random.nextInt(101);
        if (randomNum <= 5) {
            return Vehicle.Cleanliness.SPARKLING;
        } else if (randomNum <= 40) {
            return Vehicle.Cleanliness.CLEAN;
        } else {
            return Vehicle.Cleanliness.DIRTY;
        }
    }

    /**
     * Default random condition generator with uniform probability
     *
     * @return a VehicleCondition
     */
    public static Vehicle.VehicleCondition RandomConditionGenerator() {
        Random random = new Random();
        double randomNum = random.nextInt(101);
        if (randomNum <= 33) {
            return Vehicle.VehicleCondition.BROKEN;
        } else if (randomNum <= 66) {
            return Vehicle.VehicleCondition.LIKE_NEW;
        } else {
            return Vehicle.VehicleCondition.USED;
        }
    }

    /**
     * Get a uniformly random buying type for Buyer
     * @return a buying type
     */
    public static Buyer.BuyingType getRandomBuyingType() {
        Random random = new Random();
        double randomNum = random.nextInt(100);
        if (randomNum <= 33) {
            return Buyer.BuyingType.JUST_LOOKING;
        } else if (randomNum <= 66) {
            return Buyer.BuyingType.NEEDS_ONE;
        } else {
            return Buyer.BuyingType.WANTS_ONE;
        }
    }

    /**
     * Get uniformly random Vehicle Type
     *
     * @return a Vehicle Type
     */
    public static Vehicle.VehicleType getRandomVehicleType() {
        Random random = new Random();
        double randomNum = random.nextInt(100);
        if (randomNum <= 33) {
            return Vehicle.VehicleType.PERFORMANCE_CAR;
        } else if (randomNum <= 66) {
            return Vehicle.VehicleType.CAR;
        } else {
            return Vehicle.VehicleType.PICKUP;
        }
    }
}