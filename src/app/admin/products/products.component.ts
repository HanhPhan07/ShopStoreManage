import { Component, OnInit, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { Pagination, PaginatedResult } from 'src/app/_models/pagination';
import { SanPham } from 'src/app/_models/sanpham';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductService } from 'src/app/_services/product.service';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { ChiTietDanhMuc } from 'src/app/_models/chitietdanhmuc';
import { NhaSanXuat } from 'src/app/_models/nhasanxuat';
import { CateProductService } from 'src/app/_services/cate-product.service';
import { DanhMucSP } from 'src/app/_models/danhmucsp';
import { ManufProdService } from 'src/app/_services/manuf-prod.service';
import { HttpResponse } from '@angular/common/http';
import { CateDetailProductService } from 'src/app/_services/cate-detail-product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {
  modalRefAddProd: BsModalRef;
  modalRefEditProd: BsModalRef;
  pagination: Pagination;
  listProds: SanPham[];
  searchTerm: string;
  // fitlerdanhmucsp: number;
  // fitlernhasx: number;
  baseDataListProds: SanPham[];
  filterStatus: number;
  listStatusProd = [
    'Đang kinh doanh',
    'Đã ngừng kinh doanh'
  ];
  listCateProd: DanhMucSP[];
  listManufProd: NhaSanXuat[];
  itemsPerPage = 4;
  listSubTrTableProd = [];
  productAdd: SanPham;
  addProdForm = new FormGroup({
    tensp: new FormControl('', Validators.required),
    soluong: new FormControl('', Validators.required),
    giavon: new FormControl('', Validators.required),
    giaban: new FormControl('', Validators.required),
    danhmuc: new FormArray([]),
    nhasx: new FormControl('', Validators.required),
    donvitinh: new FormControl('', Validators.required),
    hot: new FormControl('', Validators.required),
    new: new FormControl('', Validators.required),
    display: new FormControl('', Validators.required),
    anhsp: new FormControl(''),
    motasp: new FormControl('')
  });
  productCurrent: SanPham;
  productEdit: SanPham;
  editProdForm = new FormGroup({
    masp: new FormControl({value: '', disabled: true}),
    tensp: new FormControl('', Validators.required),
    soluong: new FormControl('', Validators.required),
    giavon: new FormControl('', Validators.required),
    giaban: new FormControl('', Validators.required),
    danhmuc: new FormArray([]),
    nhasx: new FormControl('', Validators.required),
    hot: new FormControl('', Validators.required),
    new: new FormControl('', Validators.required),
    display: new FormControl('', Validators.required),
    anhsp: new FormControl(''),
    motasp: new FormControl('')
  });
    masp: string;
    tensp: string;
    soluong: number;
    giavon: number;
    giaban: number;
    danhmuc: ChiTietDanhMuc;
    nhasx: NhaSanXuat;
    hot: boolean;
    new: boolean;
    display: boolean;
    anhsp: string;
    motasp: string;

    selectedFiles: FileList;
    currentFile: File;

  constructor(
    private modalService: BsModalService,
    private productService: ProductService,
    private router: Router,
    private cateProdService: CateProductService,
    private manufProdService: ManufProdService,
    private activatedRoute: ActivatedRoute,
    private cateDetailProd: CateDetailProductService) { }

  openModal(template: TemplateRef<any>) {
    this.modalRefAddProd = this.modalService.show(template);
    //this.modalRefEditProd = this.modalService.show(template);
  }
  openEditModal(template: TemplateRef<any>) {
    this.modalRefEditProd = this.modalService.show(template);
  }
  ngOnInit() {
    // this.fitlerdanhmucsp = 0;
    // this.fitlernhasx = 0;
    this.filterStatus = 0;
    this.listProds = new Array();
    this.pagination = {
      currentPage: 1,
      totalItems: 0,
      totalPages: 0,
      itemsPerPage: this.itemsPerPage
    };
    this.resetFilter();
    this.baseDataListProds = [];
    this.activatedRoute.data.subscribe(data => {
        this.listProds = data.product.result;
        this.pagination = data.product.pagination;
        //this.listCateProd = data.cates;
     });
    this.getListCateProd();
    this.getListManufProd();
    this.productCurrent = JSON.parse(localStorage.getItem('sanpham'));
    this.productEdit = this.productCurrent;
    //this.updateValueProdForm();
  }
  onCheckChange(event) {
    let formArray: FormArray = this.addProdForm.get('danhmuc') as FormArray;
    /* Selected */
    if (event.target.checked) {
      // Add a new control in the arrayForm
      formArray.push(new FormControl(event.target.value));
    } else {
      // find the unselected element
      let i = 0;
      formArray.controls.forEach((ctrl: FormControl) => {
        if (ctrl.value === event.target.value) {
          // Remove the unselected element from the arrayForm
          formArray.removeAt(i);
          return;
        }
        i++;
      });
    }
  }
  createProduct() {
    this.productAdd = new SanPham();
    this.productAdd.tensp = this.addProdForm.controls['tensp'].value;
    this.productAdd.soluong = this.addProdForm.controls['soluong'].value;
    this.productAdd.giagoc = this.addProdForm.controls['giavon'].value;
    this.productAdd.giaban = this.addProdForm.controls['giaban'].value;
    console.log(this.addProdForm.controls['danhmuc'].value);
    let productManu = this.listManufProd.find( x => {
      return x.id == this.addProdForm.controls['nhasx'].value;
    });
    this.productAdd.nhasanxuat = productManu;
    this.productAdd.donvitinh = this.addProdForm.controls['donvitinh'].value;
    this.productAdd.ishot = this.addProdForm.controls['hot'].value;
    this.productAdd.isnew = this.addProdForm.controls['new'].value;
    this.productAdd.displaywebsite = this.addProdForm.controls['display'].value;
    this.productAdd.motasp = this.addProdForm.controls['motasp'].value;
    this.currentFile = this.selectedFiles.item(0);
    this.productService.uploadFile(this.currentFile).subscribe(response => {
      this.productAdd.anhsp = response.fileDownloadUri;
      this.productService.addProduct(this.productAdd).subscribe( next => {
        this.addProdForm.controls['danhmuc'].value.forEach(y => {
          let danhmuc: DanhMucSP = this.listCateProd.find(x=>x.id==y);
          let chitietdanhmuc: ChiTietDanhMuc = {
            id_sanpham: next.id,
            danhmucsp: danhmuc
          };
          this.cateDetailProd.addCateDetailProd(chitietdanhmuc).subscribe(() => {
            alert('Thêm thành công !');
            this.getListProduct();
            this.modalRefAddProd.hide();
          },
          error => console.log(error));
        });
      },
        error => {
          alert('Lỗi');
          console.log(error);
        }, () => {});
    });
    
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }



  toggleChiTietSanPham(id: number) {
    this.listSubTrTableProd[id] = !this.listSubTrTableProd[id];
  }

  getListCateProd() {
    this.cateProdService.getListCateArr().subscribe(
      data => {
        this.listCateProd = data;
      }
    );
  }
  getListManufProd() {
    this.manufProdService.getListManu().subscribe(
      data => {
        this.listManufProd = data;
      }
    );
  }


  editProduct(maSP: number) {
    //this.router.navigate(['/admin/products/' + maSP]);
  }
  // updateValueProdForm() {
  //   this.editProdForm.controls[' masp '].setValue(this.productCurrent.masp);
  //   this.editProdForm.controls[' tensp '].setValue(this.productCurrent.tensp);
  //   this.editProdForm.controls[' soluong '].setValue(this.productCurrent.soluong);
  //   this.editProdForm.controls[' giavon '].setValue(this.productCurrent.giagoc);
  //   this.editProdForm.controls[' giaban '].setValue(this.productCurrent.giaban);
  //   this.editProdForm.controls[' danhmuc '].setValue(this.productCurrent.chitietdanhmuc);
  //   this.editProdForm.controls[' nhasx '].setValue(this.productCurrent.nhasanxuat);
  //   this.editProdForm.controls[' hot '].setValue(this.productCurrent.ishot);
  //   this.editProdForm.controls[' new '].setValue(this.productCurrent.isnew);
  //   this.editProdForm.controls[' display '].setValue(this.productCurrent.displaywebsite);
  //   this.editProdForm.controls[' anhsp '].setValue(this.productCurrent.anhsp);
  //   this.editProdForm.controls[' motasp '].setValue(this.productCurrent.motasp);
  // }
  // updateProduct() {
  //   this.productEdit.masp = this.productCurrent.masp;
  //   this.productEdit.tensp = this.editProdForm.controls[' tensp '].value;
  //   this.productEdit.soluong = this.editProdForm.controls[' soluong '].value;
  //   this.productEdit.giagoc = this.editProdForm.controls[' giavon '].value;
  //   this.productEdit.giaban = this.editProdForm.controls[' giaban '].value;
  //   this.productEdit.chitietdanhmuc = this.editProdForm.controls[' danhmuc '].value;
  //   this.productEdit.nhasanxuat = this.editProdForm.controls[' nhasx '].value;
  //   this.productEdit.ishot = this.editProdForm.controls[' hot '].value;
  //   this.productEdit.isnew = this.editProdForm.controls[' new '].value;
  //   this.productEdit.displaywebsite = this.editProdForm.controls[' display '].value;
  //   this.productEdit.anhsp = this.editProdForm.controls[' anhsp '].value;
  //   this.productEdit.motasp = this.editProdForm.controls[' motasp '].value;
  //   this.productService.updateProduct(this.productEdit).subscribe(next => {
  //   this.productCurrent = this.productEdit;
  //   localStorage.setItem('sanpham', JSON.stringify(this.productCurrent));
  //   alert('Update Successfully');
  //   }, error => {
  //     alert('Error');
  //   }, () => {});
  // }

  deleteProduct(id: number) {
    if (confirm('Bạn thực sự muốn xóa sản phẩm này?')) {
      this.productService.deleteProduct(id).subscribe(() => {
        this.getListProduct();
        alert('Xóa thành công!');
      },
        error => {
          console.log(error);
          alert('Xóa thất bại!');
        }
      );
    }
  }
  getListProduct() {
    this.productService.getProductPage (
      this.pagination.currentPage, this.pagination.itemsPerPage).subscribe(
        (data: PaginatedResult<SanPham[]>) => {
          if (typeof(data.pagination) !== 'undefined') {
            this.pagination = data.pagination;
          } else {
              this.pagination = {
                currentPage: 1,
                totalItems: 0,
                totalPages: 0,
                itemsPerPage: this.itemsPerPage
              };
          }
          this.updateListProduct(data.result);
        },
      error => console.log(error)
    );
  }
  updateListProduct(data) {
    this.listProds = data;
    this.baseDataListProds = [];
    this.listSubTrTableProd = [];
    if (data != null ) {
      this.listProds.forEach(x => {
        this.baseDataListProds.push(x);
        this.listSubTrTableProd.push(false);
      });
    }
  }
  resetFilter() {
    this.searchTerm = '';
  }
  search() {
        if (typeof (this.searchTerm) === 'undefined' || this.searchTerm === null) {
          this.searchTerm = '';
        }
        this.productService.getSearchListProduct (
          this.pagination.currentPage, this.pagination.itemsPerPage, this.searchTerm).subscribe(
            (data: PaginatedResult<SanPham[]>) => {
              if (typeof(data.pagination) !== 'undefined') {
                this.pagination = data.pagination;
              } else {
                  this.pagination = {
                    currentPage: 1,
                    totalItems: 0,
                    totalPages: 0,
                    itemsPerPage: this.itemsPerPage
                  };
              }
              this.updateListProduct(data.result);
        },
          error => console.log(error)
        );
  }
  filter() {
    if (this.filterStatus == 2) {
      this.listProds = this.baseDataListProds.filter(this.isNonSale);
    } else if (this.filterStatus == 0) {
      this.listProds = this.baseDataListProds;
    } else {
      this.listProds = this.baseDataListProds.filter(this.isSale);
    }
  }
  isSale(sanpham: SanPham){
    return sanpham.trangthai == 1;
  }
  isNonSale(sanpham: SanPham){
    return sanpham.trangthai == 2;
  }
  pageChanged(event: any): void {
    this.pagination.currentPage = event.page;
    this.search();
  }

    checkInputProduct() {
      if (this.masp === undefined || this.tensp === undefined || this.soluong === undefined ||
        this.giavon === undefined || this.giaban === undefined ||
        this.danhmuc === undefined || this.nhasx === undefined ||
        this.hot === undefined || this.new === undefined || this.display === undefined) { return false; }
      return true;
    }
}
