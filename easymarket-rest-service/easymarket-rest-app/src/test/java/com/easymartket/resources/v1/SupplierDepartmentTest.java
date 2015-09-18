package com.easymartket.resources.v1;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.when;

import java.awt.image.BufferedImage;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easymarket.core.Product;
import com.easymarket.core.ProductCategory;
import com.easymarket.core.Supplier;
import com.easymarket.core.SupplierDepartment;
import com.easymarket.core.param.ProductRequest;
import com.easymarket.core.param.SupplierRequest;
import com.easymarket.dao.ProductDAO;
import com.easymarket.dao.SupplierDAO;
import com.easymarket.resources.v1.ProductResourceV1;
import com.easymarket.service.ProductService;
import com.easymarket.service.SupplierService;
import com.easymarket.service.impl.ProductServiceImpl;
import com.easymarket.service.impl.SupplierServiceImpl;
import com.easymartket.mockdata.MockData;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.oned.Code128Writer;

import io.dropwizard.jersey.sessions.Session;
/**
 *
 * Unit tests for {@link ProductResourceV1}.
 *
 * @author Adriano
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SupplierDepartmentTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierDepartmentTest.class);

    private final SessionFactory factory = mock(SessionFactory.class);
    private final Criteria criteria = mock(Criteria.class);
    private final Query query = mock(Query.class);
    private final Session session = mock(Session.class);
    private final ProductDAO productDAO = mock(ProductDAO.class);
    private final SupplierDAO supplierDAO = mock(SupplierDAO.class);

    private final ProductService productService=new ProductServiceImpl(productDAO);
    private final SupplierService supplierService=new SupplierServiceImpl(supplierDAO);

    private MockData MOCKDATA;

    @Before
    public void setUp() {
        MOCKDATA = new MockData();
    }

    @After
    public void tearDown() {
        reset(productDAO);
        reset(supplierDAO);
    }

    /**
     * Barcode - code128
     *
     * @throws Exception
     */
    @Test
    public void testProductBarCodeEncodeCode128() throws Exception {

        // https://en.wikipedia.org/wiki/Code_128
        String barCodeExpected = "98376373783111111111"; // this is the text that we want to encode

        int width = 400;
        int height = 300; // change the height and width as per your requirement

        BitMatrix bitMatrix = new Code128Writer().encode(barCodeExpected, BarcodeFormat.CODE_128, width, height);
        BufferedImage barCodeBufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Reader reader = new MultiFormatReader();
        Result result = reader.decode(bitmap);

        String barCodeGenerated = result.getText();
        LOGGER.info("Barcode text is " + barCodeGenerated);

        assertEquals("Barcode generated is different than expected", barCodeExpected, barCodeGenerated);
    }

    @Test
    public void testFindSupplierNearBy() throws Exception {


        //For POA
        List<Supplier> listSupplier = MOCKDATA.mockSuppliersPortoAlegre();
        List<SupplierDepartment> listDepartament = MOCKDATA.mockDepartament();
        List<ProductCategory> listCategory = MOCKDATA.mockProductCategory();
        //Product from POA
        List<Product> listProduct = MOCKDATA.mockProductsFromPortoAlegre(listSupplier, listDepartament, listCategory);

        Integer maxLimitInMeter=2000;
        String gpsCoordinateGonellaHome=MOCKDATA.GPS_COORDINATE_GONELLA_HOME;

        final String cityExpected="Porto Alegre";
        final String stateExpected="RS";
        final String countryExpected="Brazil";

        SupplierRequest supplierRequest=new SupplierRequest();
        supplierRequest.setGpsCoordinates(gpsCoordinateGonellaHome);
        supplierRequest.setMaxLimitInMeter(maxLimitInMeter);
        supplierRequest.setCity(cityExpected);
        supplierRequest.setState(stateExpected);
        supplierRequest.setCountry(countryExpected);

        when(supplierDAO.findBasedOnCityAndStateAndCountry(cityExpected, stateExpected, countryExpected)).thenReturn(listSupplier);
        List<Supplier> supplierFound = supplierService.findSupplierByGpsCoordinate(supplierRequest);

        for (Supplier supplier : supplierFound) {
            LOGGER.info(supplier.getName()+" - "+supplier.getDescription());
        }
        assertEquals("Number of supplier retrieved is different",5,supplierFound.size());
    }


    @Test
    public void testFindProductsByDepartamentFromSupplierClose() throws Exception {

        List<Supplier> listSupplier=MOCKDATA.mockSuppliersPortoAlegreClose2000MetersFromGonellaHome();
        List<SupplierDepartment> mockDepartament = MOCKDATA.mockDepartament();

        SupplierDepartment departmentSelected = mockDepartament.get(0);

        ProductRequest productParam=new ProductRequest();
        productParam.setDepartmentId(departmentSelected.getId());
        //when(productDAO.findProductsFromSuppliers(listSupplier)).thenReturn(listSupplier);
        productService.findProductsFromSuppliers(productParam,listSupplier);


    }

}
