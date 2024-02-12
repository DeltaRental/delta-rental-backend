package com.delta.rental.deltarental.services.rules;

import com.delta.rental.deltarental.entities.concretes.Brand;
import com.delta.rental.deltarental.repositories.BrandRepository;
import com.delta.rental.deltarental.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    private final BrandRepository brandRepository;

    //DB içerisinde aynı Brand id' ye sahip marka olup olmama durumu kontrolü
    public Brand checkByBrandId(int id){
        if(!(brandRepository.existsById(id))){
            throw new RuntimeException(id+ Messages.BrandMessages.BRAND_NOT_FOUND);
        }
        return brandRepository.findById(id).orElseThrow();
    }

    //DB içerisinde aynı marka adına sahip markaların var olup olmama kontrolü
    public void checkByBrandName(String name){
        if(brandRepository.existsByName(name.trim().toUpperCase().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT))){
            throw new RuntimeException(Messages.BrandMessages.SAME_BRAND_NAME_EXISTS);
        }
    }

    //Kullanıcının güncellemek istediği markanın adını , DB 'de aynı marka ismine sahip başka bir marka var mı durumunun kontrolünü sağlayan kod
    public void checkByBrandNameWhenUpdate(int id,String name){
        Optional<Brand> existingBrandOptional = brandRepository.findById(id);
        Brand existingBrand = existingBrandOptional.get();
        String newName = name.trim().toUpperCase().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT);

        //Eğer DB de girilen markaya sahip başka bir marka ismi var ise bu hata oluşur.Ancak yok ise güncellenir(kendi marka ismi dahil).
        if (!existingBrand.getName().equals(newName) && brandRepository.existsByName(newName)) {
            throw new RuntimeException(Messages.BrandMessages.SAME_BRAND_NAME_EXISTS);
        }
    }
}
