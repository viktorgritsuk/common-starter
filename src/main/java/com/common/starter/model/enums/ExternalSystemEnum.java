package com.common.starter.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum of external systems.
 */
@Getter
@RequiredArgsConstructor
public enum ExternalSystemEnum implements ExternalSystem {

    CORE("Core"),
    BPM("BPM"),
    SWITCH("Switch"),
    DATABASE("Database");

    private final String systemName;

}
