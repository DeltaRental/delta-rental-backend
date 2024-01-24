package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.repositories.CityRepository;
import com.delta.rental.deltarental.services.abstracts.CityService;
import com.delta.rental.deltarental.services.dtos.requests.city.AddCityRequest;
import com.delta.rental.deltarental.services.dtos.requests.city.UpdateCityRequest;
import com.delta.rental.deltarental.services.dtos.responses.city.GetCityListResponse;
import com.delta.rental.deltarental.services.dtos.responses.city.GetCityResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CityManager implements CityService {
    private final CityRepository cityRepository;
    private ModelMapperService modelMapperService;
    @Override
    public GetCityResponse getById(int id) {
        return null;
    }

    @Override
    public List<GetCityListResponse> getAll() {
        return null;
    }

    @Override
    public void add(AddCityRequest addCityRequest) {

    }

    @Override
    public void update(UpdateCityRequest updateCityRequest) {

    }

    @Override
    public void delete(int id) {

    }
}
