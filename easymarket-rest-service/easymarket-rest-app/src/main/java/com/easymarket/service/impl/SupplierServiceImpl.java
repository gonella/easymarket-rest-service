package com.easymarket.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easymarket.core.Supplier;
import com.easymarket.core.param.NearByRequest;
import com.easymarket.dao.SupplierDAO;
import com.easymarket.service.SupplierService;
import com.easymarket.util.CoordinateUtil;
import com.google.common.base.Optional;

//Service not working(fixing inject by dropwizard guicey(https://github.com/xvik/dropwizard-guicey)
//@Service
public class SupplierServiceImpl implements SupplierService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierServiceImpl.class);

    private SupplierDAO dao = null;

    public SupplierServiceImpl(SupplierDAO dao) {
        this.dao = dao;
    }

    @Override
    public Supplier createSupplier(Supplier obj) {
        return dao.create(obj);
    }

    @Override
    public void deleteSupplier(Long id) {
        Optional<Supplier> result = dao.findById(id);
        dao.delete(result.get());

    }

    @Override
    public Supplier getSupplier(Long id) {
        return findSafely(id);
    }

    private Supplier findSafely(long id) {
        final Optional<Supplier> result = dao.findById(id);
        if (!result.isPresent()) {
            throw new NotFoundException("No such Supplier.");
        }
        return result.get();
    }

    @Override
    public List<Supplier> listSupplier() {
        return dao.findAll();
    }

    @Override
    public List<Supplier> findSupplierByGpsCoordinate(NearByRequest supplierRequest)
            throws Exception {
        LOGGER.info("Supplier request informed :{}",supplierRequest);

        String gpsCoordinates = supplierRequest.getGpsCoordinates();
        Integer maxLimitInMeter = supplierRequest.getMaxLimitInMeter();

        //FIXME
        //Os valores abaixo vão ser encontrados baseado no GPS. Talvez usar o serviço do google.
        String city = supplierRequest.getCity();
        String state = supplierRequest.getState();
        String country = supplierRequest.getCountry();

        // TODO - Poderiamos usar alguma informação do usuário, o ideal é usar o cadastro. Evitaria de fazer a conversão de GPS para address.

        // FIXME - Based on GPS we need to find city/state/country.
        // AddressComponent address =
        // CoordinateUtil.getAddressBasedOnGpsCoordinatesFromGoogleMapApi(gpsCoordinate);

        List<Supplier> listResultSameCity = dao.findBasedOnCityAndStateAndCountry(city, state, country);
        for (Supplier supplier : listResultSameCity) {
            LOGGER.info("Supplier found based on address :"+supplier.toString());
        }
        List<Supplier> listResult = findSupplierCloseToMaxLimit(gpsCoordinates, maxLimitInMeter, listResultSameCity);

        return listResult;
    }

    @Override
    public List<Supplier> findSupplierCloseToMaxLimit(
            String gpsCoordinateInformed,
            Integer maxLimitInMeter,
            List<Supplier> listResultSameCity)
                    throws Exception {
        List<Supplier> listResult = new ArrayList<Supplier>();

        if (listResultSameCity != null) {
            for (Supplier supplier : listResultSameCity) {

                String gpsCoordinateFound = supplier.getGpsCoordinate();

                float distance = CoordinateUtil.getDistance(gpsCoordinateInformed, gpsCoordinateFound);

                // Supplier in radius
                if (maxLimitInMeter > distance) {
                    listResult.add(supplier);
                    LOGGER.info("Supplier - " + supplier.getName() + " - distance :" + distance);
                }
            }
        }
        return listResult;
    }

}
