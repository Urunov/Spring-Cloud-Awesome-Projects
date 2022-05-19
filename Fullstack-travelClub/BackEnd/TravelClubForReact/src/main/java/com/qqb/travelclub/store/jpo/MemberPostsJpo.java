/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.store.jpo;

import com.qqb.travelclub.auditable.Auditable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "member_posts")
@Getter
@Setter
public class MemberPostsJpo extends Auditable {
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String writerEmail;
    private String contents;
    private Date writtenDate;
    private int readCount;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name="member_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private CommunityMemberJpo member;
}
