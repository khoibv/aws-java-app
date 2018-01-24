package vn.nev.aws.demo.controller;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.nev.aws.demo.form.SearchForm;
import vn.nev.aws.demo.model.Product;
import vn.nev.aws.demo.service.ProductService;

@Controller
@RequestMapping("product")
public class ProductController {

  private static final String SESSION_PRODUCT_SEARCH_FORM = "Product.SearchForm";

  @Autowired
  private ProductService productService;

  private Logger logger = LoggerFactory.getLogger(ProductController.class);

  @GetMapping({"", "/index"})
  public String index(HttpSession session, Model model) {
    SearchForm searchForm = (SearchForm) session.getAttribute(SESSION_PRODUCT_SEARCH_FORM);
    model.addAttribute("searchForm", searchForm == null ? new SearchForm() : searchForm);
    model.addAttribute("products", productService.list());

    return "product/index";
  }

  @PostMapping("search")
  public String search(SearchForm searchForm, HttpSession session, Model model) {

    logger.info("Search data: {}", searchForm);

    session.setAttribute(SESSION_PRODUCT_SEARCH_FORM, searchForm);
    model.addAttribute("searchForm", searchForm);
    model.addAttribute("products", productService.search(searchForm));

    return "product/index";
  }

  @GetMapping("/create")
  public String create(Model model) {
    model.addAttribute("product", new Product());

    return "product/createOrUpdate";
  }


  @PostMapping("/create")
  public String create(Product product, RedirectAttributes redirectAttributes) {
    // TODO: Validate

    productService.create(product);
    redirectAttributes.addFlashAttribute("message", "Product created");

    return "redirect:index";
  }

  @GetMapping("/update")
  public String update(@RequestParam(name = "id") int productId, Model model) {
    model.addAttribute("product", productService.findById(productId));

    return "product/createOrUpdate";
  }

  @PostMapping("/update")
  public String update(Product product, RedirectAttributes redirectAttributes) {
    // TODO: validate

    Product updatedProduct = productService.findById(product.getId());
    logger.info("Current product: {}, id: {}", updatedProduct, product.getId());

    if (updatedProduct != null) {
      updatedProduct.setProductName(product.getProductName());
      updatedProduct.setCategory(product.getCategory());
      updatedProduct.setPrice(product.getPrice());

      productService.update(updatedProduct);
      redirectAttributes.addFlashAttribute("message", "Product updated");
    }

    return "redirect:index";
  }

  @PostMapping(path = "/delete")
  @ResponseBody
  public String delete(@RequestParam(name = "id") int productId) {
    productService.delete(productId);

    return "OK";
  }
}
