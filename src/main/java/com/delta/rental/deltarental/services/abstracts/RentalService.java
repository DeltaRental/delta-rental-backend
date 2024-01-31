package com.delta.rental.deltarental.services.abstracts;

import com.delta.rental.deltarental.services.dtos.requests.car.UpdateCarRequest;
import com.delta.rental.deltarental.services.dtos.requests.model.AddModelRequest;
import com.delta.rental.deltarental.services.dtos.requests.model.UpdateModelRequest;
import com.delta.rental.deltarental.services.dtos.requests.rental.AddRentalRequest;
import com.delta.rental.deltarental.services.dtos.requests.rental.UpdateRentalRequest;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelListResponse;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelResponse;
import com.delta.rental.deltarental.services.dtos.responses.rental.GetRentalListResponse;
import com.delta.rental.deltarental.services.dtos.responses.rental.GetRentalResponse;

import java.util.List;

public interface RentalService {
    GetRentalResponse getById(int id);
    List<GetRentalListResponse> getAll();
    void add (AddRentalRequest addRentalRequest);
    void update(UpdateRentalRequest updateRentalRequest);
    void delete(int id);

    //void carReturn(int carId, UpdateRentalRequest updateRentalRequest,AddRentalRequest addRentalRequest);


}
