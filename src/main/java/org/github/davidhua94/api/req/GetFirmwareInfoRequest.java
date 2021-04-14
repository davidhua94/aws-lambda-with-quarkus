package org.github.davidhua94.api.req;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.github.davidhua94.core.Request;

import java.util.StringJoiner;

/**
 * @author David Hua
 * @date 2021/4/8
 * @desc
 */
@RegisterForReflection
public class GetFirmwareInfoRequest implements Request {
    private String firmwareId;

    public String getFirmwareId() {
        return firmwareId;
    }

    public void setFirmwareId(String firmwareId) {
        this.firmwareId = firmwareId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GetFirmwareInfoRequest.class.getSimpleName() + "[", "]")
                .add("firmwareId='" + firmwareId + "'")
                .toString();
    }
}
