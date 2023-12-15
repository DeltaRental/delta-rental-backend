package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.Model;
import com.delta.rental.deltarental.repositories.ModelRepository;
import com.delta.rental.deltarental.services.abstracts.ModelService;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {

    private final ModelRepository modelRepository;
    private ModelMapperService modelMapperService;

    @Override
    public GetModelResponse getById(int id) {
        Model model = modelRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException(id + " nolu id' ye sahip model bulunmamaktadır.");
        });

        GetModelResponse modelResponse = modelMapperService.forResponse().map(model, GetModelResponse.class);
        return modelResponse;
    }
}
