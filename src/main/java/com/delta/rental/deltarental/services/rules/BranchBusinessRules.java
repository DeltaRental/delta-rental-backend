package com.delta.rental.deltarental.services.rules;


import com.delta.rental.deltarental.entities.concretes.Branch;
import com.delta.rental.deltarental.repositories.BranchRepository;
import com.delta.rental.deltarental.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BranchBusinessRules {
    private final BranchRepository branchRepository;

    //DB içerisinde aynı Branch id' ye sahip şube olup olmama durumu kontrolü
    public Branch checkByBranchId(int id){
        if(!(branchRepository.existsById(id))){
            throw new RuntimeException(id + Messages.BranchMessages.BRANCH_NOT_FOUND);
        }
        return branchRepository.findById(id).orElseThrow();
    }

    //DB içerisinde aynı şube adına sahip şubelerin var olup olmama kontrolü
    public void checkByBranchName(String name){
        if(branchRepository.existsByName(name.trim().toUpperCase().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT))){
            throw new RuntimeException(Messages.BranchMessages.SAME_BRANCH_NAME_EXISTS);
        }
    }

    //Kullanıcının güncellemek istediği şubenin adını , DB 'de aynı şube ismine sahip başka bir şube var mı durumunun kontrolünü sağlayan kod
    public void checkByBranchNameWhenUpdate(int id,String name){
        Optional<Branch> existingBranchOptional = branchRepository.findById(id);
        Branch existingBranch = existingBranchOptional.get();
        String newName = name.trim().toUpperCase().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT);

        //Eğer DB de girilen şubeye sahip başka bir şube ismi var ise bu hata oluşur.Ancak yok ise güncellenir(kendi şube ismi dahil).
        if (!existingBranch.getName().equals(newName) && branchRepository.existsByName(newName)) {
            throw new RuntimeException(Messages.BranchMessages.SAME_BRANCH_NAME_EXISTS);
        }
    }
}
