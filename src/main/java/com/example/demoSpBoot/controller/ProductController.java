package com.example.demoSpBoot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demoSpBoot.model.hoadonbanhang;
import com.example.demoSpBoot.model.sanpham;
import com.example.demoSpBoot.service.ProductService;

import java.util.logging.Logger;

@RestController
@RequestMapping("/ShopStore")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/allproducts")
	/* ---------------- GET ALL PRODUCT ------------------------ */
	public ResponseEntity<List<sanpham>> findAllProduct() {
		//return new ResponseEntity<ServiceResult>(customerService.findAll(), HttpStatus.OK);
		
		List<sanpham> listProduct= productService.findAllProd();
		if(listProduct.isEmpty()) {
			return new ResponseEntity<List<sanpham>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<sanpham>>(listProduct, HttpStatus.OK);
	}
	
	@GetMapping("/product")
	/* ---------------- GET ALL PRODUCT ------------------------ */
	public ResponseEntity<Page<sanpham>> findAllProduct(@RequestParam int pageNumber, @RequestParam int pageSize) {
		//return new ResponseEntity<ServiceResult>(customerService.findAll(), HttpStatus.OK);
		
		Page<sanpham> listProduct= productService.findAll(pageNumber,pageSize);
		if(listProduct.isEmpty()) {
			return new ResponseEntity<Page<sanpham>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<sanpham>>(listProduct, HttpStatus.OK);
	}
	/* ---------------- GET PRODUCT BY ID ------------------------ */
	@GetMapping("/product/{id}")
	
	public ResponseEntity<sanpham> getProductById(
            @PathVariable("id") int id) {
        Optional<sanpham> product = productService.findByID(id);

        if (!product.isPresent()) {
            return new ResponseEntity<>(product.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

	/* ---------------- CREATE NEW PRODUCT ------------------------ */
	@PostMapping("/product")
	public ResponseEntity<sanpham> saveProduct(@Valid @RequestBody sanpham product) {
		if(productService.create(product)) return new ResponseEntity<sanpham>(product,HttpStatus.OK);
		else {
			return new ResponseEntity<sanpham>(product,HttpStatus.NOT_FOUND);
		}
		
	}
	
	/* ---------------- UPDATE PRODUCT ------------------------ */
	@PutMapping("/product")

	public ResponseEntity<sanpham> updateProduct(@RequestBody sanpham product) {
		if(productService.update(product)) return new ResponseEntity<sanpham>(product,HttpStatus.OK);
		else {
			return new ResponseEntity<sanpham>(product,HttpStatus.NOT_FOUND);
		}
	}
	/* ---------------- DELETE PRODUCT ------------------------ */
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<sanpham> deleteProduct(@PathVariable("id") int id) {
		if(productService.delete(id)) return new ResponseEntity<sanpham>(HttpStatus.OK);
		else {
			return new ResponseEntity<sanpham>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/product/search")
	/* ---------------- SEARCH ------------------------ */
	public ResponseEntity<Page<sanpham>> findProduct(@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam String searchTerm) throws ParseException {
		Page<sanpham> listBill = null;
			listBill= productService.searchProduct(pageNumber,pageSize,searchTerm);
		
			if(listBill.isEmpty()) {
				return new ResponseEntity<Page<sanpham>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<Page<sanpham>>(listBill, HttpStatus.OK);
	}
	
	//upload anhsp
	private static final Logger logger = Logger.getLogger(ProductController.class.getName());
	@SuppressWarnings("null")
	@PostMapping("/upload")
	public ResponseEntity<String> uploadData(@RequestParam("file") MultipartFile file) throws Exception {
		if (file == null) {
			throw new RuntimeException("You must select the a file for uploading");
		}
		MultipartFile multipartFile = file;
		InputStream inputStream = file.getInputStream();
		String fileName = multipartFile.getOriginalFilename();
		String originalName = file.getOriginalFilename();
		File folder = new File(this.getFolderUpload(),fileName);
		multipartFile.transferTo(folder);
		Resource filePath=multipartFile.getResource();
		String name = file.getName();
		String contentType = file.getContentType();
		long size = file.getSize();
		URL url = Resources.getResource("file name")
		logger.info("inputStream: " + inputStream);
		logger.info("originalName: " + originalName);
		logger.info("name: " + name);
		logger.info("contentType: " + contentType);
		logger.info("size: " + size);
		// Do processing with uploaded file data in Service layer
		return new ResponseEntity<String>(filePath.getURI().toString(), HttpStatus.OK);
	}
	public File getFolderUpload() {
	    File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
	    if (!folderUpload.exists()) {
	      folderUpload.mkdirs();
	    }
	    System.out.print(folderUpload.getPath());
	    return folderUpload;
	  }
	@GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws MalformedURLException {
        // Load file as Resource
        Resource resource = new UrlResource(getFolderUpload().getPath().toString()+"\\"+fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
