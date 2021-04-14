package org.github.davidhua94.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.StringJoiner;

/**
 * @author David Hua
 * @date 2021/4/13
 * @desc
 */
@Entity
@Table(name = "ADM_FIRMWARE")
public class FirmwareInfo {

    @Id
    @Column(name = "FIRMWARE_ID")
    private String firmwareId;

    @Column(name = "FIRMWARE_VERSION")
    private String firmwareVersion;

    @Column(name = "FIRMWARE_LOCATION")
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
        return new StringJoiner(", ", FirmwareInfo.class.getSimpleName() + "[", "]")
                .add("firmwareId='" + firmwareId + "'")
                .add("firmwareVersion='" + firmwareVersion + "'")
                .add("firmwareLocation='" + firmwareLocation + "'")
                .toString();
    }
}
