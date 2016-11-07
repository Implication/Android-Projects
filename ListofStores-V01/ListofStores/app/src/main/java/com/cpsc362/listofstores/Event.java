package com.cpsc362.listofstores;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Anthony on 10/14/2016.
 */

public class Event implements Serializable {
    int eventID;
    int storeID;
    String eventName;
    String eventDate;
    String eventDescription;

    public Event(int id, int storeID, String n, String date, String desc) {
        this.eventID = id;
        this.storeID = storeID;
        this.eventName = n;
        this.eventDate = date;
        this.eventDescription = desc;
    }
}