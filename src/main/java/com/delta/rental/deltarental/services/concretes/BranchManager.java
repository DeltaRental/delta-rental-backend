package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.repositories.BranchRepository;
import com.delta.rental.deltarental.services.abstracts.BranchService;
import com.delta.rental.deltarental.services.dtos.requests.branch.AddBranchRequest;
import com.delta.rental.deltarental.services.dtos.requests.branch.UpdateBranchRequest;
import com.delta.rental.deltarental.services.dtos.responses.branch.GetBranchListResponse;
import com.delta.rental.deltarental.services.dtos.responses.branch.GetBranchResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BranchManager implements BranchService {
    private final BranchRepository branchRepository;
    private ModelMapperService modelMapperService;
    @Override
    public GetBranchResponse getById(int id) {
        return null;
    }

    @Override
    public List<GetBranchListResponse> getAll() {
        return null;
    }

    @Override
    public void add(AddBranchRequest addBranchRequest) {

    }

    @Override
    public void update(UpdateBranchRequest updateBranchRequest) {

    }

    @Override
    public void delete(int id) {

    }
}
