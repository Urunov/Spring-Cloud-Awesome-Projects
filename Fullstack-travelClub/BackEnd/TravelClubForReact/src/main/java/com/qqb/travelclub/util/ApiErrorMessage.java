/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorMessage {
    private String code;
    private String name;
    private String message;
}
