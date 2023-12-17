package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.Model;
import com.delta.rental.deltarental.repositories.ModelRepository;
import com.delta.rental.deltarental.services.abstracts.BrandService;
import com.delta.rental.deltarental.services.abstracts.ModelService;
import com.delta.rental.deltarental.services.dtos.requests.model.AddModelRequest;
import com.delta.rental.deltarental.services.dtos.requests.model.UpdateModelRequest;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelListResponse;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {

    private final ModelRepository modelRepository;
    private final BrandService brandService;
    private ModelMapperService modelMapperService;


    @Override
    public GetModelResponse getById(int id) {
        Model model = modelRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException(id + " nolu id' ye sahip model bulunmamaktadır.");
        });

        GetModelResponse modelResponse = modelMapperService.forResponse().map(model, GetModelResponse.class);
        return modelResponse;
    }

    @Override
    public List<GetModelListResponse> getAll() {
        List<Model> modelList = modelRepository.findAll();

        List<GetModelListResponse> modelResponse = modelList.stream()
                .map(model ->this.modelMapperService.forResponse()
                        .map(model, GetModelListResponse.class)).collect(Collectors.toList());
        return modelResponse;
    }

    @Override
    public void add(AddModelRequest addModelRequest) {
        if(modelRepository.existsByName(addModelRequest.getName().trim().toUpperCase().replaceAll("\\s", ""))){
            throw new RuntimeException("Bu model adı zaten var!!");
        }

        //Ekleme yaparken brand id' nin db' de var olup olamama durumu kontrolü.
        //id kontrolü BrandService'de sağlanıyor.
        brandService.getById(addModelRequest.getBrandId());


        Model model = this.modelMapperService.forRequest()
                .map(addModelRequest, Model.class);

        model.setName(model.getName().trim().toUpperCase().replaceAll("\\s", ""));

        modelRepository.save(model);

    }

    @Override
    public void update(UpdateModelRequest updateModelRequest) {

        if(!(modelRepository.existsById(updateModelRequest.getId()))){
            throw new RuntimeException(updateModelRequest.getId()+" nolu id'ye sahip bir model bulunmamaktadır.");
        }

        //Güncelleme yaparken brand id' nin db' de var olup olamama durumu kontrolü.
        brandService.getById(updateModelRequest.getBrandId());

        //Kullanıcının güncellemek istediği model adını , DB 'de aynı model ismine sahip başka bir model var mı durumunun kontrolünü sağlayan kod
        Optional<Model> existingModelOptional = modelRepository.findById(updateModelRequest.getId());
        Model existingModel = existingModelOptional.get();
        String newName = updateModelRequest.getName().trim().toUpperCase().replaceAll("\s", "");

        //Eğer DB de girilen model ismine sahip başka bir model ismi var ise bu hata oluşur.Ancak yok ise güncellenir(kendi model ismi dahil).

        if (!existingModel.getName().equals(newName) && modelRepository.existsByName(newName)) {
            throw new RuntimeException("bu model ismi zaten var !!");
        }

        //Model Mapper işlemi
        Model model = this.modelMapperService.forRequest()
                .map(updateModelRequest, Model.class);


        model.setName(model.getName().trim().toUpperCase().replaceAll("\\s", ""));

        modelRepository.save(model);

    }
    @Override
    public void delete(int id) {
        modelRepository.deleteById(id);
    }


}
