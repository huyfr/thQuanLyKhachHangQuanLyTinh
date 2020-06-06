package controller;

import common.Alert;
import model.CustomerEntity;
import model.ProvinceEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.CustomerService;
import service.ProvinceService;

@Controller
public class ProvinceController {

    private static Logger logger = LogManager.getLogger(ProvinceController.class);

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CustomerService customerService;

    //load list
    @RequestMapping(value = "/province/overview", method = RequestMethod.GET)
    public ModelAndView loadListProvince() {
        logger.trace("Vao loadListProvince()");
        ModelAndView listProvince = null;
        Iterable<ProvinceEntity> provinces;
        try {
            provinces = provinceService.findAll();
            logger.info(provinces.toString());
            listProvince = new ModelAndView("province/list");
            listProvince.addObject("provinces", provinces);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Thoat loadListProvince()");
        return listProvince;
    }

    //load creat form
    @RequestMapping(value = "/create_province", method = RequestMethod.GET)
    public ModelAndView loadCreateForm() {
        logger.trace("Vao loadCreateForm()");
        ModelAndView createForm = null;
        ProvinceEntity province;
        try {
            createForm = new ModelAndView("province/create");
            province = new ProvinceEntity();
            createForm.addObject("province", province);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Thoat loadCreateForm()");
        return createForm;
    }

    //create new province
    @RequestMapping(value = "/create_province", method = RequestMethod.POST)
    public ModelAndView createProvince(@ModelAttribute("province") ProvinceEntity province) {
        logger.trace("Vao createProvince()");
        ModelAndView newProvince = null;
        try {
            provinceService.save(province);
            logger.info(province.toString());
            newProvince = backToListProvince(Alert.CREATE, loadListProvince());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Thoat createProvince()");
        return newProvince;
    }

    //load edit form
    @RequestMapping(value = "/edit_province/{id}", method = RequestMethod.GET)
    public ModelAndView loadEditForm(@PathVariable("id") Integer id) {
        logger.trace("Vao loadEditForm()");
        ModelAndView editForm = null;
        ProvinceEntity province;
        try {
            province = provinceService.findById(id);
            logger.info(province.toString());
            editForm = new ModelAndView("province/edit");
            editForm.addObject("province", province);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Thoat loadEditForm()");
        return editForm;
    }

    //edit province
    @RequestMapping(value = "/edit_province", method = RequestMethod.POST)
    public ModelAndView editProvince(@ModelAttribute("province") ProvinceEntity province) {
        logger.trace("Vao editProvince()");
        ModelAndView editProvince = null;
        try {
            provinceService.save(province);
            logger.info(province.toString());
            editProvince = backToListProvince(Alert.EDIT, loadListProvince());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Thoat editProvince()");
        return editProvince;
    }

    //delete province
    @RequestMapping(value = "/delete_province/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProvince(@PathVariable("id") Integer id) {
        logger.trace("Vao deleteProvince()");
        ModelAndView deleteProvince = null;
        try {
            logger.info("Province co id la: " + id);
            provinceService.remove(id);
            deleteProvince = backToListProvince(Alert.DELETE, loadListProvince());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Thoat deleteProvince()");
        return deleteProvince;
    }

    //view details
    @RequestMapping(value = "/view_province/{id}", method = RequestMethod.GET)
    public ModelAndView loadViewDetail(@PathVariable("id") Integer id) {
        logger.trace("Go into loadViewDetail()");
        ProvinceEntity province;
        Iterable<CustomerEntity> customers;
        ModelAndView viewDetail = null;
        ModelAndView error = null;
        try {
            province = provinceService.findById(id);
            logger.info(provinceService.findById(id).toString());
            if (province == null) {
                error = new ModelAndView("error");
                return error;
            }
            customers = customerService.findAllByProvinceByProvinceId(province);

            viewDetail = new ModelAndView("province/viewDetail");
            viewDetail.addObject("province", province);
            viewDetail.addObject("customers", customers);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Exit loadViewDetail()");
        return viewDetail;
    }

    private ModelAndView backToListProvince(Integer type, ModelAndView modelAndView) {
        switch (type) {
            case Alert.CREATE:
                modelAndView.addObject("alert", "Them thanh cong");
                break;
            case Alert.EDIT:
                modelAndView.addObject("alert", "Sua thanh cong");
                break;
            case Alert.DELETE:
                modelAndView.addObject("alert", "Xoa thanh cong");
                break;
        }
        return modelAndView;
    }
}
