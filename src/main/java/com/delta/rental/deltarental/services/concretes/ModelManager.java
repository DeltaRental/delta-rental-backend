package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.concretes.Model;
import com.delta.rental.deltarental.repositories.ModelRepository;
import com.delta.rental.deltarental.services.abstracts.ModelService;
import com.delta.rental.deltarental.services.constants.Messages;
import com.delta.rental.deltarental.services.dtos.requests.model.AddModelRequest;
import com.delta.rental.deltarental.services.dtos.requests.model.UpdateModelRequest;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelListResponse;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelResponse;
import com.delta.rental.deltarental.services.rules.ModelBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {
    private final ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private final ModelBusinessRules modelBusinessRules;

    @Override
    public GetModelResponse getById(int id) {
        Model model = modelBusinessRules.checkByModelId(id);
        GetModelResponse modelResponse = modelMapperService.forResponse().map(model, GetModelResponse.class);
        return modelResponse;
    }

    @Override
    public List<GetModelListResponse> getAll() {
        List<Model> modelList = modelRepository.findAll();

        List<GetModelListResponse> modelResponse = modelList.stream()
                .map(model ->this.modelMapperService.forResponse()
                        .map(model, GetModelListResponse.class)).collect(Collectors.toList());
        return modelResponse;
    }

    @Override
    public void add(AddModelRequest addModelRequest) {
        modelBusinessRules.checkByModelName(addModelRequest.getName());
        modelBusinessRules.checkByBrandId(addModelRequest.getBrandId());

        Model model = this.modelMapperService.forRequest()
                .map(addModelRequest, Model.class);

        model.setName(model.getName().trim().toUpperCase().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT));

        modelRepository.save(model);

    }

    @Override
    public void update(UpdateModelRequest updateModelRequest) {
        modelBusinessRules.checkByModelId(updateModelRequest.getId());
        modelBusinessRules.checkByBrandId(updateModelRequest.getBrandId());
        modelBusinessRules.checkByModelNameWhenUpdate(updateModelRequest.getId(),updateModelRequest.getName());

        //Model Mapper işlemi
        Model model = this.modelMapperService.forRequest()
                .map(updateModelRequest, Model.class);

        model.setName(model.getName().trim().toUpperCase().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT));

        modelRepository.save(model);

    }
    @Override
    public void delete(int id) {
        modelBusinessRules.checkByModelId(id);
        modelRepository.deleteById(id);
    }


}
