package com.delta.rental.deltarental.services.rules;


import com.delta.rental.deltarental.entities.Branch;
import com.delta.rental.deltarental.entities.Brand;
import com.delta.rental.deltarental.repositories.BranchRepository;
import com.delta.rental.deltarental.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BranchBusinessRules {
    private final BranchRepository branchRepository;

    public Branch checkByBranchId(int id){
        if(!(branchRepository.existsById(id))){
            throw new RuntimeException(id+" nolu id'ye sahip Şube bulunmamaktadır.");
        }
        return branchRepository.findById(id).orElseThrow();
    }
}
