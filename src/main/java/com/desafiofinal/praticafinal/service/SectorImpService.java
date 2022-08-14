package com.desafiofinal.praticafinal.service;

import com.desafiofinal.praticafinal.model.InBoundOrder;
import com.desafiofinal.praticafinal.model.Sector;
import com.desafiofinal.praticafinal.model.WareHouse;
import com.desafiofinal.praticafinal.repository.ISectorRepo;
import com.desafiofinal.praticafinal.repository.IWareHouseRepo;
import com.desafiofinal.praticafinal.repository.InBoundOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SectorImpService {

    @Autowired
    private ISectorRepo sectorRepo;

    @Autowired
    private InBoundOrderRepo inBoundOrderRepo;

    @Autowired
    private IWareHouseRepo wareHouseRepo;

    public Sector saveSector (Sector sector){
        Optional<Sector> foundSector = sectorRepo.findById(sector.getSectorId());

        if(foundSector.isPresent()){
            sector.setSectorId(foundSector.get().getSectorId()); ;
        } else {
            sector.setSectorId(0L);
        }

        List<InBoundOrder> inBoundOrderList = new ArrayList<>();

        for (InBoundOrder inBoundOrder: sector.getOrderList()){
            Optional<InBoundOrder> foundOrder = inBoundOrderRepo.findById(inBoundOrder.getOrderId());

            if(foundSector.isPresent()){
                inBoundOrder.setOrderId(foundOrder.get().getOrderId());
            }else{
                inBoundOrder.setOrderId(0L);
            }

            InBoundOrder savedInBoundOrder = inBoundOrderRepo.save(foundOrder.get());
            inBoundOrderList.add(savedInBoundOrder);
        }

       Optional<WareHouse> foundWareHouse = wareHouseRepo.findById(sector.getWareHouse().getWareHouseId());

       if (foundWareHouse.isPresent()){
           sector.getWareHouse().setWareHouseId(foundWareHouse.get().getWareHouseId());
       }else{
           sector.getWareHouse().setWareHouseId(0L);
       }

        wareHouseRepo.save(foundWareHouse.get());

        inBoundOrderRepo.saveAll(inBoundOrderList);

        return sectorRepo.save(foundSector.get());
    }


}
