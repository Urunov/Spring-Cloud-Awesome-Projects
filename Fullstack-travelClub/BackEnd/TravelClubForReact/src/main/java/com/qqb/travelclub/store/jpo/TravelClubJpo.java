/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.store.jpo;

import com.qqb.travelclub.aggregate.club.TravelClub;
import com.qqb.travelclub.auditable.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "club")
@Getter
@Setter
@NoArgsConstructor
public class TravelClubJpo extends Auditable {
    //
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String intro;
    private long  foundationTime;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "club")
    private List<MembershipJpo> membershipJpo;

    public TravelClubJpo(TravelClub club) {
        BeanUtils.copyProperties(club,this);
    }

    public TravelClub toDomain(){
        TravelClub club = new TravelClub();
        BeanUtils.copyProperties(this, club);
        club.setId(String.valueOf(this.getId()));
        return club;
    }

    public static List<TravelClub> toDomains(List<TravelClubJpo> jpos){
        return jpos
                .stream()
                .map(TravelClubJpo::toDomain)
                .collect(Collectors.toList());
    }
}
