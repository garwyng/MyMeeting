package com.openclassrooms.mymeeting.controler;


import com.openclassrooms.mymeeting.models.Meeting;
import com.openclassrooms.mymeeting.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class MyMeetingApiGenerator {
    public static List<String> getMeetingRooms(){
        return new ArrayList<>(MEETING_ROOMS);}
    private static final List <String> MEETING_ROOMS = Arrays.asList(
            "Réunion 1",
            "Réunion 2",
            "Réunion 3",
            "Réunion 4",
            "Réunion 5",
            "Réunion 6",
            "Réunion 7",
            "Réunion 8",
            "Réunion 9",
            "Réunion 10"
    );
    public static List<Meeting> getMeetings(){return new ArrayList<Meeting>(MEETINGS_LIST);}

    public static List<Meeting> MEETINGS_LIST;

    public static List<String> guests = Arrays.asList( new String("sebastien@lamazon.fr"),
            new String("sebastien@lamazon.fr"),
            new String("antoine@lamazon.fr"),
            new String("miryam@lamazon.fr"),
            new String("mohamed@lamazon.fr"),
            new String("hiroki@lamazon.fr"),
            new String("emilie@lamazon.fr")
    );
    public static final List<String> getGuests(){return guests; }

    static {        MEETINGS_LIST = Arrays.asList(
                    new Meeting(1, "Point projet en cours", getGuests(),"Réunion 1",
                            Utils.getDateFromString("01/08/23 09:00"),
                            Utils.getDateFromString("01/08/23 10:00")),
                    new Meeting(2, "budjet", getGuests(),"Réunion 2",
                            Utils.getDateFromString("01/09/23 09:00"),
                            Utils.getDateFromString("01/09/23 10:00")),
                    new Meeting(3, "campagne achat", getGuests(),"Réunion 3",
                            Utils.getDateFromString("01/10/23 09:00"),
                            Utils.getDateFromString("01/10/23 10:00")),
                    new Meeting(4, "Plan formation", getGuests(),"Réunion 4",
                            Utils.getDateFromString("01/10/23 09:00"),
                            Utils.getDateFromString("01/10/23 10:00")),
                    new Meeting(5, "nouveau projet", getGuests(),"Réunion 5",
                            Utils.getDateFromString("01/11/23 09:00"),
                            Utils.getDateFromString("01/11/23 10:00")),
                    new Meeting(6, "Augmentation", getGuests(),"Réunion 1",
                            Utils.getDateFromString("01/08/23 09:00"),
                            Utils.getDateFromString("01/08/23 10:00")),
                    new Meeting(7, "Présention équipe", getGuests(),"Réunion 7",
                            Utils.getDateFromString("01/08/23 09:00"),
                            Utils.getDateFromString("01/08/23 10:00")),
                    new Meeting(8, "Point projet en cours", getGuests(),"Réunion 8",
                            Utils.getDateFromString("01/08/23 09:00"),
                            Utils.getDateFromString("01/08/23 10:00")),
                    new Meeting(9, "Point projet en cours", getGuests(),"Réunion 9",
                            Utils.getDateFromString("01/09/23 09:00"),
                            Utils.getDateFromString("01/09/23 10:00")),
                    new Meeting(10, "Point projet en cours", getGuests(),"Réunion 1",
                            Utils.getDateFromString("01/10/23 09:00"),
                            Utils.getDateFromString("01/10/23 10:00")),
                    new Meeting(11, "Point projet en cours", getGuests(),"Réunion 1",
                            Utils.getDateFromString("01/11/23 09:00"),
                            Utils.getDateFromString("01/11/23 10:00")),
                    new Meeting(12, "budjet", getGuests(),"Réunion 2",
                            Utils.getDateFromString("01/12/23 09:00"),
                            Utils.getDateFromString("01/12/23 10:00")),
                    new Meeting(13, "campagne achat", getGuests(),"Réunion 3",
                            Utils.getDateFromString("01/11/23 09:00"),
                            Utils.getDateFromString("01/11/23 10:00")),
                    new Meeting(14, "Plan formation", getGuests(),"Réunion 4",
                            Utils.getDateFromString("10/08/23 09:00"),
                            Utils.getDateFromString("10/08/23 10:00")),
                    new Meeting(15, "nouveau projet", getGuests(),"Réunion 5",
                            Utils.getDateFromString("15/08/23 09:00"),
                            Utils.getDateFromString("15/08/23 10:00"))
            );
        }

}


