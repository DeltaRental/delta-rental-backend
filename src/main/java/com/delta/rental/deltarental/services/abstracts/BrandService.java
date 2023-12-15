package com.delta.rental.deltarental.services.abstracts;

import com.delta.rental.deltarental.services.dtos.requests.brand.AddBrandRequest;
import com.delta.rental.deltarental.services.dtos.requests.brand.UpdateBrandRequest;
import com.delta.rental.deltarental.services.dtos.requests.color.UpdateColorRequest;
import com.delta.rental.deltarental.services.dtos.responses.brand.GetBrandListResponse;
import com.delta.rental.deltarental.services.dtos.responses.brand.GetBrandResponse;

import java.util.List;

public interface BrandService {

    GetBrandResponse getById(int id);
    List<GetBrandListResponse> getAll();
    void add (AddBrandRequest addBrandRequest);
    void update(UpdateBrandRequest updateBrandRequest);
    void delete(int id);
}
