package com.delta.rental.deltarental.controllers;


import com.delta.rental.deltarental.services.abstracts.BranchService;
import com.delta.rental.deltarental.services.dtos.requests.branch.AddBranchRequest;
import com.delta.rental.deltarental.services.dtos.requests.branch.UpdateBranchRequest;
import com.delta.rental.deltarental.services.dtos.responses.branch.GetBranchListResponse;
import com.delta.rental.deltarental.services.dtos.responses.branch.GetBranchResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/branches")
@CrossOrigin
public class BranchesController {
    private BranchService branchService;

    @GetMapping("{id}")
    public GetBranchResponse getById(int id){
        return branchService.getById(id);
    }

    @GetMapping("/getAll")
    public List<GetBranchListResponse> getAll(){
        return branchService.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddBranchRequest addBranchRequest) {
        branchService.add(addBranchRequest);
    }

    @PutMapping()
    public void update(@RequestBody @Valid UpdateBranchRequest updateBranchRequest){
        branchService.update(updateBranchRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        branchService.delete(id);
    }
}
