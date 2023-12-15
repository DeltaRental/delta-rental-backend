package com.delta.rental.deltarental.services.abstracts;

import com.delta.rental.deltarental.services.dtos.responses.model.GetModelResponse;

public interface ModelService {
    GetModelResponse getById(int id);
}
