package org.github.davidhua94.api;

import org.github.davidhua94.api.req.GetFirmwareInfoRequest;
import org.github.davidhua94.api.resp.GetFirmwareInfoResponse;
import org.github.davidhua94.core.Api;
import org.github.davidhua94.core.CommonRepository;
import org.github.davidhua94.core.LambdaApi;
import org.github.davidhua94.entity.FirmwareInfo;
import org.github.davidhua94.exception.ApiException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author David Hua
 * @date 2021/4/8
 * @desc
 */
@Api("GetFirmwareInfo")
@Named("GetFirmwareInfoApi")
@ApplicationScoped
public class GetFirmwareInfoApi implements LambdaApi<GetFirmwareInfoRequest, GetFirmwareInfoResponse> {

    @Inject
    CommonRepository commonRepository;

    @Override
    public void execute(GetFirmwareInfoRequest req, GetFirmwareInfoResponse resp) throws Exception {
        FirmwareInfo exist = commonRepository.get(req.getFirmwareId(), FirmwareInfo.class);
        if (exist == null) {
            throw new ApiException("Firmware not exist");
        }

        resp.setFirmwareId(req.getFirmwareId());
        resp.setFirmwareVersion(exist.getFirmwareVersion());
        resp.setFirmwareLocation(exist.getFirmwareLocation());
    }
}
