package com.qqb.travelclub.service.sdo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TravelClubCdo implements Serializable {
    //
    @ApiModelProperty(notes = "The club name",required = true)
    private String name;
    @ApiModelProperty(notes = "The club intro",required = true)
    private String intro;
}
