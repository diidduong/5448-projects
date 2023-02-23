package utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class RandomGeneratorTest {

    //Uncomment the print commands in the monsterTruckNameGenerator() to test the logic
    @Test
    void monsterTruckNameGenerator_logicTest() {
        for (int i = 0; i < 100; i++) {
            RandomGenerator.monsterTruckNameGenerator();
        }

    }

    //Uncomment the print commands in the genericNameGenerator() to test the logic
    @Test
    void motorcycleNameGenerator_logicTest() {
        for (int i = 0; i < 100; i++) {
            RandomGenerator.motorcycleNameGenerator();
        }
    }

    //Uncomment the print commands in the genericNameGenerator() to test the logic
    @Test
    void electricCarNameGenerator_logicTest() {
        for (int i = 0; i < 100; i++) {
            RandomGenerator.electricCarNameGenerator();
        }
    }

    @Test
    void randomIntFromNormalDistributionWithMeanAndStdWithMinimum_logicTest() {
        for (int i = 0; i < 100; i++) {
            RandomGenerator.randomIntFromNormalDistributionWithMeanAndStdWithMinimum(700, 300, 1000);
        }
    }
}