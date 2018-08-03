package com.ngdroidapp;

import com.mycompany.myngdroidapp.GameActivity;

public class ContextClass {
    private static GameActivity activity;
    public static void setActivity(GameActivity activity) {
        ContextClass.activity = activity;
    }
    public static GameActivity getActivity() { return activity; }
}
