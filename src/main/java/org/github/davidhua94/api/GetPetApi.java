package org.github.davidhua94.api;

import org.github.davidhua94.api.req.AddPetRequest;
import org.github.davidhua94.api.req.GetPetRequest;
import org.github.davidhua94.api.resp.GetPetResponse;
import org.github.davidhua94.core.Api;
import org.github.davidhua94.core.CommonRepository;
import org.github.davidhua94.core.EmptyResponse;
import org.github.davidhua94.core.LambdaApi;
import org.github.davidhua94.entity.Pet;
import org.github.davidhua94.exception.ApiException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

/**
 * @author David Hua
 * @date 2021/4/8
 * @desc
 */
@Api("GetPet")
@Named("GetPetApi")
@ApplicationScoped
public class GetPetApi implements LambdaApi<GetPetRequest, GetPetResponse> {

    @Inject
    CommonRepository commonRepository;

    @Override
    public void execute(GetPetRequest request, GetPetResponse response) throws Exception {
        Pet pet = commonRepository.get(request.getId(), Pet.class);
        if (pet == null) {
            throw new ApiException("Pet not exist");
        }

        response.setId(pet.getId());
        response.setName(pet.getName());
        response.setAge(pet.getAge());
    }
}
