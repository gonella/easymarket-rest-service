package com.easymarket.service;

import java.util.List;

import com.easymarket.core.Supplier;
import com.easymarket.core.param.NearByRequest;

public interface SupplierService {

    public Supplier createSupplier(Supplier Supplier);

    public void deleteSupplier(Long id);

    public Supplier getSupplier(Long id);

    public List<Supplier> listSupplier();

    public List<Supplier> findSupplierByGpsCoordinate(NearByRequest request)
            throws Exception;

    public List<Supplier> findSupplierCloseToMaxLimit(
            String gpsCoordinateInformed,
            Integer maxLimitInMeter,
            List<Supplier> listResultSameCity)
                    throws Exception;
}
