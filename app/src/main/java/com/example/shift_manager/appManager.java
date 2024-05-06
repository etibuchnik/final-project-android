package com.example.shift_manager;

import com.google.firebase.auth.FirebaseUser;

public class appManager {
    private static int numOfCChosenShifts ;
    public static FirebaseUser user;
    private static int[] ShiftsIWant;
    private static int[] ShiftsIGot;
    public static int hoursOfWork;
    public static int userIdApp=0;



    public appManager() {
        ShiftsIWant = new int[12];
        ShiftsIGot =  new int[12];
        numOfCChosenShifts = 0;
        hoursOfWork = 0;
        userIdApp++;
    }


    public static int userChooseShift(int num){
        if (ShiftsIWant[num-1] == 0) {
            ShiftsIWant[num - 1] = 1;
            numOfCChosenShifts++;
            return 1;
        }
        if (ShiftsIWant[num-1] == 1){
            ShiftsIWant[num-1] = 0;
            numOfCChosenShifts--;
            return 0;
        }
        else return 1;

    }




    public static int getNumOfCChosenShifts() {
        numOfCChosenShifts=0;
        for (int i=0; i<12 ; i++){
            if(ShiftsIWant[i] == 1)
                numOfCChosenShifts++;
        }
        return numOfCChosenShifts;

    }


    public static int[] getShiftsIWant() {
        return ShiftsIWant;
    }

    public appManager setShiftsIWant(int[] shiftsIWant) {
        ShiftsIWant = shiftsIWant;
        return this;
    }

    public static int[] getShiftsIGot() {
        return ShiftsIGot;
    }

    public  appManager setShiftsIGot(int[] shiftsIGot) {
        ShiftsIGot = shiftsIGot;
        return this;
    }

    public static int getHoursOfWork() {
       int mone=0;
       for (int i=0; i<12 ; i++){
           if(ShiftsIGot[i] == 1)
               mone++;
       }
       return 8*mone;

    }


}
