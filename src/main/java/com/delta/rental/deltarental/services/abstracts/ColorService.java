package com.delta.rental.deltarental.services.abstracts;

import com.delta.rental.deltarental.services.dtos.requests.color.AddColorRequest;
import com.delta.rental.deltarental.services.dtos.requests.color.UpdateColorRequest;
import com.delta.rental.deltarental.services.dtos.responses.color.GetColorListResponse;
import com.delta.rental.deltarental.services.dtos.responses.color.GetColorResponse;

import java.util.List;

public interface ColorService {
    GetColorResponse getById(int id);

    List<GetColorListResponse> getAll();
    void add (AddColorRequest addColorRequest);
    void update(UpdateColorRequest updateColorRequest);
    void delete(int id);
}
