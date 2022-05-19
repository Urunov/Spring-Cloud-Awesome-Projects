/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.aggregate.club;

import com.qqb.travelclub.aggregate.Entity;
import com.qqb.travelclub.shared.NameValue;
import com.qqb.travelclub.shared.NameValueList;
import com.qqb.travelclub.util.helper.DateUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialBoard extends Entity {
    //
    private String clubId;
    private String name;
    private String adminEmail;
    private String createDate;

    public SocialBoard(String id) {
        //
        super(id);
    }

    public SocialBoard(String clubId, String name, String adminEmail) {
        //
        this.clubId = clubId;
        this.name = name;
        this.adminEmail = adminEmail;
        this.createDate = DateUtil.today();
    }

    @Override
    public String toString() {
        //
        StringBuilder builder = new StringBuilder();

        builder.append("SocialBoard id: ").append(clubId);
        builder.append(", name: ").append(name);
        builder.append(", admin email: ").append(adminEmail);
        builder.append(", creation date: ").append(createDate);

        return builder.toString();
    }


    public void modifyValues(NameValueList nameValues) {
        //
        for (NameValue nameValue : nameValues.getNameValues()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "name":
                    this.name = value;
                    break;
                case "adminEmail":
                    this.adminEmail = value;
                    break;
                case "clubId":
                    this.clubId = value;
                    break;
                case "createDate":
                    this.createDate = value;
                    break;
            }
        }
    }

    public static SocialBoard sample(TravelClub club){
        //
        CommunityMember member = CommunityMember.sample();

        SocialBoard socialBoard = new SocialBoard(
                club.getId(),
                "Board for "+club.getName(),
                member.getEmail()
        );

        socialBoard.setCreateDate("2016.07.02");

        return socialBoard;
    }

}
