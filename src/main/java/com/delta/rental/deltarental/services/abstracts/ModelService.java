package com.delta.rental.deltarental.services.abstracts;

import com.delta.rental.deltarental.services.dtos.requests.model.AddModelRequest;
import com.delta.rental.deltarental.services.dtos.requests.model.UpdateModelRequest;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelListResponse;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelResponse;

import java.util.List;

public interface ModelService {
    GetModelResponse getById(int id);

    List<GetModelListResponse> getAll();
    void add (AddModelRequest addModelRequest);
    void update(UpdateModelRequest updateModelRequest);
    void delete(int id);
}
