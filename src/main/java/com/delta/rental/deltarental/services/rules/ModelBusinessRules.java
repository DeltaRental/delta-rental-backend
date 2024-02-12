package com.delta.rental.deltarental.services.rules;

import com.delta.rental.deltarental.entities.concretes.Model;
import com.delta.rental.deltarental.repositories.ModelRepository;
import com.delta.rental.deltarental.services.abstracts.BrandService;
import com.delta.rental.deltarental.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    private final ModelRepository modelRepository;
    private final BrandService brandService;

    //DB içerisinde  id' ye göre model olup olmama durumu kontrolü
    public Model checkByModelId(int id){
        if(!(modelRepository.existsById(id))){
            throw new RuntimeException(id + Messages.ModelMessages.MODEL_NOT_FOUND);
        }
        return modelRepository.findById(id).orElseThrow();
    }

    //DB içerisinde aynı model adına sahip modellerin var olup olmama kontrolü
    public void checkByModelName(String name){
        if(modelRepository.existsByName(name.trim().toUpperCase().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT))){
            throw new RuntimeException(Messages.ModelMessages.SAME_MODEL_NAME_EXISTS);
        }
    }

    //Ekleme yaparken brand id' nin db' de var olup olamama durumu kontrolü.
    //id kontrolü BrandService'de sağlanıyor.
    public void checkByBrandId(int id){
        brandService.getById(id);
    }

    //Kullanıcının güncellemek istediği model adını , DB 'de aynı model ismine sahip başka bir model var mı durumunun kontrolünü sağlayan kod
    public void checkByModelNameWhenUpdate(int id, String name){
        Optional<Model> existingModelOptional = modelRepository.findById(id);
        Model existingModel = existingModelOptional.get();
        String newName = name.trim().toUpperCase().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT);

        //Eğer DB de girilen model ismine sahip başka bir model ismi var ise bu hata oluşur.Ancak yok ise güncellenir(kendi model ismi dahil).

        if (!existingModel.getName().equals(newName) && modelRepository.existsByName(newName)) {
            throw new RuntimeException(Messages.ModelMessages.SAME_MODEL_NAME_EXISTS);
        }

    }
}
