package org.github.davidhua94.api.resp;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.github.davidhua94.core.Response;

import java.util.StringJoiner;

/**
 * @author David Hua
 * @date 2021/4/13
 * @desc
 */
@RegisterForReflection
public class GetFirmwareInfoResponse implements Response {
    private String firmwareId;

    private String firmwareVersion;

    private String firmwareLocation;

    public String getFirmwareId() {
        return firmwareId;
    }

    public void setFirmwareId(String firmwareId) {
        this.firmwareId = firmwareId;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getFirmwareLocation() {
        return firmwareLocation;
    }

    public void setFirmwareLocation(String firmwareLocation) {
        this.firmwareLocation = firmwareLocation;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GetFirmwareInfoResponse.class.getSimpleName() + "[", "]")
                .add("firmwareId='" + firmwareId + "'")
                .add("firmwareVersion='" + firmwareVersion + "'")
                .add("firmwareLocation='" + firmwareLocation + "'")
                .toString();
    }
}
