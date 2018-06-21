package com.iwchen;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;


public class Hotel {

  private Map<Integer, Room> roomListing;

  public Hotel() {
    roomListing = new HashMap<Integer, Room>();
  }

  public void bookRoom(int numPeople, Date start, Date end, Room.RoomType rType) {

    List<Room> availableRooms = getAvailableRooms(roomListing, rType);

    //Select a random room from available rooms
    int idx = new Random().nextInt(availableRooms.size());
    Room reservedRoom = availableRooms.get(idx);
    reservedRoom.isReserved = true;
    reservedRoom.occupancy = numPeople;
  }

  private List<Room> getAvailableRooms(Map<Integer, Room> map, Room.RoomType rType) {
    List<Room> availableRooms = new ArrayList<Room>();

    //Iterate through roomListing
    Iterator it = map.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry)it.next();
      Room currRoom = (Room)pair.getValue();
      System.out.println(pair.getKey() + ": " + currRoom.getRoomType());

      //Append matching roomtypes into availableRooms ArrayList
      if(currRoom.getRoomType() == rType && !currRoom.isReserved) {
        availableRooms.add(currRoom);
        System.out.println(currRoom.roomNumber);
      }
    }
    return availableRooms;
  }

  public void checkoutRoom(int roomNumber) {
    Room checkedOutRoom = roomListing.get(roomNumber);
    checkedOutRoom.isReserved = false;
    checkedOutRoom.occupancy = 0;

  }

  public Map<Integer, Room> getRoomListing() {
    return roomListing;
  }

  public void setRoomListing(Map<Integer, Room> rooms) {
    this.roomListing = rooms;
  }

}
