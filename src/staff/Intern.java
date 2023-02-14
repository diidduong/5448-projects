package staff;

import activity.Activity;
import activity.Wash;
import staff.Staff;
import java.util.ArrayList;
import utilities.Registry;
import utilities.RandomGenerator;

import java.util.HashMap;

public class Intern extends Staff {

    private double dailyRate = 80;
    private double turnOverProbability = 0.1;
    private double bonus;
    private int daysOfWork;
    private int capacity;
    public Registry internRegistry= new Registry();

    public ArrayList<Intern> internList;



    public Intern(int day){
        setName(RandomGenerator.nameGenerator());
        HashMap<String, String> registryAction = new HashMap<String,String>();
        System.out.printf("\n%s is hired as an intern.\n", getName());
        registryAction.put("name", getName());
        String formattedDay = String.format("Day_%d_intern_%s", day, getName().replace(' ', '_'));
        internRegistry.add(formattedDay, registryAction);
        //System.out.println(internRegistry.getRegistry());
    }

//    public Intern(int day){
//        setName(RandomGenerator.nameGenerator());
//        HashMap<String, String> registryAction = new HashMap<String,String>();
//        System.out.printf("\n%s is hired as an intern.\n", getName());
//        registryAction.put("name", getName());
//        String formattedDay = String.format("Day_%d_intern_%s", day, getName().replace(' ', '_'));
//        internRegistry.add(formattedDay, registryAction);
//        //System.out.println(internRegistry.getRegistry());
//    }


//    public void hire(int day){
//        Intern intern = new intern(day);
//        internList.add(intern);
//        numberOfInterns++;
//
//    }

    public void workOrQuit(int day){
        for (Intern i: internList){
            boolean quitDecision = RandomGenerator.probabilisticOutcomeGenerator(turnOverProbability);
            if (quitDecision) {
                System.out.printf("\n%s quitted.\n", i.getName());
//                numberOfInterns--;
//                hire(day);
            }
            else {
                i.addSalary();
                System.out.println(i.getSalary());
                i.addWorkDay();
                System.out.println(i.daysOfWork);

            }
        }


    }
    //    public void addSalary() {
//        salary = salary + dailyRate;
//    }
    // need to be completed
    public void wash(int day) {

    }
}