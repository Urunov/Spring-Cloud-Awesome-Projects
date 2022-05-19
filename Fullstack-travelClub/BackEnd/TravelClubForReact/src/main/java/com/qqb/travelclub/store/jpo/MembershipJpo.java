/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.store.jpo;

import com.qqb.travelclub.aggregate.club.CommunityMember;
import com.qqb.travelclub.aggregate.club.Membership;
import com.qqb.travelclub.aggregate.club.TravelClub;
import com.qqb.travelclub.aggregate.club.vo.RoleInClub;
import com.qqb.travelclub.auditable.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "membership")
@Getter
@Setter
@NoArgsConstructor
public class MembershipJpo extends Auditable {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    private RoleInClub role;
    @Enumerated(EnumType.STRING)
    private RoleInClub role_name;
    private String joinDate;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "club_id", nullable = false)
    private TravelClubJpo club;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private CommunityMemberJpo member;

    public MembershipJpo(Membership membership) {
        BeanUtils.copyProperties(membership, this);
        TravelClub club = membership.getClub();
        CommunityMember member = membership.getMember();
        TravelClubJpo travelClubJpo = new TravelClubJpo(club);
        travelClubJpo.setId(Long.valueOf(club.getId()));
        this.setClub(travelClubJpo);
        CommunityMemberJpo communityMemberJpo = new CommunityMemberJpo(member);
        communityMemberJpo.setId(Long.valueOf(member.getId()));
        this.setMember(communityMemberJpo);
    }

    public Membership toDomain(){
        Membership membership = new Membership();
        BeanUtils.copyProperties(this, membership);
        membership.setId(String.valueOf(this.getId()));
        membership.setClub(club.toDomain());
        membership.setMember(member.toDomain());
        return membership;
    }

    public static List<Membership> toDomains(List<MembershipJpo> jpos){
        return jpos
                .stream()
                .map(MembershipJpo::toDomain)
                .collect(Collectors.toList());
    }
}
