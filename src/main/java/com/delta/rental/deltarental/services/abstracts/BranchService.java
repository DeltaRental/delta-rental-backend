package com.delta.rental.deltarental.services.abstracts;

import com.delta.rental.deltarental.services.dtos.requests.branch.AddBranchRequest;
import com.delta.rental.deltarental.services.dtos.requests.branch.UpdateBranchRequest;
import com.delta.rental.deltarental.services.dtos.responses.branch.GetBranchListResponse;
import com.delta.rental.deltarental.services.dtos.responses.branch.GetBranchResponse;

import java.util.List;

public interface BranchService {

    GetBranchResponse getById(int id);

    List<GetBranchListResponse> getAll();

    void add(AddBranchRequest addBranchRequest);

    void update(UpdateBranchRequest updateBranchRequest);

    void delete(int id);

}
