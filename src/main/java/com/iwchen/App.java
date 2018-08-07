package com.iwchen;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * Hello world!
 *
 */
public class App
{

    private static Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args )
    {

        Hotel hyattRegency = new Hotel();
        Map rooms = hyattRegency.getRoomListing();
        rooms.put(1408, new Room(Room.RoomType.MINI_SUITE, 1408));
        rooms.put(111, new Room(Room.RoomType.SINGLE, 111));
        rooms.put(490, new Room(Room.RoomType.DOUBLE_DOUBLE, 490));
        rooms.put(2501, new Room(Room.RoomType.PENTHOUSE_SUITE, 2501));
        rooms.put(769, new Room(Room.RoomType.QUEEN, 769));
        hyattRegency.bookRoom(4, null, null, Room.RoomType.QUEEN);
        // hyattRegency.bookRoom(3, null, null, Room.RoomType.PENTHOUSE_SUITE);
        // hyattRegency.bookRoom(2, null, null, Room.RoomType.PENTHOUSE_SUITE);

        // printRooms(hyattRegency);
        // System.out.println(hyattRegency.getAvailableRooms(rooms, Room.RoomType.SINGLE));

    }

    private static void printRooms (Hotel hotel) {
      Map<Integer, Room> rooms = hotel.getRoomListing();
      System.out.println(Arrays.asList(rooms));
    }
}
