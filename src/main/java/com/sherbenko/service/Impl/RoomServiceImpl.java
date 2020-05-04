package com.sherbenko.service.Impl;

import com.sherbenko.entity.Room;
import com.sherbenko.repository.RoomRepository;
import com.sherbenko.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;


    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room getRoomById(long id) {
        return roomRepository.findById(id);
    }

    @Override
    public Iterable<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
