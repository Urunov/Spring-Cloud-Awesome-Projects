/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.controller;

import com.qqb.travelclub.aggregate.club.CommunityMember;
import com.qqb.travelclub.aggregate.club.TravelClub;
import com.qqb.travelclub.service.MemberService;
import com.qqb.travelclub.service.sdo.MemberCdo;
import com.qqb.travelclub.shared.NameValueList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/member")
@Api(description = "Controller for Member")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping
    @ApiOperation(value = "Register Member for new members", notes = "This method creates a new member")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = String.class) })
    ResponseEntity<String> registerMember(@RequestBody MemberCdo memberCdo){
        return ResponseEntity.ok(memberService.registerMember(memberCdo));
    }

    @Cacheable(value = "members", key = "#result.id")
    @GetMapping("/id/{memberId}")
    @ApiOperation(value = "Member id bilan izlash", notes = "Bu metod member id bo'yicha izlash.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval") })
    public CommunityMember findMemberById(@PathVariable String memberId){
        return memberService.findMemberById(memberId);
    }

    @GetMapping(value = "/members",produces = "application/json")
    @ApiOperation(value = "Memberlar ro'yxati", notes = "Bu metod memberlarni chiqaradi.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval") })
    List<CommunityMember> findMembersAll(){
        return memberService.findMembers();
    }

    @GetMapping("/email/{memberEmail}")
    @ApiOperation(value = "Member email bilan izlash", notes = "Bu metod member email bo'yicha izlash.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval") })
    CommunityMember findMemberByEmail(@PathVariable String memberEmail){
        return memberService.findMemberByEmail(memberEmail);
    }

    @GetMapping("/members/{name}")
    @ApiOperation(value = "Member nomi bilan izlash", notes = "Bu metod member nomi bo'yicha izlash.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval") })
    List<CommunityMember> findMembersByName(@PathVariable String name){
        return memberService.findMembersByName(name);
    }

    @CachePut(value = "members",key = "#memberId")
    @PutMapping("/{memberId}")
    @ApiOperation(value = "Change an existing member", notes = "This method change an existing member")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = String.class) })
    public ResponseEntity<String> modify(@PathVariable String memberId, @RequestBody NameValueList nameValueList){
        memberService.modifyMember(memberId, nameValueList);
        return ResponseEntity.ok("{\"message\":\"Member updated successfully\"}");
    }

    @CacheEvict(value = "members",key = "#memberId")
    @DeleteMapping("/{memberId}")
    @ApiOperation(value = "Member id bilan o'chirish", notes = "Bu metod member id bo'yicha o'chiradi.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error, serverda xatolik ro'y berdi..."),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 303, message = "303 xatolik"),
            @ApiResponse(code = 200, message = "Successful retrieval") })
    public ResponseEntity<String> remove(@PathVariable String memberId){
        memberService.removeMember(memberId);
        return ResponseEntity.ok("Member deleted successfully");
    }
}
