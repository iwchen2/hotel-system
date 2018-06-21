package com.iwchen;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Arrays;


/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Hotel hyattRegency = new Hotel();
        Map rooms = hyattRegency.getRoomListing();
        rooms.put(1408, new Room(Room.RoomType.MINI_SUITE, 1408));
        rooms.put(111, new Room(Room.RoomType.SINGLE, 111));
        rooms.put(490, new Room(Room.RoomType.DOUBLE_DOUBLE, 490));
        rooms.put(2501, new Room(Room.RoomType.PENTHOUSE_SUITE, 2501));
        hyattRegency.bookRoom(3, null, null, Room.RoomType.PENTHOUSE_SUITE);

        printRooms(hyattRegency);

    }

    private static void printRooms (Hotel h) {
      Map<Integer, Room> rooms = h.getRoomListing();
      System.out.println(Arrays.asList(rooms));
    }
}
