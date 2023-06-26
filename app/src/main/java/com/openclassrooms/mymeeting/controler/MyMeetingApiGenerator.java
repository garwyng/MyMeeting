package com.openclassrooms.mymeeting.controler;


import com.openclassrooms.mymeeting.models.Meeting;
import com.openclassrooms.mymeeting.models.Room;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

abstract class MyMeetingApiGenerator {
    public static List<Room> getMeetingRooms(){
        return new ArrayList<>(MEETING_ROOMS);}
    private static final List <Room> MEETING_ROOMS = Arrays.asList(
            new Room(1,"Réunion 1"),
            new Room(2,"Réunion 2"),
            new Room(3,"Réunion 3"),
            new Room(4,"Réunion 4"),
            new Room(5,"Réunion 5"),
            new Room(6,"Réunion 6"),
            new Room(7,"Réunion 7"),
            new Room(8,"Réunion 8"),
            new Room(9,"Réunion 9"),
            new Room(10,"Réunion 10")
    );
    public static List<Meeting> getMeetings(){return new ArrayList<Meeting>(MEETINGS_LIST);}
    static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm", Locale.FRANCE);

    public static List<Meeting> MEETINGS_LIST;

    public static List<String> guests = Arrays.asList( new String("sebastien@lamazon.fr"),
            new String("sebastien@lamazon.fr"),
            new String("antoine@lamazon.fr"),
            new String("miryam@lamazon.fr"),
            new String("mohamed@lamazon.fr"),
            new String("hiroki@lamazon.fr"),
            new String("emilie@lamazon.fr")
    );
    public static List<String> getGuests(){return guests; }

    static {
        try {
            MEETINGS_LIST = Arrays.asList(
                    new Meeting(1, "Point projet en cours", getGuests(),new Room(1,"Réunion 1"),
                            format.parse("23/06/20 14:00"),format.parse("23/06/20 14:00")),
                    new Meeting(2, "budjet", getGuests(),new Room(2,"Réunion 2"),
                            format.parse("23/06/20 14:00"),format.parse("23/06/20 14:00")),
                    new Meeting(3, "campagne achat", getGuests(),new Room(3,"Réunion 3"),
                            format.parse("23/06/20 14:00"),format.parse("23/06/20 14:00")),
                    new Meeting(4, "Plan formation", getGuests(),new Room(4,"Réunion 4"),
                            format.parse("23/06/20 14:00"),format.parse("23/06/20 14:00")),
                    new Meeting(5, "nouveau projet", getGuests(),new Room(5,"Réunion 5"),
                            format.parse("23/06/20 14:00"),format.parse("23/06/20 14:00")),
                    new Meeting(6, "Augmentation", getGuests(),new Room(6,"Réunion 6"),
                            format.parse("23/06/20 14:00"),format.parse("23/06/20 14:00")),
                    new Meeting(7, "Présention équipe", getGuests(),new Room(7,"Réunion 7"),
                            format.parse("23/06/20 14:00"),format.parse("23/06/20 14:00")),
                    new Meeting(8, "Point projet en cours", getGuests(),new Room(8,"Réunion 8"),
                            format.parse("23/06/20 14:00"),format.parse("23/06/20 14:00")),
                    new Meeting(9, "Point projet en cours", getGuests(),new Room(9,"Réunion 9"),
                            format.parse("23/06/20 14:00"),format.parse("23/06/20 14:00")),
                    new Meeting(10, "Point projet en cours", getGuests(),new Room(10,"Réunion 10"),
                            format.parse("23/06/20 14:00"),format.parse("23/06/20 14:00")),
                    new Meeting(1, "Point projet en cours", getGuests(),new Room(1,"Réunion 1"),
                            format.parse("23/06/20 14:00"),format.parse("23/06/20 14:00")),
                    new Meeting(11, "budjet", getGuests(),new Room(2,"Réunion 2"),
                            format.parse("23/06/20 14:00"),format.parse("23/06/20 14:00")),
                    new Meeting(12, "campagne achat", getGuests(),new Room(3,"Réunion 3"),
                            format.parse("23/06/20 14:00"),format.parse("23/06/20 14:00")),
                    new Meeting(13, "Plan formation", getGuests(),new Room(4,"Réunion 4"),
                            format.parse("23/06/20 14:00"),format.parse("23/06/20 14:00")),
                    new Meeting(14, "nouveau projet", getGuests(),new Room(5,"Réunion 5"),
                            format.parse("23/06/20 14:00"),format.parse("23/06/20 14:00"))
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }


}


