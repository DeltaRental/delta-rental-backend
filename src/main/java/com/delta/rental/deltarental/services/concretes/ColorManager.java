package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.Car;
import com.delta.rental.deltarental.entities.Color;
import com.delta.rental.deltarental.repositories.ColorRepository;
import com.delta.rental.deltarental.services.abstracts.ColorService;
import com.delta.rental.deltarental.services.dtos.requests.color.AddColorRequest;
import com.delta.rental.deltarental.services.dtos.requests.color.UpdateColorRequest;
import com.delta.rental.deltarental.services.dtos.responses.color.GetColorListResponse;
import com.delta.rental.deltarental.services.dtos.responses.color.GetColorResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ColorManager implements ColorService {
    ColorRepository colorRepository;
    ModelMapperService modelMapperService;

    @Override
    public GetColorResponse getById(int id) {
        Color color = colorRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException(id + " nolu id' ye sahip renk bulunmamaktadır.");
        });

        GetColorResponse colorResponse = modelMapperService.forResponse().map(color, GetColorResponse.class);
        return colorResponse;
    }

    @Override
    public List<GetColorListResponse> getAll() {
        List<Color> colorList = colorRepository.findAll();

        List<GetColorListResponse> colorResponse = colorList.stream()
                .map(color ->this.modelMapperService.forResponse()
                        .map(color, GetColorListResponse.class)).collect(Collectors.toList());
        return colorResponse;
    }

    @Override
    public void add(AddColorRequest addColorRequest) {
        if(colorRepository.existsByName(addColorRequest.getName().trim().toUpperCase().replaceAll("\\s", ""))){
            throw new RuntimeException("Bu renk zaten var!!");
        }

        Color color = this.modelMapperService.forRequest()
                .map(addColorRequest, Color.class);

        color.setName(color.getName().trim().toUpperCase().replaceAll("\\s", ""));

        colorRepository.save(color);

    }

    @Override
    public void update(UpdateColorRequest updateColorRequest) {

        if(!(colorRepository.existsById(updateColorRequest.getId()))){
            throw new RuntimeException(updateColorRequest.getId()+" nolu id'ye sahip renk bulunmamaktadır.");
        }

        //Kullanıcının güncellemek istediği rengin adını , DB 'de aynı plakaya sahip başka bir araç var mı durumunun kontrolünü sağlayan kod
        Optional<Color> existingColorOptional = colorRepository.findById(updateColorRequest.getId());
        Color existingColor = existingColorOptional.get();
        String newName = updateColorRequest.getName().trim().toUpperCase().replaceAll("\s", "");

        //Eğer DB de girilen palakaya sahip başka bir color ismi var ise bu hata oluşur.Ancak yok ise güncellenir(kendi renge dahil).

        if (!existingColor.getName().equals(newName) && colorRepository.existsByName(newName)) {
            throw new RuntimeException("bu renge sahip zaten bir renk var!!");
        }

        //Model Mapper işlemi
        Color color = this.modelMapperService.forRequest()
                .map(updateColorRequest, Color.class);

        color.setName(color.getName().trim().toUpperCase().replaceAll("\\s", ""));

        colorRepository.save(color);


    }

    @Override
    public void delete(int id) {
        colorRepository.deleteById(id);

    }
}
