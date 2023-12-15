package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.Color;
import com.delta.rental.deltarental.repositories.ColorRepository;
import com.delta.rental.deltarental.services.abstracts.ColorService;
import com.delta.rental.deltarental.services.dtos.responses.color.GetColorResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
