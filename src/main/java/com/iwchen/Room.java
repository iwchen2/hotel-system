package com.iwchen;

public class Room {

public enum RoomType{

  PENTHOUSE_SUITE,
  MASTER_SUITE,
  MINI_SUITE,
  DOUBLE_DOUBLE,
  TWIN,
  KING,
  QUEEN,
  TRIPLE,
  DOUBLE,
  SINGLE
}

  RoomType roomType;
  double price;
  boolean isReserved;
  Date startDate;
  Date endDate;
  int roomNumber;
  int occupancy;

  public Room(RoomType roomType){
    this.roomType = roomType;
  }
}
