/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.controller;

import com.qqb.travelclub.aggregate.club.CommunityMember;
import com.qqb.travelclub.aggregate.club.Membership;
import com.qqb.travelclub.service.MembershipService;
import com.qqb.travelclub.service.sdo.MembershipCdo;
import com.qqb.travelclub.shared.NameValueList;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/membership")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class MembershipController{
    //
    @Autowired
    MembershipService membershipService;

    @PostMapping()
    @ApiOperation(value = "Register Membership for new members", notes = "This method creates a new membership")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval") })
    public String registerMembership(@RequestBody MembershipCdo membershipCdo) {
        return membershipService.registerMembership(membershipCdo);
    }

    @GetMapping("/id/{membershipId}")
    @ApiOperation(value = "Membership id bilan izlash", notes = "Bu metod membership id bo'yicha izlash.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval") })
    public Membership findMembership(@PathVariable String membershipId) {
        return membershipService.findMembership(membershipId);
    }

    @GetMapping("/byClubAndMember/{clubId}/{memberId}")
    @ApiOperation(value = "Membership clubid va memberId bilan izlash", notes = "Bu metod club id va member id bo'yicha izlash.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval") })
    public Membership findMembershipByClubIdAndMemberId(@PathVariable String clubId, @PathVariable String memberId) {
        return membershipService.findMembershipByClubIdAndMemberId(clubId, memberId);
    }

    @GetMapping("/byClubAndEmail/{clubId}/{memberEmail}")
    @ApiOperation(value = "Membership clubId va member email bilan izlash", notes = "Bu metod membershipni clubid va member email bo'yicha izlash.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval") })
    public Membership findMembershipByClubIdAndMemberEmail(@PathVariable String clubId, @PathVariable String memberEmail) {
        return membershipService.findMembershipByClubIdAndMemberEmail(clubId, memberEmail);
    }

    @GetMapping("/membershipsClub/{clubId}")
    @ApiOperation(value = "Membershiplarni clubId bilan izlash", notes = "Bu metod membershiplarni clubId bo'yicha izlash.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval") })
    public List<Membership> findAllMembershipsOfClub(String clubId) {
        return membershipService.findAllMembershipsOfClub(clubId);
    }

    @GetMapping("/membershipsMember/{memberId}")
    @ApiOperation(value = "Membershiplarni memberid bilan izlash", notes = "Bu metod membershiplarni memberid bo'yicha izlash.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval") })
    public List<Membership> findAllMembershipsOfMember(@PathVariable String memberId) {
        return membershipService.findAllMembershipsOfMember(memberId);
    }

    @PutMapping("/{membershipId}")
    @ApiOperation(value = "Change an existing membership", notes = "This method change an existing membership")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval") })
    public ResponseEntity<String> modifyMembership(@PathVariable String membershipId, @RequestBody NameValueList nameValueList) {
        membershipService.modifyMembership(membershipId, nameValueList);
        return ResponseEntity.ok("{\"message\":\"Membership updated successfully\"}");
    }

    @DeleteMapping("/{membershipId}")
    @ApiOperation(value = "Membership id bilan o'chirish", notes = "Bu metod membership id bo'yicha o'chiradi.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval") })
    public ResponseEntity<String> removeMembership(@PathVariable String membershipId) {
        membershipService.removeMembership(membershipId);
        return ResponseEntity.ok("{\"message\":\"Membership deleted successfully\"}");
    }

    @GetMapping(value = "/memberships",produces = "application/json")
    @ApiOperation(value = "Membershiplar ro'yxati", notes = "Bu metod membershiplarni chiqaradi.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval") })
    List<Membership> findMembershipsAll(){
        return membershipService.findAllMemberships();
    }
}
