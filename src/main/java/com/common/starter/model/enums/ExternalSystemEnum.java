package com.common.starter.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum of external systems.
 */
@RequiredArgsConstructor
@Getter
public enum ExternalSystemEnum implements ExternalSystem {

    CORE("Core"),
    BPM("BPM"),
    SWITCH("Switch");

    private final String systemName;

}
