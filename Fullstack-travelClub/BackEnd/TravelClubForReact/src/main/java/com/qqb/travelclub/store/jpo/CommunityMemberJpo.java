/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.store.jpo;

import com.qqb.travelclub.aggregate.club.CommunityMember;
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
@Table(name = "community_member")
@Setter
@Getter
@NoArgsConstructor
public class CommunityMemberJpo extends Auditable {
    //
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private String nickName;
    private String birthDay;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "member")
    private List<MembershipJpo> membershipJpo;

//    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "member")
//    private MemberProfileJpo memberProfileJpo;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "member")
//    private List<MemberPostsJpo> postsJpos = new ArrayList<>();

    public CommunityMemberJpo(CommunityMember communityMember) {
        BeanUtils.copyProperties(communityMember, this);
    }

    public CommunityMember toDomain(){
        CommunityMember communityMember = new CommunityMember();
        BeanUtils.copyProperties(this, communityMember);
        communityMember.setId(String.valueOf(this.getId()));
        return communityMember;
    }

    public static List<CommunityMember> toDomains(List<CommunityMemberJpo> jpos){
        return jpos
                .stream()
                .map(CommunityMemberJpo::toDomain)
                .collect(Collectors.toList());
    }
}
