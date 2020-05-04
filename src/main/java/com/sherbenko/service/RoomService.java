package com.sherbenko.service;

import com.sherbenko.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Room saveRoom(Room room);

    Room getRoomById(long id);

    Iterable<Room> getAllRooms();
}
