package com.delta.rental.deltarental.services.abstracts;

import com.delta.rental.deltarental.services.dtos.responses.color.GetColorResponse;

public interface ColorService {
    GetColorResponse getById(int id);
}
