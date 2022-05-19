/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.auditable;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class LoggedUserAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(System.getProperty("user.name"));
    }
}
