package org.github.davidhua94.api;

import org.github.davidhua94.api.req.AddPetRequest;
import org.github.davidhua94.core.Api;
import org.github.davidhua94.core.CommonRepository;
import org.github.davidhua94.core.EmptyResponse;
import org.github.davidhua94.core.LambdaApi;
import org.github.davidhua94.entity.Pet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

/**
 * @author David Hua
 * @date 2021/4/8
 * @desc
 */
@Api("AddPet")
@Named("AddPetApi")
@ApplicationScoped
public class AddPetApi implements LambdaApi<AddPetRequest, EmptyResponse> {

    @Inject
    CommonRepository commonRepository;

    @Override
    public void execute(AddPetRequest request, EmptyResponse response) throws Exception {
        Pet pet = new Pet();
        pet.setId(UUID.randomUUID().toString().replace("-", ""));
        pet.setName(request.getName());
        pet.setAge(request.getAge());

        commonRepository.save(pet);
    }
}
