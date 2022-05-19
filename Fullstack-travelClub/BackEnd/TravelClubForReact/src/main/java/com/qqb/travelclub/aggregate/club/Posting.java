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
public class Posting extends Entity {
    //
    private String title;
    private String writerEmail; //member email
    private String contents;
    private String writtenDate;
    private int readCount;

    private String boardId;

    public Posting(String id) {
        //
        super(id);
        this.readCount = 0;
    }

    public Posting(SocialBoard board, String title, String writerEmail, String contents) {
        //
        this.boardId = board.getId();
        this.title = title;
        this.writerEmail = writerEmail;
        this.contents = contents;
        this.writtenDate = DateUtil.today();
    }

    public Posting(String postingId, String boardId, String title, String writerEmail, String contents) {
        //
        this.boardId = boardId;
        this.title = title;
        this.writerEmail = writerEmail;
        this.contents = contents;
        this.writtenDate = DateUtil.today();
    }

    @Override
    public String toString() {
        //
        StringBuilder builder = new StringBuilder();

        builder.append("Posting id: "+getId());
        builder.append(", title: "+title);
        builder.append(", writer email: " + writerEmail);
        builder.append(", read count: "+readCount);
        builder.append(", written date: "+writtenDate);
        builder.append(", contents: "+contents);
        builder.append(", board id: "+boardId);

        return builder.toString();
    }

    public void modifyValues(NameValueList nameValues) {
        //
        for (NameValue nameValue : nameValues.getNameValues()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "boardId":
                    this.boardId = value;
                    break;
                case "contents":
                    this.contents = value;
                    break;
                case "title":
                    this.title = value;
                    break;
                case "writerEmail":
                    this.writerEmail = value;
                    break;
                case "writtenDate":
                    this.writtenDate = value;
                    break;
            }
        }
    }
}
