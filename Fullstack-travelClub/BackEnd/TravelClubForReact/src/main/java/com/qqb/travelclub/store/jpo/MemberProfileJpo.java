/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.store.jpo;

import com.qqb.travelclub.aggregate.club.vo.Gender;
import com.qqb.travelclub.auditable.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "member_profile")
@Getter
@Setter
public class MemberProfileJpo extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dateOfBirth;

    private String address1;
    private String address2;
    private String street;
    private String city;
    private String state;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private CommunityMemberJpo member;
}
