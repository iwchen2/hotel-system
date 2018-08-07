package com.iwchen;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.util.Collections;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * Hello world!
 *
 */
public class App
{

    private static Logger logger = LogManager.getLogger(App.class);

    private static final List<Room.RoomType> VALUES = Collections.unmodifiableList(Arrays.asList(Room.RoomType.values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static void main( String[] args )
    {

        Hotel hyattRegency = new Hotel();
        Map rooms = hyattRegency.getRoomListing();
        createRooms(50, rooms);
        // hyattRegency.bookRoom(4, null, null, Room.RoomType.QUEEN);
        // hyattRegency.bookRoom(3, null, null, Room.RoomType.PENTHOUSE_SUITE);
        // hyattRegency.bookRoom(2, null, null, Room.RoomType.PENTHOUSE_SUITE);

        printRooms(hyattRegency);
        // System.out.println(hyattRegency.getAvailableRooms(rooms, Room.RoomType.SINGLE));

    }

    private static void printRooms (Hotel hotel) {
      Map<Integer, Room> rooms = hotel.getRoomListing();
      System.out.println(rooms.values());
    }

    private static void createRooms(int numRooms, Map rooms){

      while(numRooms > 0){
        Room.RoomType roomType = VALUES.get(RANDOM.nextInt(SIZE));
        // System.out.println(randRoom);
        int roomNumber = RANDOM.nextInt(2000);
        Room newRoom = new Room(roomType, roomNumber);
        rooms.put(roomNumber, newRoom);
        System.out.println(String.format("%d: %s", roomNumber, roomType));
        numRooms--;
      }
    }
}
