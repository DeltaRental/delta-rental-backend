package com.delta.rental.deltarental.services.rules;

import com.delta.rental.deltarental.entities.concretes.Color;
import com.delta.rental.deltarental.repositories.ColorRepository;
import com.delta.rental.deltarental.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ColorBusinessRules {
    private final ColorRepository colorRepository;

    //DB içerisinde aynı Color id' ye sahip color olup olmama durumu kontrolü
    public Color checkByColorId(int id){
        if(!(colorRepository.existsById(id))){
            throw new RuntimeException(id + Messages.ColorMessages.COLOR_NOT_FOUND);
        }
        return colorRepository.findById(id).orElseThrow();
    }

    //DB içerisinde aynı renk adına sahip renklerin var olup olmama kontrolü
    public void checkByColorName(String name){
        if(colorRepository.existsByName(name.trim().toUpperCase().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT))){
            throw new RuntimeException(Messages.ColorMessages.SAME_COLOR_EXISTS);
        }
    }

    //Kullanıcının güncellemek istediği rengin adını , DB 'de aynı renk adına sahip başka bir renk var mı durumunun kontrolünü sağlayan kod
    public void checkByColorNameWhenUpdate(int id,String name){
        Optional<Color> existingColorOptional = colorRepository.findById(id);
        Color existingColor = existingColorOptional.get();
        String newName = name.trim().toUpperCase().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT);

        //Eğer DB de girilen renk ismine sahip başka bir renk ismi var ise bu hata oluşur.Ancak yok ise güncellenir(kendi rengi dahil).
        if (!existingColor.getName().equals(newName) && colorRepository.existsByName(newName)) {
            throw new RuntimeException(Messages.ColorMessages.SAME_COLOR_EXISTS);
        }
    }
}
