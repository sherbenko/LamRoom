package com.sherbenko.repository;

import com.sherbenko.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<Room,Long> {
    Room findById(long id);
}
