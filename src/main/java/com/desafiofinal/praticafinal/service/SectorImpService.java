package com.desafiofinal.praticafinal.service;

import com.desafiofinal.praticafinal.model.Sector;
import com.desafiofinal.praticafinal.repository.ISectorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectorImpService {

    @Autowired
    private ISectorRepo sectorRepo;


    public Sector createSector (Sector sector){
        Optional<Sector> foundSector = Optional.ofNullable(sectorRepo.findByCategory(sector.getCategory()));

        if(foundSector.isPresent()){
           throw new RuntimeException("O setor já existe");
        } else {
            sector.setSectorId(0L);
        }

        return sectorRepo.save(sector);
    }
}
