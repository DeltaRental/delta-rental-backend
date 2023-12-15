package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.Brand;
import com.delta.rental.deltarental.entities.Car;
import com.delta.rental.deltarental.entities.Color;
import com.delta.rental.deltarental.repositories.BrandRepository;
import com.delta.rental.deltarental.services.abstracts.BrandService;
import com.delta.rental.deltarental.services.dtos.requests.brand.AddBrandRequest;
import com.delta.rental.deltarental.services.dtos.requests.brand.UpdateBrandRequest;
import com.delta.rental.deltarental.services.dtos.responses.brand.GetBrandListResponse;
import com.delta.rental.deltarental.services.dtos.responses.brand.GetBrandResponse;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarListResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;
    private ModelMapperService modelMapperService;


    @Override
    public GetBrandResponse getById(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow();
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
        if(brandRepository.existsByName(addBrandRequest.getName().trim().toUpperCase().replaceAll("\\s", ""))){
            throw new RuntimeException("Bu araba markası zaten var!!");
        }

        Brand brand = this.modelMapperService.forRequest()
                .map(addBrandRequest, Brand.class);

        brand.setName(brand.getName().trim().toUpperCase().replaceAll("\\s", ""));

        brandRepository.save(brand);

    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {

        if(!(brandRepository.existsById(updateBrandRequest.getId()))){
            throw new RuntimeException(updateBrandRequest.getId()+" nolu id'ye sahip bir marka bulunmamaktadır.");
        }

        //Kullanıcının güncellemek istediği markanın adını , DB 'de aynı marka ismine sahip başka bir marka var mı durumunun kontrolünü sağlayan kod
        Optional<Brand> existingBrandOptional = brandRepository.findById(updateBrandRequest.getId());
        Brand existingBrand = existingBrandOptional.get();
        String newName = updateBrandRequest.getName().trim().toUpperCase().replaceAll("\s", "");

        //Eğer DB de girilen palakaya sahip başka bir marka ismi var ise bu hata oluşur.Ancak yok ise güncellenir(kendi marka ismi dahil).

        if (!existingBrand.getName().equals(newName) && brandRepository.existsByName(newName)) {
            throw new RuntimeException("bu marka ismi zaten var !!");
        }

        //Model Mapper işlemi
        Brand brand = this.modelMapperService.forRequest()
                .map(updateBrandRequest, Brand.class);

        brand.setName(brand.getName().trim().toUpperCase().replaceAll("\\s", ""));

        brandRepository.save(brand);

    }

    @Override
    public void delete(int id) {
        brandRepository.deleteById(id);

    }
}
