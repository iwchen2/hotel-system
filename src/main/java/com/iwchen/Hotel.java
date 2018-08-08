package com.iwchen;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Hotel {

  private Map<Integer, Room> roomListing;

  private static Logger logger = LogManager.getLogger(Hotel.class);

  public Hotel() {
    roomListing = new HashMap<Integer, Room>();
  }

  public void bookRoom(int numPeople, Date start, Date end, Room.RoomType rType) {

    List<Room> availableRooms = getAvailableRooms(roomListing, rType);
    if(availableRooms.isEmpty()){
      logger.error(String.format("Error Reserving Room: No available rooms - ROOM NOT BOOKED"));
      return;
    }

    //Select a random room from available rooms
    int idx = new Random().nextInt(availableRooms.size());
    Room reservedRoom = availableRooms.get(idx);
    if(reservedRoom.maxOccupancy < numPeople){
      logger.error(String.format("Error Reserving Room: Room accomadates %d, while you require %d - ROOM NOT BOOKED", reservedRoom.maxOccupancy, numPeople));
    }
    reservedRoom.isReserved = true;
    reservedRoom.occupancy = numPeople;
    logger.info(String.format("Successfully booked room %d", reservedRoom.roomNumber));
  }

  public List<Room> getAvailableRooms(Map<Integer, Room> map, Room.RoomType rType) {
    List<Room> availableRooms = new ArrayList<Room>();

    //Iterate through roomListing
    Iterator it = map.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry)it.next();
      Room currRoom = (Room)pair.getValue();

      //Append matching roomtypes into availableRooms ArrayList
      if(currRoom.getRoomType() == rType && !currRoom.isReserved) {
        availableRooms.add(currRoom);
        // System.out.println(currRoom.roomNumber);
      }
    }
    return availableRooms;
  }

  public void checkoutRoom(int roomNumber) {
    Room checkedOutRoom = roomListing.get(roomNumber);
    checkedOutRoom.isReserved = false;
    checkedOutRoom.occupancy = 0;

  }

  public Room getRoom(int roomNumber) {
    return roomListing.getOrDefault(roomNumber, null);
  }

  public List<Room> getRoom(Room.RoomType roomType, boolean availability) {
    List<Room> rooms = new ArrayList<>();

    //Iterate through roomListing
    Iterator it = roomListing.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      Room currRoom = (Room) pair.getValue();

      if(availability) {
        if(currRoom.roomType == roomType && !currRoom.isReserved) {
          rooms.add(currRoom);
        }
      }else{
        if(currRoom.roomType == roomType) {
          rooms.add(currRoom);
        }
      }
    }
    return rooms;
  }

  public Map<Integer, Room> getRoomListing() {
    return roomListing;
  }

  public void setRoomListing(Map<Integer, Room> rooms) {
    this.roomListing = rooms;
  }

}
