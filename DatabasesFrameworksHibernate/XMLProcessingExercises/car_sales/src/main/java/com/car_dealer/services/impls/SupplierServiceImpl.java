package com.car_dealer.services.impls;

import com.car_dealer.dtos.binding.add.SupplierAddDto;
import com.car_dealer.dtos.binding.relations.SupplierDto;
import com.car_dealer.dtos.view.LocalSupplierView;
import com.car_dealer.dtos.view.xmlWrappers.DomesticSuppliersXmlWrapper;
import com.car_dealer.entities.Supplier;
import com.car_dealer.repositories.SupplierRepository;
import com.car_dealer.services.apis.SupplierService;
import com.car_dealer.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository carRepository) {
        this.supplierRepository = carRepository;
    }

    @Override
    public void save(SupplierAddDto supplierDao) {
        Supplier supplier = ModelParser.getInstance().map(supplierDao, Supplier.class);
        this.supplierRepository.saveAndFlush(supplier);
    }

    @Override
    public List<SupplierDto> findAllDtos() {
        List<Supplier> suppliers = this.supplierRepository.findAll();
        List<SupplierDto> supplierDtos = new ArrayList<>();

        for (Supplier supplier : suppliers) {
            supplierDtos.add(ModelParser.getInstance().map(supplier, SupplierDto.class));
        }
        return supplierDtos;
    }

    @Override
    public List<LocalSupplierView> findAllLocalSuppliers() {
        return this.supplierRepository.findAllLocalSuppliers();
    }

    @Override
    public DomesticSuppliersXmlWrapper findAllLocalSuppliersXml() {
        List<LocalSupplierView> allLocalSuppliers = this.findAllLocalSuppliers();
        DomesticSuppliersXmlWrapper domesticSuppliersXmlWrapper = new DomesticSuppliersXmlWrapper();
        domesticSuppliersXmlWrapper.setLocalSupplierViews(allLocalSuppliers);
        return domesticSuppliersXmlWrapper;
    }
}
