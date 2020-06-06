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
public class CustomerController {

    private static Logger logger = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProvinceService provinceService;

    @ModelAttribute("province")
    public Iterable<ProvinceEntity> province() {
        return provinceService.findAll();
    }

    @RequestMapping(value = "/customer/overview", method = RequestMethod.GET)
    public ModelAndView loadListCustomer() {
        logger.trace("Go into loadListCustomer()");
        ModelAndView lisCustomer = null;
        Iterable<CustomerEntity> customers;
        try {
            customers = customerService.findAll();
            logger.info(customers.toString());
            lisCustomer = new ModelAndView("customer/list");
            lisCustomer.addObject("customers", customers);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Exit loadListCustomer()");
        return lisCustomer;
    }

    //load create form
    @RequestMapping(value = "/create_customer", method = RequestMethod.GET)
    public ModelAndView loadCreateForm() {
        logger.trace("Go into loadCreateForm()");
        ModelAndView createForm = null;
        CustomerEntity customer;
        try {
            customer = new CustomerEntity();
            createForm = new ModelAndView("customer/create");
            createForm.addObject("customer", customer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Exit loadCreateForm()");
        return createForm;
    }

    //create new customer
    @RequestMapping(value = "create_customer", method = RequestMethod.POST)
    public ModelAndView createCustomer(@ModelAttribute("customer") CustomerEntity customer) {
        logger.trace("Go into newCustomer()");
        ModelAndView newCustomer = null;
        try {
            customerService.save(customer);
            logger.info(customer.toString());
            newCustomer = backToListCustomers(Alert.CREATE, loadListCustomer());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Exit newCustomer()");
        return newCustomer;
    }

    //load edit form
    @RequestMapping(value = "/edit_customer/{id}", method = RequestMethod.GET)
    public ModelAndView loadEditForm(@PathVariable("id") Integer id) {
        logger.trace("Go into loadEditForm()");
        ModelAndView editForm = null;
        CustomerEntity customer;
        try {
            customer = customerService.findById(id);
            logger.info(customer.toString());
            editForm = new ModelAndView("customer/edit");
            editForm.addObject("customer", customer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Exit loadEditForm()");
        return editForm;
    }

    //edit customer
    @RequestMapping(value = "/edit_customer", method = RequestMethod.POST)
    public ModelAndView editCustomer(@ModelAttribute("customer") CustomerEntity customer) {
        logger.trace("Go into editCustomer()");
        ModelAndView editCustomer = null;
        try {
            customerService.save(customer);
            logger.info(customer.toString());
            editCustomer = backToListCustomers(Alert.EDIT, loadListCustomer());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Exit editCustomer()");
        return editCustomer;
    }

    //delete customer
    @RequestMapping(value = "/delete_customer/{id}", method = RequestMethod.GET)
    public ModelAndView deleteCustomer(@PathVariable("id") Integer id) {
        logger.trace("Go into deleteCustomer()");
        ModelAndView deleteCustomer = null;
        try {
            logger.info(customerService.findById(id).toString());
            customerService.remove(id);
            deleteCustomer = backToListCustomers(Alert.DELETE, loadListCustomer());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Exit deleteCustomer()");
        return deleteCustomer;
    }

    private ModelAndView backToListCustomers(Integer type, ModelAndView modelAndView) {
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