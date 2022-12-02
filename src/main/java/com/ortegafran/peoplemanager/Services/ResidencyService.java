package com.ortegafran.peoplemanager.Services;

import com.ortegafran.peoplemanager.Model.Entities.Residency;
import com.ortegafran.peoplemanager.Model.Repositories.ResidencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResidencyService {

    @Autowired
    private final ResidencyRepository residencyRepository;

    public ResidencyService(ResidencyRepository residencyRepository) {
        this.residencyRepository = residencyRepository;
    }

    public Optional<Residency> addOne(Residency residency){
        return Optional.of(residencyRepository.save(residency));
    }

    public Optional<Residency> updateOne(Residency residency){
        Optional<Residency> r = residencyRepository.findById(residency.getId());

        if(r.isEmpty()) return Optional.empty();

        return Optional.of(residencyRepository.save(residency));

    }
}
