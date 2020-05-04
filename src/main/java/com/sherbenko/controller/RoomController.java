package com.sherbenko.controller;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.sherbenko.FindCountry;
import com.sherbenko.entity.Room;

import com.sherbenko.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class RoomController {
    @Autowired
    private RoomService roomService;


    @RequestMapping(value = {"/all-rooms","/"},method = RequestMethod.GET)
    public String getAllRooms(Model model){
        model.addAttribute("rooms",roomService.getAllRooms());
        return "listRooms";
    }
   @RequestMapping(value = "/add",method = RequestMethod.GET)
   public String addRoom(Model model){
        model.addAttribute("room",new Room());
        return "add-room";
   }

    @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") long id, Model model) throws IOException, GeoIp2Exception {


        String country = FindCountry.findCountryFromDb();
        Room room = roomService.getRoomById(id);
        if(!room.getCountry().equals(country)){
            return "errorPageNotAcces";
        }

        model.addAttribute("room", room);
        return "update-room";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addRoom(@Valid Room room, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "add-room";
        }
        roomService.saveRoom(room);
        return "redirect:/all-rooms";
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String updateRoom(@RequestParam(value = "id", required = false) Long id,@RequestParam(value = "light", required = false) boolean light,Model model){
        Room room = roomService.getRoomById(id);
        room.setLight(light);
        roomService.saveRoom(room);
        return "redirect:/all-rooms";
    }



}
