/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.controller;

import com.qqb.travelclub.aggregate.club.TravelClub;
import com.qqb.travelclub.service.ClubService;
import com.qqb.travelclub.service.sdo.TravelClubCdo;
import com.qqb.travelclub.shared.NameValueList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/club")
@Api(description = "Controller for Club")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class ClubController {

    @Autowired
    ClubService clubService;

    @PostMapping
    @ApiOperation(value = "Register Club for new clubs", notes = "This method creates a new Club")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval") })
    ResponseEntity<String> registerClub(@RequestBody TravelClubCdo travelClubCdo){
        return ResponseEntity.ok(clubService.registerClub(travelClubCdo));
    }

    @GetMapping("/name/{clubName}")
    @ApiOperation(value = "Club nomi bilan izlash", notes = "Bu metod club nomi bo'yicha izlash.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = String.class) })
    List<TravelClub> retrieveByName(@PathVariable String clubName){
        return clubService.findClubsByName(clubName);
    }

    @PutMapping("/{clubId}")
    @ApiOperation(value = "Change an existing club", notes = "This method change an existing club")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = String.class) })
    ResponseEntity<String > modify(@PathVariable String clubId, @RequestBody NameValueList nameValueList){
        clubService.modify(clubId, nameValueList);
        return ResponseEntity.ok("{\"message\":\"Club updated successfully\"}");
    }

    @GetMapping(value = "/clubs",produces = "application/json")
    @ApiOperation(value = "Clublar ro'yxati", notes = "Bu metod clublarni chiqaradi.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = String.class) })
    List<TravelClub> findClubAll(){
        return clubService.findClubs();
    }

    @GetMapping(value = "/id/{clubId}",produces = "application/json")
    @ApiOperation(value = "Club id bilan izlash", notes = "Bu metod club id bo'yicha izlash.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = String.class) })
    TravelClub findClubById(@PathVariable String clubId){
        return clubService.findClubById(clubId);
    }

    @DeleteMapping(value = "/{clubId}")
    @ApiOperation(value = "Club id bilan o'chirish", notes = "Bu metod club id bo'yicha o'chiradi.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = String.class) })
    ResponseEntity<String> remove(@PathVariable String clubId){
        clubService.remove(clubId);
        return ResponseEntity.ok("{\"message\":\"Club deleted successfully\"}");
    }
}
