package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.concretes.Brand;
import com.delta.rental.deltarental.repositories.BrandRepository;
import com.delta.rental.deltarental.services.abstracts.BrandService;
import com.delta.rental.deltarental.services.constants.Messages;
import com.delta.rental.deltarental.services.dtos.requests.brand.AddBrandRequest;
import com.delta.rental.deltarental.services.dtos.requests.brand.UpdateBrandRequest;
import com.delta.rental.deltarental.services.dtos.responses.brand.GetBrandListResponse;
import com.delta.rental.deltarental.services.dtos.responses.brand.GetBrandResponse;
import com.delta.rental.deltarental.services.rules.BrandBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;
    private ModelMapperService modelMapperService;
    private final BrandBusinessRules brandBusinessRules;


    @Override
    public GetBrandResponse getById(int id) {
        Brand brand = brandBusinessRules.checkByBrandId(id);
        GetBrandResponse brandResponse = modelMapperService.forResponse().map(brand, GetBrandResponse.class);
        return brandResponse;
    }

    @Override
    public List<GetBrandListResponse> getAll() {
        List<Brand> brandList = brandRepository.findAll();

        List<GetBrandListResponse> brandResponse = brandList.stream()
                .map(brand ->this.modelMapperService.forResponse()
                        .map(brand, GetBrandListResponse.class)).collect(Collectors.toList());
        return brandResponse;
    }

    @Override
    public void add(AddBrandRequest addBrandRequest) {
        brandBusinessRules.checkByBrandName(addBrandRequest.getName());

        Brand brand = this.modelMapperService.forRequest()
                .map(addBrandRequest, Brand.class);

        brand.setName(brand.getName().trim().toUpperCase().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT));

        brandRepository.save(brand);

    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {
        brandBusinessRules.checkByBrandId(updateBrandRequest.getId());
        brandBusinessRules.checkByBrandNameWhenUpdate(updateBrandRequest.getId(), updateBrandRequest.getName());

        //Model Mapper işlemi
        Brand brand = this.modelMapperService.forRequest()
                .map(updateBrandRequest, Brand.class);

        brand.setName(brand.getName().trim().toUpperCase().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT));

        brandRepository.save(brand);

    }

    @Override
    public void delete(int id) {
        brandBusinessRules.checkByBrandId(id);
        brandRepository.deleteById(id);

    }
}
