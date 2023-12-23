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
import com.delta.rental.deltarental.services.rules.ColorBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ColorManager implements ColorService {
    private final ColorRepository colorRepository;
    private ModelMapperService modelMapperService;
    private ColorBusinessRules colorBusinessRules;

    @Override
    public GetColorResponse getById(int id) {
        Color color = colorBusinessRules.checkByColorId(id);
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
        colorBusinessRules.checkByColorName(addColorRequest.getName());

        Color color = this.modelMapperService.forRequest()
                .map(addColorRequest, Color.class);

        color.setName(color.getName().trim().toUpperCase().replaceAll("\\s", ""));

        colorRepository.save(color);

    }

    @Override
    public void update(UpdateColorRequest updateColorRequest) {
        colorBusinessRules.checkByColorId(updateColorRequest.getId());
        colorBusinessRules.checkByColorNameWhenUpdate(updateColorRequest.getId(), updateColorRequest.getName());

        //Model Mapper işlemi
        Color color = this.modelMapperService.forRequest()
                .map(updateColorRequest, Color.class);

        color.setName(color.getName().trim().toUpperCase().replaceAll("\\s", ""));

        colorRepository.save(color);


    }

    @Override
    public void delete(int id) {
        colorBusinessRules.checkByColorId(id);
        colorRepository.deleteById(id);

    }
}
