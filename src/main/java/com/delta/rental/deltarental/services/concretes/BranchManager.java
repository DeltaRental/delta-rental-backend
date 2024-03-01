package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.concretes.Branch;
import com.delta.rental.deltarental.repositories.BranchRepository;
import com.delta.rental.deltarental.services.abstracts.BranchService;
import com.delta.rental.deltarental.services.constants.Messages;
import com.delta.rental.deltarental.services.dtos.requests.branch.AddBranchRequest;
import com.delta.rental.deltarental.services.dtos.requests.branch.UpdateBranchRequest;
import com.delta.rental.deltarental.services.dtos.responses.branch.GetBranchListResponse;
import com.delta.rental.deltarental.services.dtos.responses.branch.GetBranchResponse;
import com.delta.rental.deltarental.services.rules.BranchBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BranchManager implements BranchService {
    private final BranchBusinessRules branchBusinessRules;
    private final BranchRepository branchRepository;
    private ModelMapperService modelMapperService;
    @Override
    public GetBranchResponse getById(int id) {
        Branch branch = branchBusinessRules.checkByBranchId(id);
        GetBranchResponse branchResponse = modelMapperService.forResponse().map(branch, GetBranchResponse.class);
        return branchResponse;
    }

    @Override
    public List<GetBranchListResponse> getAll() {
        List<Branch> branchList = branchRepository.findAll();

        List<GetBranchListResponse> branchResponse = branchList.stream()
                .map(branch ->this.modelMapperService.forResponse()
                        .map(branch, GetBranchListResponse.class)).collect(Collectors.toList());
        return branchResponse;
    }

    @Override
    public void add(AddBranchRequest addBranchRequest) {
        branchBusinessRules.checkByBranchName(addBranchRequest.getName());

        Branch branch = this.modelMapperService.forRequest()
                .map(addBranchRequest, Branch.class);

        branch.setName(branch.getName().trim().toUpperCase().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT));

        branchRepository.save(branch);

    }

    @Override
    public void update(UpdateBranchRequest updateBranchRequest) {
        branchBusinessRules.checkByBranchId(updateBranchRequest.getId());
        branchBusinessRules.checkByBranchNameWhenUpdate(updateBranchRequest.getId(), updateBranchRequest.getName());

        //Model Mapper işlemi
        Branch branch = this.modelMapperService.forRequest()
                .map(updateBranchRequest, Branch.class);

        branch.setName(branch.getName().trim().toUpperCase().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT));

        branchRepository.save(branch);

    }

    @Override
    public void delete(int id) {
        branchBusinessRules.checkByBranchId(id);
        branchRepository.deleteById(id);

    }
}
