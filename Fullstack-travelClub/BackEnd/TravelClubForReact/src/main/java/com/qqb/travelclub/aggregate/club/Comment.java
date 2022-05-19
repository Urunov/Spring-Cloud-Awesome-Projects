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
public class Comment extends Entity {
    //
    private String writer;
    private String contents;
    private String writtenDate;
    private String postingId;

    public Comment(String id) {
        super(id);
    }

    public Comment(String postingId,String writer, String contents) {
        this.writer = writer;
        this.contents = contents;
        this.postingId = postingId;
        this.writtenDate = DateUtil.today();
    }

    @Override
    public String toString() {
        //
        StringBuilder builder = new StringBuilder();

        builder.append("Comment id: ").append(this.getId());
        builder.append(", posting id: ").append(postingId);
        builder.append(", writer: ").append(writer);
        builder.append(", written date: ").append(writtenDate);

        return builder.toString();
    }


    public void modifyValues(NameValueList nameValues) {
        //
        for (NameValue nameValue : nameValues.getNameValues()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "postingId":
                    this.postingId = value;
                    break;
                case "writer":
                    this.writer = value;
                    break;
                case "contents":
                    this.contents = value;
                    break;
            }
        }
    }
}
