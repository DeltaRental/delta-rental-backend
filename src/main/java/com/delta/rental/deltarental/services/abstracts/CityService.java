package com.delta.rental.deltarental.services.abstracts;

import com.delta.rental.deltarental.services.dtos.requests.branch.AddBranchRequest;
import com.delta.rental.deltarental.services.dtos.requests.branch.UpdateBranchRequest;
import com.delta.rental.deltarental.services.dtos.requests.city.AddCityRequest;
import com.delta.rental.deltarental.services.dtos.requests.city.UpdateCityRequest;
import com.delta.rental.deltarental.services.dtos.responses.branch.GetBranchListResponse;
import com.delta.rental.deltarental.services.dtos.responses.branch.GetBranchResponse;
import com.delta.rental.deltarental.services.dtos.responses.city.GetCityListResponse;
import com.delta.rental.deltarental.services.dtos.responses.city.GetCityResponse;

import java.util.List;

public interface CityService {

    GetCityResponse getById(int id);

    List<GetCityListResponse> getAll();

    void add(AddCityRequest addCityRequest);

    void update(UpdateCityRequest updateCityRequest);

    void delete(int id);
}
